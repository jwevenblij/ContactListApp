package bottombar.bottombarbuttons;

import com.thoughtworks.xstream.XStream;
import contactcard.ContactCardBase;
import contactcard.ContactList;
import contactcard.ContactPerson;
import mainwindow.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenFile extends JButton {

    public OpenFile() { addActionListeners(); }

    public void addActionListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XStream xStream = new XStream();
                xStream.allowTypesByWildcard(new String[] {"contactcard.ContactPerson"});

                String filePath = "";
                String importString = "";

                // Import the xml file with all contacts as a String
                try {
                    importString = Files.readString(Path.of("src/main/fullcontactlists/testFile.xml"));
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
                for (int i = 0; i < importStringArray.length - 2; i++) {
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
                SwingUtilities.updateComponentTreeUI(MainWindow.mainWindowFrame);
            }
        });
    }

}
