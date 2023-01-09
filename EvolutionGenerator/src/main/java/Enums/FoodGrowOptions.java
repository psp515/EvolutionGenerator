package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import ElementsExtensions.Food.LinearGrow;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Others.GetClassRepresentationTwoParam;
import Models.MapStatistics;
import Tools.SingleFoodField;

public enum FoodGrowOptions implements GetClassRepresentationTwoParam<IFoodGenerator, SingleFoodField[][], MapStatistics> {
    DEFAULT,
    EQUATORS,
    DEATH_BODIES,
    LINEAR_GROW;

    @Override
    public IFoodGenerator getClassRepresentation(SingleFoodField[][] mapFields, MapStatistics statistics){
        return switch (this) {
            case DEFAULT, EQUATORS -> new Equators(mapFields);
            case LINEAR_GROW -> new LinearGrow(mapFields);
            case DEATH_BODIES -> new DeathBodies(mapFields, statistics);
        };
    }

    public String toString()
    {
        return switch (this) {
            case DEFAULT -> "Default";
            case EQUATORS -> "Equators";
            case LINEAR_GROW -> "Linear Grow";
            case DEATH_BODIES -> "Death Bodies";
        };
    }
}
