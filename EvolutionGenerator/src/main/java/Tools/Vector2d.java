package Tools;

import Enums.MapDirection;

import java.util.Objects;

public class Vector2d {
    public int x;
    public int y;



    /**
     * Creates random vector in rectangle.
     * @param startBound Top left corner.
     * @param endBound Bottom right corner.
     */
    public Vector2d(Vector2d startBound, Vector2d endBound){
        this.x = getRandomNumber(startBound.x, endBound.x);
        this.y = getRandomNumber(startBound.y,endBound.y);
    }

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2d vector2d) {
        return vector2d.x >= this.x && vector2d.y >= this.y;
    }
    public boolean follows(Vector2d vector2d) {
        return vector2d.x <= this.x && vector2d.y <= this.y;
    }

    public Vector2d upperRight(Vector2d vector2d) {
        return new Vector2d(Math.max(this.x, vector2d.x), Math.max(this.y, vector2d.y));
    }
    public Vector2d lowerLeft(Vector2d vector2d) {
        return new Vector2d(Math.min(this.x, vector2d.x), Math.min(this.y, vector2d.y));
    }

    public Vector2d add(Vector2d vector2d) {
        return new Vector2d(this.x + vector2d.x, this.y + vector2d.y);
    }
    public Vector2d substract(Vector2d vector2d) {
        return new Vector2d(this.x - vector2d.x, this.y - vector2d.y);
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    /**
     * Two positions must be next to each other!
     */
    public MapDirection getDirectionBetweenPositions(Vector2d toVector)
    {
        return toVector.substract(this).toMapDirection();
    }

    public boolean isInRectangle(Vector2d first, Vector2d last)
    {
        return this.follows(first) && this.precedes(last);
    }

    public Vector2d copy(){
        return new Vector2d(this.x, this.y);
    }

    public boolean equals(Object obj) {
      
        if (!(obj instanceof Vector2d))
            return false;
        
        Vector2d that = (Vector2d) obj;
        return that.x == this.x && that.y == this.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public MapDirection toMapDirection() {

        if( this.equals(new Vector2d(0, 1)))
            return MapDirection.NORTH;
        if( this.equals(new Vector2d(1, 1)))
            return MapDirection.NORTHEAST;
        if( this.equals(new Vector2d(1, 0)))
            return MapDirection.EAST;
        if( this.equals(new Vector2d(1, -1)))
            return MapDirection.SOUTHEAST;
        if( this.equals(new Vector2d(0, -1)))
            return MapDirection.SOUTH;
        if( this.equals(new Vector2d(-1, -1)))
            return MapDirection.SOUTHWEST;
        if( this.equals(new Vector2d(-1, 0)))
            return MapDirection.WEST;
        if( this.equals(new Vector2d(-1, 1)))
            return MapDirection.NORTHWEST;


        throw new IllegalArgumentException("You can use toMapDairetion only when Vector is Versor.");
    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
