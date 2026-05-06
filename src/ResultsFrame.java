import javax.swing.*;
import java.awt.*;

public class ResultsFrame extends JFrame {

    private JTextArea resultArea;
    private JButton closeButton;

    public ResultsFrame(String title, String content, Color accentColor) {

        setTitle(title);

        setSize(550, 400);

        setLocationRelativeTo(null);

        setResizable(false);

        getContentPane().setBackground(UITheme.BG_DARK);

        setLayout(new BorderLayout());

        buildUI(title, content, accentColor);
    }

    private void buildUI(String title, String content, Color accentColor) {

        // TOP BAR

        JPanel topBar = new JPanel(new BorderLayout());

        topBar.setBackground(UITheme.BG_CARD);

        topBar.setPreferredSize(new Dimension(0, 50));

        JPanel stripe = new JPanel();

        stripe.setBackground(accentColor);

        stripe.setPreferredSize(new Dimension(6, 0));

        topBar.add(stripe, BorderLayout.WEST);

        JLabel titleLabel = new JLabel(" " + title);

        titleLabel.setFont(UITheme.HEADING);

        titleLabel.setForeground(UITheme.TEXT_WHITE);

        topBar.add(titleLabel, BorderLayout.CENTER);

        add(topBar, BorderLayout.NORTH);

        // CONTENT AREA

        resultArea = new JTextArea(content);

        resultArea.setEditable(false);

        resultArea.setFont(UITheme.BODY);

        resultArea.setBackground(UITheme.BG_DARK);

        resultArea.setForeground(UITheme.TEXT_WHITE);

        resultArea.setLineWrap(true);

        resultArea.setWrapStyleWord(true);

        resultArea.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JScrollPane scrollPane = new JScrollPane(resultArea);

        scrollPane.setBackground(UITheme.BG_DARK);

        scrollPane.getViewport().setBackground(UITheme.BG_DARK);

        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);

        // BOTTOM BAR

        JPanel bottomBar = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 15, 10)
        );

        bottomBar.setBackground(UITheme.BG_CARD);

        closeButton = UITheme.makeButton("Close");

        closeButton.addActionListener(e -> dispose());

        bottomBar.add(closeButton);

        add(bottomBar, BorderLayout.SOUTH);
    }

    // Normal result

    public static void showResult(String title, String content) {

        new ResultsFrame(
                title,
                content,
                UITheme.GOLD
        ).setVisible(true);
    }

    // Success result

    public static void showSuccess(String title, String content) {

        new ResultsFrame(
                title,
                content,
                UITheme.SUCCESS
        ).setVisible(true);
    }

    // Error result

    public static void showError(String title, String content) {

        new ResultsFrame(
                title,
                content,
                UITheme.ERROR
        ).setVisible(true);
    }
}
