package Interfaces.Map;

import Enums.MapDirection;
import Models.MapField;
import Models.MapSettings;
import Models.MapStatistics;
import Tools.Vector2d;

public interface IMap {

    Vector2d moveElement(IMapElement element, MapDirection direction);
    void placeElement(IMapElement element);
    void removeElement(IMapElement element);
    MapField getDataAt(Vector2d position);
    MapField[][] getData();
    MapSettings getMapSettings();
}
