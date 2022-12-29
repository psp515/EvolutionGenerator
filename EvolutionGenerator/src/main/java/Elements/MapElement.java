package Elements;

import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Tools.Vector2d;

public abstract class MapElement implements IMapElement {

    /**
     * Link to resources folder. Mainly for elements images.
     */
    public static final String RESOURCES_STRING = "src/main/resources/";


    protected final IMap _map;
    protected final int _creationDay;
    protected Vector2d position;



    protected MapElement(IMap map, Vector2d position, int creationDay)
    {
        this._map = map;
        this._creationDay = creationDay;

        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
    @Override
    public String getImage() {
        return RESOURCES_STRING + "none.png";
    }
    @Override
    public int getCreationDay() {
        return this._creationDay;
    }

    @Override
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
