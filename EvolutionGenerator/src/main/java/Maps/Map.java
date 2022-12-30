package Maps;

import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IMapField;
import Models.MapSettings;
import Tools.Vector2d;

public abstract class Map implements IMap {

    //lewy górny róg to 0,0 !! pod nim 0,1 0,2 itd. -> tak jak w gridzie
    protected IMapField[][] fields;
    protected MapSettings settings;
    Vector2d startBound;
    Vector2d endBound;

    protected Map(MapSettings settings, IMapField[][] map)
    {
        this.settings = settings;
        this.fields = map;

        startBound = new Vector2d(0,0);
        endBound = new Vector2d(settings.width - 1, settings.height - 1);
    }

    @Override
    public void placeElement(IMapElement element) {

        IMapField field = getMapField(element.getPosition());
        field.addElement(element);
    }

    @Override
    public void removeElement(IMapElement element) {

        IMapField field = getMapField(element.getPosition());
        field.removeMapElement(element);
    }

    @Override
    public IMapField getMapField(Vector2d position) {

        if(!(position.follows(startBound) &&
                position.precedes(endBound)))
            throw new IllegalArgumentException(String.format("GetMapFiled must take as argument valid position in square between %s and %s.", startBound, endBound));

        return this.fields[position.x-1][position.y-1];
    }

    @Override
    public IMapField[][] getMap(){return this.fields;}

    @Override
    public MapSettings getMapSettings() {
        return this.settings;
    }

}
