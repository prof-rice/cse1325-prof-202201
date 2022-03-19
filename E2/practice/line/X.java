import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

public class X extends JFrame {
    public X() {
        super("Free Response 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(100,140);
        Canvas canvas = new Canvas();
        add(canvas); //, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        X x = new X();
        x.setVisible(true);
    }
}

class Canvas extends JPanel {

    public Canvas() {
        super();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g.create();     

        // Draw Shape
        g2.setColor(Color.RED);
        g2.drawLine(0,0,100,100);
        g2.drawLine(0,100,100,0);
        g2.drawString("Exam 2",30,20);
    }  
}

