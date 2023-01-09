package Elements;

import Interfaces.Map.IMap;
import Tools.Vector2d;

public class Food extends MapElement {

    public static final int NUMBER_OF_FOOD_IMAGES = 3;
    int imageNumber;

    public Food(IMap map, Vector2d position, int growDay)
    {
        super(map, position, growDay);


        imageNumber = getRandomNumber(1, NUMBER_OF_FOOD_IMAGES);
    }

    @Override
    public String getImage()
    {
        return RESOURCES_STRING + "/food/f" + imageNumber + ".png";
    }

}
