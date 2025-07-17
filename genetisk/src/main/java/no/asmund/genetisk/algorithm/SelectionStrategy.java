/**
 * Interface for selection strategies in genetic algorithms.
 * This interface defines the contract for different methods of selecting parents for reproduction.
 */
package no.asmund.genetisk.algorithm;

import java.util.List;
import no.asmund.genetisk.model.GeneticEntity;

public interface SelectionStrategy {
    
    /**
     * Selects parents from a population based on their fitness.
     * 
     * @param population The population to select from
     * @param maxFitness The maximum fitness in the population
     * @return A list of selected parents
     */
    List<GeneticEntity> selectParents(GeneticEntity[] population, float maxFitness);
    
    /**
     * Gets the name of this selection strategy.
     * 
     * @return The name of the strategy
     */
    String getName();
}