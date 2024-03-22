package tools;

import java.awt.*;

public class ColorPalette {
    public static Color bgDark = Color.decode("#776B5D");
    public static Color bgLight = Color.decode("#B0A695");
    public static Color fgDark = Color.decode("#EBE3D5");
    public static Color fgLight = Color.decode("#F3EEEA");

    static void setPaletteElegantBeige() {
        bgDark = Color.decode("#776B5D");
        bgLight = Color.decode("#B0A695");
        fgDark = Color.decode("#EBE3D5");
        fgLight = Color.decode("#F3EEEA");
    }

    static void setPaletteSoberMarine() {
        bgDark = Color.decode("#161A30");
        bgLight = Color.decode("#31304D");
        fgDark = Color.decode("#B6BBC4");
        fgLight = Color.decode("#F0ECE5");
    }

    static void setPaletteBeachWalk() {
        bgDark = Color.decode("#22668D");
        bgLight = Color.decode("#8ECDDD");
        fgDark = Color.decode("#FFCC70");
        fgLight = Color.decode("#FFFADD");
    }

}
