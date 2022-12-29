package Interfaces.Map;

import Enums.MapDirection;
import Interfaces.Animals.IMapMoveElement;
import Tools.MapField;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.util.Pair;

public interface IMap {

    Pair<MapDirection, Vector2d> moveElement(IMapMoveElement element, Vector2d oldPosition, Vector2d newPosition);
    void placeElement(IMapElement element);
    void removeElement(IMapElement element);
    MapField getDataAt(Vector2d position);
    MapField[][] getData();
    MapSettings getMapSettings();
}
