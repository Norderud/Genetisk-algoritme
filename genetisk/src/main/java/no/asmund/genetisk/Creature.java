package no.asmund.genetisk;

import javafx.scene.paint.Color;

import static java.lang.Math.sqrt;

public class Creature {
    float x, y;
    float startX = 400;
    float startY = 300;
    float angle;
    DNA dna;
    int geneCounter;
    Color color;
    float recordDist;
    float fitness;
    int d; //diameter
    boolean completed;

    public Creature(DNA dna) {
        this.dna = dna;
        geneCounter = 0;
        x = startX;
        y = startY;

        d = 5;
        float r = ((dna.genes[10] * dna.genes[10]) / 100);
        float g = ((dna.genes[20] * dna.genes[20]) / 100);
        float b = ((dna.genes[30] * dna.genes[30]) / 100);
        color = new Color(r, g, b, 1);
        angle = (float) (dna.genes[10] * dna.genes[10] * 3.6);
    }

    void run() {
        if (!completed) {
            update();
            geneCounter++;
        } else {
            System.out.println("ferdig");
        }

    }

    void fitness() {
        float x2 = StartApp.targetX;
        float y2 = StartApp.targetY;
        recordDist = (float) sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));
        fitness = 1 / recordDist;
        if (recordDist < 50) {
            completed = true;
            fitness *= 10;
        }
        geneCounter = 0;
    }

    void update() {
        x = x + (float) ((Math.cos(Math.toRadians(angle)) * 2));
        y = y + (float) ((Math.sin(Math.toRadians(angle)) * 2));
        angle += dna.genes[geneCounter];
    }
}
