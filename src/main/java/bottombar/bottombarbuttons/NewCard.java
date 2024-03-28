package bottombar.bottombarbuttons;

import contactcard.ContactCardFull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCard extends JButton {

    public NewCard() {
        addActionListeners();
    }

    public void addActionListeners() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactCardFull contactCardFull = new ContactCardFull();
            }
        });
    }

}
