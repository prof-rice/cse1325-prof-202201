import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Paint {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame f = new JFrame("CSE1325 Paint");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Canvas());
        f.pack();
        f.setVisible(true);
    }
}

