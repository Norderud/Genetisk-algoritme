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
import no.asmund.genetisk.Obstacle;

public class StartApp extends Application {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 1200;
    public static int lifetime;
    private int lifecycle;
    public static int popNr;
    private GraphicsContext gc;
    private Timeline loop;
    private Population population;
    public static int targetX, targetY;
    public static List<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        lifetime = 300;
        lifecycle = 0;
        popNr = 100;

        targetX = 1000;
        targetY = HEIGHT/2;

        obstacles.add(new Obstacle(WIDTH/2 - 50, HEIGHT/2 - 20, 100, 40));

        StackPane root = new StackPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        population = new Population(1, popNr);

        loop = new Timeline(new KeyFrame(Duration.millis(5), e -> draw()));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
        primaryStage.setTitle("Genetisk Algoritme");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void draw() {
        if (lifecycle < lifetime) {
            repaint();
            lifecycle++;
        } else {
            gc.clearRect(0, 0, WIDTH, HEIGHT);
            lifecycle = 0;
            population.fitness();
            population.selection();
            population.reproduction();

        }
    }
    private void repaint() {
//        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.RED);
        gc.fillRect(targetX - 5, targetY - 5, 10, 10);

        for (Obstacle o : obstacles) {
            o.draw(gc);
        }

        gc.setFill(Color.BLACK);
        for (int i = 0; i < popNr; i++) {
            Creature r = population.population[i];
            r.run();
            gc.setFill(r.color);
            gc.fillOval(r.x - r.d / 2, r.y - r.d / 2, r.d, r.d);
        }
    }

}
