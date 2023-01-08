package Interfaces.Map;


import Elements.Food;
import Tools.Vector2d;

// wariant wyrastania
public interface IFoodGenerator {
    Food growFood(IMap map, int day);
}
