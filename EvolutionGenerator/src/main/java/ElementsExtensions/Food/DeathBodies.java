package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Models.MapStatistics;

public class DeathBodies extends FoodGenerator {


    public DeathBodies(IMap map, MapStatistics statistics) {
        super(map);
    }

    @Override
    public Food growFood() {

        return null;
    }

}
