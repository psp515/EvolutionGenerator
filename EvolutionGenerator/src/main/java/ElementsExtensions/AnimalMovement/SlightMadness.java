package ElementsExtensions.AnimalMovement;

import Enums.MapDirection;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;

import java.util.concurrent.ThreadLocalRandom;

public class SlightMadness extends AnimalMovement {


    @Override
    public Vector2d nextPosition(IGenes genotype, Vector2d actualPosition, MapDirection actDirection)
    {
        int isRandom = ThreadLocalRandom.current().nextInt(5); // jak 1 to randomowy kolejny;
        int nextGene;
        if(isRandom==1)
            nextGene = genotype.skipToRandomGene();
        else
            nextGene = genotype.activateNextGene();

        for(int i = 0; i < nextGene; i++)
            actDirection = actDirection.getNext();

        return new Vector2d(actualPosition.x + actDirection.toUnitVector().x,actualPosition.y+actDirection.toUnitVector().y);
    }
}
