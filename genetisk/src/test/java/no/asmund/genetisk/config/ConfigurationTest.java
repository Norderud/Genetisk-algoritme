/**
 * Tests for the Configuration class.
 */
package no.asmund.genetisk.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {
    
    @Test
    void testDefaultConfiguration() {
        Configuration config = new Configuration();
        
        // Check that the default values are set correctly
        assertEquals(1200, config.getWidth(), "Default width should be 1200");
        assertEquals(600, config.getHeight(), "Default height should be 600");
        assertEquals(1000, config.getTargetX(), "Default targetX should be 1000");
        assertEquals(300, config.getTargetY(), "Default targetY should be 300");
        assertEquals(400, config.getStartX(), "Default startX should be 400");
        assertEquals(300, config.getStartY(), "Default startY should be 300");
        assertEquals(300, config.getLifetime(), "Default lifetime should be 300");
        assertEquals(100, config.getPopulationSize(), "Default populationSize should be 100");
        assertEquals(0.001f, config.getMutationRate(), "Default mutationRate should be 0.001");
    }
    
    @Test
    void testCustomConfiguration() {
        int width = 800;
        int height = 400;
        int targetX = 700;
        int targetY = 200;
        float startX = 100;
        float startY = 100;
        int lifetime = 200;
        int populationSize = 50;
        float mutationRate = 0.01f;
        
        Configuration config = new Configuration(width, height, targetX, targetY, startX, startY,
                                               lifetime, populationSize, mutationRate);
        
        // Check that the custom values are set correctly
        assertEquals(width, config.getWidth(), "Width should match");
        assertEquals(height, config.getHeight(), "Height should match");
        assertEquals(targetX, config.getTargetX(), "TargetX should match");
        assertEquals(targetY, config.getTargetY(), "TargetY should match");
        assertEquals(startX, config.getStartX(), "StartX should match");
        assertEquals(startY, config.getStartY(), "StartY should match");
        assertEquals(lifetime, config.getLifetime(), "Lifetime should match");
        assertEquals(populationSize, config.getPopulationSize(), "PopulationSize should match");
        assertEquals(mutationRate, config.getMutationRate(), "MutationRate should match");
    }
    
    @Test
    void testBuilder() {
        Configuration config = Configuration.builder()
                .width(800)
                .height(400)
                .targetX(700)
                .targetY(200)
                .startX(100)
                .startY(100)
                .lifetime(200)
                .populationSize(50)
                .mutationRate(0.01f)
                .build();
        
        // Check that the builder sets values correctly
        assertEquals(800, config.getWidth(), "Width should match");
        assertEquals(400, config.getHeight(), "Height should match");
        assertEquals(700, config.getTargetX(), "TargetX should match");
        assertEquals(200, config.getTargetY(), "TargetY should match");
        assertEquals(100, config.getStartX(), "StartX should match");
        assertEquals(100, config.getStartY(), "StartY should match");
        assertEquals(200, config.getLifetime(), "Lifetime should match");
        assertEquals(50, config.getPopulationSize(), "PopulationSize should match");
        assertEquals(0.01f, config.getMutationRate(), "MutationRate should match");
    }
    
    @Test
    void testBuilderWithDefaultValues() {
        // Create a builder but don't set any values
        Configuration config = Configuration.builder().build();
        
        // Check that the default values are used
        assertEquals(1200, config.getWidth(), "Default width should be 1200");
        assertEquals(600, config.getHeight(), "Default height should be 600");
        assertEquals(1000, config.getTargetX(), "Default targetX should be 1000");
        assertEquals(300, config.getTargetY(), "Default targetY should be 300");
        assertEquals(400, config.getStartX(), "Default startX should be 400");
        assertEquals(300, config.getStartY(), "Default startY should be 300");
        assertEquals(300, config.getLifetime(), "Default lifetime should be 300");
        assertEquals(100, config.getPopulationSize(), "Default populationSize should be 100");
        assertEquals(0.001f, config.getMutationRate(), "Default mutationRate should be 0.001");
    }
    
    @Test
    void testBuilderWithPartialValues() {
        // Create a builder and set only some values
        Configuration config = Configuration.builder()
                .width(800)
                .height(400)
                .build();
        
        // Check that the specified values are set and the rest are defaults
        assertEquals(800, config.getWidth(), "Width should match");
        assertEquals(400, config.getHeight(), "Height should match");
        assertEquals(1000, config.getTargetX(), "Default targetX should be 1000");
        assertEquals(300, config.getTargetY(), "Default targetY should be 300");
        assertEquals(400, config.getStartX(), "Default startX should be 400");
        assertEquals(300, config.getStartY(), "Default startY should be 300");
        assertEquals(300, config.getLifetime(), "Default lifetime should be 300");
        assertEquals(100, config.getPopulationSize(), "Default populationSize should be 100");
        assertEquals(0.001f, config.getMutationRate(), "Default mutationRate should be 0.001");
    }
}