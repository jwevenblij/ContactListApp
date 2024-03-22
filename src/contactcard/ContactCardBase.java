package contactcard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContactCardBase {
    String firstName, middleName, lastName;
    String eMail;
    int phoneNumber, phoneNetNumber;
    Image profilePicture;

    public ContactCardBase() {
        setCardBaseDefaults();
        setDefaultProfilePicture();
        System.out.println("super test");
    }

    // Configure CardBase defaults
    public void setCardBaseDefaults() {
        firstName = "";
        middleName = "Middle Name";
        lastName = "Last Name";
        eMail = "Email address";
        phoneNetNumber = -1;
        phoneNumber = -1;
    }

    // Set default profile picture
    public void setDefaultProfilePicture() {
        profilePicture = null;

        try {
            File pathToIconFile = new File("src/resources/DefaultPfp.png");
            profilePicture = ImageIO.read(pathToIconFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
