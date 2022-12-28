package ElementsExtensions.Genes;

import Elements.Animal;
import Interfaces.Animals.IGenes;

public class Genes implements IGenes {
    private int[] genes;
    private int activatedGene;

    public Genes(int[] genes){
        this.genes = genes;
        activatedGene = 0;
        mutate();
    }

    @Override
    public int[] getGenes() {
        return genes;
    }
    @Override
    public void mutate() {
        this.genes = null;
    }

    @Override
    public IGenes generateGenes(Animal parent1, Animal parent2) {
        return null;
    }

    @Override
    public int getActiveGene() {
        int gen = this.activatedGene;
        return gen;
    }
}
