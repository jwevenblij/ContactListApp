//package contactcard;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class ContactCardSmall extends JButton {
//
//    Icon profilePicture;
//    String firstName;
//    String middleName;
//    String lastName;
//    String fullName;
//    String emailAddress;
//    String countryCode;
//    String phoneNumber;
//    String fullPhoneNumber;
//
//    JLabel profilePictureJLabel;
//    JLabel nameJLabel;
//    JLabel phoneNumberJLabel;
//
//    JPanel profilePictureJPanel;
//    JPanel titleTextJPanel;
//
//    public ContactCardSmall() {
//        setCurrentContactDetails();
//        setJLabelsAndJPanels();
//    }
//
//    // Set current contact details
//    public void setCurrentContactDetails() {
//        profilePicture = new ImageIcon(ContactPerson.getProfilePictureFilePathImport());
//        firstName = ContactPerson.getFirstName();
//        middleName = ContactPerson.getMiddleName();
//        lastName = ContactPerson.getLastName();
//
//        if(!middleName.equalsIgnoreCase("")) {
//            fullName = firstName + " " + middleName + " " + lastName;
//        } else {
//            fullName = firstName + " " + lastName;
//        }
//
//        emailAddress = ContactPerson.geteMail();
//        countryCode = ContactPerson.getCountryCode();
//        phoneNumber = ContactPerson.getPhoneNumber();
//        fullPhoneNumber = "+" + countryCode + " " + phoneNumber;
//    }
//
//    // Set current JLabels and JPanels
//    public void setJLabelsAndJPanels() {
//
//    }
//
//}
