package ElementsExtensions.Food;

import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Tools.IMapField;
import Models.MapStatistics;
import Tools.SingleFoodField;

public abstract class FoodGenerator implements IFoodGenerator {

<<<<<<< Updated upstream
    protected FoodGenerator(SingleFoodField[][] mapField)
    {

=======
    public int[][] deathlist;
    public SingleFoodField[][] field;

    protected FoodGenerator(SingleFoodField[][] mapField, MapStatistics statistics) {
        this.field = mapField;
        this.deathlist = statistics.animalDeathsOnField;
>>>>>>> Stashed changes

    }

}
