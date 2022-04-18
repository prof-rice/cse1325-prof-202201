
import java.util.Random;
import java.util.HashMap;

class Coordinate {
    public static final int maxX = 29;
    public static final int maxY = 29;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        validate();
    }
    public Coordinate() {
        
        this(rand.nextInt(maxX), rand.nextInt(maxY));
    }
    public int x() {return this.x;}
    public int y() {return this.y;}
    
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    public void add(Coordinate rhs) {
        x += rhs.x;
        y += rhs.y;
        validate();
    }
    
    protected void validate() {
        if(x < 0 || x > maxX) throw new IllegalArgumentException("Invalid x: " + x);
        if(y < 0 || y > maxX) throw new IllegalArgumentException("Invalid y: " + y);
    }
        
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false; 
        if(this.getClass() != o.getClass()) return false;
        Coordinate c = (Coordinate) o; // Downcast to a Coordinate
        return (x == c.x) && (y == c.y);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + x;
        hash = 31*hash + y;
        return hash;
    }

    protected int x;
    protected int y;

    protected static Random rand = new Random(0XC0FFEE);
}

class Treasure {
    public Treasure(Coordinate c, String name, double value) {
        this.coordinate = c;
        this.treasureName = name;
        this.treasureValue = value;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false; 
        if(this.getClass() != o.getClass()) return false;
        Treasure t = (Treasure) o; // Downcast to a Treasure
        return coordinate.equals(t.coordinate)
            && treasureName.equals(t.treasureName)
            && (treasureValue == t.treasureValue);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + (coordinate == null ? 0 : coordinate.hashCode());
        hash = 31*hash + (treasureName == null ? 0 : treasureName.hashCode());
        hash = 31*hash + (int) (treasureValue * 100.0);
        return hash;
    }
    
    private Coordinate coordinate;  // Our custom class
    private String treasureName;    // A JCL class
    private double treasureValue;   // A primitive
}

public class EqualsAndHashCode {
    public static void main(String[] args) {
        HashMap<Treasure, Integer> map = new HashMap<>();
        map.put(new Treasure(new Coordinate(23,17), "Blackbeard's", 12835.19), 1);
        map.put(new Treasure(new Coordinate(23,17), "Blackbeard's", 12835.19), 0);
        System.out.println("This should be 1: " + map.size());
    }
}
