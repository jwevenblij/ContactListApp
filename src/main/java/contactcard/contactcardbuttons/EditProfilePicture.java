package contactcard.contactcardbuttons;

import contactcard.ContactCardBase;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class EditProfilePicture extends JButton {

    static JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG and PNG files","jpg", "png");

    public EditProfilePicture() {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(filter);
    }

    public static File editProfilePicture() {
        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ContactCardBase.setProfilePictureFilePath(String.valueOf(EditProfilePicture.editProfilePicture()));
            return selectedFile;
        } else {
            JOptionPane.showMessageDialog(null, "Selected file format is not JPG or PNG", "Incorrect file format", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
