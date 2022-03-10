import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Rectangle;  // oops!
import java.util.ArrayList;
import shapes.*;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
        /*   
        // Create a turtle
        
        Turtle turtle = new Turtle();
        turtle.pen(Turtle.Pen.DOWN);
        
        for(int i=1; i<300; i += 1) {
            turtle.forward(i); 
            turtle.turn(61);
        }
        shapes.add(turtle);
        */
        
        // Create a generic shape
        
        Shape shape = new Shape();
        int xCenter = -270;
        int yCenter = -200;
        for(double theta=0; theta < 360; theta+=30) {
            int x = (int) (30.0 * Math.cos(Math.toRadians(theta)));
            int y = (int) (30.0 * Math.sin(Math.toRadians(theta)));
            shape.addLine(new Line(xCenter-x, yCenter-y, xCenter+x, yCenter+y));
        }
        shapes.add(shape);
        
        // Create a polygon
        
        Polygon polygon = new Polygon();
        xCenter = -xCenter;
        polygon.addLine(new Line(xCenter-30, yCenter+30, xCenter-30, yCenter-10));
        polygon.addPoint(xCenter,    yCenter-30);
        polygon.addPoint(xCenter+30, yCenter-10);
        polygon.addPoint(xCenter+30, yCenter+30);
        polygon.addPoint(xCenter,    yCenter+10);
        polygon.addPoint(xCenter+30, yCenter-10);
        polygon.addPoint(xCenter-30, yCenter-10);
        polygon.addPoint(xCenter,    yCenter+10);
        polygon.addPoint(xCenter-30, yCenter+30);
        polygon.addPoint(xCenter+30, yCenter+30);
        shapes.add(polygon);
        
        // Create a rectangle
        
        Rectangle rectangle = new Rectangle(-350, -275, 350, 275);
        shapes.add(rectangle);
        
        /*
        // Verify that points and lines can't be added to a Rectangle
        try {
            rectangle.addPoint(0, 0);  // Should throw exception
            System.err.println("FAIL: Did not protect Rectangle!");
        } catch (UnsupportedOperationException e) {
        }
        */
        
        
        /*
        Plot f1 = new Plot(x -> Math.cos(x), 
                           x -> Math.sin(x), 
                           0, 0,   0, 3.14*6,   160,   50, 50);
                           //-125, -150,   0, 3.14*6,   160,   100, 100);
        shapes.add(f1);

        Plot f2 = new Plot(x -> (Math.sin(12*x) + 2) * Math.cos(x), 
                           x -> (Math.sin(12*x) + 2) * Math.sin(x), 
                           0, 0,   0, 3.14*6,   1600,   50, 50);
        shapes.add(f2);

        Plot f3 = new Plot(x -> (Math.sin(12*x+Math.PI) + 2) * Math.cos(x), 
                           x -> (Math.sin(12*x+Math.PI) + 2) * Math.sin(x), 
                           0, 0,   0, 3.14*6,   1600,   75, 75);
        shapes.add(f3);
        */
        
        Plot f1 = new Plot(x -> Plot.spiroX(100, 2, 80, x),
                           x -> Plot.spiroY(100, 2, 80, x),
                           0, 0,   0, 3.14*2,   2001,   1.3, 1.3);
        shapes.add(f1);                           
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(750,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Move origin to center
        java.awt.Rectangle size = getBounds();  // Java's Rectangle now fully qualified!
        g.translate(size.width / 2, size.height/2);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
    }
    
    protected ArrayList<Shape> shapes = new ArrayList<>();
}
