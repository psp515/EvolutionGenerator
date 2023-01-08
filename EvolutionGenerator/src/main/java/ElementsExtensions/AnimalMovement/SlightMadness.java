package ElementsExtensions.AnimalMovement;

import Enums.MapDirection;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;
import javafx.util.Pair;

public class SlightMadness extends AnimalMovement {


    @Override
    public Pair<MapDirection, Vector2d> nextPosition(IGenes genotype, Vector2d actualPosition, MapDirection actDirection)
    {
<<<<<<< Updated upstream
        return null;
=======
        int isRandom = ThreadLocalRandom.current().nextInt(5); // jak 1 to randomowy kolejny;
        int nextGene;
        if(isRandom==1)
            nextGene = genotype.skipToRandomGene();
        else
            nextGene = genotype.activateNextGene();

        for(int i = 0; i < nextGene; i++)
            actDirection = actDirection.getNext();

        Pair<MapDirection, Vector2d> pair = new Pair<>(actDirection, new Vector2d(actualPosition.x + actDirection.toUnitVector().x,actualPosition.y+actDirection.toUnitVector().y));

        return pair;
>>>>>>> Stashed changes
    }
}
