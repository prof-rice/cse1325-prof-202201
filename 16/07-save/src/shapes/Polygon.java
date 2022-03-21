package shapes;
import java.awt.Graphics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polygon extends Shape {
    public Polygon() {
        super();
    }

    // File I/O members
    public static String id() {return "Polygon";}
    public Polygon(BufferedReader br) throws IOException {
        super(br);
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        super.save(bw);
    }

    // Methods    
    @Override
    public void paintComponent(Graphics g) {
        // paint open polyline
        super.paintComponent(g); 
        
        // calculate closure line
        Line lineFirst = lines.get(0);
        Line lineLast  = lines.get(lines.size()-1);
        Line closure = new Line(lineLast.x2, lineLast.y2, lineFirst.x1, lineFirst.y1, lineLast.color, lineLast.stroke);
        
        // paint closure line
        closure.paintComponent(g);
    }
}
