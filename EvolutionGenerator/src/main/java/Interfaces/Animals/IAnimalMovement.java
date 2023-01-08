package Interfaces.Animals;

import Enums.MapDirection;
import Tools.Vector2d;
import javafx.util.Pair;

public interface IAnimalMovement {
    Pair<MapDirection, Vector2d> nextPosition(IGenes genotype, Vector2d actualPosition, MapDirection actDirection);
}
