package Enums;

import Tools.Vector2d;

import java.util.concurrent.ThreadLocalRandom;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    public static MapDirection getRandom() {
        return MapDirection.values()[ThreadLocalRandom.current().nextInt(7)];
    }

    public MapDirection whatever(){return MapDirection.EAST}

    public MapDirection getOpposed(){
        return switch (this) {
            case NORTH -> SOUTH;
            case NORTHEAST -> SOUTHWEST;
            case EAST -> WEST;
            case SOUTHEAST -> NORTHWEST;
            case SOUTH -> NORTH;
            case SOUTHWEST -> NORTHEAST;
            case WEST -> EAST;
            case NORTHWEST -> SOUTHEAST;
        };
    }

    public String toString() {
        return switch (this) {
            case NORTH -> "N";
            case NORTHEAST -> "NE";
            case EAST -> "E";
            case SOUTHEAST -> "SE";
            case SOUTH -> "S";
            case SOUTHWEST -> "SW";
            case WEST -> "W";
            case NORTHWEST -> "NW";
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case NORTHEAST -> new Vector2d(1, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTHEAST -> new Vector2d(1, -1);
            case SOUTH -> new Vector2d(0, -1);
            case SOUTHWEST -> new Vector2d(-1, -1);
            case WEST -> new Vector2d(-1, 0);
            case NORTHWEST -> new Vector2d(-1, 1);
        };
    }

}
