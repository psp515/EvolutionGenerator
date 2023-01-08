package ElementsExtensions.Food;

import Interfaces.Map.IFoodGenerator;
import Tools.SingleFoodField;

public abstract class FoodGenerator implements IFoodGenerator {

<<<<<<< Updated upstream
    public SingleFoodField[][] field;

    protected FoodGenerator(SingleFoodField[][] mapFields) {
        this.field = mapFields;
=======
    public int[][] deathlist;
    public SingleFoodField[][] field;

    protected FoodGenerator(SingleFoodField[][] mapField, MapStatistics statistics) {
        this.field = mapField;
        this.deathlist = statistics.animalDeathsOnField;
>>>>>>> Stashed changes

    }

}
