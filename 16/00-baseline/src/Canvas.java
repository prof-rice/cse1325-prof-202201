import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import shapes.Shape;
import shapes.Turtle;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
        // Create a turtle
        
        Turtle turtle = new Turtle();
        turtle.pen(Turtle.Pen.DOWN);
        
        for(int i=1; i<300; i += 1) {
            turtle.forward(i); 
            turtle.turn(61);
        }
        shapes.add(turtle);                        
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1024,768);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Move origin to center
        java.awt.Rectangle size = getBounds();
        g.translate(size.width / 2, size.height/2);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
    }
    
    protected ArrayList<Shape> shapes = new ArrayList<>();
}
