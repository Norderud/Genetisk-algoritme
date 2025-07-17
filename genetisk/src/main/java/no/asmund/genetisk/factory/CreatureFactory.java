/**
 * Interface for factories that create creatures.
 * This interface defines the contract for creating different types of creatures.
 */
package no.asmund.genetisk.factory;

import no.asmund.genetisk.model.GeneticEntity;
import no.asmund.genetisk.model.DNA;

public interface CreatureFactory {

    /**
     * Creates a new creature with random DNA.
     * 
     * @return A new creature
     */
    GeneticEntity createCreature();

    /**
     * Creates a new creature with the specified DNA.
     * 
     * @param dna The DNA for the new creature
     * @return A new creature with the specified DNA
     */
    GeneticEntity createCreature(DNA dna);

    /**
     * Creates a new creature that is a child of the specified parents.
     * 
     * @param parent1 The first parent
     * @param parent2 The second parent
     * @return A new creature that is a child of the specified parents
     */
    GeneticEntity createChild(GeneticEntity parent1, GeneticEntity parent2);
}
