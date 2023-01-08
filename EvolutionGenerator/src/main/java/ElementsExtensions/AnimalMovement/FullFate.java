package ElementsExtensions.AnimalMovement;

import Enums.MapDirection;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;
import javafx.util.Pair;

public class FullFate extends AnimalMovement {


    @Override
<<<<<<< Updated upstream
    public Vector2d nextPosition(IGenes genotype, Vector2d actualPosition, MapDirection actDirection) {
        return null;
=======
    public Pair<MapDirection, Vector2d> nextPosition (IGenes genotype, Vector2d actualPosition, MapDirection actDirection) {

        int turns = genotype.activateNextGene();
        MapDirection dir2 = MapDirection.NORTH;
        for(int i = 0; i < turns; i++)
            dir2 = dir2.getNext();
        actDirection = dir2;
        Pair<MapDirection, Vector2d> pair = new Pair<>(actDirection, new Vector2d(actualPosition.x + actDirection.toUnitVector().x,actualPosition.y+actDirection.toUnitVector().y));

        return pair;
>>>>>>> Stashed changes
    }
}
