package Interfaces.Animals;

import Enums.MapDirection;
import Tools.Vector2d;

public interface IAnimalMovement {
    Vector2d nextPosition(IGenes genotype, Vector2d actualPosition, MapDirection actDirection);
}
