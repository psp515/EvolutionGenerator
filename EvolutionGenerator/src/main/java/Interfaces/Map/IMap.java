package Interfaces.Map;

import Enums.MapDirection;
import Interfaces.Tools.IMapField;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.util.Pair;

public interface IMap {

    Pair<MapDirection, Vector2d> moveElement(IMapMoveElement element, Vector2d oldPosition, Vector2d newPosition);
    void placeElement(IMapElement element);
    void removeElement(IMapElement element);
    IMapField getMapField(Vector2d position);
    IMapField[][] getMapFields();
    MapSettings getMapSettings();
    Vector2d getStartBound();
    Vector2d getEndBound();
}
