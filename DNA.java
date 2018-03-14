package smartrocket;

import java.util.Random;

/**
 *
 * @author Åsmund
 */
public class DNA {

    float genes[];
    Random rand = new Random();

    public DNA() {
        genes = new float[SmartRocketApp.lifetime];
        for (int i = 0; i < genes.length; i++) {
            int angle = rand.nextInt(20) - 10;
            genes[i] = angle;
        }
    }
    public DNA(float newgenes[]){
        genes = newgenes;
    }

    // Lager en ny DNA-sekvens med to forskjellige DNA'er.
    DNA crossover(DNA partner) {
        float child[] = new float[genes.length];
        // Velger et midtpunkt å dele på.
        int crossover = rand.nextInt(genes.length);

        for (int i = 0; i < genes.length; i++) {
            if (i < crossover) {
                child[i] = genes[i];
            } else{
                child[i] = partner.genes[i];
            }
        }
        DNA newgenes = new DNA(child);
        return newgenes;
    }
    void mutation(){
        for (int i = 0; i < genes.length; i++) {
            int r = rand.nextInt(1000);
            if(r < 1){
                genes[i] = rand.nextInt(20) - 10;
            }
        }
    }

}
