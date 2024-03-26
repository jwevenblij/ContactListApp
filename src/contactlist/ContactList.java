package contactlist;

import tools.ColorPalette;
import mainwindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactList {

    public static JScrollPane contactListJScrollPane = new JScrollPane();
    static JPanel contactListJPanel = new JPanel();
    static JButton tempCardButton = new JButton();

    public ContactList() {
        // Configure temporary card button
        tempCardButton.setText("Open temp card");
        tempCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactcard.ContactCardFull tempContactCard = new contactcard.ContactCardFull();
            }
        });

        // Configure contactlist.ContactList JPanel
        contactListJPanel.setLayout(new GridBagLayout());
        contactListJPanel.setPreferredSize(new Dimension(MainWindow.initialFrameWidth, 500));
        contactListJPanel.setBackground(ColorPalette.bgDark);
        contactListJPanel.add(tempCardButton);

        // Configure contactlist.ContactList JScrollPane
        contactListJScrollPane.setPreferredSize(new Dimension(MainWindow.initialFrameWidth, 500));
        contactListJScrollPane.setBackground(ColorPalette.bgDark);
        contactListJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactListJScrollPane.setViewportView(contactListJPanel);
    }
}
