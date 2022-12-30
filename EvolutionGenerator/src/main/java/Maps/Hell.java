package Maps;

import Enums.MapDirection;
import Interfaces.Animals.IMapMoveElement;
import Interfaces.Tools.IMapField;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.util.Pair;

public class Hell extends Map {
    public Hell(MapSettings settings, IMapField[][] map) {
        super(settings,map);
    }
    @Override
    public Pair<MapDirection, Vector2d> moveElement(IMapMoveElement element, Vector2d oldPosition, Vector2d newPosition) {

        if(newPosition.isInRectangle(startBound,endBound))
        {
            element.useEnergy(settings.moveCost);
            return new Pair<>(oldPosition.getDirectionBetweenPositions(newPosition), newPosition);
        }
        else
        {
            element.useEnergy(settings.copulationCostEnregy);
            return new Pair<>(oldPosition.getDirectionBetweenPositions(newPosition), new Vector2d(startBound,endBound));
        }

    }
}
