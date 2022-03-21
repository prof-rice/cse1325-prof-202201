import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.BasicStroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;;
import java.io.IOException;

import java.util.ArrayList;

import shapes.Line;
import shapes.Shape;
import shapes.Plot;
import shapes.Polygon;
import shapes.Rectangle;
import shapes.Turtle;

public class Canvas extends JPanel {

    public enum PenMode {SEGMENT, CONTIGUOUS, FREEHAND};

    public Canvas() {
        this(new File("untitled.paint"));
    }

    public Canvas(File filename) {
        super();

        this.filename = filename;
        penMode = PenMode.SEGMENT;       // Draw single shapes    
        isDirty = false;                 // No unsaved data
        rubberBand = null;               // No rubber band yet

        color = Color.RED;               // Make shapes red
        width = 1;                       // Lines are 1 pixel wide
        stroke = new BasicStroke();      // Use default stroke
        
        dashes = new ArrayList<>();      // Define dash patterns
        dashes.add(new float[]{16.0f});  // (replaced by null)
        dashes.add(new float[]{ 8.0f,  8.0f}); 
        dashes.add(new float[]{ 2.0f, 6.0f}); 
        dashes.add(new float[]{ 2.0f,  4.0f, 8.0f, 4.0f}); 
        
        dash(0);                   // Draw with a solid line
        
        // White background to our paintings by default
        setBackground(new java.awt.Color(255, 255, 255));
        
        // Manage mouse clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                onMousePressed(event);
            }
            @Override
            public void mouseReleased(MouseEvent event) {
                onMouseReleased(event);
            }
            @Override
            public void mouseClicked(MouseEvent event) {
                onMouseClicked(event);
            }
        });
        
        // Manage mouse motion
        addMouseMotionListener(new MouseAdapter() {
            // When all buttons are released
            @Override
            public void mouseMoved(MouseEvent event) {
                onMouseMoved(event);
            }
            // When any button is pressed
            @Override
            public void mouseDragged(MouseEvent event) {
                onMouseDragged(event);
            }
        });
        
        clickNumber = 0;  // Awaiting first click
    }
    
    // File I/O members
    public static String id() {return "Canvas";}
    public Canvas(BufferedReader br, String fileVersion) throws IOException {
        this();
        
        color = new Color(Integer.parseInt(br.readLine()));
        stroke = new BasicStroke(Float.parseFloat(br.readLine()));
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) {
            String shapeID = br.readLine();
            if      (shapeID.equals(Shape.id()))     shapes.add(new Shape(br, fileVersion));
            else if (shapeID.equals(Rectangle.id())) shapes.add(new Rectangle(br, fileVersion));
            else if (shapeID.equals(Plot.id()))      shapes.add(new Plot(br, fileVersion));
            else if (shapeID.equals(Turtle.id()))    shapes.add(new Turtle(br, fileVersion));
            else if (shapeID.equals(Polygon.id()))   shapes.add(new Polygon(br, fileVersion));
            else throw new IOException("Unknown shape ID: " + shapeID);

/*
            Fails because id() is not a "constant String expression"  :-(  
            switch(shapeID) {
                case Shape.id()     : shapes.add(new Shape(br, fileVersion));     break;
                case Rectangle.id() : shapes.add(new Rectangle(br, fileVersion)); break;
                case Plot.id()      : shapes.add(new Plot(br, fileVersion));      break;
                case Turtle.id()    : shapes.add(new Turtle(br, fileVersion));    break;
                case Polygon.id()   : shapes.add(new Polygon(br, fileVersion));   break;
                default: throw IOException("Unknown shape ID: " + shapeID);
            }
*/
        }
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        bw.write("" + color.getRGB() + '\n');          // Save the current color...      
        bw.write("" + stroke.getLineWidth() + '\n');   // ... and width
        bw.write("" + shapes.size() + '\n');           // Save the number of shapes
        for(Shape shape : shapes) shape.save(bw);      // Tell each shape to save itself
        isDirty = false;  // Success! No dirty data remains
    }

    // Methods
    public Dimension getPreferredSize() {
        return new Dimension(1024,768);
    }

    // /////////////////////////////////////////////////////////////////
    // Listeners
    
    // All modes except Freehand
    public void onMouseClicked(MouseEvent event) {
        // Single click with primary button
        if ((penMode != PenMode.FREEHAND) &&
            (event.getButton() == MouseEvent.BUTTON1) &&
            (event.getClickCount() == 1)) {
            
            clickNumber++;
            int x2 = event.getX() - xOffset;
            int y2 = event.getY() - yOffset;
            
            // If first click, wait for second click
            if (clickNumber == 1) { // second click
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
            // If second click, create the Shape
            if (clickNumber == 2) { // second click
                shapes.add(new Shape(      // add 1st segment
                    new Line(x1, y1, x2, y2, color, stroke)
                ));
                
                // If drawing segments, we're done
                if (penMode == PenMode.SEGMENT) {
                    clickNumber = 0;   // Start over on next click
                    rubberBand = null; // No more rubber banding
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                
                // Unsaved data is now present
                isDirty = true;
            }
                
            // If third or later click, 
            //   If mouse moved, add point to last Shape and continue
            //   Otherwise, we're done
            if (clickNumber >= 3) { 
                if(x1 != x2 || y1 != y2) { 
                    shapes.get(shapes.size()-1).addPoint(x2, y2);
                } else {
                    clickNumber = 0;
                    rubberBand = null;
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
            
            x1 = x2; // Remember where this click occurred 
            y1 = y2;
            repaint(); // request call to paintComponent
        }
    }

    public void onMouseMoved(MouseEvent event) {
        if(penMode != PenMode.FREEHAND) {
            // Single click with primary button
            if(clickNumber > 0) { // second or later click pending
                rubberBand = new Shape(
                        new Line(x1, y1, 
                                 event.getX() - xOffset,
                                 event.getY() - yOffset,
                                 color, stroke)
                );
                repaint(); // request call to paintComponent
            }        
        }
    }

    // Freehand Mode
    public void onMousePressed(MouseEvent event) {
        if(penMode == PenMode.FREEHAND) {
            int x2 = event.getX() - xOffset;
            int y2 = event.getY() - yOffset;

            shapes.add(new Shape(      // add 1st segment
                new Line(x2, y2, x2, y2, color, stroke)
            ));

            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

            repaint(); // request call to paintComponent
        }
    }

    public void onMouseDragged(MouseEvent event) {
        if(penMode == PenMode.FREEHAND) {

            int x2 = event.getX() - xOffset;
            int y2 = event.getY() - yOffset;

            shapes.get(shapes.size()-1).addPoint(x2, y2);

            x1 = x2; // Remember where this click occurred 
            y1 = y2;

            repaint(); // request call to paintComponent
        }
    }

    public void onMouseReleased(MouseEvent event) {
        if(penMode == PenMode.FREEHAND) {
             setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    // All modes
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
             

        // Move origin to center
        java.awt.Rectangle size = getBounds();
        xOffset = size.width / 2;
        yOffset = size.height/2;
        g.translate(xOffset, yOffset);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
            
        // Paint the temporary shape that "rubber bands" a pending line
        if(rubberBand != null) rubberBand.paintComponent(g);
    }
    
    // ////////////////////////////////////////////////////////////////
    // Attributes, Getters and Setters
    private File filename;        // Filename to which this Canvas will be saved
    public File filename() {return filename;}
    public void filename(File filename) {this.filename = filename;}
    
    private boolean isDirty;      // True if we have unsaved data
    public boolean isDirty() {return isDirty;}
    
    private Color color;          // Color for upcoming lines to be drawn
    public Color color() {return color;}
    public void color(Color newColor) {color = newColor;}
    
    private BasicStroke stroke;   // width and dash pattern
    private void updateStroke() {
        float[] dashArray;
        if(dashIndex == 0) dashArray = null;
        else {
            dashArray = new float[dashes.get(dashIndex).length];
            for(int i=0; i<dashArray.length; ++i)
                dashArray[i] = dashes.get(dashIndex)[i] * width;
        }        
        stroke = new BasicStroke(
                width, 
                stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), 
                dashArray, 
                stroke.getDashPhase());
    }
    
    private float width;
    public float width() {return width;}
    public void width(float newWidth) {
        width = newWidth;
        updateStroke();
    }

    private int dashIndex;
    private ArrayList<float[]> dashes;
    public int dash() {return dashIndex;}
    public void dash(int newIndex) {
        dashIndex = Math.max(0, Math.min(dashes.size()-1, newIndex));
        updateStroke();
    }

    private PenMode penMode;      // Drawing segments, contiguous lines, scribbles?
    public PenMode penMode() {return penMode;}
    public void penMode(PenMode newPenMode) {penMode = newPenMode;}
    
    private int x1, y1;           // Location of previous click
    private int xOffset, yOffset; // Amount of translation to center the origin
    private int clickNumber;      // number of clicks since start of line
     
    protected ArrayList<Shape> shapes = new ArrayList<>();
    
    protected Shape rubberBand;   // Temporary Shape showing where next object will be

}
