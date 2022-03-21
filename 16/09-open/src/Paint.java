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
        JFrame f = new MainWin("CSE1325 Paint");
    }
}

