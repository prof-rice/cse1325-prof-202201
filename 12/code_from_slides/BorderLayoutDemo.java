import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

public class BorderLayoutDemo extends JFrame {
    public BorderLayoutDemo(String title) {
        super(title); // calling non-default JFrame constructor
        // NOTE: JFrame uses BorderLayout manager by default
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        
        // Create and configure 5 labels as content
        // The second parameter controls alignment of the label
        JLabel pageStartLabel = new JLabel("PAGE_START", JLabel.CENTER);
        JLabel pageEndLabel = new JLabel("PAGE_END", JLabel.CENTER);
        JLabel lineStartLabel = new JLabel("LINE_START", JLabel.CENTER);
        JLabel lineEndLabel = new JLabel("LINE_END", JLabel.CENTER);
        JLabel centerLabel = new JLabel("CENTER", JLabel.CENTER);
        
        pageStartLabel.setOpaque(true);
        pageStartLabel.setBackground(Color.GREEN);
        pageEndLabel.setOpaque(true);
        pageEndLabel.setBackground(Color.GREEN);
        lineStartLabel.setOpaque(true);
        lineStartLabel.setBackground(Color.RED);
        lineEndLabel.setOpaque(true);
        lineEndLabel.setBackground(Color.RED);
        centerLabel.setOpaque(true);
        centerLabel.setBackground(Color.YELLOW);

        // The second parameter specifies to which region
        //     of the BorderLayout the widget is to be added
        add(pageStartLabel, BorderLayout.PAGE_START);
        add(pageEndLabel, BorderLayout.PAGE_END);
        add(lineStartLabel,  BorderLayout.LINE_START); 
        add(lineEndLabel,  BorderLayout.LINE_END);
        add(centerLabel, BorderLayout.CENTER);
    } 
    public static void main( String[] args ) {
        (new BorderLayoutDemo("BorderLayout Demo")).setVisible(true);
    }
}
