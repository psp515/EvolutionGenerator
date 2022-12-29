package Interfaces.Animals;

import Tools.Vector2d;

//Wariant poruszania
public interface IAnimalMovement {
    Vector2d nextPosition(int nextGene, Vector2d actualPosition);
}
