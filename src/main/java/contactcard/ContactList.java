package contactcard;

import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

public class ContactList {

    static String fileNameToContactPersonID = "";

    public static JScrollPane contactListJScrollPane = new JScrollPane();
    static JPanel contactListJPanel = new JPanel();
    public static List<File> allContactsList;

    public ContactList() {
        configureContactListJPanel();
        configureContactListJScrollPane();
        addEntriesToContactListJPanel();
    }

    // Configure contactListJPanel
    public void configureContactListJPanel() {
        contactListJPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 7, 7));
    }

    // Configure contactListJScrollPane
    public void configureContactListJScrollPane() {
        contactListJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactListJScrollPane.setViewportView(contactListJPanel);
        contactListJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    // Add entries to contactListJPanel
    public static void addEntriesToContactListJPanel() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {"contactcard.ContactPerson"});

        boolean addHeightOnOff = true;
        int heightIterator = 30;
        int allContactsListItemIndex = 0;
        Dimension profilePictureJLabelDimension = new Dimension(100, 165);

        contactListJPanel.removeAll();
        contactListJPanel.setPreferredSize(new Dimension(0, heightIterator));

        try {
            allContactsList = Files.walk(Paths.get("src/main/contacts"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .toList();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (File allContactsListItem : allContactsList) {
            String contactNameString, phoneNumberString, eMailString;
            JLabel contactNameJLabel, phoneNumberEMailJLabel;
            JLabel profilePictureJLabel = new JLabel();
            JPanel profilePictureSmallJPanel = new JPanel();
            JPanel titleTextSmallJPanel = new JPanel();

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
            contactNameJLabel.setFont(new Font("Dialog", Font.BOLD, 24));
            contactNameJLabel.setPreferredSize(new Dimension(505, 100));
            phoneNumberEMailJLabel = new JLabel(eMailString + "   " + phoneNumberString);
            phoneNumberEMailJLabel.setFont(new Font("Dialog", Font.BOLD, 20));
            phoneNumberEMailJLabel.setPreferredSize(new Dimension(505, 65));

            // Configure profilePicture
            profilePictureJLabel.setPreferredSize(profilePictureJLabelDimension);
            profilePictureJLabel.setIcon(ContactCardFull.resizePictureToFrame(profilePictureImageIcon, profilePictureJLabelDimension));
            profilePictureSmallJPanel.setPreferredSize(new Dimension(100, 165));
            profilePictureSmallJPanel.add(profilePictureJLabel);

            // Configure titleText
            titleTextSmallJPanel.setPreferredSize(new Dimension(505, 165));
            titleTextSmallJPanel.setLayout(new BorderLayout());

            titleTextSmallJPanel.add(contactNameJLabel, BorderLayout.NORTH);
            titleTextSmallJPanel.add(phoneNumberEMailJLabel, BorderLayout.CENTER);

            JButton contactListEntryJButton = new JButton();
            contactListEntryJButton.setLayout(new BorderLayout());
            contactListEntryJButton.setPreferredSize(new Dimension(615, 175));

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

            profilePictureSmallJPanel.setBackground(contactListEntryJButton.getBackground());
            titleTextSmallJPanel.setBackground(contactListEntryJButton.getBackground());

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
