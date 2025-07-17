/**
 * Interface for subjects that can notify observers of simulation events.
 * This interface defines the contract for objects that can be observed for simulation events.
 */
package no.asmund.genetisk.observer;

public interface SimulationSubject {
    
    /**
     * Adds an observer to be notified of simulation events.
     * 
     * @param observer The observer to add
     */
    void addObserver(SimulationObserver observer);
    
    /**
     * Removes an observer from being notified of simulation events.
     * 
     * @param observer The observer to remove
     */
    void removeObserver(SimulationObserver observer);
    
    /**
     * Notifies all observers of a new generation.
     * 
     * @param generationNumber The number of the new generation
     * @param maxFitness The maximum fitness in the new generation
     * @param averageFitness The average fitness in the new generation
     */
    void notifyNewGeneration(int generationNumber, float maxFitness, float averageFitness);
    
    /**
     * Notifies all observers of a simulation update.
     * 
     * @param step The current step in the generation
     * @param totalSteps The total number of steps in a generation
     */
    void notifySimulationUpdate(int step, int totalSteps);
    
    /**
     * Notifies all observers that a creature has reached the target.
     * 
     * @param creatureId The ID of the creature that reached the target
     * @param steps The number of steps it took to reach the target
     */
    void notifyTargetReached(int creatureId, int steps);
}