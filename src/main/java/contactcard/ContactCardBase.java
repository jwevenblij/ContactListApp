package contactcard;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContactCardBase {
    Color bgDark, bgLight, fgDark, fgLight;

    String firstName, middleName, lastName;
    String eMail;
    String countryCode;
    String phoneNumber;
    String birthDate;
    String comments;
    String profilePictureFilePath;

    JLabel nameTitleJLabel;
    JLabel eMailPhoneNumberTitleJLabel;

    ImageIcon profilePicture;

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
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getMiddleName() {
        return middleName;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String geteMail() {
        return eMail;
    }


    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


    public String getCountryCode() {
        return countryCode;
    }


    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public String getComments() {
        return comments;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }


    public String getProfilePictureFilePath() {
        return profilePictureFilePath;
    }


    public void setProfilePictureFilePath(String profilePictureFilePath) {
        this.profilePictureFilePath = profilePictureFilePath;
    }


    // Set default profile picture
    public void setDefaultProfilePicture() {
        profilePicture = new ImageIcon("src/main/java/resources/DefaultPfp.png");
    }


    // Retrieve contact from XML
    private void retrieveContactSQL() {

    }


    // Create new contact in XML
    private void newContactSQL() {

    }


    // Edit contact in XML
    private void editContactSQL() {

    }


    // Delete contact from XML
    private void deleteContactSQL() {

    }



    // Set contact details
    public void setContactDetails(

    ) {

    }
}
