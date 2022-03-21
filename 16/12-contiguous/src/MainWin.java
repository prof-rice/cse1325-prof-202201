import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class MainWin extends JFrame {
    public static final String TITLE = "CSE1325 Paint";
    public static final String MAGIC_COOKIE = "𝓒𝓼𝓮 ₁₃₂₅ 🎨 𝓟𝓪𝓲𝓷𝓽";
    public static final String VERSION = "0.12.0";
    public static final String FILE_VERSION = "0.1";
    public static final String EXTENSION = "paint";

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
        
        // File > Open
        JMenuItem open       = new JMenuItem("Open");        
        open.addActionListener((ActionEvent event) -> onOpenClick());
        file.add(open);
        
        // File > Save
        JMenuItem save       = new JMenuItem("Save");        
        save.addActionListener((ActionEvent event) -> onSaveClick());
        file.add(save);
        
        // File > Save As
        JMenuItem saveAs     = new JMenuItem("Save As");        
        saveAs.addActionListener((ActionEvent event) -> onSaveAsClick());
        file.add(saveAs);
        
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
        
        // Separator
        pen.add(new JSeparator());
        
        // Pen > Segment
        JMenuItem segment     = new JMenuItem("Single Lines");        
        segment.addActionListener((ActionEvent event) -> onSegmentClick());
        pen.add(segment);
       
        // Pen > Contiguous
        JMenuItem contiguous  = new JMenuItem("Connected Lines");        
        contiguous.addActionListener((ActionEvent event) -> onContiguousClick());
        pen.add(contiguous);
       
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
        
        // Open button
        JButton openButton  = 
            new JButton(new ImageIcon("resources/open.png"));
        openButton.setActionCommand("Open");
        openButton.setToolTipText("Open a painting from a file");
        toolbar.add(openButton);
        openButton.addActionListener((ActionEvent event) -> onOpenClick());
        
        // Save button
        JButton saveButton  = 
            new JButton(new ImageIcon("resources/save.png"));
        saveButton.setActionCommand("Save");
        saveButton.setToolTipText("Save the painting to the current file");
        toolbar.add(saveButton);
        saveButton.addActionListener((ActionEvent event) -> onSaveClick());

        // Save As button
        JButton saveAsButton  = 
            new JButton(new ImageIcon("resources/save_as.png"));
        saveAsButton.setActionCommand("Save As");
        saveAsButton.setToolTipText("Save the painting to a different file");
        toolbar.add(saveAsButton);
        saveAsButton.addActionListener((ActionEvent event) -> onSaveAsClick());

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

        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));
  
        ButtonGroup penModeGroup = new ButtonGroup();
        
        // Create the Segment toggle button (acts like a radio button)
        segmentButton  = 
            new JToggleButton(new ImageIcon("resources/segment.png"), true);
        segmentButton.setActionCommand("SelectSegment");
        segmentButton.setToolTipText("Draw single lines (click-click)");
        penModeGroup.add(segmentButton);
        toolbar.add(segmentButton);
        segmentButton.addActionListener((ActionEvent event) -> onSegmentClick());
        
        // Create the Contiguous toggle button (acts like a radio button)
        contiguousButton  = 
            new JToggleButton(new ImageIcon("resources/contiguous.png"));
        contiguousButton.setActionCommand("SelectContiguous");
        contiguousButton.setToolTipText("Draw connected lines (click-click-click…)");
        penModeGroup.add(contiguousButton);
        toolbar.add(contiguousButton);
        contiguousButton.addActionListener((ActionEvent event) -> onContiguousClick());
        

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
            if(canvas != null) {remove(canvas); canvas = null;}  // Don't delete twice
            canvas = new Canvas();
            add(canvas, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    protected void onOpenClick() {
        // Don't discard unsaved data without permission
        if(!okToExit()) return;  

        // Identify a default filename
        File filename = new File("untitled." + EXTENSION);;

        // If canvas exists, get filename from it instead, then remove it
        if(canvas != null) {
            filename = canvas.filename();
            remove(canvas); // Don't delete twice
            canvas = null;
        }

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter paintFiles = new FileNameExtensionFilter("Paint files", EXTENSION);
        fc.addChoosableFileFilter(paintFiles);
        fc.setFileFilter(paintFiles);  // Show CSE1325 Paint files only by default
        
        // Run the dialog and return the button clicked
        int result = fc.showOpenDialog(this);
        
        // If the user clicked OK, set the filename; if Cancel, just return
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
         
        // Preset error message (what a pessimist!)
        // This allows us to capture the line number in our data file
        //   in the event of an error via LineNumberReader
        String errorMessage = "Unable to open " + filename;
        try (LineNumberReader br = new LineNumberReader(new FileReader(filename))) {
            try {
                // Verify that this is indeed a CSE1325 Paint-generated file
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) 
                    throw new IOException("Not a " + TITLE + " file");
                    
                // Verify that the file format is known (i.e., version is <= to our version)
                String fileVersion = br.readLine();
                if(fileVersion.compareTo(FILE_VERSION) > 0) 
                    throw new IOException("Incompatible file format version " + fileVersion);
                
                // Verify that a canvas was saved (written by Canvas.save)
                String programName = br.readLine();
                if(!programName.equals(Canvas.id())) // Was this added by Canvas.save?
                    throw new IOException("Missing '" + Canvas.id() + "' ID");
                
                // Instance a new Canvas from the file stream
                canvas = new Canvas(br, fileVersion);
                add(canvas, BorderLayout.CENTER);
                revalidate();
                repaint();
                
            // On a read error from opened file, capture the line number and rethrow
            } catch (Exception e) {
                if (br != null) 
                    errorMessage = " Unable to read " + filename 
                                 + " at line " + br.getLineNumber();
                throw e;
            }
        // On any error (file open OR read), report to the user and clean up
        } catch (Exception e) {
            JDialog dialog = new JOptionPane(
                errorMessage + '\n' + e, 
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);        // Show the error dialog
            dialog.dispose();               // Delete the dialog from memory

            canvas = null;  // Discard possibly damaged Canvas
            onNewClick();   // Create a new, clean Canvas
        }
    }

    protected void onSaveClick() {
        // Get canvas' current filename
        File filename = canvas.filename();
        
        // Open that file for writing
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');  // Save a cookie (to recognize Paint files)
            bw.write(FILE_VERSION + '\n');  // Save the file version (for later use)
            canvas.save(bw);                // Save the painting
            
        // If an exception occurred, report it to the user
        } catch (Exception e) {
            JDialog dialog = 
                new JOptionPane("Unable to open " + filename + '\n' + e, 
                    JOptionPane.ERROR_MESSAGE,
                    JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);        // Show the error dialog
            dialog.dispose();               // Delete the dialog from memory
        }
    }

    protected void onSaveAsClick() {
        // Identify a default filename
        File filename = canvas.filename();

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter paintFiles = new FileNameExtensionFilter("Paint files", EXTENSION);
        fc.addChoosableFileFilter(paintFiles);
        fc.setFileFilter(paintFiles);  // Show CSE1325 Paint files only by default
        
        // Run the dialog and return the button clicked
        int result = fc.showSaveDialog(this);
        
        // If user clicked OK, get the filename
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();

            // If the filename is missing / incorrect, append it
            if(!filename.getPath().toUpperCase().endsWith(EXTENSION.toUpperCase()))
                filename = new File(filename.getPath() + '.' + EXTENSION);

            // Set canvas' current filename to this, then save to current filename
            canvas.filename(filename);
            onSaveClick();
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
    
    protected void onSegmentClick() {   // Draw single lines
        System.err.print("Segment ");
        canvas.penMode(Canvas.PenMode.SEGMENT);
        segmentButton.setSelected(true); // This does NOT trigger an event
    }

    protected void onContiguousClick() {   // Draw connected lines
        System.err.print("Contiguous ");
        canvas.penMode(Canvas.PenMode.CONTIGUOUS);
        contiguousButton.setSelected(true); // This does NOT trigger an event
    }

    protected boolean okToExit() {  // Returns true if OK to exit
        if (canvas == null) return true;     // OK to exit if no Canvas
        if (!canvas.isDirty()) return true;  // OK to exit if canvas already saved
        int choice = JOptionPane.showOptionDialog(this,
            "Save before discarding canvas?",
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
    
    private JToggleButton segmentButton;
    private JToggleButton contiguousButton;
}
