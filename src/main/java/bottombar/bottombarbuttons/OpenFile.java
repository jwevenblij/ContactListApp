package bottombar.bottombarbuttons;

import com.thoughtworks.xstream.XStream;
import contactcard.ContactList;
import contactcard.ContactPerson;
import mainwindow.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class OpenFile extends JButton {

    public OpenFile() { addActionListeners(); }

    public void addActionListeners() {
        this.addActionListener(e -> {
            XStream xStream = new XStream();
            xStream.allowTypesByWildcard(new String[] {"contactcard.ContactPerson"});

            String filePath = "";
            String importString = "";

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "XML files", "xml");
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileFilter(filter);
            fileChooser.addChoosableFileFilter(filter);

            int returnVal = fileChooser.showOpenDialog(null);

            DeleteAllCards.clearAllContacts();

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                // Import the xml file with all contacts as a String
                try {
                    importString = Files.readString(selectedFile.toPath(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Split the imported String into individual contacts and assign to Array
                String[] importStringArray = importString.split("</contactc", 0);

                // Properly format Array for xStream usage
                for (int i = 0; i < importStringArray.length - 1; i++) {
                    if (i == 0) {
                        importStringArray[i] = importStringArray[i].substring(1) + "</contactcard.ContactPerson>";
                    } else if (i == importStringArray.length - 1) {

                    } else {
                        importStringArray[i] = importStringArray[i].substring(20) + "</contactcard.ContactPerson>";
                    }
                }

                // Save individual contacts to folder
                for (int i = 0; i < importStringArray.length - 1; i++) {
                    ContactPerson currentContact = (ContactPerson)xStream.fromXML(importStringArray[i]);

                    filePath = ("src/main/contacts/" +
                            currentContact.getFirstName() + "_" +
                            currentContact.getLastName() + "_" +
                            currentContact.getContactID() + ".xml");

                    try {
                        String xml = xStream.toXML(currentContact);
                        FileWriter fileWriter = new FileWriter(filePath);
                        fileWriter.write(xml);
                        fileWriter.close();
                    } catch (Exception f) {
                        System.out.println(f);
                    }
                }

                // Redraw the ContactList canvas
                ContactList.addEntriesToContactListJPanel();
                MainWindow.mainWindowFrame.pack();

            } else if (returnVal == JFileChooser.ERROR_OPTION) {
                JOptionPane.showMessageDialog(null, "Selected file format is not XML", "Incorrect file format", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
