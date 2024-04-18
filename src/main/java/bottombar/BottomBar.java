package bottombar;

import bottombar.bottombarbuttons.*;
import contactcard.ContactList;
import contactcard.ContactPerson;
import mainwindow.MainWindow;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BottomBar {
    public static List<File> backupAllContactsList = new ArrayList<>(ContactList.allContactsList);
    public static ArrayList<ContactPerson> backupContactsArrayList = new ArrayList<>(ContactList.contactsArrayList);
    public static boolean activeSearch = false;

    public static JPanel bottomBarJPanel = new JPanel();

    GridBagConstraints gbc = new GridBagConstraints();

    JButton newCardJButton = new NewCard();
    JButton openFileJButton = new OpenFile();
    JButton saveFileJButton = new SaveFile();
    JButton deleteSelectedCardJButton = new DeleteSelectedCard();
    JButton deleteAllCardsJButton = new DeleteAllCards();
    JButton searchBarButtonJButton = new SearchButton();
    public static JTextField searchBarJTextField = new JTextField();


    public BottomBar() {
        configureJButtons();
        configureBottomBarJPanel();
        addSearchBarKeyListener();
    }


    // Configure JButtons
    public void configureJButtons() {
        newCardJButton.setText("New");
        openFileJButton.setText("Open");
        saveFileJButton.setText("Save");
        deleteSelectedCardJButton.setText("Delete");
        deleteAllCardsJButton.setText("Clear");
        searchBarButtonJButton.setText("Search");
    }


    // Configure bottomBarJPanel layout
    public void configureBottomBarJPanel() {
        bottomBarJPanel.setLayout(new GridBagLayout());
        bottomBarJPanel.setPreferredSize(new Dimension(MainWindow.initialFrameWidth, 50));

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;

        gbc.gridx = 0;
        bottomBarJPanel.add(newCardJButton, gbc);

        gbc.gridx = 1;
        bottomBarJPanel.add(openFileJButton, gbc);

        gbc.gridx = 2;
        bottomBarJPanel.add(saveFileJButton, gbc);

        gbc.gridx = 3;
        bottomBarJPanel.add(deleteSelectedCardJButton, gbc);

        gbc.gridx = 4;
        bottomBarJPanel.add(deleteAllCardsJButton, gbc);

        gbc.insets = new Insets(5, 5, 5, 3);
        gbc.weightx = 10;
        gbc.gridx = 5;
        bottomBarJPanel.add(searchBarJTextField, gbc);

        gbc.insets = new Insets(5,0,5,5);
        gbc.weightx = 1;
        gbc.gridx = 6;
        bottomBarJPanel.add(searchBarButtonJButton, gbc);
    }

    public void addSearchBarKeyListener() {
        String contactsPath = "src/main/contacts/";
        List<File> searchResultsList = new ArrayList<>();

        Action searchAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                ContactList.allContactsList.clear();

                if (!backupContactsArrayList.isEmpty()) {
                    for (ContactPerson currentIteration: backupContactsArrayList) {
                        if (
                                !BottomBar.searchBarJTextField.getText().isEmpty() &&
                                        !activeSearch &&
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.getFirstName()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.getMiddleName()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.getLastName()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.geteMail()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.getCountryCode()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.getPhoneNumber()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(currentIteration.getComments()).find() ||
                                        Pattern.compile(Pattern.quote(BottomBar.searchBarJTextField.getText()), Pattern.CASE_INSENSITIVE).matcher(String.valueOf(currentIteration.getContactID())).find()
                        ) {
                            activeSearch = true;
                            File addFile = new File(contactsPath +
                                    currentIteration.getFirstName() + "_" +
                                    currentIteration.getLastName() + "_" +
                                    currentIteration.getContactID() + ".xml");
                            searchResultsList.add(addFile);
                        } else {

                        }
                    }
                    MainWindow.mainWindowFrame.add(ContactList.removeSearchResultsJPanel, BorderLayout.NORTH);
                    ContactList.allContactsList.addAll(searchResultsList);
                    ContactList.contactsArrayList.clear();
                    searchResultsList.clear();

                    // Redraw the ContactList canvas
                    ContactList.addEntriesToContactListJPanel();
                    MainWindow.mainWindowFrame.pack();
                    MainWindow.mainWindowFrame.repaint();
                }
            }
        };

        searchBarJTextField.addActionListener(searchAction);
        searchBarButtonJButton.addActionListener(searchAction);
    }
}
