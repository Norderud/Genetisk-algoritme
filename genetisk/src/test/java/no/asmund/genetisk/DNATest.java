package no.asmund.genetisk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class DNATest {

    private static class SequenceRandom extends Random {
        private final int[] values;
        private int index = 0;
        SequenceRandom(int... values) {
            this.values = values;
        }
        @Override
        public int nextInt(int bound) {
            int v = values[index++ % values.length];
            if (v < 0) {
                v = -v;
            }
            return v % bound;
        }
    }

    @Test
    void testCrossoverCombinesGenesAtCrossoverPoint() {
        float[] genes1 = {1f, 1f, 1f, 1f, 1f};
        float[] genes2 = {2f, 2f, 2f, 2f, 2f};
        DNA parent1 = new DNA(genes1);
        DNA parent2 = new DNA(genes2);
        parent1.rand = new SequenceRandom(2); // crossover after index 1
        DNA child = parent1.crossover(parent2);
        float[] expected = {1f, 1f, 2f, 2f, 2f};
        assertArrayEquals(expected, child.genes, "Genes should combine at crossover");
    }

    @Test
    void testMutationAppliesWhenRandomBelowThreshold() {
        float[] genes = {0f, 0f, 0f};
        // Sequence: gene0 -> 500(no mutation), gene1 -> 0(mutate) with value 15,
        // gene2 -> 999(no mutation)
        DNA dna = new DNA(genes);
        dna.rand = new SequenceRandom(500, 0, 15, 999);
        dna.mutation();
        assertEquals(0f, dna.genes[0], 0.0001);
        assertEquals(5f, dna.genes[1], 0.0001);
        assertEquals(0f, dna.genes[2], 0.0001);
    }
}
