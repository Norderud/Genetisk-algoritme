/**
 * Represents a population of creatures in the genetic algorithm simulation.
 * This class manages a collection of creatures and implements the genetic algorithm operations.
 */
package no.asmund.genetisk.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.asmund.genetisk.factory.CreatureFactory;
import no.asmund.genetisk.model.GeneticEntity;
import no.asmund.genetisk.observer.AbstractSimulationSubject;

public class Population extends AbstractSimulationSubject {
    /**
     * The array of creatures in the population.
     */
    private GeneticEntity[] population;
    
    /**
     * The mating pool for selecting parents for reproduction.
     */
    private List<GeneticEntity> matingPool;
    
    /**
     * The number of generations that have passed.
     */
    private int generations;
    
    /**
     * The size of the population.
     */
    private final int popSize;
    
    /**
     * The mutation rate for the genetic algorithm.
     */
    private final float mutationRate;
    
    /**
     * The maximum fitness in the current generation.
     */
    private float maxFitness;
    
    /**
     * The average fitness in the current generation.
     */
    private float averageFitness;
    
    /**
     * The factory for creating creatures.
     */
    private final CreatureFactory creatureFactory;
    
    /**
     * The selection strategy for selecting parents for reproduction.
     */
    private final SelectionStrategy selectionStrategy;
    
    /**
     * Random number generator for selecting parents.
     */
    private final Random random;

    /**
     * Creates a new population with the specified parameters.
     * 
     * @param mutationRate The mutation rate for the genetic algorithm
     * @param populationSize The size of the population
     * @param creatureFactory The factory for creating creatures
     * @param selectionStrategy The strategy for selecting parents for reproduction
     */
    public Population(float mutationRate, int populationSize, CreatureFactory creatureFactory, SelectionStrategy selectionStrategy) {
        this.mutationRate = mutationRate;
        this.popSize = populationSize;
        this.creatureFactory = creatureFactory;
        this.selectionStrategy = selectionStrategy;
        this.random = new Random();
        
        population = new GeneticEntity[populationSize];
        matingPool = new ArrayList<>();
        generations = 0;
        maxFitness = 0;
        averageFitness = 0;

        // Initialize the population with random creatures
        for (int i = 0; i < popSize; i++) {
            population[i] = creatureFactory.createCreature();
        }
    }

    /**
     * Calculates the fitness of all creatures in the population.
     */
    public void calculateFitness() {
        float totalFitness = 0;
        int maxFitnessIndex = 0;
        
        for (int i = 0; i < popSize; i++) {
            population[i].calculateFitness();
            float fitness = population[i].getFitness();
            totalFitness += fitness;
            
            if (fitness > maxFitness) {
                maxFitness = fitness;
                maxFitnessIndex = i;
            }
        }
        
        averageFitness = totalFitness / popSize;
        
        // Normalize fitness values
        for (int i = 0; i < popSize; i++) {
            GeneticEntity entity = population[i];
            float normalizedFitness = entity.getFitness() / maxFitness;
            // We can't directly set the normalized fitness, so we'll rely on the selection strategy
            // to handle normalization
        }
        
        // Notify observers of the new generation
        notifyNewGeneration(generations, maxFitness, averageFitness);
    }

    /**
     * Selects parents for reproduction based on the selection strategy.
     */
    public void selection() {
        matingPool = selectionStrategy.selectParents(population, maxFitness);
    }

    /**
     * Creates a new generation through reproduction.
     */
    public void reproduction() {
        for (int i = 0; i < popSize; i++) {
            // Select two parents
            int m = random.nextInt(matingPool.size());
            int d = random.nextInt(matingPool.size());
            GeneticEntity parent1 = matingPool.get(m);
            GeneticEntity parent2 = matingPool.get(d);
            
            // Create a child from the two parents
            GeneticEntity child = creatureFactory.createChild(parent1, parent2);
            population[i] = child;
        }
        
        generations++;
    }
    
    /**
     * Runs all creatures in the population for one step.
     */
    public void run() {
        for (int i = 0; i < popSize; i++) {
            population[i].run();
        }
    }
    
    /**
     * Gets the array of creatures in the population.
     * 
     * @return The population array
     */
    public GeneticEntity[] getPopulation() {
        return population;
    }
    
    /**
     * Gets the size of the population.
     * 
     * @return The population size
     */
    public int getPopulationSize() {
        return popSize;
    }
    
    /**
     * Gets the number of generations that have passed.
     * 
     * @return The number of generations
     */
    public int getGenerations() {
        return generations;
    }
    
    /**
     * Gets the maximum fitness in the current generation.
     * 
     * @return The maximum fitness
     */
    public float getMaxFitness() {
        return maxFitness;
    }
    
    /**
     * Gets the average fitness in the current generation.
     * 
     * @return The average fitness
     */
    public float getAverageFitness() {
        return averageFitness;
    }
    
    /**
     * Gets the selection strategy used by this population.
     * 
     * @return The selection strategy
     */
    public SelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }
}