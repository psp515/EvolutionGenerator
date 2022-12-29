package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.OptionEnumTestMethods;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodGrowOptionsTest extends BaseEnumTestClass<FoodGrowOptions> implements OptionEnumTestMethods {

    @Override @Test
    public void toStringTest(){

        String[] answers = {
                "Default",
                "Equators",
                "Death Bodies"};

        assertTrue(toStringTest(FoodGrowOptions.values(),answers));
    }

    @Override @Test
    public void getClassRepresentationTest() {
        assertTrue(FoodGrowOptions.DEFAULT.getClassRepresentation(null,null) instanceof Equators);
        assertTrue(FoodGrowOptions.EQUATORS.getClassRepresentation(null,null) instanceof Equators);
        assertTrue(FoodGrowOptions.DEATH_BODIES.getClassRepresentation(null,null) instanceof DeathBodies);
    }


}
