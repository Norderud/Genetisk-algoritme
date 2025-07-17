package no.asmund.genetisk;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

import no.asmund.genetisk.algorithm.FitnessProportionateSelection;
import no.asmund.genetisk.algorithm.Population;
import no.asmund.genetisk.algorithm.SelectionStrategy;
import no.asmund.genetisk.config.Configuration;
import no.asmund.genetisk.factory.CreatureFactory;
import no.asmund.genetisk.factory.DefaultCreatureFactory;
import no.asmund.genetisk.factory.DefaultObstacleFactory;
import no.asmund.genetisk.factory.ObstacleFactory;
import no.asmund.genetisk.model.Creature;
import no.asmund.genetisk.model.DefaultEnvironment;
import no.asmund.genetisk.model.Environment;
import no.asmund.genetisk.model.GeneticEntity;
import no.asmund.genetisk.model.Obstacle;
import no.asmund.genetisk.observer.SimulationObserver;

/**
 * Main application class for the genetic algorithm simulation.
 * This class sets up the JavaFX application and manages the simulation.
 */
public class StartApp extends Application implements SimulationObserver {
    private Configuration config;
    private Environment environment;
    private int lifecycle;
    private GraphicsContext gc;
    private Timeline loop;
    private Population population;
    private CreatureFactory creatureFactory;
    private ObstacleFactory obstacleFactory;
    private int generationCount = 0;

    @Override
    public void start(Stage primaryStage) {
        // Initialize configuration
        config = new Configuration();

        // Create environment
        environment = new DefaultEnvironment(
            config.getWidth(),
            config.getHeight(),
            config.getTargetX(),
            config.getTargetY()
        );

        // Add obstacles
        ((DefaultEnvironment) environment).addObstacle(
            new Obstacle(config.getWidth()/2 - 50, config.getHeight()/2 - 20, 100, 40)
        );

        // Initialize factories
        obstacleFactory = new DefaultObstacleFactory();
        creatureFactory = new DefaultCreatureFactory(
            environment,
            config.getStartX(),
            config.getStartY(),
            config.getLifetime()
        );

        // Initialize selection strategy
        SelectionStrategy selectionStrategy = new FitnessProportionateSelection();

        // Initialize population
        population = new Population(
            config.getMutationRate(),
            config.getPopulationSize(),
            creatureFactory,
            selectionStrategy
        );

        // Register as observer
        population.addObserver(this);

        // Initialize lifecycle counter
        lifecycle = 0;

        // Set up JavaFX UI
        StackPane root = new StackPane();
        Scene scene = new Scene(root, config.getWidth(), config.getHeight());
        Canvas canvas = new Canvas(config.getWidth(), config.getHeight());

        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        // Set up animation loop
        loop = new Timeline(new KeyFrame(Duration.millis(5), e -> draw()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();

        primaryStage.setTitle("Genetisk Algoritme");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main draw method called by the animation loop.
     * This method updates the simulation and redraws the UI.
     */
    public void draw() {
        if (lifecycle < config.getLifetime()) {
            // Run one step of the simulation
            population.run();
            repaint();
            lifecycle++;
        } else {
            // End of generation, evolve the population
            gc.clearRect(0, 0, config.getWidth(), config.getHeight());
            lifecycle = 0;
            population.calculateFitness();
            population.selection();
            population.reproduction();
            generationCount++;
        }
    }

    /**
     * Repaints the UI with the current state of the simulation.
     */
    private void repaint() {
        // Draw target
        gc.setFill(Color.RED);
        gc.fillRect(environment.getTargetX() - 5, environment.getTargetY() - 5, 10, 10);

        // Draw obstacles
        for (Obstacle obstacle : environment.getObstacles()) {
            obstacle.draw(gc);
        }

        // Draw creatures
        GeneticEntity[] entities = population.getPopulation();
        for (GeneticEntity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                gc.setFill(creature.getColor());
                gc.fillOval(
                    creature.getX() - creature.getDiameter() / 2,
                    creature.getY() - creature.getDiameter() / 2,
                    creature.getDiameter(),
                    creature.getDiameter()
                );
            }
        }
    }

    /**
     * Called when a new generation is created.
     */
    @Override
    public void onNewGeneration(int generationNumber, float maxFitness, float averageFitness) {
        System.out.println("Generation " + generationNumber + 
                          " - Max Fitness: " + maxFitness + 
                          " - Avg Fitness: " + averageFitness);
    }

    /**
     * Called when the simulation is updated.
     */
    @Override
    public void onSimulationUpdate(int step, int totalSteps) {
        // Not used in this implementation
    }

    /**
     * Called when a creature reaches the target.
     */
    @Override
    public void onTargetReached(int creatureId, int steps) {
        System.out.println("Creature " + creatureId + " reached target in " + steps + " steps!");
    }

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
