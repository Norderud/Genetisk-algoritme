package no.asmund.genetisk

import kotlin.random.Random

/**
 * Represents the genetic information of a creature in the genetic algorithm.
 * 
 * @author Åsmund
 */
class DNA(val genes: FloatArray = FloatArray(StartApp.lifetime)) {
    
    init {
        // Initialize with random genes if using the default constructor
        if (genes.all { it == 0f }) {
            for (i in genes.indices) {
                genes[i] = (Random.nextInt(-10, 10)).toFloat()
            }
        }
    }
    
    /**
     * Creates a new DNA sequence by crossing over this DNA with a partner DNA.
     * 
     * @param partner The DNA to crossover with
     * @return A new DNA instance containing genes from both parents
     */
    fun crossover(partner: DNA): DNA {
        val childGenes = FloatArray(genes.size)
        // Choose a midpoint for crossover
        val crossoverPoint = Random.nextInt(genes.size)
        
        // Take genes from this DNA before the crossover point, and from partner after
        for (i in genes.indices) {
            childGenes[i] = if (i < crossoverPoint) genes[i] else partner.genes[i]
        }
        
        return DNA(childGenes)
    }
    
    /**
     * Applies random mutations to genes based on a small probability.
     */
    fun mutation(mutationRate: Float) {
        for (i in genes.indices) {
            // 0.1% chance of mutation for each gene
            if (Random.nextInt(1000) < 1) {
                genes[i] = Random.nextInt(-10, 10).toFloat()
            }
        }
    }
}