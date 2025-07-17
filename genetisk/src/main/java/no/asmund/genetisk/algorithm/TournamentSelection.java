/**
 * Implementation of tournament selection.
 * This selection strategy selects parents by running tournaments among randomly selected individuals.
 */
package no.asmund.genetisk.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import no.asmund.genetisk.model.GeneticEntity;

public class TournamentSelection implements SelectionStrategy {
    
    /**
     * The size of each tournament.
     */
    private final int tournamentSize;
    
    /**
     * The number of tournaments to run (determines the size of the mating pool).
     */
    private final int numberOfTournaments;
    
    /**
     * Random number generator for selecting tournament participants.
     */
    private final Random random;
    
    /**
     * Creates a new TournamentSelection with default parameters.
     */
    public TournamentSelection() {
        this(3, 100);
    }
    
    /**
     * Creates a new TournamentSelection with the specified parameters.
     * 
     * @param tournamentSize The size of each tournament
     * @param numberOfTournaments The number of tournaments to run
     */
    public TournamentSelection(int tournamentSize, int numberOfTournaments) {
        this.tournamentSize = tournamentSize;
        this.numberOfTournaments = numberOfTournaments;
        this.random = new Random();
    }
    
    @Override
    public List<GeneticEntity> selectParents(GeneticEntity[] population, float maxFitness) {
        List<GeneticEntity> matingPool = new ArrayList<>();
        
        // Run tournaments to select parents
        for (int i = 0; i < numberOfTournaments; i++) {
            GeneticEntity winner = runTournament(population);
            matingPool.add(winner);
        }
        
        return matingPool;
    }
    
    /**
     * Runs a single tournament and returns the winner.
     * 
     * @param population The population to select from
     * @return The winner of the tournament
     */
    private GeneticEntity runTournament(GeneticEntity[] population) {
        GeneticEntity best = null;
        float bestFitness = -1;
        
        // Select random participants and find the best one
        for (int i = 0; i < tournamentSize; i++) {
            int index = random.nextInt(population.length);
            GeneticEntity participant = population[index];
            float fitness = participant.getFitness();
            
            if (best == null || fitness > bestFitness) {
                best = participant;
                bestFitness = fitness;
            }
        }
        
        return best;
    }
    
    @Override
    public String getName() {
        return "Tournament Selection";
    }
}