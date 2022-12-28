package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Others.GetClassRepresentation;

public enum FoodGrowingOptions implements GetClassRepresentation<IFoodGenerator, IMap> {
    Equators,
    DeathBodies;

    @Override
    public IFoodGenerator getClassRepresentation(IMap map){
        return switch (this) {
            case Equators -> new Equators(map);
            case DeathBodies -> new DeathBodies(map);
        };
    }

    //TODO : to string -> nazwy dla view
}
