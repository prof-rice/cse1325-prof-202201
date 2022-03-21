package shapes;

import java.awt.Color;
import java.awt.BasicStroke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Rectangle extends Shape {
    public Rectangle(int x1, int y1, int x2, int y2, 
                     Color color, BasicStroke stroke) {
        super();
        super.addLine(new Line(x1, y1, x2, y1, color, stroke));
        super.addPoint(x2, y2);
        super.addPoint(x1, y2);
        super.addPoint(x1, y1);
    }
    
    // File I/O members
    public static String id() {return "Rectangle";}
    public Rectangle(BufferedReader br) throws IOException {
        super(br);
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        super.save(bw);
    }

    // Methods    
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
