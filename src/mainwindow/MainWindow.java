package mainwindow;

import tools.ColorPalette;
import bottombar.BottomBar;
import contactlist.ContactList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow {

    ContactList contactList = new ContactList();
    BottomBar bottomBar = new BottomBar();

    JFrame mainWindowFrame = new JFrame("My Contact List");

    public static int initialFrameWidth = 800;
    static int initialFrameHeight = 600;
    static Dimension initialAppSize = new Dimension(initialFrameWidth, initialFrameHeight);

    Color bgDark, bgLight, fgDark, fgLight;

    public MainWindow() {
        // Set color profile
        bgDark = ColorPalette.bgDark;
        bgLight = ColorPalette.bgLight;
        fgDark = ColorPalette.fgDark;
        fgLight = ColorPalette.fgLight;

        // Configure main window
        mainWindowFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindowFrame.setPreferredSize(initialAppSize);
        mainWindowFrame.setResizable(true);
        mainWindowFrame.setIconImage(setIconImage());
        mainWindowFrame.setLayout(new BorderLayout());
        mainWindowFrame.getContentPane().setBackground(bgDark);

        // Add panels to main window
        mainWindowFrame.add(ContactList.contactListJScrollPane, BorderLayout.NORTH);
        mainWindowFrame.add(BottomBar.bottomBarPanel, BorderLayout.SOUTH);

        // Finalize main window
        mainWindowFrame.pack();
        mainWindowFrame.setVisible(true);

    }

    // Set main window icon
    public static Image setIconImage() {

        Image iconImage = null;

        try {
            File pathToIconFile = new File("src/resources/Icon256.png");
            iconImage = ImageIO.read(pathToIconFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
        return iconImage;
    }

}
