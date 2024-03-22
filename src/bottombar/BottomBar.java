package bottombar;

import mainwindow.MainWindow;
import tools.ColorPalette;

import javax.swing.*;
import java.awt.*;

public class BottomBar {
    public static JPanel bottomBarPanel = new JPanel();

    public BottomBar() {
        bottomBarPanel.setLayout(new GridBagLayout());
        bottomBarPanel.setPreferredSize(new Dimension(MainWindow.initialFrameWidth, 100));
        bottomBarPanel.setBackground(ColorPalette.bgLight);
    }

}
