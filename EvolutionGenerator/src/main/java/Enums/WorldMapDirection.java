package Enums;

import Tools.Vector2d;

import java.util.concurrent.ThreadLocalRandom;

public enum WorldMapDirection {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    public static WorldMapDirection getRandom() {
        return WorldMapDirection.values()[ThreadLocalRandom.current().nextInt(7)];
    }

    public String toString() {
        return switch (this) {
            case NORTH -> "N";
            case NORTHWEST -> "NW";
            case WEST -> "W";
            case SOUTHWEST -> "SW";
            case SOUTH -> "S";
            case SOUTHEAST -> "SE";
            case EAST -> "E";
            case NORTHEAST -> "NE";
        };
    }

    public WorldMapDirection next() {
        return WorldMapDirection.values()[(this.ordinal() + 1) % WorldMapDirection.values().length];
    }

    public WorldMapDirection previous() {
        return WorldMapDirection.values()[(this.ordinal() + WorldMapDirection.values().length - 1) % WorldMapDirection.values().length];
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
