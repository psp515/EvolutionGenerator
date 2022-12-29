package Interfaces.Map;

import Tools.Vector2d;

/**
 * Required methods for element to be placed on map.
 */
public interface IMapElement {

    /**
     * @return Position of the element on map.
     */
    Vector2d getPosition();

    /**
     * Method created for javafx.
     * @return Route to image representing element state.
     */
    String getImage();

    /**
     * @return Returns day when element was placed on map.
     */
    int getCreationDay();

    /**
     * Generates random number with given bounds.
     * @param min Minimal random number.
     * @param max Maximal random number.
     * @return Random number from range.
     */
    int getRandomNumber(int min, int max);
}
