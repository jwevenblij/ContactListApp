package mainwindow;

import tools.ColorPalette;
import bottombar.BottomBar;
import contactcard.ContactList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow {

    ContactList contactList = new ContactList();
    BottomBar bottomBar = new BottomBar();

    public static JFrame mainWindowFrame = new JFrame("My Contact List");

    public static int initialFrameWidth = 1280;
    public static int initialFrameHeight = 720;
    static Dimension initialAppSize = new Dimension(initialFrameWidth, initialFrameHeight);

    Color bgDark, bgLight, fgDark, fgLight;

    public MainWindow() {
        // Set color profile
        bgDark = ColorPalette.bgDark;
        bgLight = ColorPalette.bgLight;
        fgDark = ColorPalette.fgDark;
        fgLight = ColorPalette.fgLight;

        // Configure main window
        mainWindowFrame.setLayout(new BorderLayout());
        mainWindowFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindowFrame.setPreferredSize(initialAppSize);
        mainWindowFrame.setResizable(false);
        mainWindowFrame.setIconImage(setIconImage());
//        mainWindowFrame.getContentPane().setBackground(bgDark);

        // Add panels to main window
        mainWindowFrame.add(ContactList.contactListJScrollPane, BorderLayout.CENTER);
        mainWindowFrame.add(BottomBar.bottomBarJPanel, BorderLayout.SOUTH);

        // Finalize main window
        packAndSetVisible();
    }

    // Pack and setVisible main window
    public static void packAndSetVisible() {
        mainWindowFrame.pack();
        mainWindowFrame.setVisible(true);
    }

    // Set main window icon
    public static Image setIconImage() {

        Image iconImage = null;

        try {
            File pathToIconFile = new File("src/main/java/resources/Icon256.png");
            iconImage = ImageIO.read(pathToIconFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return iconImage;
    }

}
