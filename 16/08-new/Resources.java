import javax.swing.UIManager;
import javax.swing.UIDefaults;
import javax.swing.Icon;

import java.util.Enumeration;

// This program prints all Icons available in UIManager
public class Resources {
    public static void main(String[] args) throws Exception {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        for ( Enumeration e = defaults.keys(); e.hasMoreElements(); ) {
            Object key = e.nextElement();
            Object value = defaults.get(key);

            if (value instanceof Icon) System.out.println(key);
        }
    }
}
