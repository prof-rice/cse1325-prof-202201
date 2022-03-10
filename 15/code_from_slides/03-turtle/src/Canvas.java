import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
        turtle = new Turtle();
        turtle.pen(Turtle.Pen.DOWN);
        
        for(int i=1; i<300; i += 1) {
            turtle.forward(i); 
            turtle.turn(61);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(750,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Move origin to center
        Rectangle size = getBounds();
        g.translate(size.width / 2, size.height/2);
        
        // Paint the turtle        
        turtle.paintComponent(g);
    }
    
    private Turtle turtle;
}
