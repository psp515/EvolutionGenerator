package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Tools.SingleFoodField;
import Tools.Vector2d;

import static java.lang.System.out;

public class Randomer extends FoodGenerator {

    public Randomer(SingleFoodField[][] mapFields) {
        super(mapFields);
    }

    @Override
    public Food growFood(IMap map, int day) {

        for(int i = 0; i < map.getMapSettings().width; i++)
        {
            for(int j = 0; j < map.getMapSettings().width; j++)
            {
                if(!field[i][j].containsFood())
                {
                    var food = new Food(map, new Vector2d(i,j), day);
                    return food;
                }
            }
        }

        return null;
    }
}
