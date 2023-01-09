package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Models.MapStatistics;
import Tools.SingleFoodField;
import Tools.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Equators extends FoodGenerator {
    public Equators(SingleFoodField[][] mapField) {
        super(mapField);
    }

    @Override
    public Food growFood(IMap map, int day) {

        int first_bound = (int) (map.getMapSettings().height * 0.2);

        int second_bound = (int) (map.getMapSettings().height * 0.8);

        if(ThreadLocalRandom.current().nextInt(5) == 4)
        {
            if(ThreadLocalRandom.current().nextInt(2) == 1)
            {

                for(int i = 0; i < map.getMapSettings().width; i++)
                    for(int j = 0; j < first_bound;j++)
                    {
                        SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                        if(!possibleField.containsFood())
                            return new Food(map,new Vector2d(i, j),day);
                    }

                for(int i = 0; i < map.getMapSettings().width; i++)
                    for(int j = second_bound; j < map.getMapSettings().height; j++)
                    {
                        SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                        if(!possibleField.containsFood())
                            return new Food(map, new Vector2d(i, j),day);
                    }

                for(int i = 0; i < map.getMapSettings().width; i++)
                    for(int j = first_bound; j < second_bound;j++)
                    {
                        SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                        if(!possibleField.containsFood())
                            return new Food(map, new Vector2d(i, j),day);
                    }
            }
            else {
                for(int i = 0; i < map.getMapSettings().width; i++)
                    for(int j = second_bound; j < map.getMapSettings().height; j++)
                    {
                        SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                        if(!possibleField.containsFood())
                            return new Food(map, new Vector2d(i, j),day);
                    }

                for(int i = 0; i < map.getMapSettings().width; i++)
                    for(int j = 0; j < first_bound;j++)
                    {
                        SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                        if(!possibleField.containsFood())
                            return new Food(map,new Vector2d(i, j),day);
                    }

                for(int i = 0; i < map.getMapSettings().width; i++)
                    for(int j = first_bound; j < second_bound;j++)
                    {
                        SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                        if(!possibleField.containsFood())
                            return new Food(map, new Vector2d(i, j),day);
                    }

            }
        }
        else
        {
            for(int i = 0; i < map.getMapSettings().width; i++)
                for(int j = first_bound; j < second_bound;j++)
                {
                    SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                    if(!possibleField.containsFood())
                        return new Food(map, new Vector2d(i, j),day);
                }

            for(int i = 0; i < map.getMapSettings().width; i++)
                for(int j = second_bound; j < map.getMapSettings().height; j++)
                {
                    SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                    if(!possibleField.containsFood())
                        return new Food(map, new Vector2d(i, j),day);
                }

            for(int i = 0; i < map.getMapSettings().width; i++)
                for(int j = 0; j < first_bound;j++)
                {
                    SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                    if(!possibleField.containsFood())
                        return new Food(map,new Vector2d(i, j),day);
                }
        }

        return null;
    }

}