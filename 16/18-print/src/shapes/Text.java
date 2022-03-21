package shapes;

import java.awt.Graphics;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Text extends Shape {
    public Text(String text, int x, int y, Color color) {
        super();
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    // File I/O members
    public static String id() {return "Text";}
    public Text(BufferedReader br, String fileVersion) throws IOException {
        super(br, fileVersion);
        text = br.readLine();
        x = Integer.parseInt(br.readLine());
        y = Integer.parseInt(br.readLine());
        color = new Color(Integer.parseInt(br.readLine()));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        super.save(bw);
        bw.write(text + "\n");
        bw.write("" + x + '\n');        
        bw.write("" + y + '\n');        
        bw.write("" + color.getRGB() + '\n');                
    }

    // Methods    
    @Override
    @Deprecated
    public void addLine(Line line) {
        throw new UnsupportedOperationException("Text has no lines!");
    }

    @Override
    @Deprecated
    public void addPoint(int x, int y) {
        throw new UnsupportedOperationException("Text has no lines!");
    }

    // Listeners
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.drawString(text, x, y);
    }
    
    String text;
    Color color;
    int x, y;
}
