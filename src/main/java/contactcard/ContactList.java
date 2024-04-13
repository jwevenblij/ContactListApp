package contactcard;

import com.thoughtworks.xstream.XStream;
import tools.ColorPalette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

public class ContactList {

    static String fileNameToContactPersonID = "";

    static Icon profilePictureIcon = new ImageIcon("src/main/java/resources/DefaultPfp.png");

    public static JScrollPane contactListJScrollPane = new JScrollPane();
    static JPanel contactListJPanel = new JPanel();
    static List<File> allContactsList;

    public ContactList() {
        configureContactListJPanel();
        configureContactListJScrollPane();
        addEntriesToContactListJPanel();
    }

    // Configure contactListJPanel
    public void configureContactListJPanel() {
        contactListJPanel.setLayout(new FlowLayout());
//        contactListJPanel.setBackground(ColorPalette.bgDark);
    }

    // Configure contactListJScrollPane
    public void configureContactListJScrollPane() {
//        contactListJScrollPane.setBackground(ColorPalette.bgDark);
        contactListJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactListJScrollPane.setViewportView(contactListJPanel);
        contactListJScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }

    // Add entries to contactListJPanel
    public static void addEntriesToContactListJPanel() {
        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[] {"contactcard.ContactPerson"});

        boolean addHeightOnOff = true;
        int heightIterator = 30;
        int allContactsListItemIndex = 0;
        GridBagConstraints gbc = new GridBagConstraints();
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
            JLabel contactNameJLabel, phoneNumberJLabel, eMailJlabel, phoneNumberEMailJLabel;
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

            ContactPerson currentContact = (ContactPerson)xstream.fromXML(xml);
            ImageIcon profilePictureImageIcon = new ImageIcon(currentContact.getProfilePictureFilePath());

            if (!currentContact.getMiddleName().equalsIgnoreCase("")) {
                contactNameString = currentContact.getFirstName() + " " + currentContact.getMiddleName() + " " + currentContact.getLastName();
            } else {
                contactNameString = currentContact.getFirstName() + " " + currentContact.getLastName();
            }
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

//            resizeJLabelText(contactNameJLabel);
//            resizeJLabelText(phoneNumberEMailJLabel);

            contactListEntryJButton.add(profilePictureSmallJPanel, BorderLayout.WEST);
            contactListEntryJButton.add(titleTextSmallJPanel, BorderLayout.CENTER);


        contactListJPanel.add(contactListEntryJButton);
            if (addHeightOnOff) {
                heightIterator += 175;
                addHeightOnOff = false;
            } else {
                addHeightOnOff = true;
            }

            profilePictureSmallJPanel.setBackground(contactListEntryJButton.getBackground());
            titleTextSmallJPanel.setBackground(contactListEntryJButton.getBackground());
            contactListJPanel.setPreferredSize(new Dimension(0, heightIterator));
            allContactsListItemIndex++;

            contactListEntryJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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

                    contactCardFull.firstNameJTextField.setText(currentContact.getFirstName());
                    contactCardFull.middleNameJTextField.setText(currentContact.getMiddleName());
                    contactCardFull.lastNameJTextField.setText(currentContact.getLastName());
                    contactCardFull.birthDateJTextField.setText(currentContact.getBirthDate());
                    contactCardFull.countryCodeJTextField.setText(currentContact.getCountryCode());
                    contactCardFull.phoneNumberJTextField.setText(currentContact.getPhoneNumber());
                    contactCardFull.eMailJTextField.setText(currentContact.geteMail());
                    contactCardFull.commentsJTextArea.setText(currentContact.getComments());

                    contactCardFull.profilePictureJButton.setIcon(
                            contactCardFull.resizePictureToFrame(
                                    new ImageIcon(String.valueOf(currentContact.getProfilePictureFilePath())),
                                    new Dimension(
                                            contactCardFull.profilePictureJButton.getWidth(),
                                            contactCardFull.profilePictureJButton.getHeight())));

                    contactCardFull.nameTitleJLabel.setText(currentContact.getFirstName() + " " + currentContact.getMiddleName() + " " + currentContact.getLastName());
                    contactCardFull.eMailPhoneNumberTitleJLabel.setText(currentContact.geteMail() + "     " + "+ " + currentContact.getCountryCode() + " " + currentContact.getPhoneNumber());
                    contactCardFull.resizeJLabelText(contactCardFull.nameTitleJLabel);
                    contactCardFull.resizeJLabelText(contactCardFull.eMailPhoneNumberTitleJLabel);
                }
            });
        }
    }

    // Resize JLabel text to fit Parent JLabel
    // Courtesy to (https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size/2715279#2715279)
    public static void resizeJLabelText(JLabel tempJLabel) {
        Font labelFont;
        String labelText;
        int stringWidth;
        int componentWidth;
        int newFontSize;
        int componentHeight;
        int fontSizeToUse;
        double widthRatio;

        labelFont = tempJLabel.getFont();
        labelText = tempJLabel.getText();
        stringWidth = tempJLabel.getFontMetrics(labelFont).stringWidth(labelText);
        componentWidth = tempJLabel.getWidth();
        widthRatio = (double)componentWidth / (double)stringWidth;
        newFontSize = (int)(labelFont.getSize() * widthRatio);
        componentHeight = tempJLabel.getHeight();
        fontSizeToUse = Math.min(newFontSize, componentHeight);
        tempJLabel.setFont(new Font(labelFont.getName(), Font.BOLD, fontSizeToUse));
    }
}
