package ElementsExtensions.Genes;

import Elements.Animal;
import Interfaces.Animals.IGenes;

import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public abstract class Genes implements IGenes {
    private int[] genes;
    private int activatedGene;
    private int genLength;

    public Genes(int[] genes){
        this.genes = genes;
        activatedGene = 0;
        genLength = genes.length;
        mutate();
    }

    public Genes(Animal parent1, Animal parent2, int genLength) {
        int part1 = (genLength * parent1.getEnergy()) / (parent1.getEnergy() + parent2.getEnergy());
        int part2 = genLength - part1;
        int side1 = ThreadLocalRandom.current().nextInt(2);
        int side2 = 1 - side1;
        this.genLength = genLength;
        int[] childgenes = new int[genLength];

        for (int i = 0; i < (part1 * side2 + part2 * side1); i++) {
            childgenes[i] = side2 * parent1._genotype.getGenes()[i] + side1 * parent2._genotype.getGenes()[i];
        }
        for (int i = part1; i < (part1 * side1 + part2 * side2); i++) {
            childgenes[i] = side1 * parent1._genotype.getGenes()[i] + side2 * parent2._genotype.getGenes()[i];

            this.genes = childgenes;
            mutate();
        }
    }

    @Override
    public int[] getGenes() { return genes; }
    @Override
    public int getActiveGene() { return this.activatedGene; }
    public int getGenLength(){ return this.genLength; }
    @Override
    abstract public void mutate();
}
