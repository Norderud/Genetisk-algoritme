package no.asmund.genetisk

import kotlin.random.Random

class Population(val mutationRate: Float, val popSize: Int) {
    val population: Array<Creature>
    val matingPool: MutableList<Creature> = mutableListOf()
    var generations: Int = 0
    var maxFit: Float = 0f

    init {
        population = Array(popSize) { Creature(DNA()) }
    }

    fun fitness() {
        var max = 0
        for (i in 0 until popSize) {
            population[i].fitness()
            if (population[i].fitness > maxFit) {
                maxFit = population[i].fitness
                max = i
            }
        }
        println(population[max].fitness)

        // Normalize fitness values
        for (i in 0 until popSize) {
            population[i].fitness /= maxFit
        }
    }

    fun selection() {
        matingPool.clear()
        for (i in 0 until popSize) {
            val n = (population[i].fitness * 100).toInt()
            repeat(n) {
                matingPool.add(population[i])
            }
        }
    }

    fun reproduction() {
        for (i in 0 until popSize) {
            // Select two parents
            val m = Random.nextInt(matingPool.size)
            val d = Random.nextInt(matingPool.size)
            val mom = matingPool[m]
            val dad = matingPool[d]

            // Get their genes
            val momGenes = mom.dna
            val dadGenes = dad.dna

            // Create child by crossing over parents
            val child = momGenes.crossover(dadGenes)
            child.mutation(mutationRate)
            population[i] = Creature(child)
        }
        generations++
    }
}