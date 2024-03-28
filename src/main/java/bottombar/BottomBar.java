package bottombar;

import bottombar.bottombarbuttons.NewCard;
import bottombar.bottombarbuttons.OpenFile;
import bottombar.bottombarbuttons.SaveFile;
import mainwindow.MainWindow;
import tools.ColorPalette;

import javax.swing.*;
import java.awt.*;

public class BottomBar {
    public static JPanel bottomBarJPanel = new JPanel();

    GridBagConstraints gbc = new GridBagConstraints();

    JButton newCardJButton = new NewCard();
    JButton openFileJButton = new OpenFile();
    JButton saveFileJButton = new SaveFile();
    JButton deleteSelectedCardJButton = new JButton();
    JButton deleteAllCardsJButton = new JButton();
    JButton searchBarButtonJButton = new JButton();
    JTextField searchBarJTextField = new JTextField();


    public BottomBar() {
        configureJButtons();
        configureBottomBarJPanel();
    }


    // Configure JButtons
    public void configureJButtons() {
        newCardJButton.setText("New");
        openFileJButton.setText("Open");
        saveFileJButton.setText("Save");
        deleteSelectedCardJButton.setText("Delete");
        deleteAllCardsJButton.setText("Clear");
        searchBarButtonJButton.setText("Search");
    }


    // Configure bottomBarJPanel layout
    public void configureBottomBarJPanel() {
        bottomBarJPanel.setLayout(new GridBagLayout());
        bottomBarJPanel.setPreferredSize(new Dimension(MainWindow.initialFrameWidth, 100));
        bottomBarJPanel.setBackground(ColorPalette.bgLight);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;

        gbc.gridx = 0;
        bottomBarJPanel.add(newCardJButton, gbc);

        gbc.gridx = 1;
        bottomBarJPanel.add(openFileJButton, gbc);

        gbc.gridx = 2;
        bottomBarJPanel.add(saveFileJButton, gbc);

        gbc.gridx = 3;
        bottomBarJPanel.add(deleteSelectedCardJButton, gbc);

        gbc.gridx = 4;
        bottomBarJPanel.add(deleteAllCardsJButton, gbc);

        gbc.gridx = 5;
        bottomBarJPanel.add(searchBarButtonJButton, gbc);

        gbc.weightx = 10;
        gbc.gridx = 6;
        bottomBarJPanel.add(searchBarJTextField, gbc);
    }

}
