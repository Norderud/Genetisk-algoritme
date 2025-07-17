/**
 * Interface for simulation environments in the genetic algorithm.
 * This interface defines the contract for environments where genetic entities evolve.
 */
package no.asmund.genetisk.model;

import java.util.List;

public interface Environment {

    /**
     * Gets the width of the environment.
     * 
     * @return The width
     */
    int getWidth();

    /**
     * Gets the height of the environment.
     * 
     * @return The height
     */
    int getHeight();

    /**
     * Gets the target X coordinate.
     * 
     * @return The target X coordinate
     */
    int getTargetX();

    /**
     * Gets the target Y coordinate.
     * 
     * @return The target Y coordinate
     */
    int getTargetY();

    /**
     * Gets the list of obstacles in the environment.
     * 
     * @return The list of obstacles
     */
    List<Obstacle> getObstacles();

    /**
     * Checks if a point collides with any obstacle in the environment.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @return true if the point collides with an obstacle, false otherwise
     */
    boolean collidesWithObstacle(float x, float y);
}
