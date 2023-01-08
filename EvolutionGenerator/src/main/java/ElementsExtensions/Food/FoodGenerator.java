package ElementsExtensions.Food;

import Interfaces.Map.IFoodGenerator;
import Models.MapStatistics;
import Tools.SingleFoodField;

public abstract class FoodGenerator implements IFoodGenerator {

    public SingleFoodField[][] field;


    public int[][] deathlist;

    protected FoodGenerator(SingleFoodField[][] mapField, MapStatistics statistics) {
        this.field = mapField;
        this.deathlist = statistics.animalDeathsOnField;

    }

}
