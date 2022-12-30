package Enums;

import Elements.Animal;
import ElementsExtensions.Genes.NormalGenotype;
import ElementsExtensions.Genes.DiscouragedGenotype;
import Interfaces.Animals.IGenes;
import Interfaces.Others.GetClassRepresentationOneParam;

public enum GenesOptions implements GetClassRepresentationOneParam<IGenes,int[]> {
    DEFAULT,
    NORMAL_GENOTYPE,
    DISCOURAGED_GENOTYPE;

    @Override
    public String toString()
    {
        return switch (this) {
            case DEFAULT -> "Default";
            case NORMAL_GENOTYPE -> "Normal Genotype";
            case DISCOURAGED_GENOTYPE -> "Discouraged Genotype";
        };
    }

    @Override
    public IGenes getClassRepresentation(int[] genes){
        return switch (this) {
            case DEFAULT, NORMAL_GENOTYPE -> new NormalGenotype(genes);
            case DISCOURAGED_GENOTYPE -> new DiscouragedGenotype(genes);
        };
    }


    public IGenes getClassRepresentation(Animal p1, Animal p2, int len){
        return switch (this) {
            case DEFAULT, NORMAL_GENOTYPE -> new NormalGenotype(p1, p2, len);
            case DISCOURAGED_GENOTYPE -> new DiscouragedGenotype(p1, p2, len);
        };
    }

}
