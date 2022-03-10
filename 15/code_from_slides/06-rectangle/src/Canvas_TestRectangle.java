import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import shapes.Shape;
import shapes.Polygon;
import shapes.Line;

public class Canvas_TestRectangle extends JPanel {
    public Canvas_TestRectangle() {
    
        // Create a rectangle using Polygon - close enough, right?
        
        Polygon rectangle = new Polygon();
        rectangle.addLine (new Line(
                           -350, -275, 
                            350, -275));
        rectangle.addPoint( 350,  275);
        rectangle.addPoint(-350,  275);

        // Can we add a 5th corner to our "rectangle"?
        //    My math teacher said "No!"
        //    My class library says...
        rectangle.addPoint(0, 0);  // Does this "break" my rectangle?

        shapes.add(rectangle);
    }

    public Dimension getPreferredSize() {
        return new Dimension(750,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Turtle Path
        Rectangle size = getBounds();
        g.translate(size.width / 2, size.height/2);
        
        for(Shape shape : shapes)
            shape.paintComponent(g);
    }
    
    protected ArrayList<Shape> shapes = new ArrayList<>();
}
