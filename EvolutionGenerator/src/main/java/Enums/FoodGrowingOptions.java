package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.Map.IFoodGrowing;
import Interfaces.Map.IMap;
import Interfaces.Others.EnumClassRepresentationMap;

public enum FoodGrowingOptions implements EnumClassRepresentationMap<IFoodGrowing> {
    Equators,
    DeathBodies;

    @Override
    public IFoodGrowing getClassRepresentation(IMap map){
        return switch (this) {
            case Equators -> new Equators(map);
            case DeathBodies -> new DeathBodies(map);
        };
    }

}
