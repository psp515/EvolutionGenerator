package Interfaces.Map;

import Enums.MapDirection;
import Tools.MapField;
import Models.MapSettings;
import Tools.Vector2d;

public interface IMap {

    Vector2d moveElement(IMapElement element, MapDirection direction);
    void placeElement(IMapElement element);
    void removeElement(IMapElement element);
    MapField getDataAt(Vector2d position);
    MapField[][] getData();
    MapSettings getMapSettings();
}
