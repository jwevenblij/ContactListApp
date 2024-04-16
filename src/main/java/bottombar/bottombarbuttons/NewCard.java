package bottombar.bottombarbuttons;

import contactcard.ContactCardFull;

import javax.swing.*;

public class NewCard extends JButton {

    public NewCard() {
        addActionListeners();
    }

    public void addActionListeners() {
        this.addActionListener(e -> {
            new ContactCardFull();
        });
    }

}
