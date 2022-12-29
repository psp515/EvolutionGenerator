package Enums;

import Interfaces.Map.IMap;
import Interfaces.Others.GetClassRepresentation;
import Maps.Earth;
import Maps.Hell;
import Models.MapSettings;

public enum MapOptions implements GetClassRepresentation<IMap, MapSettings> {
    DEFAULT,
    EARTH,
    HELL;

    @Override
    public IMap getClassRepresentation(MapSettings settings) {
        return switch (this) {
            case EARTH, DEFAULT -> new Earth(settings);
            case HELL -> new Hell(settings);
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
