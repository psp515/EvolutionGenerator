package Enums;

import Interfaces.Map.IMap;
import Interfaces.Others.GetClassRepresentation;
import Interfaces.Others.GetClassRepresentationOneParam;
import Interfaces.Others.GetClassRepresentationTwoParam;
import Interfaces.Tools.IMapField;
import Maps.Earth;
import Maps.Hell;
import Models.MapSettings;

public enum MapOptions implements GetClassRepresentationTwoParam<IMap, MapSettings,IMapField[][]> {
    DEFAULT,
    EARTH,
    HELL;

    @Override
    public IMap getClassRepresentation(MapSettings settings, IMapField[][] map) {
        return switch (this) {
            case EARTH, DEFAULT -> new Earth(settings, map);
            case HELL -> new Hell(settings, map);
        };
    }

    public String toString()
    {
        return switch (this) {
            case DEFAULT -> "Default";
            case EARTH -> "Earth";
            case HELL -> "Hell";
        };
    }
}
