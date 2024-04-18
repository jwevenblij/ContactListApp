package contactcard;

import bottombar.BottomBar;
import bottombar.bottombarbuttons.SearchButton;
import com.thoughtworks.xstream.XStream;
import mainwindow.MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContactList {

    static String fileNameToContactPersonID = "";

    public static JScrollPane contactListJScrollPane = new JScrollPane();
    public static JPanel contactListJPanel = new JPanel();
    public static JPanel removeSearchResultsJPanel = new JPanel();
    public static JButton removeSearchResultsJButton = new JButton();

    public static List<File> allContactsList;
    public static ArrayList<String> selectedHashCodesArrayList = new ArrayList<>();
    public static ArrayList<ContactPerson> contactsArrayList = new ArrayList<>();

    public ContactList() {
        configureContactListJPanel();
        configureRemoveSearchResultsJPanel();
        configureContactListJScrollPane();
        populateFullContactList();
        addEntriesToContactListJPanel();
    }

    // Configure contactListJPanel
    public void configureContactListJPanel() {
        contactListJPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 7, 7));
    }

    // Configure removeSearchResultsJPanel
    public void configureRemoveSearchResultsJPanel() {
        removeSearchResultsJPanel.setPreferredSize(new Dimension(0, 50));
        removeSearchResultsJButton.setPreferredSize(new Dimension(new Dimension(200, 40)));
        removeSearchResultsJButton.setText("Remove Search Results");
        removeSearchResultsJPanel.add(removeSearchResultsJButton, BorderLayout.CENTER);

        removeSearchResultsJButton.addActionListener(e -> {
            MainWindow.mainWindowFrame.remove(removeSearchResultsJPanel);
            allContactsList.clear();
            contactsArrayList.clear();
            allContactsList = new ArrayList<>(BottomBar.backupAllContactsList);
            contactsArrayList = new ArrayList<>(BottomBar.backupContactsArrayList);
            BottomBar.activeSearch = false;
            addEntriesToContactListJPanel();
            MainWindow.mainWindowFrame.pack();
            MainWindow.mainWindowFrame.repaint();
        });
    }

    // Configure contactListJScrollPane
    public void configureContactListJScrollPane() {
        contactListJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactListJScrollPane.setViewportView(contactListJPanel);
        contactListJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    //
    public static void populateFullContactList() {
        try {
            allContactsList = new LinkedList<>(Files.walk(Paths.get("src/main/contacts"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .toList());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Add entries to contactListJPanel
    public static void addEntriesToContactListJPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {"contactcard.ContactPerson"});

        boolean addHeightOnOff = true;
        int heightIterator = 30;
        int allContactsListItemIndex = 0;
        Dimension profilePictureJLabelDimension = new Dimension(100, 165);

        contactListJPanel.removeAll();
        contactListJPanel.setPreferredSize(new Dimension(0, heightIterator));

        for (File allContactsListItem : allContactsList) {
            String contactNameString, phoneNumberString, eMailString;
            JLabel contactNameJLabel, eMailJLabel, phoneNumberJLabel;
            JLabel profilePictureJLabel = new JLabel();
            JLabel profilePictureEmbedJLabel = new JLabel();
            JPanel profilePictureSmallJPanel = new JPanel();
            JPanel titleTextSmallJPanel = new JPanel();
            JCheckBox cardJCheckBox = new JCheckBox();

            fileNameToContactPersonID = (String.valueOf(allContactsListItem).substring(0, ((String.valueOf(allContactsListItem).length() - 4))));
            String xml = "";

            try {
                xml = Files.readString(allContactsListItem.toPath(), StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println(e);
            }

            ContactPerson currentContact = (ContactPerson)xStream.fromXML(xml);
            ImageIcon profilePictureImageIcon = new ImageIcon(currentContact.getProfilePictureFilePath());

            contactNameString = currentContact.getFirstName() + " " + currentContact.getLastName();

            phoneNumberString = "+" + currentContact.getCountryCode() + " " + currentContact.getPhoneNumber();
            eMailString = currentContact.geteMail();

            contactNameJLabel = new JLabel(contactNameString);
            contactNameJLabel.setFont(new Font("Dialog", Font.BOLD, 36));
            contactNameJLabel.setPreferredSize(new Dimension(505, 100));
            eMailJLabel = new JLabel(eMailString);
            eMailJLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
            eMailJLabel.setForeground(Color.decode("#9f9f9f"));
            eMailJLabel.setPreferredSize(new Dimension(505, 0));
            phoneNumberJLabel = new JLabel(phoneNumberString);
            phoneNumberJLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
            phoneNumberJLabel.setForeground(Color.decode("#9f9f9f"));
            eMailJLabel.setPreferredSize(new Dimension(505,0));


            // Configure profilePicture
            JPanel emptyJPanel = new JPanel();
            JPanel emptyJPanel2 = new JPanel();
            emptyJPanel.setOpaque(false);
            emptyJPanel2.setOpaque(false);

            profilePictureJLabel.setPreferredSize(new Dimension(100, 140));
            profilePictureJLabel.setBackground(contactListJPanel.getBackground());
            profilePictureJLabel.setOpaque(true);
            profilePictureJLabel.setIcon(ContactCardFull.resizePictureToFrame(profilePictureImageIcon, profilePictureJLabelDimension));
            profilePictureJLabel.setBorder(new CompoundBorder(
                    BorderFactory.createMatteBorder(2, 2, 2, 2, contactListJPanel.getBackground().darker()),
                    BorderFactory.createEmptyBorder(0, 0, 0, 0)));

            profilePictureEmbedJLabel.setLayout(new BorderLayout());
            profilePictureEmbedJLabel.setPreferredSize(new Dimension(100, 140));
            profilePictureEmbedJLabel.add(profilePictureJLabel, BorderLayout.CENTER);

            profilePictureSmallJPanel.setLayout(new GridBagLayout());
            profilePictureSmallJPanel.setPreferredSize(new Dimension(100, 165));
            profilePictureSmallJPanel.setOpaque(false);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0,0,0,0);

            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 0;
            profilePictureSmallJPanel.add(emptyJPanel, gbc);

            gbc.weighty = 0;
            gbc.gridx = 0;
            gbc.gridy = 1;
            profilePictureSmallJPanel.add(profilePictureEmbedJLabel, gbc);

            gbc.weighty = 0;
            gbc.gridx = 0;
            gbc.gridy = 2;
            profilePictureSmallJPanel.add(emptyJPanel2, gbc);

            // Configure titleText
            titleTextSmallJPanel.setPreferredSize(new Dimension(505, 165));
            titleTextSmallJPanel.setLayout(new GridBagLayout());

            gbc.insets = new Insets(0,0,0,0);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.weightx = 0;

            gbc.weighty = 11;
            gbc.gridx = 0;
            gbc.gridy = 0;
            titleTextSmallJPanel.add(new JLabel(""), gbc);

            gbc.gridwidth = 2;
            gbc.weighty = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            titleTextSmallJPanel.add(contactNameJLabel, gbc);

            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 1;
            gbc.gridx = 0;
            gbc.gridy = 2;
            titleTextSmallJPanel.add(new JLabel(""), gbc);

            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.weightx = 10;
            gbc.gridx = 1;
            gbc.gridy = 2;
            titleTextSmallJPanel.add(eMailJLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            titleTextSmallJPanel.add(phoneNumberJLabel, gbc);

            gbc.anchor = GridBagConstraints.SOUTHEAST;
            gbc.weightx = 0;
            gbc.weighty = 5;
            gbc.gridx = 2;
            gbc.gridy = 4;
            titleTextSmallJPanel.add(cardJCheckBox, gbc);

            JButton contactListEntryJButton = new JButton();
            contactListEntryJButton.setLayout(new BorderLayout(20, 0));
            contactListEntryJButton.setPreferredSize(new Dimension(615, 175));

            // Add content to button
            contactListEntryJButton.add(profilePictureSmallJPanel, BorderLayout.WEST);
            contactListEntryJButton.add(titleTextSmallJPanel, BorderLayout.CENTER);

            contactListJPanel.add(contactListEntryJButton);
            allContactsListItemIndex++;

            if (addHeightOnOff) {
                heightIterator += 178;
                addHeightOnOff = false;
            } else {
                addHeightOnOff = true;
            }

            titleTextSmallJPanel.setOpaque(false);

            contactsArrayList.add(currentContact);

            contactListEntryJButton.addActionListener(e -> {
                ContactCardFull contactCardFull = getContactCardFull(currentContact);

                contactCardFull.contactCardFullFrame.setTitle(currentContact.getFirstName() + " " + currentContact.getLastName());

                contactCardFull.firstNameJTextField.setText(currentContact.getFirstName());
                contactCardFull.middleNameJTextField.setText(currentContact.getMiddleName());
                contactCardFull.lastNameJTextField.setText(currentContact.getLastName());
                contactCardFull.birthDateJTextField.setText(currentContact.getBirthDate());
                contactCardFull.countryCodeJTextField.setText(currentContact.getCountryCode());
                contactCardFull.phoneNumberJTextField.setText(currentContact.getPhoneNumber());
                contactCardFull.eMailJTextField.setText(currentContact.geteMail());
                contactCardFull.commentsJTextArea.setText(currentContact.getComments());
                contactCardFull.saveChanges.oldFilePath = currentContact.getFirstName() + "_" + currentContact.getLastName() + "_" + currentContact.getContactID();

                contactCardFull.profilePictureJButton.setIcon(
                        ContactCardFull.resizePictureToFrame(
                                new ImageIcon(String.valueOf(currentContact.getProfilePictureFilePath())),
                                new Dimension(
                                        contactCardFull.profilePictureJButton.getWidth(),
                                        contactCardFull.profilePictureJButton.getHeight())));
                ContactCardBase.setProfilePictureFilePath(currentContact.getProfilePictureFilePath());

                contactCardFull.nameTitleJLabel.setText(currentContact.getFirstName() + " " + currentContact.getMiddleName() + " " + currentContact.getLastName());
                contactCardFull.eMailPhoneNumberTitleJLabel.setText(currentContact.geteMail() + "     " + "+ " + currentContact.getCountryCode() + " " + currentContact.getPhoneNumber());
                ContactCardFull.resizeJLabelText(contactCardFull.nameTitleJLabel);
                ContactCardFull.resizeJLabelText(contactCardFull.eMailPhoneNumberTitleJLabel);
            });

            // Form array out of selected checkboxes
            cardJCheckBox.addActionListener(e -> {
                if (cardJCheckBox.isSelected()) {
                    selectedHashCodesArrayList.add(currentContact.getFirstName() + "_" + currentContact.getLastName() + "_" + currentContact.getContactID() + ".xml");
                } else {
                    for(int i = 0; i < selectedHashCodesArrayList.size(); i++) {
                        if (selectedHashCodesArrayList.get(i).equals(currentContact.getFirstName() + "_" + currentContact.getLastName() + "_" + currentContact.getContactID() + ".xml")) {
                            selectedHashCodesArrayList.remove(i);
                            i--;
                        }
                    }
                }
            });

            ContactCardBase.setProfilePictureFilePath("src/main/java/resources/DefaultPfp.png");
        }

        if (allContactsListItemIndex % 2 == 0) {
            contactListJPanel.setPreferredSize(new Dimension(0, ((allContactsListItemIndex / 2) * 175) + ((allContactsListItemIndex / 2) * 7) + 7));
        } else if (allContactsListItemIndex % 2 == 1) {
            contactListJPanel.setPreferredSize(new Dimension(0, ((allContactsListItemIndex / 2) * 175) + ((allContactsListItemIndex / 2) * 7) + 189));
        }
    }

    private static ContactCardFull getContactCardFull(ContactPerson currentContact) {
        ContactCardFull contactCardFull = new ContactCardFull();
        contactCardFull.setFirstName(currentContact.getFirstName());
        contactCardFull.setMiddleName(currentContact.getMiddleName());
        contactCardFull.setLastName(currentContact.getLastName());
        contactCardFull.setBirthDate(currentContact.getBirthDate());
        contactCardFull.setCountryCode(currentContact.getCountryCode());
        contactCardFull.setPhoneNumber(currentContact.getPhoneNumber());
        contactCardFull.seteMail(currentContact.geteMail());
        contactCardFull.setComments(currentContact.getComments());
        contactCardFull.setCurrentHashCode(currentContact.getContactID());
        return contactCardFull;
    }
}
