package Enums;

import Tools.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapDirectionTest extends BaseEnumTestClass<MapDirection> {

    @Test
    public void toStringTest()
    {
        String[] answers = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        assertTrue(toStringTest(MapDirection.values(), answers));
    }

    @Test
    public void toUnitVectorTest()
    {
        EnumMap<MapDirection, Vector2d> directionNextAnswers = new EnumMap<MapDirection, Vector2d>(MapDirection.class);
        directionNextAnswers.put(MapDirection.NORTH, new Vector2d(0,1));
        directionNextAnswers.put(MapDirection.NORTHEAST, new Vector2d(1,1));
        directionNextAnswers.put(MapDirection.EAST, new Vector2d(1,0));
        directionNextAnswers.put(MapDirection.SOUTHEAST, new Vector2d(1,-1));
        directionNextAnswers.put(MapDirection.SOUTH, new Vector2d(0,-1));
        directionNextAnswers.put(MapDirection.SOUTHWEST, new Vector2d(-1,-1));
        directionNextAnswers.put(MapDirection.WEST, new Vector2d(-1,0));
        directionNextAnswers.put(MapDirection.NORTHWEST, new Vector2d(-1,1));

        for(MapDirection direction : MapDirection.values())
            assertEquals(directionNextAnswers.get(direction), direction.toUnitVector());

    }

}
