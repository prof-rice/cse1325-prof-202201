package shapes;

import java.awt.Color;
import java.awt.BasicStroke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Turtle extends Shape {
    public Turtle() {
        super();
    }
    
    // File I/O members
    public static String id() {return "Turtle";}
    public Turtle(BufferedReader br) throws IOException {
        super(br);
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        super.save(bw);
    }

    // Methods    
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
    
    // Attributes    
    private double x, y, angle;
    private Pen pen;
    private Color color;
    private final BasicStroke stroke = new BasicStroke();
}
    
