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

    // Set default profile picture
    public void setDefaultProfilePicture() {
        profilePicture = new ImageIcon("src/resources/DefaultPfp.png");
    }


}
