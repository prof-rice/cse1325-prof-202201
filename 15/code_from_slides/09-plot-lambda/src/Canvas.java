import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import shapes.Shape;
import shapes.Line;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Turtle;
import shapes.FunctionToPlot;
import shapes.Plot;

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
        
      
        // Plot two functions
        //FunctionToPlot ftp1 = new Cos();
        //Plot f1 = new Plot(ftp1,   -370, 0,   0, 3.14*6,   160,   40, 25);
        Plot f1 = new Plot(x -> Math.cos(x),   -370, 0,   0, 3.14*6,   160,   40, 25);
        shapes.add(f1);

        //FunctionToPlot ftp2 = new SlopingCos();
        //Plot f2 = new Plot(ftp2,   -370, 0,   0, 3.14*6,   160,   40, 25);
        Plot f2 = new Plot(x -> Math.cos(x) + x/3.14,   -370, 0,   0, 3.14*6,   160,   40, 25);
        shapes.add(f2);
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
