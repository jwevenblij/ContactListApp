package contactcard.contactcardbuttons;

import contactcard.ContactList;
import mainwindow.MainWindow;

import javax.swing.*;

public class CloseCard extends JButton {

    public CloseCard(JFrame currentFrame) { addActionListeners(currentFrame); }

    public void addActionListeners(JFrame currentFrame) {
        this.addActionListener(e -> {
            ContactList.addEntriesToContactListJPanel();
            MainWindow.mainWindowFrame.pack();
            currentFrame.dispose();
        });
    }
}
