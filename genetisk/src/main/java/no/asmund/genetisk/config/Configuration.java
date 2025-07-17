/**
 * Configuration class for the genetic algorithm simulation.
 * This class centralizes all configuration parameters for the simulation.
 */
package no.asmund.genetisk.config;

public class Configuration {
    
    /**
     * The width of the simulation environment.
     */
    private final int width;
    
    /**
     * The height of the simulation environment.
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
     * The starting x position for creatures.
     */
    private final float startX;
    
    /**
     * The starting y position for creatures.
     */
    private final float startY;
    
    /**
     * The number of steps in a generation.
     */
    private final int lifetime;
    
    /**
     * The size of the population.
     */
    private final int populationSize;
    
    /**
     * The mutation rate for the genetic algorithm.
     */
    private final float mutationRate;
    
    /**
     * Creates a new Configuration with default values.
     */
    public Configuration() {
        this.width = 1200;
        this.height = 600;
        this.targetX = 1000;
        this.targetY = height / 2;
        this.startX = 400;
        this.startY = 300;
        this.lifetime = 300;
        this.populationSize = 100;
        this.mutationRate = 0.001f;
    }
    
    /**
     * Creates a new Configuration with the specified values.
     * 
     * @param width The width of the simulation environment
     * @param height The height of the simulation environment
     * @param targetX The x coordinate of the target
     * @param targetY The y coordinate of the target
     * @param startX The starting x position for creatures
     * @param startY The starting y position for creatures
     * @param lifetime The number of steps in a generation
     * @param populationSize The size of the population
     * @param mutationRate The mutation rate for the genetic algorithm
     */
    public Configuration(int width, int height, int targetX, int targetY, float startX, float startY,
                         int lifetime, int populationSize, float mutationRate) {
        this.width = width;
        this.height = height;
        this.targetX = targetX;
        this.targetY = targetY;
        this.startX = startX;
        this.startY = startY;
        this.lifetime = lifetime;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
    }
    
    /**
     * Gets the width of the simulation environment.
     * 
     * @return The width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gets the height of the simulation environment.
     * 
     * @return The height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Gets the x coordinate of the target.
     * 
     * @return The target x coordinate
     */
    public int getTargetX() {
        return targetX;
    }
    
    /**
     * Gets the y coordinate of the target.
     * 
     * @return The target y coordinate
     */
    public int getTargetY() {
        return targetY;
    }
    
    /**
     * Gets the starting x position for creatures.
     * 
     * @return The starting x position
     */
    public float getStartX() {
        return startX;
    }
    
    /**
     * Gets the starting y position for creatures.
     * 
     * @return The starting y position
     */
    public float getStartY() {
        return startY;
    }
    
    /**
     * Gets the number of steps in a generation.
     * 
     * @return The lifetime
     */
    public int getLifetime() {
        return lifetime;
    }
    
    /**
     * Gets the size of the population.
     * 
     * @return The population size
     */
    public int getPopulationSize() {
        return populationSize;
    }
    
    /**
     * Gets the mutation rate for the genetic algorithm.
     * 
     * @return The mutation rate
     */
    public float getMutationRate() {
        return mutationRate;
    }
    
    /**
     * Creates a builder for creating custom configurations.
     * 
     * @return A new builder
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Builder class for creating Configuration objects.
     */
    public static class Builder {
        private int width = 1200;
        private int height = 600;
        private int targetX = 1000;
        private int targetY = 300;
        private float startX = 400;
        private float startY = 300;
        private int lifetime = 300;
        private int populationSize = 100;
        private float mutationRate = 0.001f;
        
        /**
         * Sets the width of the simulation environment.
         * 
         * @param width The width
         * @return This builder
         */
        public Builder width(int width) {
            this.width = width;
            return this;
        }
        
        /**
         * Sets the height of the simulation environment.
         * 
         * @param height The height
         * @return This builder
         */
        public Builder height(int height) {
            this.height = height;
            return this;
        }
        
        /**
         * Sets the x coordinate of the target.
         * 
         * @param targetX The target x coordinate
         * @return This builder
         */
        public Builder targetX(int targetX) {
            this.targetX = targetX;
            return this;
        }
        
        /**
         * Sets the y coordinate of the target.
         * 
         * @param targetY The target y coordinate
         * @return This builder
         */
        public Builder targetY(int targetY) {
            this.targetY = targetY;
            return this;
        }
        
        /**
         * Sets the starting x position for creatures.
         * 
         * @param startX The starting x position
         * @return This builder
         */
        public Builder startX(float startX) {
            this.startX = startX;
            return this;
        }
        
        /**
         * Sets the starting y position for creatures.
         * 
         * @param startY The starting y position
         * @return This builder
         */
        public Builder startY(float startY) {
            this.startY = startY;
            return this;
        }
        
        /**
         * Sets the number of steps in a generation.
         * 
         * @param lifetime The lifetime
         * @return This builder
         */
        public Builder lifetime(int lifetime) {
            this.lifetime = lifetime;
            return this;
        }
        
        /**
         * Sets the size of the population.
         * 
         * @param populationSize The population size
         * @return This builder
         */
        public Builder populationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }
        
        /**
         * Sets the mutation rate for the genetic algorithm.
         * 
         * @param mutationRate The mutation rate
         * @return This builder
         */
        public Builder mutationRate(float mutationRate) {
            this.mutationRate = mutationRate;
            return this;
        }
        
        /**
         * Builds a new Configuration with the current values.
         * 
         * @return A new Configuration
         */
        public Configuration build() {
            return new Configuration(width, height, targetX, targetY, startX, startY,
                                    lifetime, populationSize, mutationRate);
        }
    }
}