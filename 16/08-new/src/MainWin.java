import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainWin extends JFrame {
    public static final String TITLE = "CSE1325 Paint";
    public static final String MAGIC_COOKIE = "𝓒𝓼𝓮 ₁₃₂₅ 🎨 𝓟𝓪𝓲𝓷𝓽";
    public static final String VERSION = "0.8.0";
    public static final String FILE_VERSION = "0.1";

    public MainWin(String title) {
        super(title);

        setSize(1024, 768);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        // File menu
        
        JMenu     file       = new JMenu("File");
        
        // File > New
        JMenuItem newp       = new JMenuItem("New");        
        newp.addActionListener((ActionEvent event) -> onNewClick());
        file.add(newp);
        
        // File > Save
        JMenuItem save       = new JMenuItem("Save");        
        save.addActionListener((ActionEvent event) -> onSaveClick());
        file.add(save);
        
        // File > Quit
        JMenuItem quit       = new JMenuItem("Quit");        
        quit.addActionListener((ActionEvent event) -> onQuitClick());
        // Make clicking the 'x' the same as File > Quit
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onQuitClick(); // Call this when 'x' is clicked
            }
        });
        file.add(quit);
        
        menubar.add(file);
        
        // Pen menu
        
        // Pen > Color
        JMenu     pen         = new JMenu("Pen");
        JMenuItem color       = new JMenuItem("Color");        
        color.addActionListener((ActionEvent event) -> onColorClick());
        pen.add(color);
        
        // Pen > Width
        JMenuItem width       = new JMenuItem("Width");        
        width.addActionListener((ActionEvent event) -> onWidthClick());
        pen.add(width);
        
        menubar.add(pen);
        
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Paint Controls");

        // New button
        JButton newButton  = 
            new JButton(new ImageIcon("resources/new_file.png"));
        newButton.setActionCommand("New");
        newButton.setToolTipText("Create a new painting");
        toolbar.add(newButton);
        newButton.addActionListener((ActionEvent event) -> onNewClick());
        
        // Save button
        JButton saveButton  = 
            new JButton(new ImageIcon("resources/save.png"));
        saveButton.setActionCommand("Save");
        saveButton.setToolTipText("Save the painting to the current file");
        toolbar.add(saveButton);
        saveButton.addActionListener((ActionEvent event) -> onSaveClick());
        
        // A "horizontal strut" is just a space of the specified pixel width
       toolbar.add(Box.createHorizontalStrut(25));
  
        // Create the Color button
        JButton colorButton  = 
            new JButton(new ImageIcon("resources/color.png"));
        colorButton.setActionCommand("SelectPenColor");
        colorButton.setToolTipText("Select the pen color");
        toolbar.add(colorButton);
        colorButton.addActionListener((ActionEvent event) -> onColorClick());

        // Create the Width button
        JButton widthButton  = 
            new JButton(new ImageIcon("resources/width.png"));
        widthButton.setActionCommand("SelectPenWidth");
        widthButton.setToolTipText("Select the pen width");
        toolbar.add(widthButton);
        widthButton.addActionListener((ActionEvent event) -> onWidthClick());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
      
        // /////////////////////////// ////////////////////////////////////////////
        // C A N V A S
        // Provide a JPanel to act as the Canvas
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);
        
        // Make everything in the JFrame visible
        setVisible(true);
    }
    
    // Listeners
    protected void onNewClick() {
        if(okToExit()) {
            remove(canvas);
            canvas = new Canvas();
            add(canvas, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    protected void onSaveClick() {
        try (BufferedWriter bw = new BufferedWriter(
                                 new FileWriter(canvas.filename()))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            canvas.save(bw);
        } catch (Exception e) {
            JDialog dialog = 
                new JOptionPane("Unable to open " + canvas.filename() + '\n' + e, 
                    JOptionPane.ERROR_MESSAGE,
                    JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);             // Show the error dialog
            dialog.dispose();                    // Delete the dialog from memory
        }
    }
    
    protected void onQuitClick() {   // Exit the program
        if(okToExit()) dispose(); //System.exit(0);
    }
    
    protected void onColorClick() {   // Select a new pen color
        canvas.color(
            JColorChooser.showDialog(this, "Choose a color", canvas.color())
        );
    }
    
    protected void onWidthClick() {   // Select a new pen width
        // Configure a slider to select the pen width
        JSlider slider = new JSlider(1, 25, (int) canvas.width());
        slider.setMajorTickSpacing(3);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);

        // Use the slider as the "message" in an Option Dialog
        int choice = JOptionPane.showOptionDialog(this, 
            slider,
            "Select Line Width", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null,
            null, 
            null);

        if(choice == JOptionPane.OK_OPTION) {
            canvas.width((float) slider.getValue());
        }    
     }
    
    protected boolean okToExit() {  // Returns true if OK to exit
        if (!canvas.isDirty()) return true;  // OK to exit if data already saved
        int choice = JOptionPane.showOptionDialog(this,
            "Save data before exiting?",
            "Unsaved Data",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            null,
            null); 
        if (choice == JOptionPane.YES_OPTION) {
            onSaveClick();
            return !canvas.isDirty(); // OK to exit only is saved successfully
        } 
        if (choice == JOptionPane.NO_OPTION) {
            return true;     // OK to discard data and exit
        } 
        return false;  // Exit is not approved
    }       
   
    private Canvas canvas;
}
