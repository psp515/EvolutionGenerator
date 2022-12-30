package Models;

import Interfaces.Animals.IGenes;

public class MapStatistics
{
    public MapStatistics(int width, int height)
    {
        animalDeathsOnField = new int[width][height];
        plantGrowsOnField = new int[width][height];
    }

    public int animalsOnMap;
    public int foodOnMap;

    public int FreePlacesCount;

    public IGenes MostPoupularGenotype;

    public double AverageEnergy;

    public double AverageLiveLength;

    public int dayDeaths;

    public int dayBorns;

    public int[][]  animalDeathsOnField;
    public int[][]  plantGrowsOnField;
}
