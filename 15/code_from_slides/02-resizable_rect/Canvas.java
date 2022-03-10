import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Rectangle
        Rectangle size = getBounds();
        g.drawRect(border, border, size.width - 2*border, size.height - 2*border);
    }  
}
