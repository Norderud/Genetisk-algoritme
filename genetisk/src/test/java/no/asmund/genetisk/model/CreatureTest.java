/**
 * Tests for the Creature class.
 */
package no.asmund.genetisk.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;

class CreatureTest {
    
    private Environment environment;
    private DNA dna;
    private Creature creature;
    
    private static final float START_X = 100;
    private static final float START_Y = 100;
    private static final int TARGET_X = 200;
    private static final int TARGET_Y = 200;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    
    @BeforeEach
    void setUp() {
        // Create a test environment
        environment = new DefaultEnvironment(WIDTH, HEIGHT, TARGET_X, TARGET_Y);
        
        // Create a DNA with predictable genes
        float[] genes = new float[100];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = 1; // No angle changes
        }
        dna = new DNA(genes);
        
        // Create a creature with the test environment and DNA
        creature = new Creature(dna, environment, START_X, START_Y);
    }
    
    @Test
    void testCreatureInitialization() {
        assertEquals(START_X, creature.getX(), "X position should be initialized to startX");
        assertEquals(START_Y, creature.getY(), "Y position should be initialized to startY");
        assertFalse(creature.hasReachedTarget(), "Creature should not have reached target initially");
        assertEquals(0, creature.getGeneCounter(), "Gene counter should be initialized to 0");
        assertNotNull(creature.getColor(), "Creature should have a color");
        assertEquals(5, creature.getDiameter(), "Creature should have a diameter of 5");
    }
    
    @Test
    void testCalculateFitness() {
        creature.calculateFitness();
        
        // Calculate expected fitness
        float distance = (float) Math.sqrt(Math.pow(START_X - TARGET_X, 2) + Math.pow(START_Y - TARGET_Y, 2));
        float expectedFitness = 1 / distance;
        
        assertEquals(expectedFitness, creature.getFitness(), 0.0001, "Fitness should be calculated correctly");
        assertEquals(0, creature.getGeneCounter(), "Gene counter should be reset after fitness calculation");
    }
    
    @Test
    void testReachTarget() {
        // Create a creature very close to the target
        Creature nearTarget = new Creature(dna, environment, TARGET_X - 10, TARGET_Y - 10);
        
        // Calculate fitness, which should mark the creature as having reached the target
        nearTarget.calculateFitness();
        
        assertTrue(nearTarget.hasReachedTarget(), "Creature should have reached target");
        
        // Fitness should be boosted by the target reach bonus
        float distance = (float) Math.sqrt(Math.pow(nearTarget.getX() - TARGET_X, 2) + Math.pow(nearTarget.getY() - TARGET_Y, 2));
        float expectedFitness = (1 / distance) * 10; // 10 is the target reach bonus
        
        assertEquals(expectedFitness, nearTarget.getFitness(), 0.0001, "Fitness should be boosted when target is reached");
    }
    
    @Test
    void testUpdate() {
        // Run the creature for one step
        creature.update();
        
        // Since we set all genes to 0, the creature should move in a straight line
        // The angle is set in the constructor based on DNA, so we can't easily predict the exact position
        // But we can check that the creature has moved
        assertNotEquals(START_X, creature.getX(), "X position should change after update");
        assertNotEquals(START_Y, creature.getY(), "Y position should change after update");
    }
    
    @Test
    void testRun() {
        // Run the creature for one step
        creature.run();
        
        // The creature should have moved and the gene counter should have incremented
        assertNotEquals(START_X, creature.getX(), "X position should change after run");
        assertNotEquals(START_Y, creature.getY(), "Y position should change after run");
        assertEquals(1, creature.getGeneCounter(), "Gene counter should increment after run");
    }
    
    @Test
    void testReset() {
        // Run the creature for a few steps
        for (int i = 0; i < 5; i++) {
            creature.run();
        }
        
        // Reset the creature
        creature.reset();
        
        // The creature should be back at the starting position with gene counter reset
        assertEquals(START_X, creature.getX(), "X position should be reset to startX");
        assertEquals(START_Y, creature.getY(), "Y position should be reset to startY");
        assertEquals(0, creature.getGeneCounter(), "Gene counter should be reset to 0");
        assertFalse(creature.hasReachedTarget(), "Creature should not have reached target after reset");
    }
    
    @Test
    void testCollisionWithObstacle() {
        // Add an obstacle in the creature's path
        ((DefaultEnvironment) environment).addObstacle(new Obstacle(150, 100, 20, 20));
        
        // Record the initial position and angle
        float initialX = creature.getX();
        float initialY = creature.getY();
        
        // Run the creature until it hits the obstacle or moves too far
        for (int i = 0; i < 100 && creature.getX() < 170; i++) {
            creature.run();
        }
        
        // The creature should have changed direction after hitting the obstacle
        assertTrue(creature.getX() < 150 || creature.getX() > 170 || creature.getY() < 100 || creature.getY() > 120,
                "Creature should change direction after hitting obstacle");
    }
}