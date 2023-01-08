package Interfaces.Animals;

public interface IGenes {

    int[] getGenes();

    void mutate();

    int getActiveGene();
    int getGenLength();
    int skipToRandomGene();
    int activateNextGene();
}
