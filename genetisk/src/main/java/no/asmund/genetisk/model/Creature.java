/**
 * Represents a creature in the genetic algorithm simulation.
 * This class implements the GeneticEntity interface and represents an entity that can evolve.
 */
package no.asmund.genetisk.model;

import javafx.scene.paint.Color;
import no.asmund.genetisk.model.DNA;
import no.asmund.genetisk.model.Environment;

import static java.lang.Math.sqrt;

public class Creature implements GeneticEntity {
    /**
     * The current x position of the creature.
     */
    private float x;

    /**
     * The current y position of the creature.
     */
    private float y;

    /**
     * The starting x position of the creature.
     */
    private final float startX;

    /**
     * The starting y position of the creature.
     */
    private final float startY;

    /**
     * The current angle of the creature's movement.
     */
    private float angle;

    /**
     * The DNA of the creature, which determines its behavior.
     */
    private final DNA dna;

    /**
     * The current gene being expressed.
     */
    private int geneCounter;

    /**
     * The color of the creature for visualization.
     */
    private final Color color;

    /**
     * The closest distance to the target achieved so far.
     */
    private float recordDist;

    /**
     * The fitness of the creature, which determines its likelihood of reproduction.
     */
    private float fitness;

    /**
     * The diameter of the creature for visualization and collision detection.
     */
    private final int diameter;

    /**
     * Whether the creature has reached the target.
     */
    private boolean completed;

    /**
     * The environment in which the creature exists.
     */
    private final Environment environment;

    /**
     * The unique identifier of the creature.
     */
    private final int id;

    /**
     * Counter for generating unique IDs.
     */
    private static int nextId = 0;

    /**
     * The speed of the creature's movement.
     */
    private static final float SPEED = 2.0f;

    /**
     * The distance at which the creature is considered to have reached the target.
     */
    private static final float TARGET_REACH_DISTANCE = 50.0f;

    /**
     * The fitness bonus for reaching the target.
     */
    private static final float TARGET_REACH_BONUS = 10.0f;

    /**
     * Creates a new creature with the specified DNA and environment.
     * 
     * @param dna The DNA of the creature
     * @param environment The environment in which the creature exists
     * @param startX The starting x position
     * @param startY The starting y position
     */
    public Creature(DNA dna, Environment environment, float startX, float startY) {
        this.dna = dna;
        this.environment = environment;
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
        this.geneCounter = 0;
        this.diameter = 5;
        this.id = nextId++;

        // Generate a color based on DNA
        float r = ((dna.getGene(10) * dna.getGene(10)) / 100);
        float g = ((dna.getGene(20) * dna.getGene(20)) / 100);
        float b = ((dna.getGene(30) * dna.getGene(30)) / 100);
        this.color = new Color(r, g, b, 1);

        // Set initial angle based on DNA
        this.angle = (float) (dna.getGene(10) * dna.getGene(10) * 3.6);
    }

    @Override
    public void run() {
        if (!completed) {
            update();
            geneCounter++;
        }
    }

    @Override
    public void calculateFitness() {
        float targetX = environment.getTargetX();
        float targetY = environment.getTargetY();
        recordDist = (float) sqrt((x - targetX) * (x - targetX) + (y - targetY) * (y - targetY));
        fitness = 1 / recordDist;

        if (recordDist < TARGET_REACH_DISTANCE) {
            completed = true;
            fitness *= TARGET_REACH_BONUS;
        }

        geneCounter = 0;
    }

    @Override
    public float getFitness() {
        return fitness;
    }

    @Override
    public void update() {
        float dx = (float) ((Math.cos(Math.toRadians(angle)) * SPEED));
        float dy = (float) ((Math.sin(Math.toRadians(angle)) * SPEED));

        // Bounce when hitting canvas edges
        if (x + dx < 0 || x + dx > environment.getWidth()) {
            angle = 180 - angle;
            dx = -dx;
        }
        if (y + dy < 0 || y + dy > environment.getHeight()) {
            angle = -angle;
            dy = -dy;
        }

        // Test collision with obstacles
        if (environment.collidesWithObstacle(x + dx, y + dy)) {
            angle = angle + 180;
            dx = -dx;
            dy = -dy;
        }

        x += dx;
        y += dy;

        // Update angle based on current gene if we haven't reached the end of the genes
        if (geneCounter < dna.getLength()) {
            angle += dna.getGene(geneCounter);
        }
    }

    /**
     * Gets the current x position of the creature.
     * 
     * @return The x position
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the current y position of the creature.
     * 
     * @return The y position
     */
    public float getY() {
        return y;
    }

    /**
     * Gets the diameter of the creature.
     * 
     * @return The diameter
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * Gets the color of the creature.
     * 
     * @return The color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the DNA of the creature.
     * 
     * @return The DNA
     */
    public DNA getDNA() {
        return dna;
    }

    /**
     * Gets the unique identifier of the creature.
     * 
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the creature has reached the target.
     * 
     * @return true if the creature has reached the target, false otherwise
     */
    public boolean hasReachedTarget() {
        return completed;
    }

    /**
     * Gets the current gene counter value.
     * 
     * @return The gene counter
     */
    public int getGeneCounter() {
        return geneCounter;
    }

    /**
     * Resets the creature to its starting position.
     */
    public void reset() {
        x = startX;
        y = startY;
        geneCounter = 0;
        completed = false;
    }
}
