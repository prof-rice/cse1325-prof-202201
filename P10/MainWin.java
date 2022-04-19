import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComponent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;

import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import shelter.Shelter;
import shelter.Client;
import shelter.Animal;
import shelter.Dog;
import shelter.DogBreed;
import shelter.Cat;
import shelter.CatBreed;
import shelter.GuineaPig;
import shelter.GuineaPigBreed;
import shelter.Gender;

public class MainWin extends JFrame {
    private void setBorder(JComponent component) {
        // component.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        component.setBorder(BorderFactory.createEmptyBorder());
    }
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file        = new JMenu("File");
        JMenuItem anew        = new JMenuItem("New Shelter");
        JMenuItem open        = new JMenuItem("Open Shelter");
        JMenuItem save        = new JMenuItem("Save Shelter");
        JMenuItem saveas      = new JMenuItem("Save Shelter As");
        JMenuItem quit        = new JMenuItem("Quit");
        JMenu     animal      = new JMenu("Animal");
        JMenuItem newDog      = new JMenuItem("New Dog");
        JMenuItem newCat      = new JMenuItem("New Cat");
        JMenuItem newGP       = new JMenuItem("New Guinea Pig");
        JMenuItem listAnimals = new JMenuItem("List Available");
        JMenu     client      = new JMenu("Client");
        JMenuItem newClient   = new JMenuItem("New Client");
        JMenuItem listClients = new JMenuItem("List Clients");
        JMenu     help        = new JMenu("Help");
        JMenuItem rules       = new JMenuItem("Rules");
        JMenuItem about       = new JMenuItem("About");
        
        anew       .addActionListener(event -> onNewShelterAsClick());
        open       .addActionListener(event -> onOpenShelterClick());
        save       .addActionListener(event -> onSaveShelterClick());
        saveas     .addActionListener(event -> onSaveShelterAsClick());
        quit       .addActionListener(event -> onQuitClick());
        newDog     .addActionListener(event -> onNewDogClick());
        newCat     .addActionListener(event -> onNewCatClick());
        newGP      .addActionListener(event -> onNewGPClick());
        listAnimals.addActionListener(event -> updateDisplay(DataView.ANIMALS));
        newClient  .addActionListener(event -> onNewClientClick());
        listClients.addActionListener(event -> updateDisplay(DataView.CLIENTS));
        about      .addActionListener(event -> onAboutClick());

        file.add(anew);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(quit);
        animal.add(newDog);
        animal.add(newCat);
        animal.add(newGP);
        animal.add(listAnimals);
        client.add(newClient);
        client.add(listClients);
        help.add(about);
        
        menubar.add(file);
        menubar.add(animal);
        menubar.add(client);
        menubar.add(help);
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("MASS Commands");

        // Add a New Shelter stock icon
        JButton anewB  = new JButton(new ImageIcon("resources/file_new.png"));
          anewB.setActionCommand("New Shelter");
          anewB.setToolTipText("Create a new shelter, discarding any currently open");
          setBorder(anewB);
          toolbar.add(anewB);
          anewB.addActionListener(event -> onNewShelterAsClick());

        toolbar.add(Box.createHorizontalStrut(4));
                
        // Add an Open Shelter icon
        JButton openB  = new JButton(new ImageIcon("resources/file_open.png"));
          openB.setActionCommand("Open Shelter");
          openB.setToolTipText("Open a shelter file, discarding any currently open");
          setBorder(openB);
          toolbar.add(openB);
          openB.addActionListener(event -> onOpenShelterClick());
        
        toolbar.add(Box.createHorizontalStrut(4));
                
        // Add a Save Shelter icon
        JButton saveB  = new JButton(new ImageIcon("resources/file_save.png"));
          saveB.setActionCommand("Save Shelter");
          saveB.setToolTipText("Save shelter data");
          setBorder(saveB);
          toolbar.add(saveB);
          saveB.addActionListener(event -> onSaveShelterClick());
        
        toolbar.add(Box.createHorizontalStrut(4));
                
        // Add a Save Shelter As icon
        JButton saveAsB  = new JButton(new ImageIcon("resources/file_save_as.png"));
          saveAsB.setActionCommand("Save Shelter As");
          saveAsB.setToolTipText("Save shelter data as new file");
          setBorder(saveAsB);
          toolbar.add(saveAsB);
          saveAsB.addActionListener(event -> onSaveShelterAsClick());
        
        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(24));
        
        // Create the 3 buttons using the icons provided
        JButton buttonNewDog  = new JButton(new ImageIcon("resources/new_dog.png"));
          buttonNewDog.setActionCommand("New dog");
          buttonNewDog.setToolTipText("Add a new dog");
          setBorder(buttonNewDog);
          toolbar.add(buttonNewDog);
          buttonNewDog.addActionListener(event -> onNewDogClick());

        toolbar.add(Box.createHorizontalStrut(4));

        JButton buttonNewCat  = new JButton(new ImageIcon("resources/new_cat.png"));
          buttonNewCat.setActionCommand("New cat");
          buttonNewCat.setToolTipText("Add a new cat");
          setBorder(buttonNewCat);
          toolbar.add(buttonNewCat);
          buttonNewCat.addActionListener(event -> onNewCatClick());

        toolbar.add(Box.createHorizontalStrut(4));

        JButton buttonNewGP  = new JButton(new ImageIcon("resources/new_GP.png"));
          buttonNewGP.setActionCommand("New guinea pig");
          buttonNewGP.setToolTipText("Add a new guinea pig");
          setBorder(buttonNewGP);
          toolbar.add(buttonNewGP);
          buttonNewGP.addActionListener(event -> onNewGPClick());

        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(24));
        
        JButton buttonNewClient  = new JButton(new ImageIcon("resources/new_client.png"));
          buttonNewClient.setActionCommand("New client");
          buttonNewClient.setToolTipText("Add a new client");
          setBorder(buttonNewClient);
          toolbar.add(buttonNewClient);
          buttonNewClient.addActionListener(event -> onNewClientClick());
        
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        
        // /////////////////////////// ////////////////////////////////////////////
        // D A T A   D I S P L A Y
        // Provide a text entry box to show data
        data = new JLabel();
        data.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(data, BorderLayout.CENTER);

        // Just for fun, a cute dog / cat splash image 
        //   (removed on first data, see updateDisplay())
        try {
            data.setHorizontalAlignment(JLabel.CENTER);
            BufferedImage pic = ImageIO.read(new File("resources/cat-and-dog.jpg"));
            ImageIcon icon = new ImageIcon(pic);
            data.setIcon(icon);
        } catch(IOException e) {
        }

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);

        // Make everything in the JFrame visible
        setVisible(true);
        
        onNewShelterClick("Mav's Animal Shelter");
    }
    
    // ///////////////////////////////
    // L I S T E N E R S
    public void onNewShelterAsClick() {
        String name = JOptionPane.showInputDialog(
            this, 
            "Enter the new shelter's name", 
            "Shelter Name", 
            JOptionPane.QUESTION_MESSAGE);
        if(name != null && name.length() > 0) {
            onNewShelterClick(name);
            setTitle("MASS - " + name);
        }
    }
    public void onNewShelterClick(String name) {
        shelter = new Shelter(name);
        shelter.setFilename("Untitled.mass");
        updateDisplay(DataView.ANIMALS);
    }
    public void onOpenShelterClick() {
        File filename = new File(shelter.getFilename());
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter massFiles = new FileNameExtensionFilter("MASS files", "mass");
        fc.addChoosableFileFilter(massFiles);
        fc.setFileFilter(massFiles);
        
        int result = fc.showOpenDialog(this); 
        // Also available: CANCEL_OPTION and ERROR_OPTION
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile(); 
            
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                // String magicCookie = br.readLine();
                // if(!magicCookie.equals(MAGIC_COOKIE)) throw new RuntimeException("Not a Mass file");
                // String fileVersion = br.readLine();
                // if(!fileVersion.equals(FILE_VERSION)) throw new RuntimeException("Incompatible Mass file format");
                
                shelter = new Shelter(br); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Unable to open " + filename + '\n' + e, 
                    "Failed", JOptionPane.ERROR_MESSAGE); 
             } 
             updateDisplay(DataView.ANIMALS);
        }
    }

    public void onSaveShelterClick() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(shelter.getFilename())))) {
            // bw.write(MAGIC_COOKIE + '\n');
            // bw.write(FILE_VERSION + '\n');
            shelter.save(bw);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open " + shelter.getFilename() + '\n' + e,
                "Failed", JOptionPane.ERROR_MESSAGE); 
        }
    }

    public void onSaveShelterAsClick() {
        File filename = new File(shelter.getFilename());
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter massFiles = new FileNameExtensionFilter("Mass files", "mass");
        fc.addChoosableFileFilter(massFiles); 
        fc.setFileFilter(massFiles);
        
        int result = fc.showSaveDialog(this);
        // Also available: CANCEL_OPTION and ERROR_OPTION
        if (result == JFileChooser.APPROVE_OPTION) { 
            filename = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith(".mass"))
                filename = new File(filename.getAbsolutePath() + ".mass");
            shelter.setFilename(filename.getAbsolutePath());
            onSaveShelterClick(); 
        }    
    }
    
    // Generic animal dialog

    // Listeners
    private JLabel breed = new JLabel("Breed");

    private JLabel name = new JLabel("<HTML><br/>Name</HTML>");
    private JTextField names = new JTextField(20);
        
    private JLabel gender = new JLabel("<HTML><br/>Gender</HTML>");
    private JComboBox genders = new JComboBox(Gender.values());
        
    private JLabel age = new JLabel("<HTML><br/>Age</HTML>");
    private SpinnerModel range = new SpinnerNumberModel(0, 0, 100, 1);
    private JSpinner ages = new JSpinner(range);

    private <T extends Animal> void newAnimal(T animal, JComboBox breeds) {

        Object[] objects = { // Array of widgets to display
            breed, breeds, name, names, gender, genders, age, ages};
        
        int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "New Animal",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) {
            animal.create(breeds.getSelectedItem(), names.getText(), 
                         (Gender) genders.getSelectedItem(), (int) ages.getValue()
            );
            shelter.addAnimal(animal);
            updateDisplay(DataView.ANIMALS);
        }
    }

   protected void onNewDogClick() { 
        newAnimal(new Dog(), new JComboBox(DogBreed.values()));
    }
   protected void onNewCatClick() { 
        newAnimal(new Cat(), new JComboBox(CatBreed.values()));
    }
   protected void onNewGPClick() { 
        newAnimal(new GuineaPig(), new JComboBox(GuineaPigBreed.values()));
    }
    
    protected void onNewClientClick() {
        JLabel name = new JLabel("Name");
        JTextField tfName = new JTextField(20);
        JLabel phone = new JLabel("<html><br/>Phone<html>");
        JTextField tfPhone = new JTextField(20);
        
        Object[] objects = { // Array of widgets to display
            name, tfName, phone, tfPhone};
        
        int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "New Client",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) {
            shelter.addClient(new Client(tfName.getText(), tfPhone.getText()));
            updateDisplay(DataView.CLIENTS);
        }
    }
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("Mav's Animal Shelter Software");
        
        try {
            BufferedImage pic = ImageIO.read(new File("cat-and-dog.jpg"));
            JLabel logo = new JLabel(new ImageIcon(pic));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>MASS</font></p>"
          + "</html>");
        JPanel titlePanel = new JPanel();
        titlePanel.add(title);
        info.add(titlePanel);

        JLabel subtitle = new JLabel("<html>"
          + "<p><font size=+1>Mavs Animal Shelter Software</font></p>"
          + "</html>");
        JPanel subtitlePanel = new JPanel();
        subtitlePanel.add(subtitle);
        info.add(subtitlePanel);

        JLabel artists = new JLabel("<html></br>"
          + "<p>Version 1.0</p>"
          + "<p>Copyright 2022 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by Anna Langova, CC0 Public Domain</p>"
          + "<p><font size=-2>https://www.publicdomainpictures.net/<br/>en/view-image.php?image=24076</font></p>"
          + "<br/>"
          + "<p><a href=\"https://www.flaticon.com/free-icons/dog\" title=\"dog icons\">Dog icons created by Freepik - Flaticon</a></p>"
          + "<p><a href=\"https://www.flaticon.com/free-icons/cat\" title=\"cat icons\">Cat icons created by justicon - Flaticon</a></p>"  
          + "<p><a href=\"https://www.flaticon.com/free-icons/guinea-pig\" title=\"guinea pig icons\">Guinea pig icons created by Freepik - Flaticon</a>"
          + "<a href=\"https://www.flaticon.com/free-icons/client\" title=\"client icons\">Client icons created by Freepik - Flaticon</a>"
          + "<br/><br/></html>");
        info.add(artists); 

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        JPanel okPanel = new JPanel();
        okPanel.add(ok);
        info.add(okPanel);
        
        about.add(info);
        
        about.pack();
        about.setVisible(true);
     }
    protected void onQuitClick() {
        System.exit(0);
    }

    // Update the data display in the main window
    private void updateDisplay(DataView view) {
        data.setIcon(null);  // remove the splash image if present
        data.setHorizontalAlignment(JLabel.LEFT);
        data.setVerticalAlignment(JLabel.TOP);
        String result = (view == DataView.ANIMALS) 
                      ? shelter.toString()
                      : shelter.clientsToString();
        data.setText("<html>" + result.replaceAll("<","&lt;")
                                      .replaceAll(">", "&gt;")
                                      .replaceAll("\n", "<br/>")
                              + "</html>");
        data.revalidate(); // Not usually required
    }

    private Shelter shelter;
    
    private JLabel data;                    // Display of data
    private enum DataView {ANIMALS, CLIENTS};
    private JLabel msg;                     // Status message display
}
