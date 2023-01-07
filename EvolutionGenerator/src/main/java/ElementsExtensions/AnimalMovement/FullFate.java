package ElementsExtensions.AnimalMovement;

import Enums.MapDirection;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;

public class FullFate extends AnimalMovement {


    @Override
    public Vector2d nextPosition(IGenes genotype, Vector2d actualPosition, MapDirection actDirection) {

        int turns = genotype.activateNextGene();
        for(int i = 0; i < turns; i++)
            actDirection = actDirection.getNext();

        return new Vector2d(actualPosition.x + actDirection.toUnitVector().x,actualPosition.y+actDirection.toUnitVector().y);
    }
}
