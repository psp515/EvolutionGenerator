package Models;

import Enums.FoodGrowingOptions;
import Enums.MovementsOptions;
import Enums.MutationsOptions;

public class SimulationSettings
{
    public int width;
    public int height;

    public int moveEnergy;
    public int energyFromFood;
    public int copulationMinimalEnergy;
    public int copulationCostEnregy;

    public int startingFood;
    public int dailyFoodGrow;

    public int startingAnimals;
    public int startingAnimalsEnergy;

    public FoodGrowingOptions growingOptions;
    public MovementsOptions movementsOptions;

    public MutationsOptions mutationsOptions;

    public int gensLength;

    public boolean saveToCsv;

    public MapSettings generateMapSettings()
    {
        MapSettings mapSettings = new MapSettings();

        mapSettings.width = width;
        mapSettings.height = height;

        mapSettings.moveEnergy = moveEnergy;
        mapSettings.energyFromFood = energyFromFood;
        mapSettings.copulationMinimalEnergy = copulationMinimalEnergy;
        mapSettings.copulationCostEnregy = copulationCostEnregy;

        mapSettings.growingOptions = growingOptions;
        mapSettings.movementsOptions = movementsOptions;
        mapSettings.mutationsOptions = mutationsOptions;

        mapSettings.gensLength = gensLength;

        return mapSettings;
    }
}
