/**
 * Tests for the DNA class.
 */
package no.asmund.genetisk.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class DNATest {

    /**
     * A custom Random implementation that returns a sequence of predetermined values.
     * This is used to make the tests deterministic.
     */
    private static class SequenceRandom extends Random {
        private final int[] intValues;
        private final float[] floatValues;
        private int intIndex = 0;
        private int floatIndex = 0;
        
        /**
         * Creates a new SequenceRandom with the specified integer values.
         * 
         * @param intValues The integer values to return
         */
        SequenceRandom(int... intValues) {
            this.intValues = intValues;
            this.floatValues = new float[0];
        }
        
        /**
         * Creates a new SequenceRandom with the specified integer and float values.
         * 
         * @param intValues The integer values to return
         * @param floatValues The float values to return
         */
        SequenceRandom(int[] intValues, float[] floatValues) {
            this.intValues = intValues;
            this.floatValues = floatValues;
        }
        
        @Override
        public int nextInt(int bound) {
            int v = intValues[intIndex++ % intValues.length];
            if (v < 0) {
                v = -v;
            }
            return v % bound;
        }
        
        @Override
        public float nextFloat() {
            return floatValues[floatIndex++ % floatValues.length];
        }
    }

    @Test
    void testCrossoverCombinesGenesAtCrossoverPoint() {
        float[] genes1 = {1f, 1f, 1f, 1f, 1f};
        float[] genes2 = {2f, 2f, 2f, 2f, 2f};
        DNA parent1 = new DNA(genes1);
        DNA parent2 = new DNA(genes2);
        parent1.setRandom(new SequenceRandom(2)); // crossover after index 1
        DNA child = parent1.crossover(parent2);
        
        // Check that the child's genes are a combination of the parents' genes
        assertEquals(1f, child.getGene(0), 0.0001, "First gene should be from parent1");
        assertEquals(1f, child.getGene(1), 0.0001, "Second gene should be from parent1");
        assertEquals(2f, child.getGene(2), 0.0001, "Third gene should be from parent2");
        assertEquals(2f, child.getGene(3), 0.0001, "Fourth gene should be from parent2");
        assertEquals(2f, child.getGene(4), 0.0001, "Fifth gene should be from parent2");
    }

    @Test
    void testMutationAppliesWhenRandomBelowThreshold() {
        float[] genes = {0f, 0f, 0f};
        DNA dna = new DNA(genes);
        
        // Set up the random generator to:
        // 1. Return 0.002 for the first gene (above threshold, no mutation)
        // 2. Return 0.0005 for the second gene (below threshold, mutate)
        // 3. Return 0.002 for the third gene (above threshold, no mutation)
        // 4. Return 5 for the mutation value of the second gene
        dna.setRandom(new SequenceRandom(new int[]{5}, new float[]{0.002f, 0.0005f, 0.002f}));
        
        dna.mutation();
        
        // Check that only the second gene was mutated
        assertEquals(0f, dna.getGene(0), 0.0001, "First gene should not be mutated");
        assertEquals(-5f, dna.getGene(1), 0.0001, "Second gene should be mutated to -5");
        assertEquals(0f, dna.getGene(2), 0.0001, "Third gene should not be mutated");
    }
    
    @Test
    void testDNAConstructorWithGeneLength() {
        int geneLength = 10;
        DNA dna = new DNA(geneLength);
        
        // Check that the DNA has the correct number of genes
        assertEquals(geneLength, dna.getLength(), "DNA should have the specified number of genes");
        
        // Check that all genes are within the expected range
        for (int i = 0; i < geneLength; i++) {
            float gene = dna.getGene(i);
            assertTrue(gene >= -10 && gene <= 10, "Gene should be within range [-10, 10]");
        }
    }
}