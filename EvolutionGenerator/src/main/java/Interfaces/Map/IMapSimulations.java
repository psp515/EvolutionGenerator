package Interfaces.Map;

import Tools.Vector2d;

public interface IMapSimulations
{
    void simulateDeaths();
    void moveAnimals();
    void simulateEating();
    void simulateBorns();
    //void growFood();

    void growFood(Vector2d startpoint, Vector2d endpoint);
}
