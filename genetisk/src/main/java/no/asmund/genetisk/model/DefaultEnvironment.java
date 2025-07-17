/**
 * Default implementation of the Environment interface.
 * This class provides a default implementation of the environment for the genetic algorithm simulation.
 */
package no.asmund.genetisk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultEnvironment implements Environment {
    
    /**
     * The width of the environment.
     */
    private final int width;
    
    /**
     * The height of the environment.
     */
    private final int height;
    
    /**
     * The x coordinate of the target.
     */
    private final int targetX;
    
    /**
     * The y coordinate of the target.
     */
    private final int targetY;
    
    /**
     * The list of obstacles in the environment.
     */
    private final List<Obstacle> obstacles;
    
    /**
     * Creates a new DefaultEnvironment with the specified parameters.
     * 
     * @param width The width of the environment
     * @param height The height of the environment
     * @param targetX The x coordinate of the target
     * @param targetY The y coordinate of the target
     */
    public DefaultEnvironment(int width, int height, int targetX, int targetY) {
        this.width = width;
        this.height = height;
        this.targetX = targetX;
        this.targetY = targetY;
        this.obstacles = new ArrayList<>();
    }
    
    /**
     * Creates a new DefaultEnvironment with the specified parameters.
     * 
     * @param width The width of the environment
     * @param height The height of the environment
     * @param targetX The x coordinate of the target
     * @param targetY The y coordinate of the target
     * @param obstacles The list of obstacles in the environment
     */
    public DefaultEnvironment(int width, int height, int targetX, int targetY, List<Obstacle> obstacles) {
        this.width = width;
        this.height = height;
        this.targetX = targetX;
        this.targetY = targetY;
        this.obstacles = new ArrayList<>(obstacles);
    }
    
    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public int getTargetX() {
        return targetX;
    }
    
    @Override
    public int getTargetY() {
        return targetY;
    }
    
    @Override
    public List<Obstacle> getObstacles() {
        return Collections.unmodifiableList(obstacles);
    }
    
    @Override
    public boolean collidesWithObstacle(float x, float y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.collides(x, y)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Adds an obstacle to the environment.
     * 
     * @param obstacle The obstacle to add
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
    
    /**
     * Removes an obstacle from the environment.
     * 
     * @param obstacle The obstacle to remove
     * @return true if the obstacle was removed, false otherwise
     */
    public boolean removeObstacle(Obstacle obstacle) {
        return obstacles.remove(obstacle);
    }
    
    /**
     * Clears all obstacles from the environment.
     */
    public void clearObstacles() {
        obstacles.clear();
    }
}