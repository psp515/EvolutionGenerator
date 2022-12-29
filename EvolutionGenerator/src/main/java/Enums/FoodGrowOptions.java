package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Others.GetClassRepresentationTwoParam;
import Models.MapStatistics;

public enum FoodGrowOptions implements GetClassRepresentationTwoParam<IFoodGenerator, IMap,MapStatistics> {
    DEFAULT,
    EQUATORS,
    DEATH_BODIES;

    @Override
    public IFoodGenerator getClassRepresentation(IMap map, MapStatistics statistics){
        return switch (this) {
            case DEFAULT, EQUATORS -> new Equators(map);
            case DEATH_BODIES -> new DeathBodies(map, statistics);
        };
    }

    public String toString()
    {
        return switch (this) {
            case DEFAULT -> "Default";
            case EQUATORS -> "Equators";
            case DEATH_BODIES -> "Death Bodies";
        };
    }
}
