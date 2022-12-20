package Enums;

import java.util.concurrent.ThreadLocalRandom;

public enum MapDirection {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    public static MapDirection getRandom() {
        return MapDirection.values()[ThreadLocalRandom.current().nextInt(7)];
    }

    public String toString() {
        switch (this) {
            case NORTH:
                return "N";
            case NORTHWEST:
                return "NW";
            case WEST:
                return "W";
            case SOUTHWEST:
                return "SW";
            case SOUTH:
                return "S";
            case SOUTHEAST:
                return "SE";
            case EAST:
                return "E";
            case NORTHEAST:
                return "NE";
            default:
                return null;
        }
        return null;
    }

    public MapDirection next() {
        return MapDirection.values()[(this.ordinal() + 1) % MapDirection.values().length];
    }

    public MapDirection previous() {
        return MapDirection.values()[(this.ordinal() + MapDirection.values().length - 1) % MapDirection.values().length];
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case EAST:
                return new Vector2d(1, 0);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            case SOUTH:
                return new Vector2d(0, -1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            default:
                return new Vector2d(0, 0);
        }
        return new Vector2d(0, 0);
    }

}
