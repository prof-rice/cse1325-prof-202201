package shapes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.util.ArrayList;

public class Shape {
    public Shape() { }
    public Shape(Line line) {
        this();
        addLine(line);
    }
    public void addLine(Line line) {
        lines.add(line);
    }
    public void addPoint(int x, int y) {
        if(lines.isEmpty()) {
            lines.add(new Line(x, y, x, y, Color.BLACK, new BasicStroke()));
        } else {
            Line lastLine = lines.get(lines.size()-1);
            Line line = new Line(lastLine.x2, lastLine.y2, x, y, 
                                 lastLine.color, lastLine.stroke);
            lines.add(line);
        }
    }   
    
    public void paintComponent(Graphics g) {
        for(Line line : lines) line.paintComponent(g);
    }
    
    protected ArrayList<Line> lines = new ArrayList<>();
}
