import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private Hotel hotel;
    private JButton enterButton;

    public LoginFrame(Hotel hotel) {
        this.hotel = hotel;

        setTitle("KSU Hotel");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(UITheme.BG_DARK);

        buildUI();
    }

    private void buildUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());

        JLabel logoLabel = new JLabel("KSU HOTEL");
        logoLabel.setFont(UITheme.TITLE);
        logoLabel.setForeground(UITheme.GOLD);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logoLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subtitleLabel = new JLabel("Luxury Management System");
        subtitleLabel.setFont(UITheme.BODY);
        subtitleLabel.setForeground(UITheme.TEXT_MUTED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(subtitleLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel separator = new JPanel();
        separator.setBackground(UITheme.GOLD);
        separator.setMaximumSize(new Dimension(200, 2));
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(separator);

        add(Box.createRigidArea(new Dimension(0, 20)));

        enterButton = UITheme.makeButton("ENTER SYSTEM");
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterButton.addActionListener(e -> enterButtonClicked());
        add(enterButton);

        add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel footerLabel = new JLabel("Staff Portal");
        footerLabel.setFont(UITheme.SMALL);
        footerLabel.setForeground(UITheme.TEXT_MUTED);
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(footerLabel);

        add(Box.createVerticalGlue());
    }

    private void enterButtonClicked() {
        new MainDashboardFrame(hotel).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Hotel hotel = HotelFileManager.loadHotel();
            new LoginFrame(hotel).setVisible(true);
        });
    }
}


// --- End of LoginFrame.java --- 
