package shapes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Shape {
    public Shape() { }
    public Shape(Line line) {
        this();
        addLine(line);
    }
    // File I/O members
    public static String id() {return "Shape";}
    public Shape(BufferedReader br, String fileVersion) throws IOException {
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) {
            String lineID = br.readLine();
            if(!lineID.equals(Line.id()))
                throw new IOException("Missing Line ID: " + lineID);
            lines.add(new Line(br, fileVersion));
        }
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        bw.write("" + lines.size() + '\n'); 
        for(Line line : lines) line.save(bw);
    }

    // Methods    
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
    
    // Listeners
    public void paintComponent(Graphics g) {
        for(Line line : lines) line.paintComponent(g);
    }
    
    // Attributes    
    protected ArrayList<Line> lines = new ArrayList<>();
}
