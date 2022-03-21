package shapes;

import java.awt.Color;
import java.awt.BasicStroke;

public class Turtle extends Shape {
    public enum Pen {UP, DOWN};
    
    public void pen(Pen newPen) {pen = newPen;}
    public void color(Color newColor) {color = newColor;}
    public void turn(double degrees) {
        angle += Math.toRadians(degrees);
        angle = Math.atan2(Math.sin(angle), Math.cos(angle)); // normalize
    }
    public void forward(double distance) {
        double x2 = x + distance*Math.cos(angle);
        double y2 = y + distance*Math.sin(angle);
        if(pen == Pen.DOWN)
            addLine(new Line((int)x, (int)y, (int)x2, (int)y2, color, stroke));
        x = x2;
        y = y2;
    }
    
    private double x, y, angle;
    private Pen pen;
    private Color color;
    private final BasicStroke stroke = new BasicStroke();
}
    
