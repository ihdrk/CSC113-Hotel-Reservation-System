import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class UITheme {

    // Background colors
    public static final Color BG_DARK = new Color(15, 23, 42);
    public static final Color BG_CARD = new Color(30, 41, 59);
    public static final Color BG_SIDEBAR = new Color(8, 15, 30);

    // Accent colors
    public static final Color GOLD = new Color(212, 175, 55);
    public static final Color GOLD_LIGHT = new Color(255, 215, 80);

    // Text colors
    public static final Color TEXT_WHITE = new Color(248, 250, 252);
    public static final Color TEXT_MUTED = new Color(148, 163, 184);

    // Status colors
    public static final Color SUCCESS = new Color(34, 197, 94);
    public static final Color ERROR = new Color(239, 68, 68);

    // Border color
    public static final Color BORDER = new Color(51, 65, 85);

    // Fonts
    public static final Font TITLE = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font HEADING = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font BODY = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font LABEL = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font SMALL = new Font("Segoe UI", Font.PLAIN, 11);

    // Creates a styled gold button
    public static JButton makeButton(String text) {
        JButton btn = new JButton(text);

        btn.setBackground(GOLD);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    // Creates a styled dark text field
    public static JTextField makeTextField() {
        JTextField field = new JTextField();

        field.setBackground(BG_DARK);
        field.setForeground(TEXT_WHITE);
        field.setFont(BODY);
        field.setCaretColor(GOLD);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        return field;
    }

    // Creates a styled dark card panel
    public static JPanel makeCard() {
        JPanel card = new JPanel();

        card.setBackground(BG_CARD);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        return card;
    }
}
