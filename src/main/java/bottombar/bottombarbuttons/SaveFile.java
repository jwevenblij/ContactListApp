package bottombar.bottombarbuttons;

import contactcard.ContactList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        this.addActionListener(e -> saveMethod());
    }

    public static void saveMethod() {
        List<File> allContactsList = ContactList.allContactsList;
        ArrayList<String> contactListFileArray = new ArrayList<>();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "XML files", "xml");
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter);

        FileWriter fileWriter;

        int returnVal = fileChooser.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

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
                if (selectedFile.toString().length() >= 4 && selectedFile.toString().substring((int) (selectedFile.toString().length() - 4), (int) selectedFile.toString().length()).equalsIgnoreCase(".xml")) {
                    System.out.println(selectedFile.toString().substring((int) (selectedFile.toString().length() - 3)));
                    fileWriter = new FileWriter(selectedFile.toString().substring(0, (int) (selectedFile.toString().length() - 4)) + ".xml");
                } else {
                    System.out.println(selectedFile.toString().substring((int) (selectedFile.toString().length() - 3)));
                    fileWriter = new FileWriter(selectedFile + ".xml");
                }
                fileWriter.write(String.valueOf(contactListFileArray));
                fileWriter.close();

            } catch (IOException j) {
                j.printStackTrace();
            }
        }
    }

}
