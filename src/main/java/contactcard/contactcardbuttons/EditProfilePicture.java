package contactcard.contactcardbuttons;

import contactcard.ContactCardBase;
import contactcard.ContactPerson;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class EditProfilePicture extends JButton {

    public EditProfilePicture() {

    }

    public static File editProfilePicture() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG and PNG files", "jpg", "png");
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(filter);
        fileChooser.addChoosableFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ContactCardBase.setProfilePictureFilePath(selectedFile.getPath());
//            contactPerson.setProfilePictureFilePathImport(selectedFile.getPath());
            return selectedFile;
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            return null;
        } else if (returnVal == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null, "Selected file format is not JPG or PNG", "Incorrect file format", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            return null;
        }
    }
}
