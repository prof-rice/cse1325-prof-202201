import java.util.Scanner;

public class Polygon {
    private int sides;
    private double perimeter;
    public void addSide(double length) {
        perimeter += length;
        ++sides;
    }
    public double getPerimeter() {
        return perimeter;
    }
    public int getSides() {
        return sides;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Polygon polygon = new Polygon();
        double sideLength = 0;
        while(true) {
            System.out.print("Side length (0 when done): ");
            if((sideLength = in.nextDouble()) <= 0) break;
            polygon.addSide(sideLength);
        }
        System.out.println("Perimeter of " + polygon.getSides() 
                         + "-sided polygon is " + polygon.getPerimeter());
    }
}
