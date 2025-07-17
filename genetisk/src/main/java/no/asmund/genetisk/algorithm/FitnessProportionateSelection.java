/**
 * Implementation of fitness proportionate selection (roulette wheel selection).
 * This selection strategy selects parents with probability proportional to their fitness.
 */
package no.asmund.genetisk.algorithm;

import java.util.ArrayList;
import java.util.List;
import no.asmund.genetisk.model.GeneticEntity;

public class FitnessProportionateSelection implements SelectionStrategy {
    
    /**
     * The number of times each entity is added to the mating pool per fitness unit.
     * Higher values create more copies of fit individuals.
     */
    private final int selectionPressure;
    
    /**
     * Creates a new FitnessProportionateSelection with default selection pressure.
     */
    public FitnessProportionateSelection() {
        this(100);
    }
    
    /**
     * Creates a new FitnessProportionateSelection with the specified selection pressure.
     * 
     * @param selectionPressure The number of times each entity is added to the mating pool per fitness unit
     */
    public FitnessProportionateSelection(int selectionPressure) {
        this.selectionPressure = selectionPressure;
    }
    
    @Override
    public List<GeneticEntity> selectParents(GeneticEntity[] population, float maxFitness) {
        List<GeneticEntity> matingPool = new ArrayList<>();
        
        // Normalize fitness values
        for (GeneticEntity entity : population) {
            float normalizedFitness = entity.getFitness() / maxFitness;
            
            // Add entities to mating pool based on their normalized fitness
            int n = (int) (normalizedFitness * selectionPressure);
            for (int j = 0; j < n; j++) {
                matingPool.add(entity);
            }
        }
        
        return matingPool;
    }
    
    @Override
    public String getName() {
        return "Fitness Proportionate Selection";
    }
}