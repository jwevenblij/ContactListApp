package contactcard;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
//        currentHashCode = 0;
    }


    public static String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public static String getMiddleName() {
        return middleName;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public static String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public static String geteMail() {
        return eMail;
    }


    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


    public static String getCountryCode() {
        return countryCode;
    }


    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public static String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public static String getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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
