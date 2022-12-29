package Maps;

import Enums.MapDirection;
import Interfaces.Animals.IMapMoveElement;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IMapField;
import Tools.MapField;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.util.Pair;

public abstract class Map implements IMap {

    //lewy górny róg to 0,0 !! pod nim 0,1 0,2 itd.
    private IMapField[][] fields;
    private MapSettings settings;

    protected Map(MapSettings settings, IMapField[][] map)
    {
        this.settings = settings;
        this.fields = map;
    }

    @Override
    public Pair<MapDirection, Vector2d> moveElement(IMapMoveElement element, Vector2d oldPosition, Vector2d newPosition) {

        //TU SIE ZMIENIA ENERGIE ELEMENTU przy ruchu.
        return null;
    }

    @Override
    public void placeElement(IMapElement element) {

        IMapField field = getMapField(element.getPosition());

    }

    @Override
    public void removeElement(IMapElement element) {

    }

    @Override
    public IMapField getMapField(Vector2d position) {

        //TODO validate position

        return this.fields[position.x-1][position.y-1];
    }

    @Override
    public IMapField[][] getMap(){return null;}

    @Override
    public MapSettings getMapSettings() {
        return null;
    }

}
