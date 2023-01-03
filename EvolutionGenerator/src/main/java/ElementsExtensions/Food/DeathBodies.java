package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Models.MapStatistics;
import Tools.SingleFoodField;
import Tools.Vector2d;

public class DeathBodies extends FoodGenerator {


    public DeathBodies(SingleFoodField[][] mapField, MapStatistics statistics) {
        super(mapField);
    }

    @Override
    public Food growFood(Vector2d startpoint, Vector2d endpoint, IMap map, int day) {

        return null;
    }

}
