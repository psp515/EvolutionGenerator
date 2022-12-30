package Interfaces.Animals;

import Enums.MapDirection;
import Tools.Vector2d;

public interface IAnimalMovement {
    Vector2d nextPosition(int nextGene, Vector2d actualPosition, MapDirection actDirection);
}
