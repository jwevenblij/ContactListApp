package contactcard;

import tools.ColorPalette;
import mainwindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ContactList {

    public static JScrollPane contactListJScrollPane = new JScrollPane();
    static JPanel contactListJPanel = new JPanel();

    int heightIterator = 30;
    boolean addHeightOnOff = true;

    List<File> allContactsList;

    public ContactList() {
        // Configure contactListJPanel
        contactListJPanel.setLayout(new FlowLayout());
        contactListJPanel.setBackground(ColorPalette.bgDark);

        // Configure contactListJScrollPane
        contactListJScrollPane.setBackground(ColorPalette.bgDark);
        contactListJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactListJScrollPane.setViewportView(contactListJPanel);

        // Add entries to contactListJPanel
        try {
            allContactsList = Files.walk(Paths.get("src/main/contacts"))
                                            .filter(Files::isRegularFile)
                                            .map(Path::toFile)
                                            .toList();
        } catch(Exception e) {
            System.out.println(e);
        }

        for (File allContactsListItem : allContactsList) {
            JButton contactListEntryJButton = new JButton("kaas");
            contactListEntryJButton.setPreferredSize(new Dimension(615, 175));
            contactListJPanel.add(contactListEntryJButton);
            if (addHeightOnOff) {
                heightIterator += 175;
                addHeightOnOff = false;
            } else {
                addHeightOnOff = true;
            }
            contactListJPanel.setPreferredSize(new Dimension(0, heightIterator));
        }
    }
}
