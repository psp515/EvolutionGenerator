package Enums;

import ElementsExtensions.AnimalMovement.FullFate;
import ElementsExtensions.AnimalMovement.SlightMadness;
import Interfaces.OptionEnumTestMethods;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalMovementOptionsTest extends BaseEnumTestClass<AnimalMovementOptions> implements OptionEnumTestMethods {

    @Override @Test
    public void toStringTest(){

        String[] answers = {
                "Default",
                "Full Fate",
                "Slight Madness"};

        assertTrue(toStringTest(AnimalMovementOptions.values(), answers));
    }

    @Override @Test
    public void getClassRepresentationTest() {
        assertTrue(AnimalMovementOptions.DEFAULT.getClassRepresentation() instanceof FullFate);
        assertTrue(AnimalMovementOptions.FULL_FATE.getClassRepresentation() instanceof FullFate);
        assertTrue(AnimalMovementOptions.SLIGHT_MADNESS.getClassRepresentation() instanceof SlightMadness);
    }
}
