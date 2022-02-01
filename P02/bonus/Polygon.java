import java.util.Scanner;

public class Polygon {
    public final static int MAX_SIDES = 32; 
    private int sides = 0;
    private double[] lengths = new double[MAX_SIDES];
    
    public void addSide(double length) {
        if(sides < MAX_SIDES) lengths[sides++] = length;
        else System.err.println("Error: too many sides!");
    }
    public double getPerimeter() {
        double perimeter = 0;
        for(int i=0; i<sides; ++i) perimeter += lengths[i];
        return perimeter;
    }
    public int getSides() {
        return sides;
    }
    public double getArea(double apothem) {
        return 0.5 * apothem * getPerimeter();
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
        System.out.print("Apothem: ");
        double apothem = in.nextDouble();
        System.out.println("Perimeter of " + polygon.getSides() 
                         + "-sided polygon is " + polygon.getPerimeter());
        System.out.println("Area is " 
                         + polygon.getArea(apothem));
    }
}
