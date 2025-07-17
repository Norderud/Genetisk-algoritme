/**
 * Tests for the Population class.
 */
package no.asmund.genetisk.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import no.asmund.genetisk.factory.CreatureFactory;
import no.asmund.genetisk.factory.DefaultCreatureFactory;
import no.asmund.genetisk.model.Creature;
import no.asmund.genetisk.model.DNA;
import no.asmund.genetisk.model.DefaultEnvironment;
import no.asmund.genetisk.model.Environment;
import no.asmund.genetisk.model.GeneticEntity;
import no.asmund.genetisk.observer.SimulationObserver;

class PopulationTest {
    
    private Environment environment;
    private CreatureFactory creatureFactory;
    private SelectionStrategy selectionStrategy;
    private Population population;
    
    private static final int POPULATION_SIZE = 10;
    private static final float MUTATION_RATE = 0.01f;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int TARGET_X = 300;
    private static final int TARGET_Y = 300;
    private static final float START_X = 100;
    private static final float START_Y = 100;
    private static final int GENE_LENGTH = 50;
    
    @BeforeEach
    void setUp() {
        // Create a test environment
        environment = new DefaultEnvironment(WIDTH, HEIGHT, TARGET_X, TARGET_Y);
        
        // Create a creature factory
        creatureFactory = new DefaultCreatureFactory(environment, START_X, START_Y, GENE_LENGTH);
        
        // Create a selection strategy
        selectionStrategy = new FitnessProportionateSelection();
        
        // Create a population
        population = new Population(MUTATION_RATE, POPULATION_SIZE, creatureFactory, selectionStrategy);
    }
    
    @Test
    void testPopulationInitialization() {
        assertEquals(POPULATION_SIZE, population.getPopulationSize(), "Population size should match the specified size");
        assertEquals(0, population.getGenerations(), "Initial generation should be 0");
        assertEquals(0, population.getMaxFitness(), "Initial max fitness should be 0");
        assertEquals(0, population.getAverageFitness(), "Initial average fitness should be 0");
        assertSame(selectionStrategy, population.getSelectionStrategy(), "Selection strategy should be the one specified");
        
        GeneticEntity[] entities = population.getPopulation();
        assertEquals(POPULATION_SIZE, entities.length, "Population array should have the specified size");
        
        // Check that all entities are not null
        for (GeneticEntity entity : entities) {
            assertNotNull(entity, "Entity should not be null");
        }
    }
    
    @Test
    void testCalculateFitness() {
        // Calculate fitness
        population.calculateFitness();
        
        // Max fitness and average fitness should be updated
        assertTrue(population.getMaxFitness() > 0, "Max fitness should be greater than 0 after calculation");
        assertTrue(population.getAverageFitness() > 0, "Average fitness should be greater than 0 after calculation");
    }
    
    @Test
    void testSelection() {
        // Calculate fitness first
        population.calculateFitness();
        
        // Perform selection
        population.selection();
        
        // We can't easily test the result of selection directly, but we can test that it doesn't throw exceptions
        // and that the population is still intact
        GeneticEntity[] entities = population.getPopulation();
        assertEquals(POPULATION_SIZE, entities.length, "Population array should still have the specified size");
    }
    
    @Test
    void testReproduction() {
        // Calculate fitness and perform selection first
        population.calculateFitness();
        population.selection();
        
        // Get the current population
        GeneticEntity[] oldPopulation = population.getPopulation();
        
        // Perform reproduction
        population.reproduction();
        
        // Get the new population
        GeneticEntity[] newPopulation = population.getPopulation();
        
        // The generation should have incremented
        assertEquals(1, population.getGenerations(), "Generation should increment after reproduction");
        
        // The population size should be the same
        assertEquals(POPULATION_SIZE, newPopulation.length, "Population size should remain the same after reproduction");
        
        // The new population should be different from the old one
        boolean allSame = true;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (oldPopulation[i] != newPopulation[i]) {
                allSame = false;
                break;
            }
        }
        assertFalse(allSame, "New population should be different from old population");
    }
    
    @Test
    void testRun() {
        // Get the initial positions of all creatures
        float[] initialX = new float[POPULATION_SIZE];
        float[] initialY = new float[POPULATION_SIZE];
        
        GeneticEntity[] entities = population.getPopulation();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Creature creature = (Creature) entities[i];
            initialX[i] = creature.getX();
            initialY[i] = creature.getY();
        }
        
        // Run the population for one step
        population.run();
        
        // Check that all creatures have moved
        boolean allMoved = false;
        for (int i = 0; i < POPULATION_SIZE; i++) {
            Creature creature = (Creature) entities[i];
            if (creature.getX() != initialX[i] || creature.getY() != initialY[i]) {
                allMoved = true;
                break;
            }
        }
        assertTrue(allMoved, "At least one creature should have moved after run");
    }
    
    @Test
    void testObserverNotification() {
        // Create a test observer
        TestObserver observer = new TestObserver();
        
        // Add the observer to the population
        population.addObserver(observer);
        
        // Calculate fitness, which should notify the observer of a new generation
        population.calculateFitness();
        
        // Check that the observer was notified
        assertTrue(observer.newGenerationCalled, "Observer should be notified of new generation");
        assertEquals(0, observer.generationNumber, "Generation number should be 0");
        assertEquals(population.getMaxFitness(), observer.maxFitness, "Max fitness should match");
        assertEquals(population.getAverageFitness(), observer.averageFitness, "Average fitness should match");
    }
    
    /**
     * A test observer that records when it's notified.
     */
    private static class TestObserver implements SimulationObserver {
        boolean newGenerationCalled = false;
        boolean simulationUpdateCalled = false;
        boolean targetReachedCalled = false;
        int generationNumber;
        float maxFitness;
        float averageFitness;
        int step;
        int totalSteps;
        int creatureId;
        int steps;
        
        @Override
        public void onNewGeneration(int generationNumber, float maxFitness, float averageFitness) {
            newGenerationCalled = true;
            this.generationNumber = generationNumber;
            this.maxFitness = maxFitness;
            this.averageFitness = averageFitness;
        }
        
        @Override
        public void onSimulationUpdate(int step, int totalSteps) {
            simulationUpdateCalled = true;
            this.step = step;
            this.totalSteps = totalSteps;
        }
        
        @Override
        public void onTargetReached(int creatureId, int steps) {
            targetReachedCalled = true;
            this.creatureId = creatureId;
            this.steps = steps;
        }
    }
}