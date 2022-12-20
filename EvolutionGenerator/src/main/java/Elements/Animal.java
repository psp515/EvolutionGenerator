package Elements;

import Interfaces.Animals.IGenes;
import Interfaces.Animals.IParent;

public class Animal extends MapElement implements IGenes, IParent {


    int energy;
    int[] genes;


    @Override
    public int[] getGenes() {
        return new int[0];
    }

    @Override
    public void setGenes(int[] newGenes) {

    }

    @Override
    public IParent copulate(IParent secondParent) {
        return null;
    }
}
