package Interfaces.Animals;

import Elements.Animal;

public interface IGenes {

    int[] getGenes();

    void mutate();

    int[] generateGenes(Animal parent1, Animal parent2, int genLength);

    int getActiveGene();
}
