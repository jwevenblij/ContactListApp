package contactcard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import contactcard.contactcardbuttons.*;
import mainwindow.MainWindow;
import tools.ColorPalette;


public class ContactCardFull extends ContactCardBase {

    GridBagConstraints gbc = new GridBagConstraints();
    Insets insetsZero = new Insets(0,0,0,0);
    Insets insetsJLabel = new Insets(2, 10, 2, 0);
    Insets insetsJTextField = new Insets(2, 2, 2, 10);
    Insets insetsNorthJPanel = new Insets(5,5,5,5);

    JFrame contactCardFullFrame = new JFrame("Temp ContactCardFull");
    JScrollPane contactCardFullJScrollPane = new JScrollPane();
    JPanel contactCardFullNorthJPanel = new JPanel();
    JPanel contactCardFullCenterTitleJPanel = new JPanel();
    JPanel profilePictureJPanel = new JPanel();
    JPanel contactCardTitleTextJPanel = new JPanel();
    JPanel contactCardButtonsJPanel = new JPanel();
    JPanel contactCardTitleJPanel = new JPanel();
    JPanel addEmailAddressJPanel = new JPanel();
    Dimension contactCardFullNorthJPanelDimension = new Dimension(600, 175);

    JTextField firstNameJTextField, middleNameJTextField, lastNameJTextField;
    JTextField birthDateJTextField;
    JTextField phoneNumberJTextField, countryCodeJTextField;
    JTextField eMailJTextField;
    JTextArea commentsJTextArea;
    JLabel firstNameJLabel, middleNameJLabel, lastNameJLabel;
    JLabel birthDateJLabel;
    JLabel phoneNumberJLabel, countryCodeJLabel;
    JLabel eMailJLabel;
    JLabel commentsJLabel;
    JButton profilePictureJButton, editProfilePictureJButton, deleteProfilePictureJButton;
    JButton addPhoneNumberJButton, addEmailAddressJButton;

    SaveChanges saveChanges = new SaveChanges();
    DeleteCard deleteCard = new DeleteCard();
    CloseCard closeCard = new CloseCard();
    DeleteProfilePicture deleteProfilePicture = new DeleteProfilePicture();
    EditProfilePicture editProfilePicture = new EditProfilePicture();


    // Constructor
    public ContactCardFull() {
        super();
        setCardFullDefaults();
        createTempFrame();
        configureProfilePictureJPanel();
        configureContactCardTitleTextJPanel();
        configureContactCardButtons();
        configureContactCardTitleJPanel();
        configureNorthJPanel();
        configureCenterJPanel();
        addToScrollPane();
        populateContactCardFullFrame();
        addFocusListeners();
        addActionListeners();
        packAndVisible();
    }


    // Configure CardFull defaults
    public void setCardFullDefaults() {
        // Color palette
        bgDark = ColorPalette.bgDark;
        bgLight = ColorPalette.bgLight;
        fgDark = ColorPalette.fgDark;
        fgLight = ColorPalette.fgLight;

        // String
        comments = "";
        birthDate = "";

        // JLabel
        firstNameJLabel = new JLabel("First Name:");
        middleNameJLabel = new JLabel("Middle Name:");
        lastNameJLabel = new JLabel("Last Name:");
        eMailJLabel = new JLabel("Email address:");
        countryCodeJLabel = new JLabel("Country Code:");
        phoneNumberJLabel = new JLabel("Phone Number:");
        birthDateJLabel = new JLabel("Date of Birth:");
        commentsJLabel = new JLabel("Comments:");

        nameTitleJLabel = new JLabel("");
        nameTitleJLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameTitleJLabel.setFont(new Font("Dialog", Font.BOLD, 24));

        eMailPhoneNumberTitleJLabel = new JLabel("");
        eMailPhoneNumberTitleJLabel.setHorizontalAlignment(SwingConstants.LEFT);
        eMailPhoneNumberTitleJLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        // JTextField
        firstNameJTextField = new JTextField();
        firstNameJTextField.setForeground(Color.GRAY);
        firstNameJTextField.setText("First Name");

        middleNameJTextField = new JTextField();
        middleNameJTextField.setForeground(Color.GRAY);
        middleNameJTextField.setText("Middle Name");

        lastNameJTextField = new JTextField();
        lastNameJTextField.setForeground(Color.GRAY);
        lastNameJTextField.setText("Last Name");

        birthDateJTextField = new JTextField();
        birthDateJTextField.setForeground(Color.GRAY);
        birthDateJTextField.setText("Date of Birth");

        countryCodeJTextField = new JTextField();
        countryCodeJTextField.setForeground(Color.GRAY);
        countryCodeJTextField.setText("Country Code");

        phoneNumberJTextField = new JTextField();
        phoneNumberJTextField.setForeground(Color.GRAY);
        phoneNumberJTextField.setText("Phone Number");

        eMailJTextField = new JTextField();
        eMailJTextField.setForeground(Color.GRAY);
        eMailJTextField.setText("Email Address");

        // JTextArea
        commentsJTextArea = new JTextArea();
        commentsJTextArea.setForeground(Color.GRAY);
        commentsJTextArea.setLineWrap(true);
        commentsJTextArea.setWrapStyleWord(true);
        commentsJTextArea.setText("Comments");

        // JButton
        profilePictureJButton = new JButton(profilePicture);
        profilePictureJButton.setPreferredSize(new Dimension(100, 140));

        editProfilePictureJButton = new JButton("Edit");
        editProfilePictureJButton.setPreferredSize(new Dimension(50, 25));
        editProfilePictureJButton.setFont(new Font("Dialog", Font.BOLD, 9));
        editProfilePictureJButton.setMargin(insetsZero);

        deleteProfilePictureJButton = new JButton("Remove");
        deleteProfilePictureJButton.setPreferredSize(new Dimension(50, 25));
        deleteProfilePictureJButton.setFont(new Font("Dialog", Font.BOLD, 9));
        deleteProfilePictureJButton.setMargin(insetsZero);

        saveChanges.setText("Save Changes");
        saveChanges.setPreferredSize(new Dimension(150, 50));
        saveChanges.setFont(new Font("Dialog", Font.BOLD, 9));
        saveChanges.setMargin(insetsZero);

        deleteCard.setText("Delete Contact");
        deleteCard.setPreferredSize(new Dimension(150, 50));
        deleteCard.setFont(new Font("Dialog", Font.BOLD, 9));
        deleteCard.setMargin(insetsZero);

        closeCard.setText("Close Card");
        closeCard.setPreferredSize(new Dimension(150, 50));
        closeCard.setFont(new Font("Dialog", Font.BOLD, 9));
        closeCard.setMargin(insetsZero);

        addPhoneNumberJButton = new JButton("+");
        addPhoneNumberJButton.setPreferredSize((new Dimension(phoneNumberJTextField.getHeight(), phoneNumberJTextField.getHeight())));
        addPhoneNumberJButton.setFont(new Font("Dialog", Font.BOLD, 12));
        addPhoneNumberJButton.setMargin(insetsZero);

        addEmailAddressJButton = new JButton("+");
        addEmailAddressJButton.setPreferredSize((new Dimension(eMailJTextField.getHeight(), eMailJTextField.getHeight())));
        addEmailAddressJButton.setFont(new Font("Dialog", Font.BOLD, 12));
        addEmailAddressJButton.setMargin(insetsZero);
    }


    public void createTempFrame() {
        contactCardFullFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        contactCardFullFrame.setPreferredSize(new Dimension(600, 400));
        contactCardFullFrame.setResizable(false);
        contactCardFullFrame.setIconImage(MainWindow.setIconImage());
        contactCardFullFrame.setLayout(new BorderLayout());
        contactCardFullFrame.getContentPane().setBackground(bgDark);
    }


    // Configure add eMail JPanel
    public void configureAddEmailJPanel() {
        addEmailAddressJPanel.setLayout(new GridBagLayout());
        addEmailAddressJPanel.setSize(phoneNumberJTextField.getSize());
        addEmailAddressJPanel.setBackground(bgDark);

        gbc.insets = insetsZero;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addEmailAddressJPanel.add(eMailJTextField, gbc);
    }


    // Configure ProfilePicture JPanel
    public void configureProfilePictureJPanel() {
        profilePictureJPanel.setLayout(new GridBagLayout());
        profilePictureJPanel.setPreferredSize(new Dimension(100, 165));
        profilePictureJPanel.setBackground(ColorPalette.bgLight);

        gbc.insets = insetsZero;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 0;
        gbc.weighty = 0;

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        profilePictureJPanel.add(profilePictureJButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        profilePictureJPanel.add(editProfilePictureJButton, gbc);

        gbc.gridx = 1;
        profilePictureJPanel.add(deleteProfilePictureJButton, gbc);
    }


    // Configure ContactCard Title Text JPanel
    public void configureContactCardTitleTextJPanel() {
        contactCardTitleTextJPanel.setLayout(new GridBagLayout());
        contactCardTitleTextJPanel.setBackground(bgLight);
        contactCardTitleTextJPanel.setPreferredSize(new Dimension(500, 115));

        gbc.insets = insetsNorthJPanel;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        contactCardTitleTextJPanel.add(nameTitleJLabel, gbc);

        gbc.gridy = 1;
        contactCardTitleTextJPanel.add(eMailPhoneNumberTitleJLabel, gbc);
    }


    // Configure ContactCard Buttons
    public void configureContactCardButtons() {
        contactCardButtonsJPanel.setLayout(new BorderLayout());
        contactCardButtonsJPanel.setPreferredSize(new Dimension(500, 50));
        contactCardButtonsJPanel.setBackground(bgLight);

        contactCardButtonsJPanel.add(saveChanges, BorderLayout.WEST);
        contactCardButtonsJPanel.add(deleteCard, BorderLayout.CENTER);
        contactCardButtonsJPanel.add(closeCard, BorderLayout.EAST);
    }


    // Configure ContactCard Title JPanel
    public void configureContactCardTitleJPanel() {
        contactCardTitleJPanel.setLayout(new BorderLayout());
        contactCardTitleJPanel.setPreferredSize(new Dimension(500, 165));
        contactCardTitleJPanel.setBackground(bgLight);

        contactCardTitleJPanel.add(contactCardTitleTextJPanel, BorderLayout.NORTH);
        contactCardTitleJPanel.add(contactCardButtonsJPanel, BorderLayout.CENTER);
    }

    // Configure North JPanel
    public void configureNorthJPanel() {
        contactCardFullNorthJPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contactCardFullNorthJPanel.setLayout(new BorderLayout());
        contactCardFullNorthJPanel.setPreferredSize(contactCardFullNorthJPanelDimension);
        contactCardFullNorthJPanel.setBackground(bgLight);

        contactCardFullNorthJPanel.add(profilePictureJPanel, BorderLayout.WEST);
        contactCardFullNorthJPanel.add(contactCardTitleJPanel, BorderLayout.CENTER);
    }


    // Configure Center JPanel
    public void configureCenterJPanel() {
        contactCardFullCenterTitleJPanel.setLayout(new GridBagLayout());
        contactCardFullCenterTitleJPanel.setBackground(ColorPalette.bgDark);

        // Labels
        gbc.insets = insetsJLabel;
        gbc.fill = GridBagConstraints.LINE_END;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;

        gbc.gridy = 0;
        contactCardFullCenterTitleJPanel.add(firstNameJLabel, gbc);

        gbc.gridy = 1;
        contactCardFullCenterTitleJPanel.add(middleNameJLabel, gbc);

        gbc.gridy = 2;
        contactCardFullCenterTitleJPanel.add(lastNameJLabel, gbc);

        gbc.gridy = 3;
        contactCardFullCenterTitleJPanel.add(eMailJLabel, gbc);

        gbc.gridy = 4;
        contactCardFullCenterTitleJPanel.add(countryCodeJLabel, gbc);

        gbc.gridy = 5;
        contactCardFullCenterTitleJPanel.add(phoneNumberJLabel, gbc);

        gbc.gridy = 6;
        contactCardFullCenterTitleJPanel.add(birthDateJLabel, gbc);

        gbc.gridy = 7;
        contactCardFullCenterTitleJPanel.add(commentsJLabel, gbc);


        // Entry fields
        gbc.insets = insetsJTextField;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 14;
        gbc.gridx = 1;

        gbc.gridwidth = 2;
        gbc.gridy = 0;
        contactCardFullCenterTitleJPanel.add(firstNameJTextField, gbc);

        gbc.gridy = 1;
        contactCardFullCenterTitleJPanel.add(middleNameJTextField, gbc);

        gbc.gridy = 2;
        contactCardFullCenterTitleJPanel.add(lastNameJTextField, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        contactCardFullCenterTitleJPanel.add(addEmailAddressJPanel, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 4;
        contactCardFullCenterTitleJPanel.add(countryCodeJTextField, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 5;
        contactCardFullCenterTitleJPanel.add(phoneNumberJTextField, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 6;
        contactCardFullCenterTitleJPanel.add(birthDateJTextField, gbc);

        gbc.gridy = 7;
        contactCardFullCenterTitleJPanel.add(commentsJTextArea, gbc);

        // Additional Entry Buttons
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 2;

        gbc.gridy = 3;
        contactCardFullCenterTitleJPanel.add(addEmailAddressJButton, gbc);

        gbc.gridy = 5;
        contactCardFullCenterTitleJPanel.add(addPhoneNumberJButton, gbc);

    }


    // Configure contactCardFull JScrollPane
    public void addToScrollPane() {
        contactCardFullJScrollPane.setPreferredSize(new Dimension(contactCardFullCenterTitleJPanel.getWidth(), contactCardFullCenterTitleJPanel.getHeight()));
        contactCardFullJScrollPane.setBackground(ColorPalette.bgDark);
        contactCardFullJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contactCardFullJScrollPane.setViewportView(contactCardFullCenterTitleJPanel);
    }


    // Populate ContactCardFull Frame
    public void populateContactCardFullFrame() {
        contactCardFullFrame.add(contactCardFullNorthJPanel, BorderLayout.NORTH);
        contactCardFullFrame.add(contactCardFullJScrollPane, BorderLayout.CENTER);
    }

    // Add FocusListeners
    public void addFocusListeners() {


        firstNameJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (firstNameJTextField.getText().trim().equals("First Name")) {
                    firstNameJTextField.setForeground(Color.BLACK);
                    firstNameJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (firstNameJTextField.getText().trim().isEmpty()) {
                    firstNameJTextField.setForeground(Color.GRAY);
                    firstNameJTextField.setText("First Name");
                } else {
                    firstName = firstNameJTextField.getText();
                    nameTitleJLabel.setText(firstName + " " + middleName + " " + lastName);
                    resizeJLabelText(nameTitleJLabel);
                }
            }
        });

        middleNameJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (middleNameJTextField.getText().trim().equals("Middle Name")) {
                    middleNameJTextField.setForeground(Color.BLACK);
                    middleNameJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (middleNameJTextField.getText().trim().isEmpty()) {
                    middleNameJTextField.setForeground(Color.GRAY);
                    middleNameJTextField.setText("Middle Name");
                } else {
                    middleName = middleNameJTextField.getText();
                    nameTitleJLabel.setText(firstName + " " + middleName + " " + lastName);
                    resizeJLabelText(nameTitleJLabel);
                }
            }
        });

        lastNameJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (lastNameJTextField.getText().trim().equals("Last Name")) {
                    lastNameJTextField.setForeground(Color.BLACK);
                    lastNameJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (lastNameJTextField.getText().trim().isEmpty()) {
                    lastNameJTextField.setForeground(Color.GRAY);
                    lastNameJTextField.setText("Last Name");
                } else {
                    lastName = lastNameJTextField.getText();
                    nameTitleJLabel.setText(firstName + " " + middleName + " " + lastName);
                    resizeJLabelText(nameTitleJLabel);
                }
            }
        });

        eMailJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (eMailJTextField.getText().trim().equals("Email Address")) {
                    eMailJTextField.setForeground(Color.BLACK);
                    eMailJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (eMailJTextField.getText().trim().isEmpty()) {
                    eMailJTextField.setForeground(Color.GRAY);
                    eMailJTextField.setText("Email Address");
                } else {
                    eMail = eMailJTextField.getText();
                    if (!(countryCodeJTextField.getText().equalsIgnoreCase("Country Code"))) {
                        eMailPhoneNumberTitleJLabel.setText(eMail + "     " + "+ " + countryCode + " " + phoneNumber);
                    } else {
                        eMailPhoneNumberTitleJLabel.setText(eMail + "     " + phoneNumber);
                    }
                    resizeJLabelText(eMailPhoneNumberTitleJLabel);
                }
            }
        });

        countryCodeJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (countryCodeJTextField.getText().trim().equals("Country Code")) {
                    countryCodeJTextField.setForeground(Color.BLACK);
                    countryCodeJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (countryCodeJTextField.getText().trim().isEmpty()) {
                    countryCodeJTextField.setForeground(Color.GRAY);
                    countryCodeJTextField.setText("Country Code");
                } else {
                    countryCode = countryCodeJTextField.getText();
                    if (!(countryCodeJTextField.getText().equalsIgnoreCase("Country Code"))) {
                        eMailPhoneNumberTitleJLabel.setText(eMail + "     " + "+ " + countryCode + " " + phoneNumber);
                    } else {
                        eMailPhoneNumberTitleJLabel.setText(eMail + "     " + phoneNumber);
                    }
                    resizeJLabelText(eMailPhoneNumberTitleJLabel);
                }
            }
        });

        phoneNumberJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (phoneNumberJTextField.getText().trim().equals("Phone Number")) {
                    phoneNumberJTextField.setForeground(Color.BLACK);
                    phoneNumberJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (phoneNumberJTextField.getText().trim().isEmpty()) {
                    phoneNumberJTextField.setForeground(Color.GRAY);
                    phoneNumberJTextField.setText("Phone Number");
                } else {
                    phoneNumber = phoneNumberJTextField.getText();
                    if (!(countryCodeJTextField.getText().equalsIgnoreCase("Country Code"))) {
                        eMailPhoneNumberTitleJLabel.setText(eMail + "     " + "+ " + countryCode + " " + phoneNumber);
                    } else {
                        eMailPhoneNumberTitleJLabel.setText(eMail + "     " + phoneNumber);
                    }
                    resizeJLabelText(eMailPhoneNumberTitleJLabel);
                }
            }
        });

        birthDateJTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (birthDateJTextField.getText().trim().equals("Date of Birth")) {
                    birthDateJTextField.setForeground(Color.BLACK);
                    birthDateJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (birthDateJTextField.getText().trim().isEmpty()) {
                    birthDateJTextField.setForeground(Color.GRAY);
                    birthDateJTextField.setText("Date of Birth");
                }
            }
        });

        commentsJTextArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (commentsJTextArea.getText().trim().equals("Comments")) {
                    commentsJTextArea.setForeground(Color.BLACK);
                    commentsJTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (commentsJTextArea.getText().trim().isEmpty()) {
                    commentsJTextArea.setForeground(Color.GRAY);
                    commentsJTextArea.setText("Comments");
                }
            }
        });
    }


    // Add ActionListeners
    public void addActionListeners() {
        editProfilePictureJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilePicture = new ImageIcon(String.valueOf(EditProfilePicture.editProfilePicture()));
                Image oldImageSize = profilePicture.getImage();

                // Check if picture ratio is wider, less wide or the same as frame
                if ((profilePicture.getIconWidth() / 100) > (profilePicture.getIconHeight() / 165)) {
                    Image newImageSize = oldImageSize.getScaledInstance(profilePictureJButton.getWidth(), ((int) ((profilePicture.getIconHeight()) / (profilePicture.getIconWidth() / profilePictureJButton.getWidth()))), Image.SCALE_SMOOTH);
                    profilePicture = new ImageIcon(newImageSize);
                } else if ((profilePicture.getIconWidth() / 100) < (profilePicture.getIconHeight() / 165)) {
                    Image newImageSize = oldImageSize.getScaledInstance(((int) ((profilePicture.getIconWidth()) / (profilePicture.getIconHeight() / profilePictureJButton.getHeight()))), profilePictureJButton.getHeight(), Image.SCALE_SMOOTH);
                    profilePicture = new ImageIcon(newImageSize);
                } else {
                    Image newImageSize = oldImageSize.getScaledInstance(profilePictureJButton.getWidth(), profilePictureJButton.getHeight(), Image.SCALE_SMOOTH);
                    profilePicture = new ImageIcon(newImageSize);
                }

                profilePictureJButton.setIcon(profilePicture);
            }
        });

        deleteProfilePictureJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilePicture = new ImageIcon("src/main/java/resources/DefaultPfp.png");
                profilePictureJButton.setIcon(profilePicture);
            }
        });

        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        closeCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        addEmailAddressJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        addPhoneNumberJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    // Pack Frame and make Visible
    public void packAndVisible() {
        contactCardFullFrame.pack();
        contactCardFullFrame.setVisible(true);
    }


    // Resize JLabel text to fit Parent JLabel
    // Courtesy to (https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size/2715279#2715279)
    public void resizeJLabelText(JLabel tempJLabel) {
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
