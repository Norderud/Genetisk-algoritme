/**
 * Default implementation of the CreatureFactory interface.
 * This class provides a default implementation for creating creatures.
 */
package no.asmund.genetisk.factory;

import no.asmund.genetisk.model.Creature;
import no.asmund.genetisk.model.DNA;
import no.asmund.genetisk.model.Environment;
import no.asmund.genetisk.model.GeneticEntity;

public class DefaultCreatureFactory implements CreatureFactory {
    
    /**
     * The environment in which creatures will exist.
     */
    private final Environment environment;
    
    /**
     * The starting x position for new creatures.
     */
    private final float startX;
    
    /**
     * The starting y position for new creatures.
     */
    private final float startY;
    
    /**
     * The length of the genes array for new creatures.
     */
    private final int geneLength;
    
    /**
     * Creates a new DefaultCreatureFactory with the specified parameters.
     * 
     * @param environment The environment in which creatures will exist
     * @param startX The starting x position for new creatures
     * @param startY The starting y position for new creatures
     * @param geneLength The length of the genes array for new creatures
     */
    public DefaultCreatureFactory(Environment environment, float startX, float startY, int geneLength) {
        this.environment = environment;
        this.startX = startX;
        this.startY = startY;
        this.geneLength = geneLength;
    }
    
    @Override
    public GeneticEntity createCreature() {
        DNA dna = new DNA(geneLength);
        return new Creature(dna, environment, startX, startY);
    }
    
    @Override
    public GeneticEntity createCreature(DNA dna) {
        return new Creature(dna, environment, startX, startY);
    }
    
    @Override
    public GeneticEntity createChild(GeneticEntity parent1, GeneticEntity parent2) {
        if (!(parent1 instanceof Creature) || !(parent2 instanceof Creature)) {
            throw new IllegalArgumentException("Parents must be instances of Creature");
        }
        
        Creature creature1 = (Creature) parent1;
        Creature creature2 = (Creature) parent2;
        
        DNA dna1 = creature1.getDNA();
        DNA dna2 = creature2.getDNA();
        
        // Create a child DNA through crossover and mutation
        DNA childDNA = dna1.crossover(dna2);
        childDNA.mutation();
        
        return createCreature(childDNA);
    }
}