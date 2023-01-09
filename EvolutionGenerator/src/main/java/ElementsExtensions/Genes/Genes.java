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
    }

    public Genes(Animal parent1, Animal parent2, int genLength) {
        int part1 = (genLength * parent1.getEnergy()) / (parent1.getEnergy() + parent2.getEnergy());
        int part2 = genLength - part1;
        this.genLength = genLength;

        if(ThreadLocalRandom.current().nextInt(2) %2 ==0)
            this.genes = createGenes(genLength, parent1, parent2, part1, part2);
        else
            this.genes = createGenes(genLength, parent2, parent1, part2, part1);

        mutate();

        int i = ThreadLocalRandom.current().nextInt(genLength);

        for(int j = 0; j < i; j++)
            this.activateNextGene();

    }

    private int[] createGenes(int len, Animal a, Animal b, int a_len, int b_len)
    {
        int[] genes = new int[len];

        int i = 0;

        for(int j = 0; j < a_len;j++)
        {
            genes[i] = a._genotype.getGenes()[i];
            i++;
        }

        for(int j = 0; j < b_len;j++)
        {
            genes[i] = b._genotype.getGenes()[i];
            i++;
        }

        return genes;
    }

    @Override
    public int[] getGenes() { return genes; }
    @Override
    public int getActiveGene() { return this.activatedGene; }
    public int getGenLength(){ return this.genLength; }
    public int activateNextGene(){
        this.activatedGene = genes[(this.activatedGene + 1) % this.genLength];
        return activatedGene;
    }
    public int skipToRandomGene(){
        int skipCount = ThreadLocalRandom.current().nextInt(this.genLength);
        for(int i=0;i<skipCount; i++)
            this.activateNextGene();
        return this.activatedGene;
    }
    @Override
    abstract public void mutate();
}
