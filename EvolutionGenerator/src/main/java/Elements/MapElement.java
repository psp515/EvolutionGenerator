package Elements;

import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Tools.Vector2d;

public class MapElement implements IMapElement {
    protected final IMap _map;
    protected Vector2d position;

    protected MapElement(IMap map, Vector2d position)
    {
        this._map = map;
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return null;
    }

    @Override
    public String getImage() {
        return null;
    }
}
