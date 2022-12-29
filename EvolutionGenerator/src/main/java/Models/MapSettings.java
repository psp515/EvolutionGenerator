package Models;

import Enums.FoodGrowOptions;
import Enums.AnimalMovementOptions;
import Enums.GenesOptions;

public class MapSettings {

    public int width;
    public int height;


    public int moveEnergy;
    public int energyFromFood;
    public int copulationMinimalEnergy;
    public int copulationCostEnregy;

    public FoodGrowOptions growingOptions;
    public AnimalMovementOptions movementsOptions;
    public GenesOptions mutationsOptions;

    public int gensLength;


}