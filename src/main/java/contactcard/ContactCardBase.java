package contactcard;

import javax.swing.*;
import java.awt.*;

public class ContactCardBase {
    Color bgDark, bgLight, fgDark, fgLight;

    static String firstName, middleName, lastName;
    static String eMail;
    static String countryCode;
    static String phoneNumber;
    static String birthDate;
    static String comments;
    static String profilePictureFilePath;
    static int currentHashCode;

    JLabel nameTitleJLabel;
    JLabel eMailPhoneNumberTitleJLabel;

    static ImageIcon profilePicture;

    public ContactCardBase() {
        setCardBaseDefaults();
        setDefaultProfilePicture();
    }

    // Configure CardBase defaults
    public void setCardBaseDefaults() {
        firstName = "";
        middleName = "";
        lastName = "";
        eMail = "";
        countryCode = "";
        phoneNumber = "";
        profilePictureFilePath = "src/main/java/resources/DefaultPfp.png";
        currentHashCode = 0;
    }


    public static String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        ContactCardBase.firstName = firstName;
    }


    public static String getMiddleName() {
        return middleName;
    }


    public void setMiddleName(String middleName) {
        ContactCardBase.middleName = middleName;
    }


    public static String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        ContactCardBase.lastName = lastName;
    }


    public static String geteMail() {
        return eMail;
    }


    public void seteMail(String eMail) {
        ContactCardBase.eMail = eMail;
    }


    public static String getCountryCode() {
        return countryCode;
    }


    public void setCountryCode(String countryCode) {
        ContactCardBase.countryCode = countryCode;
    }


    public static String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        ContactCardBase.phoneNumber = phoneNumber;
    }


    public static String getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        ContactCardBase.birthDate = birthDate;
    }


    public static String getComments() {
        return comments;
    }


    public void setComments(String comments) {
        ContactCardBase.comments = comments;
    }


    public static String getProfilePictureFilePath() {
        return profilePictureFilePath;
    }


    public static void setProfilePictureFilePath(String profilePictureFilePath) {
        ContactCardBase.profilePictureFilePath = profilePictureFilePath;
    }

    public static int getCurrentHashCode() {
        return currentHashCode;
    }

    public void setCurrentHashCode(int currentHashCode) {
        ContactCardBase.currentHashCode = currentHashCode;
    }

    // Set default profile picture
    public void setDefaultProfilePicture() {
        profilePicture = new ImageIcon("src/main/java/resources/DefaultPfp.png");
    }
}
