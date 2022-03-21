package shapes;
import java.awt.Graphics;

public class Polygon extends Shape {
    @Override
    public void paintComponent(Graphics g) {
        // paint open polyline
        super.paintComponent(g); 
        
        // calculate closure line
        Line lineFirst = lines.get(0);
        Line lineLast  = lines.get(lines.size()-1);
        Line closure = new Line(lineLast.x2, lineLast.y2, lineFirst.x1, lineFirst.y1, lineLast.color);
        
        // paint closure line
        closure.paintComponent(g);
    }
}
