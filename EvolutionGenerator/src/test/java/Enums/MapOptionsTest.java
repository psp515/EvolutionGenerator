package Enums;

import Interfaces.OptionEnumTestMethods;
import Maps.Earth;
import Maps.Hell;
import Models.MapSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapOptionsTest extends BaseEnumTestClass<MapOptions> implements OptionEnumTestMethods {

    @Override @Test
    public void toStringTest(){

        String[] answers = {
                "Default",
                "Earth",
                "Hell"};

        assertTrue(toStringTest(MapOptions.values(), answers));
    }

    @Override @Test
    public void getClassRepresentationTest() {
        assertTrue(MapOptions.DEFAULT.getClassRepresentation(new MapSettings(), null) instanceof Earth);
        assertTrue(MapOptions.EARTH.getClassRepresentation(new MapSettings(), null) instanceof Earth);
        assertTrue(MapOptions.HELL.getClassRepresentation(new MapSettings(), null) instanceof Hell);
    }

}
