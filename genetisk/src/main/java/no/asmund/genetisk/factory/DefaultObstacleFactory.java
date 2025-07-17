/**
 * Default implementation of the ObstacleFactory interface.
 * This class provides a default implementation for creating obstacles.
 */
package no.asmund.genetisk.factory;

import java.util.Random;
import no.asmund.genetisk.model.Obstacle;

public class DefaultObstacleFactory implements ObstacleFactory {

    /**
     * Random number generator for creating random obstacles.
     */
    private final Random random;

    /**
     * Creates a new DefaultObstacleFactory with a new random number generator.
     */
    public DefaultObstacleFactory() {
        this.random = new Random();
    }

    /**
     * Creates a new DefaultObstacleFactory with the specified random number generator.
     * 
     * @param random The random number generator to use
     */
    public DefaultObstacleFactory(Random random) {
        this.random = random;
    }

    @Override
    public Obstacle createRectangularObstacle(int x, int y, int width, int height) {
        return new Obstacle(x, y, width, height);
    }

    @Override
    public Obstacle createRandomObstacle(int minX, int minY, int maxX, int maxY, 
                                        int minWidth, int maxWidth, 
                                        int minHeight, int maxHeight) {
        int x = minX + random.nextInt(maxX - minX + 1);
        int y = minY + random.nextInt(maxY - minY + 1);
        int width = minWidth + random.nextInt(maxWidth - minWidth + 1);
        int height = minHeight + random.nextInt(maxHeight - minHeight + 1);

        // Ensure the obstacle is within bounds
        if (x + width > maxX) {
            width = maxX - x;
        }
        if (y + height > maxY) {
            height = maxY - y;
        }

        return createRectangularObstacle(x, y, width, height);
    }
}
