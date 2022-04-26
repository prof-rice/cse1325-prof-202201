import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.TimerTask;

class Refresh extends TimerTask {
    public Refresh(MainWin mainwin, Canvas canvas) {
        this.mainwin = mainwin;
        this.canvas = canvas;
    }
    @Override
    public void run() {
        //System.out.print('p'); System.out.flush(); 
        if(canvas.changed) {
            canvas.changed = false;
            mainwin.repaint();
            // System.out.print('r'); System.out.flush(); 
        }
    } 
    JFrame mainwin;
    Canvas canvas;
}
public class MainWin extends JFrame {
    public MainWin() {
        setTitle("Mandelbrot Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas(1000, 1000);
        add(canvas);
        pack();
        setVisible(true);

        timer = new Timer(0, (event) -> new Mandelbrot(canvas));
        timer.setRepeats(false);
        timer.start();

        timerRefresh = new java.util.Timer();
        timerRefresh.schedule(new Refresh(this, canvas), 100, 100);
        //timerRefresh.setRepeats(true);
        //timerRefresh.start();
        System.out.print("C");
    }   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWin());
    }
    Timer timer;
    java.util.Timer timerRefresh;
}

