package bottombar.bottombarbuttons;

import contactcard.ContactList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SaveFile extends JButton {

    public SaveFile() { addActionListeners(); }

    public void addActionListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<File> allContactsList = ContactList.allContactsList;
                ArrayList<String> contactListFileArray = new ArrayList<>();

                // Iterate over each individual contact.xml and apply to String
                for (File allContactsListItem : allContactsList) {
                    String xmlIndividualContact = "";

                    try {
                        xmlIndividualContact = Files.readString(allContactsListItem.toPath(), StandardCharsets.UTF_8);
                    } catch (Exception i) {
                        System.out.println(i);
                    }

                    // Add current contact to ArrayList
                    contactListFileArray.add(xmlIndividualContact);
                }

                try {
                    FileWriter fileWriter = new FileWriter("src/main/fullcontactlists/testFile.xml");
                    fileWriter.write(String.valueOf(contactListFileArray));
                    fileWriter.close();
                } catch (IOException j) {
                    j.printStackTrace();
                }
            }
        });
    }

}
