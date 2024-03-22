package contactcard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import mainwindow.MainWindow;
import tools.ColorPalette;

public class ContactCardFull extends ContactCardBase {

    JFrame contactCardFullFrame = new JFrame("Temp ContactCardFull");

    JScrollPane contactCardFullJScrollPane = new JScrollPane();
    ScrollPane contactCardFullScrollPane = new ScrollPane();

    JPanel contactCardFullNorthJPanel = new JPanel();
    JPanel contactCardFullCenterJPanel = new JPanel();

    Color bgDark, bgLight, fgDark, fgLight;

    int birthDateDay, birthDateMonth, birthDateYear;
    String comments;
    JTextField firstNameTextField, middleNameTextField, lastNameTextField;
    JTextField birthDateDayTextField, birthDateMonthTextField, birthDateYearTextField;
    JTextField phoneNumberTextField, phoneNetNumberTextField;
    JTextField eMailTextField;
    JTextArea commentsArea;

    contactcard.contactcardbuttons.CloseCard closeCardButton = new contactcard.contactcardbuttons.CloseCard();
    contactcard.contactcardbuttons.SaveChanges saveChangesButton = new contactcard.contactcardbuttons.SaveChanges();
    contactcard.contactcardbuttons.DeleteProfilePicture deleteProfilePictureButton = new contactcard.contactcardbuttons.DeleteProfilePicture();
    contactcard.contactcardbuttons.DeleteCard deleteCardButton = new contactcard.contactcardbuttons.DeleteCard();
    contactcard.contactcardbuttons.EditProfilePicture editProfilePictureButton = new contactcard.contactcardbuttons.EditProfilePicture();

    public ContactCardFull() {
        super();
        setCardFullDefaults();
        createTempFrame();
        addFocusListeners();
        configureNorthJPanel();
        configureCenterJPanel();
        addToScrollPane();
        populateContactCardFullFrame();
        packAndVisible();
    }

    // Configure CardFull defaults
    public void setCardFullDefaults() {
        bgDark = ColorPalette.bgDark;
        bgLight = ColorPalette.bgLight;
        fgDark = ColorPalette.fgDark;
        fgLight = ColorPalette.fgLight;

        comments = "";
        birthDateDay = -1;
        birthDateMonth = -1;
        birthDateYear = -1;

        firstNameTextField = new JTextField();
        firstNameTextField.setText(firstName);

        middleNameTextField = new JTextField();
        middleNameTextField.setText(middleName);

        lastNameTextField = new JTextField();
        lastNameTextField.setText(lastName);

        birthDateDayTextField = new JTextField();
        birthDateDayTextField.setText(String.valueOf(birthDateDay));

        birthDateMonthTextField = new JTextField();
        birthDateMonthTextField.setText(String.valueOf(birthDateMonth));

        birthDateYearTextField = new JTextField();
        birthDateYearTextField.setText(String.valueOf(birthDateYear));

        phoneNetNumberTextField = new JTextField();
        phoneNetNumberTextField.setText("Net Number / Country Code");

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setText("Phone Number");

        eMailTextField = new JTextField();
        eMailTextField.setText(eMail);

        commentsArea = new JTextArea();
        commentsArea.setText(comments);
    }

    public void createTempFrame() {
        contactCardFullFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        contactCardFullFrame.setPreferredSize(new Dimension(450, 300));
        contactCardFullFrame.setResizable(false);
        contactCardFullFrame.setIconImage(MainWindow.setIconImage());
        contactCardFullFrame.setLayout(new BorderLayout());
        contactCardFullFrame.getContentPane().setBackground(bgDark);
    }

    public void addFocusListeners() {
        firstNameTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (firstNameTextField.getText().trim().equals("First Name"))
                    firstNameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (firstNameTextField.getText().trim().isEmpty())
                    firstNameTextField.setText("First Name");
            }
        });

        middleNameTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (middleNameTextField.getText().trim().equals("Middle Name"))
                    middleNameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (middleNameTextField.getText().trim().isEmpty())
                    middleNameTextField.setText("Middle Name");
            }
        });

        lastNameTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameTextField.getText().trim().equals("Last Name"))
                    lastNameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameTextField.getText().trim().isEmpty())
                    lastNameTextField.setText("Last Name");
            }
        });

        birthDateDayTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (birthDateDayTextField.getText().trim().equals("Birth Day"))
                    birthDateDayTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (birthDateDayTextField.getText().trim().isEmpty())
                    birthDateDayTextField.setText("Birth Day");
            }
        });

        birthDateMonthTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (birthDateMonthTextField.getText().trim().equals("Birth Month"))
                    birthDateMonthTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (birthDateMonthTextField.getText().trim().isEmpty())
                    birthDateMonthTextField.setText("Birth Month");
            }
        });

        birthDateYearTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (birthDateYearTextField.getText().trim().equals("Birth Year"))
                    birthDateYearTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (birthDateYearTextField.getText().trim().isEmpty())
                    birthDateYearTextField.setText("Birth Year");
            }
        });
    }

    // Configure North JPanel
    public void configureNorthJPanel() {

    }

    // Configure Center JPanel
    public void configureCenterJPanel() {

    }

    // Add Center JPanel to ScrollPane
    public void addToScrollPane() {
        // Configure contactCardFull JScrollPane
        contactCardFullJScrollPane.setPreferredSize(new Dimension(450, 100));
        contactCardFullJScrollPane.setBackground(ColorPalette.bgLight);
        contactCardFullJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactCardFullJScrollPane.setViewportView(contactCardFullCenterJPanel);

        // Add JScrollPane to ScrollPane
        contactCardFullScrollPane.add(contactCardFullJScrollPane);
        contactCardFullScrollPane.setVisible(true);
    }

    // Populate ContactCardFull Frame
    public void populateContactCardFullFrame() {
        contactCardFullFrame.add(contactCardFullNorthJPanel, BorderLayout.NORTH);
        contactCardFullFrame.add(contactCardFullScrollPane, BorderLayout.CENTER);
    }

    // Pack Frame and make Visible
    public void packAndVisible() {
        contactCardFullFrame.pack();
        contactCardFullFrame.setVisible(true);
    }

    // Set custom profile picture
    public Image SetCustomProfilePicture() {
        profilePicture = null;

        try {
            File pathToIconFile = new File("");
            profilePicture = ImageIO.read(pathToIconFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return profilePicture;
    }
}
