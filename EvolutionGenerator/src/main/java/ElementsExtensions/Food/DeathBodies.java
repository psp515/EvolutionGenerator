package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IFoodGrowing;
import Interfaces.Map.IMap;
import Models.MapField;

public class DeathBodies extends FoodGrowing {


    public DeathBodies(IMap map) {
        super(map);
    }

    @Override
    public Food growFood() {

        return null;
    }

}
