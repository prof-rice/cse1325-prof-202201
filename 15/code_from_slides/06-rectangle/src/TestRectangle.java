import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class TestRectangle {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Canvas_TestRectangle());
        f.pack();
        f.setVisible(true);
    }
}

