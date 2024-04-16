package bottombar.bottombarbuttons;

import contactcard.ContactList;
import mainwindow.MainWindow;

import javax.swing.*;
import java.io.File;
import java.util.Objects;

public class DeleteAllCards extends JButton {

    public DeleteAllCards() { addActionListeners(); }

    public void addActionListeners() {
        this.addActionListener(e -> {
            Object[] options = {"Save Contacts", "Clear All Contacts", "Cancel"};

            int choiceValue = JOptionPane.showOptionDialog(null,
                    "Are you sure you want to remove all contacts? This action is irreversible.",
                    "Clear All Contacts",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[2]);

            if (choiceValue == 0) {
                SaveFile.saveMethod();
                clearAllContacts();
            } else if (choiceValue == 1) {
                clearAllContacts();
            }
        });
    }

    public static void clearAllContacts() {
        final String contactsDirectory = "src/main/contacts";

        // Delete all files from the contacts folder
        for(File file: Objects.requireNonNull(new File(contactsDirectory).listFiles()))
            file.delete();

        // Redraw the ContactList canvas
        ContactList.addEntriesToContactListJPanel();
        MainWindow.mainWindowFrame.pack();
    }
}
