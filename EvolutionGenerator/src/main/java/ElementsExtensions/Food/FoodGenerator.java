package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Tools.IMapField;
import Tools.SingleFoodField;

public abstract class FoodGenerator implements IFoodGenerator {

    public SingleFoodField[][] field;

    protected FoodGenerator(SingleFoodField[][] mapField) {
        this.field = mapField;

    }

}
