package shapes;

import java.awt.Color;

public class Rectangle extends Shape {
    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        super();
        super.addLine(new Line(x1, y1, x2, y1, color));
        super.addPoint(x2, y2);
        super.addPoint(x1, y2);
        super.addPoint(x1, y1);
    }
    
    @Override
    @Deprecated
    public void addLine(Line line) {
        throw new UnsupportedOperationException("Rectangle has 4 sides max!");
    }

    @Override
    @Deprecated
    public void addPoint(int x, int y) {
        throw new UnsupportedOperationException("Rectangle has 4 sides max!");
    }   
}
