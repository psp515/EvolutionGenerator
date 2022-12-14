package Models;

import Elements.Animal;
import Elements.Food;
import Tools.Vector2d;

import java.io.File;

public class MapStatistics
{


    public MapStatistics(int width, int height)
    {
        animalDeathsOnField = new int[width][height];
        foodGrowOnField = new int[width][height];
    }

    public int animalsOnMap;
    public int foodOnMap;

    public int placesFreeFromAnimalCount;
    public String mostPoupularGenes;
    public double averageEnergy;
    public double averageLiveLength;

    public int dayDeaths;
    public int dayBorns;

    public final int[][]  animalDeathsOnField;
    public final int[][] foodGrowOnField;
    // public File csvfile = new File("src/main/resources/csvFile.csv");


    public void safeDeadAnimal(Animal animal)
    {
        Vector2d position = animal.getPosition();

        animalDeathsOnField[position.x][position.y] += 1;
    }

    public void safeFoodGrow(Food food)
    {
        Vector2d position = food.getPosition();
        foodGrowOnField[position.x][position.y] += 1;
    }

}
