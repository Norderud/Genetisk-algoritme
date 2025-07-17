/**
 * Interface for observers of simulation events.
 * This interface defines the contract for objects that want to be notified of simulation events.
 */
package no.asmund.genetisk.observer;

public interface SimulationObserver {
    
    /**
     * Called when a new generation is created.
     * 
     * @param generationNumber The number of the new generation
     * @param maxFitness The maximum fitness in the new generation
     * @param averageFitness The average fitness in the new generation
     */
    void onNewGeneration(int generationNumber, float maxFitness, float averageFitness);
    
    /**
     * Called when the simulation is updated.
     * 
     * @param step The current step in the generation
     * @param totalSteps The total number of steps in a generation
     */
    void onSimulationUpdate(int step, int totalSteps);
    
    /**
     * Called when a creature reaches the target.
     * 
     * @param creatureId The ID of the creature that reached the target
     * @param steps The number of steps it took to reach the target
     */
    void onTargetReached(int creatureId, int steps);
}