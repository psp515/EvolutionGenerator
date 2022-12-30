package Maps;

import Enums.MapDirection;
import Interfaces.Animals.IMapMoveElement;
import Interfaces.Tools.IMapField;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.util.Pair;

public class Earth extends Map {
    public Earth(MapSettings settings, IMapField[][] map) {
        super(settings, map);
    }

    @Override
    public Pair<MapDirection, Vector2d> moveElement(IMapMoveElement element, Vector2d oldPosition, Vector2d newPosition) {

        element.useEnergy(_settings.moveCost);

        if(newPosition.y < 0)
        {
            // too much North
            var direction = oldPosition.getDirectionBetweenPositions(newPosition).getOpposed();

            return new Pair<>(direction, oldPosition);
        }
        else if(newPosition.y >= _settings.height)
        {
            // too much South
            var direction = oldPosition.getDirectionBetweenPositions(newPosition).getOpposed();
            return new Pair<>(direction, oldPosition);
        }
        else if(newPosition.x >= _settings.width)
        {
            // too much east
            var updatedNewPosition = new Vector2d(0, newPosition.y);
            return new Pair<>(oldPosition.getDirectionBetweenPositions(newPosition), updatedNewPosition);
        }
        else if(newPosition.x < 0)
        {
            // too much west
            var updatedNewPosition = new Vector2d(_settings.width-1, newPosition.y);
            return new Pair<>(oldPosition.getDirectionBetweenPositions(newPosition), updatedNewPosition);
        }
        else
        {
            // normal move
            return new Pair<>(oldPosition.getDirectionBetweenPositions(newPosition), newPosition);
        }
    }
}
