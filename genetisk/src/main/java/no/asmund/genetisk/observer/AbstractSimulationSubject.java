/**
 * Abstract implementation of the SimulationSubject interface.
 * This class provides a base implementation for managing observers and notifying them of events.
 */
package no.asmund.genetisk.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSimulationSubject implements SimulationSubject {
    
    /**
     * The list of observers.
     */
    private final List<SimulationObserver> observers = new ArrayList<>();
    
    @Override
    public void addObserver(SimulationObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    @Override
    public void removeObserver(SimulationObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyNewGeneration(int generationNumber, float maxFitness, float averageFitness) {
        for (SimulationObserver observer : observers) {
            observer.onNewGeneration(generationNumber, maxFitness, averageFitness);
        }
    }
    
    @Override
    public void notifySimulationUpdate(int step, int totalSteps) {
        for (SimulationObserver observer : observers) {
            observer.onSimulationUpdate(step, totalSteps);
        }
    }
    
    @Override
    public void notifyTargetReached(int creatureId, int steps) {
        for (SimulationObserver observer : observers) {
            observer.onTargetReached(creatureId, steps);
        }
    }
}