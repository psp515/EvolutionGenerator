package Interfaces.Map;


import Elements.Food;

// wariant wyrastania
public interface IFoodGenerator {
    Food growFood(IMap map, int day);
}
