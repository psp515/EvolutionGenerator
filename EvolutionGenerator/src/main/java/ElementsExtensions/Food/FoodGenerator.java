package ElementsExtensions.Food;

import Interfaces.Map.IFoodGenerator;
import Tools.SingleFoodField;

public abstract class FoodGenerator implements IFoodGenerator {

    public SingleFoodField[][] field;

    protected FoodGenerator(SingleFoodField[][] mapFields) {
        this.field = mapFields;

    }

}
