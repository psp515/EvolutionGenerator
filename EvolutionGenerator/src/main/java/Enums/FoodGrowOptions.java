package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Others.GetClassRepresentationTwoParam;
import Models.MapStatistics;
import Tools.SingleFoodField;

public enum FoodGrowOptions implements GetClassRepresentationTwoParam<IFoodGenerator, SingleFoodField[][], MapStatistics> {
    DEFAULT,
    EQUATORS,
    DEATH_BODIES;

    @Override
    public IFoodGenerator getClassRepresentation(SingleFoodField[][] mapFields, MapStatistics statistics){
        return switch (this) {
            case DEFAULT, EQUATORS -> new Equators(mapFields);
            case DEATH_BODIES -> new DeathBodies(mapFields, statistics);
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
