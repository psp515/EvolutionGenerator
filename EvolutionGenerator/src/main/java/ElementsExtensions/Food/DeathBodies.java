package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Models.MapStatistics;
import Tools.SingleFoodField;

public class DeathBodies extends FoodGenerator {


    public DeathBodies(SingleFoodField[][] mapField, MapStatistics statistics) {
        super(mapField);
    }

    @Override
    public Food growFood() {

        return null;
    }

}
