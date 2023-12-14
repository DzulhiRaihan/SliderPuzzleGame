package component;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class PixelFont {
    private static Font font;

    static {
        renderFont();
    }

    private static void renderFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/PixelMplus12-Regular.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/PixelMplus12-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font getFont(float size) {
        return font.deriveFont(size);
    }

    public static Font getFont(int style, float size) {
        return font.deriveFont(style, size);
    }
}

