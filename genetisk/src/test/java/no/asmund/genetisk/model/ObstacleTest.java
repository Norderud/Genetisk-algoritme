/**
 * Tests for the Obstacle class.
 */
package no.asmund.genetisk.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class ObstacleTest {
    
    private static final int X = 100;
    private static final int Y = 100;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 30;
    
    @Test
    void testObstacleInitialization() {
        // Create an obstacle with default color
        Obstacle obstacle = new Obstacle(X, Y, WIDTH, HEIGHT);
        
        // Check that the obstacle has the correct properties
        assertEquals(X, obstacle.getX(), "X coordinate should match");
        assertEquals(Y, obstacle.getY(), "Y coordinate should match");
        assertEquals(WIDTH, obstacle.getWidth(), "Width should match");
        assertEquals(HEIGHT, obstacle.getHeight(), "Height should match");
        assertEquals(Color.GRAY, obstacle.getColor(), "Default color should be gray");
        
        // Create an obstacle with a custom color
        Color customColor = Color.RED;
        Obstacle coloredObstacle = new Obstacle(X, Y, WIDTH, HEIGHT, customColor);
        
        // Check that the obstacle has the correct color
        assertEquals(customColor, coloredObstacle.getColor(), "Color should match the specified color");
    }
    
    @Test
    void testCollision() {
        Obstacle obstacle = new Obstacle(X, Y, WIDTH, HEIGHT);
        
        // Test points inside the obstacle
        assertTrue(obstacle.collides(X, Y), "Top-left corner should collide");
        assertTrue(obstacle.collides(X + WIDTH, Y), "Top-right corner should collide");
        assertTrue(obstacle.collides(X, Y + HEIGHT), "Bottom-left corner should collide");
        assertTrue(obstacle.collides(X + WIDTH, Y + HEIGHT), "Bottom-right corner should collide");
        assertTrue(obstacle.collides(X + WIDTH / 2, Y + HEIGHT / 2), "Center should collide");
        
        // Test points outside the obstacle
        assertFalse(obstacle.collides(X - 1, Y), "Point to the left should not collide");
        assertFalse(obstacle.collides(X, Y - 1), "Point above should not collide");
        assertFalse(obstacle.collides(X + WIDTH + 1, Y), "Point to the right should not collide");
        assertFalse(obstacle.collides(X, Y + HEIGHT + 1), "Point below should not collide");
        assertFalse(obstacle.collides(X - 1, Y - 1), "Point to the top-left should not collide");
        assertFalse(obstacle.collides(X + WIDTH + 1, Y - 1), "Point to the top-right should not collide");
        assertFalse(obstacle.collides(X - 1, Y + HEIGHT + 1), "Point to the bottom-left should not collide");
        assertFalse(obstacle.collides(X + WIDTH + 1, Y + HEIGHT + 1), "Point to the bottom-right should not collide");
    }
    
    @Test
    void testEdgeCases() {
        // Test an obstacle with zero width and height
        Obstacle zeroSizeObstacle = new Obstacle(X, Y, 0, 0);
        
        // The point at (X, Y) should still collide
        assertTrue(zeroSizeObstacle.collides(X, Y), "Point at obstacle position should collide even with zero size");
        
        // Points just outside should not collide
        assertFalse(zeroSizeObstacle.collides(X + 1, Y), "Point to the right should not collide");
        assertFalse(zeroSizeObstacle.collides(X, Y + 1), "Point below should not collide");
        
        // Test an obstacle with negative width and height
        // Note: This is not a valid use case, but we should test how the code handles it
        Obstacle negativeObstacle = new Obstacle(X, Y, -10, -10);
        
        // The behavior with negative dimensions is not well-defined, but we can at least check that it doesn't crash
        boolean result = negativeObstacle.collides(X, Y);
        // We don't assert anything about the result, just that the method executes without throwing an exception
    }
}