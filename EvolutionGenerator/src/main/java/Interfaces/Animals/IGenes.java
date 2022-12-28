package Interfaces.Animals;

import Elements.Animal;

public interface IGenes {

    int[] getGenes();

    void mutate();

    IGenes generateGenes(Animal parent1, Animal parent2);

    int getActiveGene();
}
