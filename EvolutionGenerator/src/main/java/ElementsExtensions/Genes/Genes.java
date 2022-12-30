package ElementsExtensions.Genes;

import Elements.Animal;
import Interfaces.Animals.IGenes;

import java.util.concurrent.ThreadLocalRandom;

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

    public Genes(Animal parent1, Animal parent2, int genLength){
        int part1 = (genLength*parent1.getEnergy())/(parent1.getEnergy()+parent2.getEnergy());
        int side1 = ThreadLocalRandom.current().nextInt(2);
        this.genLength = parent1._genotype.getGenLength();
        int[] childgenes = new int[genLength];

        for(int i=0; i<part1; i++) {
            childgenes[i] = side1*parent1._genotype.getGenes()[i] + (1-side1)*parent2._genotype.getGenes()[i];
        }
        for(int i=part1; i<genLength; i++){
            childgenes[i] = (1-side1)*parent1._genotype.getGenes()[i] + side1*parent2._genotype.getGenes()[i];
        }

        this.genes = childgenes;
        mutate();
    }

    @Override
    public int[] getGenes() { return genes; }
    @Override
    public int getActiveGene() { return this.activatedGene; }
    public int getGenLength(){ return this.genLength; }
    @Override
    abstract public void mutate();
}
