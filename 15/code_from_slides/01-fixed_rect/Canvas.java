import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Canvas extends JPanel {

    public Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Rectangle
        g.drawRect(25,20,200,150);
    }  
}
