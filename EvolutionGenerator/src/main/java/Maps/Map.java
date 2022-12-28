package Maps;

import Enums.MapDirection;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Models.MapField;
import Models.MapSettings;
import Models.MapStatistics;
import Tools.Vector2d;

public class Map implements IMap {
    private MapField[][] fields;
    private MapSettings settings;

    protected Map(MapSettings settings)
    {
        this.settings = settings;
        this.fields = new MapField[settings.width][settings.height];
    }

    @Override
    public Vector2d moveElement(IMapElement element, MapDirection direction) {
        //Tutaj nastepuje walidacja 2 wartosci i wzaleznosci od mapy rózne opcje
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
