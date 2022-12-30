package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Tools.SingleFoodField;

public class Equators extends FoodGenerator {


    public Equators(SingleFoodField[][] mapField) {
        super(mapField);
    }

    @Override
    public Food growFood() {
        return null;
    }

}
