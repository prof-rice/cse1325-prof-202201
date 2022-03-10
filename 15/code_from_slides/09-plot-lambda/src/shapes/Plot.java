package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Plot extends Shape {
    public Plot(FunctionToPlot f, int xOrigin, int yOrigin, double xFirst, double xLast, int count, double xScale, double yScale) {
        // Plot f = new Plot(ftp,        -250,           0,             0,       3.14*6,       160,            25,           250);
        double x = xFirst;
        double delta = (xLast - xFirst) / (double) count;
        while(count-- > 0) {
            int xNew = (int) ((double) xOrigin + x       * xScale);
            int yNew = (int) ((double) yOrigin + f.fn(x) * yScale);
            addPoint(xNew, yNew);
            x += delta;
        }
    }
}
