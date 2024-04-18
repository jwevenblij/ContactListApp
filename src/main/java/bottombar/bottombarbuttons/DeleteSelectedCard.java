package bottombar.bottombarbuttons;

import bottombar.BottomBar;
import contactcard.ContactList;
import contactcard.ContactPerson;
import mainwindow.MainWindow;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DeleteSelectedCard extends JButton {

    public DeleteSelectedCard() { addActionListeners(); }

    public void addActionListeners() {
        this.addActionListener(e -> {
            for (String currentIteration : ContactList.selectedHashCodesArrayList) {
                Path delFile = new File("src/main/contacts/" + currentIteration).toPath();
                File fileExists = new File(String.valueOf(delFile));

                if (fileExists.exists()) {
                    try {
                        Files.delete(delFile);
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
            }

            ContactList.populateFullContactList();
            ContactList.addEntriesToContactListJPanel();
            BottomBar.backupAllContactsList = new ArrayList<>(ContactList.allContactsList);
            MainWindow.mainWindowFrame.pack();
        });
    }
}
