package Tools;

import java.util.Objects;

public class Vector2d {
    public final int x, y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean precedes(Vector2d vector2d) {
        return vector2d.x >= this.x && vector2d.y >= this.y;
    }

    public boolean follows(Vector2d vector2d) {
        return vector2d.x <= this.x && vector2d.y <= this.y;
    }
    
  // to ogarniamy tutaj czy raczej w mapach??
    public Vector2d upperRight(Vector2d vector2d) {
        return new Vector2d(Math.max(this.x, vector2d.x), Math.max(this.y, vector2d.y));
    }
  // ten sam komentarz co wyżej
    public Vector2d lowerLeft(Vector2d vector2d) {
        return new Vector2d(Math.min(this.x, vector2d.x), Math.min(this.y, vector2d.y));
    }

    public Vector2d add(Vector2d vector2d) {
        return new Vector2d(this.x + vector2d.x, this.y + vector2d.y);
    }

    public Vector2d subtract(Vector2d vector2d) {
        return new Vector2d(this.x - vector2d.x, this.y - vector2d.y);
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
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
}
