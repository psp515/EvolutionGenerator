package Models;

import Enums.FoodGrowOptions;
import Enums.MapOptions;
import Enums.AnimalMovementOptions;
import Enums.GenesOptions;

public class SimulationSettings implements Cloneable
{
    // 20 - 200
    public int width;

    // 20 - 200
    public int height;

    // 1-10
    public int moveEnergy;

    // 1 - 100
    public int energyFromFood;

    // 1 - 100 i > od copulationCostEnregy
    public int copulationMinimalEnergy;

    // 1 - 100
    public int copulationCostEnregy;

    // 1 - 100
    public int startingEnregy;


    // 1 - 1000
    public int maxEnregy;

    // 1 - Liczba pol mapy
    public int startingFood;

    // 1 - Liczba pol mapy
    public int dailyFoodGrow;

    // 1 - Liczba pol mapy
    public int startingAnimals;


    public FoodGrowOptions growingOptions;
    public AnimalMovementOptions movementsOptions;
    public GenesOptions genesOptions;
    public MapOptions mapOption;

    // 1-32
    public int gensLength;
    
    public boolean saveToCsv;

    public MapSettings generateMapSettings()
    {
        MapSettings mapSettings = new MapSettings();

        mapSettings.width = width;
        mapSettings.height = height;

        mapSettings.maxEnergy = maxEnregy;
        mapSettings.moveCost = moveEnergy;
        mapSettings.energyFromFood = energyFromFood;
        mapSettings.copulationMinimalEnergy = copulationMinimalEnergy;
        mapSettings.copulationCostEnregy = copulationCostEnregy;

        mapSettings.growingOptions = growingOptions;
        mapSettings.movementsOptions = movementsOptions;
        mapSettings.geneOptions = genesOptions;

        mapSettings.gensLength = gensLength;

        return mapSettings;
    }

    @Override
    public SimulationSettings clone() {
        try {
            return (SimulationSettings) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
