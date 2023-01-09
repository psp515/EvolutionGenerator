package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Models.MapStatistics;
import Tools.SingleFoodField;
import Tools.Vector2d;
//import com.sun.javafx.scene.shape.ArcHelper;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DeathBodies extends FoodGenerator {

    public int[][] deathCounts;

    public DeathBodies(SingleFoodField[][] mapField, MapStatistics statistics) {
        super(mapField);
        this.deathCounts = statistics.animalDeathsOnField;
    }

    @Override
    public Food growFood(IMap map, int day) {

        HashMap<Vector2d, Integer> deathMap = new HashMap<>();

        for(int i = 0; i < map.getMapSettings().width; i++)
            for(int j = 0; j < map.getMapSettings().height;j++)
                deathMap.put(new Vector2d(i, j), deathCounts[i][j]);

        List<Map.Entry<Vector2d, Integer>> list = new ArrayList<>(deathMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        if(ThreadLocalRandom.current().nextInt(5) == 4)
        {
            for(int i = list.size() - 1; i >= 0; i--)
            {
                var position = list.get(i).getKey();
                SingleFoodField possibleField = (SingleFoodField) fields[position.x][position.y];
                if(!possibleField.containsFood())
                    return new Food(map,position.copy(),day);

            }
        }
        else
        {
            for (Map.Entry<Vector2d, Integer> vector2dIntegerEntry : list) {
                var position = vector2dIntegerEntry.getKey();
                SingleFoodField possibleField = (SingleFoodField) fields[position.x][position.y];
                if (!possibleField.containsFood())
                    return new Food(map, position.copy(), day);

            }
        }


        return null;
    }

}