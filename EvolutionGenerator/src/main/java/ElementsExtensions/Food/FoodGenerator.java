package ElementsExtensions.Food;

import Interfaces.Map.IFoodGenerator;
import Interfaces.Tools.IFoodField;

public abstract class FoodGenerator implements IFoodGenerator {

    protected IFoodField[][] fields;


    public int[][] deathlist;

    protected FoodGenerator(IFoodField[][] mapField) {
        this.fields = mapField;

    }

}
