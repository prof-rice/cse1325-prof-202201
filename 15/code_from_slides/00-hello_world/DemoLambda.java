import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class DemoLambda extends JFrame {
    public DemoLambda() {
        setTitle("Swing Paint Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Canvas());
        pack();
        setVisible(true);
    }    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DemoLambda());
    }
}

