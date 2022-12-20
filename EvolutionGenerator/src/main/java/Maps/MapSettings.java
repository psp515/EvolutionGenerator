package Maps;

import Enums.FoodGrowingOptions;
import Enums.MovementsOptions;
import Enums.MutationsOptions;

public class MapSettings
{
    public int width;
    public int height;

    public int FoodEnergy;
    public int copulationMinimalEnergy;
    public int copulationCostEnregy;

    public int startingFood;
    public int dailyFoodGrow;
    public int startingAnimals;
    public int animalStartingEnergy;

    public FoodGrowingOptions growingOptions;
    public MovementsOptions movementsOptions;
    public MutationsOptions mutationsOptions;

    public int mutationsNumber;
    public int gensLength;

}
