package Interfaces.Animals;

import Elements.Animal;
import Tools.Vector2d;

public interface IAnimalMovement {
    Vector2d newPosition(Animal animal);
}
