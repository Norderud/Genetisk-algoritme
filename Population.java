package smartrocket;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Åsmund
 */
public class Population {

    Rocket[] population;
    ArrayList<Rocket> matingPool;
    int generations;
    int popsize;
    float mutationRate;

    float maxfit;

    public Population(float m, int num) {
        mutationRate = m;
        popsize = num;
        population = new Rocket[num];
        matingPool = new ArrayList<Rocket>();
        generations = 0;

        maxfit = 0;

        for (int i = 0; i < popsize; i++) {
            population[i] = new Rocket(new DNA());
        }
    }

    public void live() {
        for (int i = 0; i < popsize; i++) {
            population[i].run();
        }
    }

    void fitness() {
        int max = 0;
        for (int i = 0; i < popsize; i++) {
            population[i].fitness();
            if (population[i].fitness > maxfit) {
                maxfit = population[i].fitness;
                max = i;
            }
        }
        System.out.println(population[max].fitness);
        //Normaliser
        for (int i = 0; i < popsize; i++) {
            population[i].fitness /= maxfit;
        }

    }

    void selection() {
        matingPool.clear();
        for (int i = 0; i < popsize; i++) {
            int n = (int)(population[i].fitness*100);
            for (int j = 0; j < n; j++) {
                matingPool.add(population[i]);
            }
        }
    }

    void reproduction() {
        Random rand = new Random();
        for (int i = 0; i < popsize; i++) {
            // Velger to foreldre
            int m = rand.nextInt(matingPool.size());
            int d = rand.nextInt(matingPool.size());
            Rocket mom = matingPool.get(m);
            Rocket dad = matingPool.get(d);
            // Henter genene
            DNA momgenes = mom.dna;
            DNA dadgenes = dad.dna;
            // Parrer moren og faren
            DNA child = momgenes.crossover(dadgenes);
            child.mutation();
            population[i] = new Rocket(child);
        }
    }
}
