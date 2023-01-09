package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Tools.SingleFoodField;
import Tools.Vector2d;

public class Randomer extends FoodGenerator {

    public Randomer(SingleFoodField[][] mapFields) {
        super(mapFields);
    }

    @Override
    public Food growFood(IMap map, int day) {

        for(int i = 0; i < map.getMapSettings().width; i++)
            for(int j = 0; j < map.getMapSettings().height; j++)
                if(!fields[i][j].containsFood())
                    return new Food(map, new Vector2d(i,j), day);

        return null;
    }
}
