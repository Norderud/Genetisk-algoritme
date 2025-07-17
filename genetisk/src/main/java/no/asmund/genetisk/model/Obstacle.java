/**
 * Represents an obstacle in the genetic algorithm simulation.
 * This class encapsulates a rectangular obstacle that creatures can collide with.
 */
package no.asmund.genetisk.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Obstacle {
    /**
     * The x coordinate of the top-left corner of the obstacle.
     */
    private final int x;
    
    /**
     * The y coordinate of the top-left corner of the obstacle.
     */
    private final int y;
    
    /**
     * The width of the obstacle.
     */
    private final int width;
    
    /**
     * The height of the obstacle.
     */
    private final int height;
    
    /**
     * The color of the obstacle for visualization.
     */
    private final Color color;

    /**
     * Creates a new obstacle with the specified position, dimensions, and default color (gray).
     * 
     * @param x The x coordinate of the top-left corner
     * @param y The y coordinate of the top-left corner
     * @param width The width of the obstacle
     * @param height The height of the obstacle
     */
    public Obstacle(int x, int y, int width, int height) {
        this(x, y, width, height, Color.GRAY);
    }
    
    /**
     * Creates a new obstacle with the specified position, dimensions, and color.
     * 
     * @param x The x coordinate of the top-left corner
     * @param y The y coordinate of the top-left corner
     * @param width The width of the obstacle
     * @param height The height of the obstacle
     * @param color The color of the obstacle
     */
    public Obstacle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Checks if a point collides with this obstacle.
     * 
     * @param px The x coordinate of the point
     * @param py The y coordinate of the point
     * @return true if the point collides with the obstacle, false otherwise
     */
    public boolean collides(float px, float py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    /**
     * Draws the obstacle on the specified graphics context.
     * 
     * @param gc The graphics context to draw on
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, width, height);
    }
    
    /**
     * Gets the x coordinate of the obstacle.
     * 
     * @return The x coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y coordinate of the obstacle.
     * 
     * @return The y coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets the width of the obstacle.
     * 
     * @return The width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gets the height of the obstacle.
     * 
     * @return The height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Gets the color of the obstacle.
     * 
     * @return The color
     */
    public Color getColor() {
        return color;
    }
}