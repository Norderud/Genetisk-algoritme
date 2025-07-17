/**
 * Represents the genetic information of a creature.
 * This class encapsulates the genes that determine a creature's behavior and appearance.
 */
package no.asmund.genetisk.model;

import java.util.Random;

public class DNA {

    /**
     * The genes array that determines the creature's behavior.
     * Each gene represents an angle adjustment for the creature's movement.
     */
    private float[] genes;
    
    /**
     * Random number generator for creating and mutating genes.
     */
    private Random rand = new Random();
    
    /**
     * The minimum angle adjustment value.
     */
    private static final int MIN_ANGLE = -10;
    
    /**
     * The maximum angle adjustment value.
     */
    private static final int MAX_ANGLE = 10;
    
    /**
     * The range of possible angle adjustments.
     */
    private static final int ANGLE_RANGE = MAX_ANGLE - MIN_ANGLE;
    
    /**
     * The mutation rate (probability of mutation per gene).
     */
    private static final float MUTATION_RATE = 0.001f;

    /**
     * Creates a new DNA with random genes.
     * 
     * @param geneLength The length of the genes array
     */
    public DNA(int geneLength) {
        genes = new float[geneLength];
        for (int i = 0; i < genes.length; i++) {
            genes[i] = rand.nextInt(ANGLE_RANGE) + MIN_ANGLE;
        }
    }
    
    /**
     * Creates a new DNA with the specified genes.
     * 
     * @param newgenes The genes for the new DNA
     */
    public DNA(float[] newgenes) {
        genes = newgenes;
    }

    /**
     * Creates a new DNA by combining genes from this DNA and a partner DNA.
     * 
     * @param partner The partner DNA to crossover with
     * @return A new DNA that is a combination of this DNA and the partner DNA
     */
    public DNA crossover(DNA partner) {
        float[] child = new float[genes.length];
        // Choose a random crossover point
        int crossover = rand.nextInt(genes.length);

        for (int i = 0; i < genes.length; i++) {
            if (i < crossover) {
                child[i] = genes[i];
            } else {
                child[i] = partner.genes[i];
            }
        }
        return new DNA(child);
    }
    
    /**
     * Mutates this DNA by randomly changing some genes.
     */
    public void mutation() {
        for (int i = 0; i < genes.length; i++) {
            if (rand.nextFloat() < MUTATION_RATE) {
                genes[i] = rand.nextInt(ANGLE_RANGE) + MIN_ANGLE;
            }
        }
    }
    
    /**
     * Gets the genes of this DNA.
     * 
     * @return The genes array
     */
    public float[] getGenes() {
        return genes;
    }
    
    /**
     * Gets the gene at the specified index.
     * 
     * @param index The index of the gene to get
     * @return The gene at the specified index
     */
    public float getGene(int index) {
        return genes[index];
    }
    
    /**
     * Gets the length of the genes array.
     * 
     * @return The length of the genes array
     */
    public int getLength() {
        return genes.length;
    }
    
    /**
     * Sets the random number generator for this DNA.
     * This is primarily used for testing to provide deterministic behavior.
     * 
     * @param random The random number generator to use
     */
    public void setRandom(Random random) {
        this.rand = random;
    }
}