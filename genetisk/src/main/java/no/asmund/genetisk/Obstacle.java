package no.asmund.genetisk;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Obstacle {
    final int x;
    final int y;
    final int width;
    final int height;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collides(float px, float py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, width, height);
    }
}
