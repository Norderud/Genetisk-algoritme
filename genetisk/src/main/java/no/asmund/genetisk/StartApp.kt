package no.asmund.genetisk

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.util.Duration

class StartApp : Application() {
    companion object {
        const val HEIGHT = 600
        const val WIDTH = 1200
        
        var lifetime: Int = 0
        var popNr: Int = 0
        var targetX: Int = 0
        var targetY: Int = 0
        
        @JvmStatic
        fun main(args: Array<String>) {
            launch(StartApp::class.java, *args)
        }
    }
    
    private var lifecycle = 0
    private lateinit var gc: GraphicsContext
    private lateinit var loop: Timeline
    private lateinit var population: Population
    
    override fun start(primaryStage: Stage) {
        lifetime = 300
        lifecycle = 0
        popNr = 100

        targetX = 1000
        targetY = HEIGHT / 2

        val root = StackPane()
        val scene = Scene(root, WIDTH.toDouble(), HEIGHT.toDouble())
        val canvas = Canvas(WIDTH.toDouble(), HEIGHT.toDouble())

        gc = canvas.graphicsContext2D
        root.children.add(canvas)

        population = Population(1000000f, popNr)

        loop = Timeline(
            KeyFrame(
                Duration.millis(5.0),
                { draw() }
            )
        )
        loop.cycleCount = Timeline.INDEFINITE
        loop.play()
        
        primaryStage.title = "Genetisk Algoritme"
        primaryStage.scene = scene
        primaryStage.show()
    }

    fun draw() {
        if (lifecycle < lifetime) {
            repaint()
            lifecycle++
        } else {
            gc.clearRect(0.0, 0.0, WIDTH.toDouble(), HEIGHT.toDouble())
            lifecycle = 0
            population.fitness()
            population.selection()
            population.reproduction()
        }
    }

    private fun repaint() {
        gc.fill = Color.RED
        gc.fillRect(
            (targetX - 5).toDouble(),
            (targetY - 5).toDouble(),
            10.0,
            10.0
        )
        
        gc.fill = Color.BLACK
        for (i in 0 until popNr) {
            val creature = population.population[i]
            creature?.run()
            creature?.color?.let { color ->
                gc.fill = color
                gc.fillOval(
                    (creature.x - creature.d / 2).toDouble(),
                    (creature.y - creature.d / 2).toDouble(),
                    creature.d.toDouble(),
                    creature.d.toDouble()
                )
            }
        }
    }
}