package Tools;

import Interfaces.IFunction;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2dTest {

    Vector2d[] Vectors = new Vector2d[]
            {
                    new Vector2d(1, 1),
                    new Vector2d(2, 2),
                    new Vector2d(2, 1),
                    new Vector2d(1, 1)
            };


    //region Override Methods Tests

    @Test
    public void hashTest() {
        assertEquals(new Vector2d(3, 3).hashCode(), new Vector2d(3, 3).hashCode());
        assertEquals(new Vector2d(1, 2).hashCode(), new Vector2d(1, 2).hashCode());
        assertEquals(new Vector2d(1, 1).hashCode(), new Vector2d(1, 1).hashCode());
    }

    @Test
    public void equalsTest() {
        Boolean[][] equalsArray = {
                {true, false, false, true},
                {false, true, false, false},
                {false, false, true, false},
                {true, false, false, true}
        };

        // Fajny sposób przekazywania funkcji
        booleanTest(equalsArray, Vector2d::equals);
    }

    @Test
    public void toStringTest() {

        HashMap<Vector2d, String> toStringMap = new HashMap<Vector2d, String>();
        toStringMap.put(Vectors[0], "(1,1)");
        toStringMap.put(Vectors[1], "(2,2)");
        toStringMap.put(Vectors[2], "(2,1)");
        toStringMap.put(Vectors[3], "(1,1)");

        for (Vector2d vector : Vectors)
            assertEquals(toStringMap.get(vector), vector.toString());
    }

    //endregion

    //region Public Methods Tests

    @Test
    public void precedesTest() {
        Boolean[][] precedesArray = {
                {true, true, true, true},
                {false, true, false, false},
                {false, true, true, false},
                {true, true, true, true}
        };

        booleanTest(precedesArray, Vector2d::precedes);
    }

    @Test
    public void followsTest() {
        Boolean[][] followsArray = {
                {true, false, false, true},
                {true, true, true, true},
                {true, false, true, true},
                {true, false, false, true}
        };

        booleanTest(followsArray, Vector2d::follows);
    }

    @Test
    public void upperRightTest() {
        Vector2d[][] upperRightArray = {
                {new Vector2d(1, 1), new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(1, 1)},
                {new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(2, 2), new Vector2d(2, 2)},
                {new Vector2d(2, 1), new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(2, 1)},
                {new Vector2d(1, 1), new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(1, 1)}
        };

        booleanTest(upperRightArray, Vector2d::upperRight);
    }

    @Test
    public void lowerLeftTest() {
        Vector2d[][] lowerLeftArray = {
                {new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1)},
                {new Vector2d(1, 1), new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(1, 1)},
                {new Vector2d(1, 1), new Vector2d(2, 1), new Vector2d(2, 1), new Vector2d(1, 1)},
                {new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 1)}
        };

        booleanTest(lowerLeftArray, Vector2d::lowerLeft);
    }

    @Test
    public void addTest() {
        Vector2d[][] addArray =
                {
                        {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(3, 2), new Vector2d(2, 2)},
                        {new Vector2d(3, 3), new Vector2d(4, 4), new Vector2d(4, 3), new Vector2d(3, 3)},
                        {new Vector2d(3, 2), new Vector2d(4, 3), new Vector2d(4, 2), new Vector2d(3, 2)},
                        {new Vector2d(2, 2), new Vector2d(3, 3), new Vector2d(3, 2), new Vector2d(2, 2)}
                };

        booleanTest(addArray, Vector2d::add);
    }

    @Test
    public void substractTest() {
        Vector2d[][] substractArray = {
                {new Vector2d(0, 0), new Vector2d(-1, -1), new Vector2d(-1, 0), new Vector2d(0, 0)},
                {new Vector2d(1, 1), new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(1, 1)},
                {new Vector2d(1, 0), new Vector2d(0, -1), new Vector2d(0, 0), new Vector2d(1, 0)},
                {new Vector2d(0, 0), new Vector2d(-1, -1), new Vector2d(-1, 0), new Vector2d(0, 0)}
        };

        booleanTest(substractArray, Vector2d::substract);
    }

    @Test
    public void oppositeTest() {
        HashMap<Vector2d, Vector2d> vectorOppositeMap = new HashMap<Vector2d, Vector2d>();
        vectorOppositeMap.put(Vectors[0], new Vector2d(-1, -1));
        vectorOppositeMap.put(Vectors[1], new Vector2d(-2, -2));
        vectorOppositeMap.put(Vectors[2], new Vector2d(-2, -1));
        vectorOppositeMap.put(Vectors[3], new Vector2d(-1, -1));

        for (Vector2d vector : Vectors)
            assertEquals(vectorOppositeMap.get(vector), vector.opposite());

    }

    //endregion

    //region Test Privates

    private void booleanTest(Boolean[][] answers, IFunction<Vector2d, Vector2d, Boolean> testFunction) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                assertEquals(answers[i][j], testFunction.apply(Vectors[i], Vectors[j]));
    }

    private void booleanTest(Vector2d[][] answers, IFunction<Vector2d, Vector2d, Vector2d> testFunction) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                assertEquals(answers[i][j], testFunction.apply(Vectors[i], Vectors[j]));
    }

    //endregion
}
