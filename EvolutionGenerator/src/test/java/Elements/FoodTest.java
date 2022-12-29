package Elements;

import Tools.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {

    @Test
    public void creationTest()
    {
        assertEquals(new Food(null, null , 10).getCreationDay(), 10);
        assertEquals(new Food(null, null , 234).getCreationDay(), 234);
    }

    @Test
    public void positionTest()
    {
        assertEquals(new Food(null, new Vector2d(2,2) , 10).getPosition(), new Vector2d(2,2));
        assertEquals(new Food(null, new Vector2d(2,234) , 10).getPosition(), new Vector2d(2,234));
    }

}
