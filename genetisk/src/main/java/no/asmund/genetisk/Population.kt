package no.asmund.genetisk;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    Creature[] population;
    ArrayList<Creature> matingPool;
    int generations;
    int popSize;
    float mutationRate;
    float maxFit;

    public Population(float m, int num) {
        mutationRate = m;
        popSize = num;
        population = new Creature[num];
        matingPool = new ArrayList<>();
        generations = 0;

        maxFit = 0;

        for (int i = 0; i < popSize; i++) {
            population[i] = new Creature(new DNA());
        }
    }

    void fitness() {
        int max = 0;
        for (int i = 0; i < popSize; i++) {
            population[i].fitness();
            if (population[i].fitness > maxFit) {
                maxFit = population[i].fitness;
                max = i;
            }
        }
        System.out.println(population[max].fitness);
        //Normaliser
        for (int i = 0; i < popSize; i++) {
            population[i].fitness /= maxFit;
        }

    }

    void selection() {
        matingPool.clear();
        for (int i = 0; i < popSize; i++) {
            int n = (int) (population[i].fitness * 100);
            for (int j = 0; j < n; j++) {
                matingPool.add(population[i]);
            }
        }
    }

    void reproduction() {
        Random rand = new Random();
        for (int i = 0; i < popSize; i++) {
            // Velger to foreldre
            int m = rand.nextInt(matingPool.size());
            int d = rand.nextInt(matingPool.size());
            Creature mom = matingPool.get(m);
            Creature dad = matingPool.get(d);
            // Henter genene
            DNA momgenes = mom.dna;
            DNA dadgenes = dad.dna;
            // Parrer moren og faren
            DNA child = momgenes.crossover(dadgenes);
            child.mutation();
            population[i] = new Creature(child);
        }
    }
    /*    public void live() {
        for (int i = 0; i < popSize; i++) {
            population[i].run();
        }
    }*/
}
