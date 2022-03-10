package shapes;
import java.awt.Graphics;

public class Polygon extends Shape {
    public Polygon() { }
    public Polygon(double radius, int sides, int offsetX, int offsetY, double rotation) {
        double theta = rotation;
        double delta = 360 / (double) sides;
        while(sides-- > 0) {
            addPoint((int) (radius * Math.cos(Math.toRadians(theta)) + offsetX), 
                     (int) (radius * Math.sin(Math.toRadians(theta)) + offsetY));
            theta += delta;
        }
    }
        
    @Override
    public void paintComponent(Graphics g) {
        // paint open polyline
        super.paintComponent(g); 
        
        // calculate closure line
        Line lineFirst = lines.get(0);
        Line lineLast  = lines.get(lines.size()-1);
        Line closure = new Line(lineLast.x2, lineLast.y2, lineFirst.x1, lineFirst.y1);
        
        // paint closure line
        closure.paintComponent(g);
    }
}
