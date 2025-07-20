package no.asmund.genetisk

import javafx.scene.paint.Color
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Represents a creature in the genetic algorithm simulation.
 * Each creature has DNA that determines its behavior and appearance.
 */
class Creature(val dna: DNA) {
    var x: Float = 400f  // Starting X position
    var y: Float = 300f  // Starting Y position
    val startX: Float = 400f
    val startY: Float = 300f
    var angle: Float
    var geneCounter: Int = 0
    val color: Color
    var recordDist: Float = 0f
    var fitness: Float = 0f
    val d: Int = 5  // Diameter
    var completed: Boolean = false

    init {
        // Calculate color based on genes
        val r = (dna.genes[10] * dna.genes[10]) / 100
        val g = (dna.genes[20] * dna.genes[20]) / 100
        val b = (dna.genes[30] * dna.genes[30]) / 100
        color = Color(r.toDouble(), g.toDouble(), b.toDouble(), 1.0)
        
        // Calculate initial angle based on genes
        angle = (dna.genes[10] * dna.genes[10] * 3.6).toFloat()
    }

    /**
     * Updates the creature's position and orientation based on its DNA.
     */
    fun run() {
        if (!completed) {
            update()
            geneCounter++
        } else {
            println("completed")
        }
    }

    /**
     * Calculates the fitness of the creature based on its distance to the target.
     * Closer creatures have higher fitness.
     */
    fun fitness() {
        val x2 = StartApp.targetX.toFloat()
        val y2 = StartApp.targetY.toFloat()
        
        // Calculate distance to target
        recordDist = sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2))
        
        // Fitness is inverse of distance (closer = higher fitness)
        fitness = 1 / recordDist
        
        // Bonus for reaching the target
        if (recordDist < 50) {
            completed = true
            fitness *= 10f
        }
        
        geneCounter = 0
    }

    /**
     * Updates the creature's position and angle based on its DNA.
     */
    fun update() {
        // Move in the current direction
        x += (cos(Math.toRadians(angle.toDouble())) * 2).toFloat()
        y += (sin(Math.toRadians(angle.toDouble())) * 2).toFloat()
        
        // Update angle based on current gene
        angle += dna.genes[geneCounter]
    }
}