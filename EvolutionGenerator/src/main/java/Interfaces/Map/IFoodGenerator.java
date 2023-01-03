package Interfaces.Map;


import Elements.Food;
import Tools.Vector2d;

// wariant wyrastania
public interface IFoodGenerator {
    Food growFood(Vector2d startpoint, Vector2d endpoint, IMap map, int day);
}
