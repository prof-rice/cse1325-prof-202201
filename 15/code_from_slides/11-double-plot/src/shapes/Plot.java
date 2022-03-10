package shapes;

import java.awt.Graphics;
import java.util.ArrayList;

public class Plot extends Shape {
    public Plot(FunctionToPlot f, int xOrigin, int yOrigin, double xFirst, double xLast, int count, double xScale, double yScale) {
        this(x -> x, f, xOrigin, yOrigin, xFirst, xLast, count, xScale, yScale);
        /*  Use constructor chaining (above) rather than duplicating the code (below)!
        double x = xFirst;
        double delta = (xLast - xFirst) / (double) count;
        while(count-- > 0) {
            int xNew = (int) ((double) xOrigin + x       * xScale);
            int yNew = (int) ((double) yOrigin + f.fn(x) * yScale);
            addPoint(xNew, yNew);
            x += delta;
        }
        */
    }
    public Plot(FunctionToPlot fx, FunctionToPlot fy, int xOrigin, int yOrigin, double xFirst, double xLast, int count, double xScale, double yScale) {
        double x = xFirst;
        double delta = (xLast - xFirst) / (double) count;
        while(count-- > 0) {
            int xNew = (int) ((double) xOrigin + fx.fn(x) * xScale);
            int yNew = (int) ((double) yOrigin + fy.fn(x) * yScale);
            addPoint(xNew, yNew);
            x += delta;
        }
    }
}
