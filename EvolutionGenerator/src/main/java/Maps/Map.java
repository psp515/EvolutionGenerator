package Maps;

import Enums.MapDirection;
import Interfaces.Animals.IMapMoveElement;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Tools.MapField;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.util.Pair;

public abstract class Map implements IMap {
    private MapField[][] fields;
    private MapSettings settings;

    protected Map(MapSettings settings)
    {
        this.settings = settings;
        this.fields = new MapField[settings.width][settings.height];
    }

    @Override
    public Pair<Vector2d, MapDirection> moveElement(IMapMoveElement element, Vector2d oldPosition, Vector2d newPosition) {

        return null;
    }

    @Override
    public void placeElement(IMapElement element) {

    }

    @Override
    public void removeElement(IMapElement element) {

    }

    @Override
    public MapField getDataAt(Vector2d position) {
        return null;
    }

    @Override
    public MapField[][] getData(){return null;}

    @Override
    public MapSettings getMapSettings() {
        return null;
    }
}
