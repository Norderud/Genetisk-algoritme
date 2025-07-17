/**
 * Interface for objects that can evolve through genetic algorithms.
 * This interface defines the contract for entities that participate in genetic evolution.
 */
package no.asmund.genetisk.model;

public interface GeneticEntity {
    
    /**
     * Calculates the fitness of this entity.
     * Fitness represents how well the entity performs in the current environment.
     */
    void calculateFitness();
    
    /**
     * Gets the calculated fitness value of this entity.
     * 
     * @return The fitness value
     */
    float getFitness();
    
    /**
     * Updates the entity's state for the current simulation step.
     */
    void update();
    
    /**
     * Runs the entity for one simulation step.
     * This typically involves updating the entity's state and position.
     */
    void run();
}