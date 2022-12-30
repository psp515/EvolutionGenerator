package Maps;

import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IMapField;
import Models.MapSettings;
import Tools.Vector2d;

public abstract class Map implements IMap {

    //lewy górny róg to 0,0 !! pod nim 0,1 0,2 itd. -> tak jak w gridzie
    protected final IMapField[][] _fields;
    protected final MapSettings _settings;
    protected final Vector2d _startBound;
    protected final Vector2d _endBound;

    protected Map(MapSettings settings, IMapField[][] map)
    {
        this._settings = settings;
        this._fields = map;

        _startBound = new Vector2d(0,0);
        _endBound = new Vector2d(settings.width - 1, settings.height - 1);
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

        if(!(position.follows(_startBound) &&
                position.precedes(_endBound)))
            throw new IllegalArgumentException(String.format("GetMapFiled must take as argument valid position in square between %s and %s.", _startBound, _endBound));

        return this._fields[position.x-1][position.y-1];
    }

    @Override
    public IMapField[][] getMapFields(){return this._fields;}

    @Override
    public MapSettings getMapSettings() {
        return this._settings;
    }

    @Override
    public Vector2d getStartBound()
    {
        return _startBound;
    }

    @Override
    public Vector2d getEndBound()
    {
        return _endBound;
    }
}
