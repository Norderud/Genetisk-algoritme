/**
 * Interface for factories that create obstacles.
 * This interface defines the contract for creating different types of obstacles.
 */
package no.asmund.genetisk.factory;

import no.asmund.genetisk.model.Obstacle;

public interface ObstacleFactory {

    /**
     * Creates a new rectangular obstacle.
     * 
     * @param x The x coordinate of the top-left corner
     * @param y The y coordinate of the top-left corner
     * @param width The width of the obstacle
     * @param height The height of the obstacle
     * @return A new rectangular obstacle
     */
    Obstacle createRectangularObstacle(int x, int y, int width, int height);

    /**
     * Creates a new obstacle at a random position within the specified bounds.
     * 
     * @param minX The minimum x coordinate
     * @param minY The minimum y coordinate
     * @param maxX The maximum x coordinate
     * @param maxY The maximum y coordinate
     * @param minWidth The minimum width of the obstacle
     * @param maxWidth The maximum width of the obstacle
     * @param minHeight The minimum height of the obstacle
     * @param maxHeight The maximum height of the obstacle
     * @return A new obstacle at a random position
     */
    Obstacle createRandomObstacle(int minX, int minY, int maxX, int maxY, 
                                 int minWidth, int maxWidth, 
                                 int minHeight, int maxHeight);
}
