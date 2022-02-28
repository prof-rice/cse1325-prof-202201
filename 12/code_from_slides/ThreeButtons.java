import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class ThreeButtons extends JFrame {
    public ThreeButtons() {
        super();  // Initialize JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        
        for(int i=0; i<3; ++i) {
            JButton button = new JButton("Hello, Button " + i + "!");
		    button.addActionListener(
		        event -> System.out.println(event.getActionCommand()));
            if(i==0) add(button, BorderLayout.PAGE_START);
            if(i==1) add(button, BorderLayout.CENTER);
            if(i==2) add(button, BorderLayout.PAGE_END);
        }
		
        setSize(220,160); // Set the JFrame size
        setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
       (new ThreeButtons()).setVisible(true);
    }
}
