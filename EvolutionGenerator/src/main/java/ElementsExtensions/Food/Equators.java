package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Tools.SingleFoodField;
import Tools.Vector2d;
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
                int k = 0;
                while (k < 10){

                    int i = ThreadLocalRandom.current().nextInt(map.getMapSettings().width);
                    int j = ThreadLocalRandom.current().nextInt(first_bound);

                    SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                    if(!possibleField.containsFood())
                        return new Food(map, new Vector2d(i, j),day);

                    k += 1;
                }

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

                int k = 0;

                while (k < 10) {

                    int i = ThreadLocalRandom.current().nextInt(map.getMapSettings().width);
                    int j = ThreadLocalRandom.current().nextInt(second_bound, map.getMapSettings().height);

                    SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                    if(!possibleField.containsFood())
                        return new Food(map, new Vector2d(i, j),day);

                    k += 1;
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
            int k = 0;

            while (k < 10) {

                int i = ThreadLocalRandom.current().nextInt(map.getMapSettings().width);
                int j = ThreadLocalRandom.current().nextInt(first_bound, second_bound);

                SingleFoodField possibleField = (SingleFoodField) fields[i][j];
                if(!possibleField.containsFood())
                    return new Food(map, new Vector2d(i, j),day);

                k += 1;
            }


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