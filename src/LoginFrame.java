import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    // Shared hotel object
    private Hotel hotel;

    // Button to enter the system
    private JButton enterButton;

    // Constructor
    public LoginFrame(Hotel hotel) {

        // Save hotel reference
        this.hotel = hotel;

        // Frame title
        setTitle("KSU Hotel");

        // Frame size
        setSize(500, 400);

        // Prevent resizing
        setResizable(false);

        // Center frame on screen
        setLocationRelativeTo(null);

        // Close application when frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color
        getContentPane().setBackground(UITheme.BG_DARK);

        // Build all UI components
        buildUI();
    }

    // Builds the login screen interface
    private void buildUI() {

        // Use vertical BoxLayout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Push content toward center
        add(Box.createVerticalGlue());

        // Hotel title label
        JLabel logoLabel = new JLabel("KSU HOTEL");

        logoLabel.setFont(UITheme.TITLE);

        logoLabel.setForeground(UITheme.GOLD);

        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(logoLabel);

        // Space between components
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Subtitle label
        JLabel subtitleLabel = new JLabel("Luxury Management System");

        subtitleLabel.setFont(UITheme.BODY);

        subtitleLabel.setForeground(UITheme.TEXT_MUTED);

        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(subtitleLabel);

        // Space before separator
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Gold separator line
        JPanel separator = new JPanel();

        separator.setBackground(UITheme.GOLD);

        separator.setMaximumSize(new Dimension(200, 2));

        separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(separator);

        // Space before button
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Create enter system button
        enterButton = UITheme.makeButton("ENTER SYSTEM");

        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button click event
        enterButton.addActionListener(e -> enterButtonClicked());

        add(enterButton);

        // Space before footer
        add(Box.createRigidArea(new Dimension(0, 15)));

        // Footer label
        JLabel footerLabel = new JLabel("Staff Portal");

        footerLabel.setFont(UITheme.SMALL);

        footerLabel.setForeground(UITheme.TEXT_MUTED);

        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(footerLabel);

        // Push content toward center
        add(Box.createVerticalGlue());
    }

    // Opens dashboard frame and closes login frame
    private void enterButtonClicked() {

        // Open main dashboard
        new MainDashboardFrame(hotel).setVisible(true);

        // Close current frame
        dispose();
    }

    // Application entry point
    public static void main(String[] args) {

        // Run Swing safely on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            // Load hotel data
            Hotel hotel = HotelFileManager.loadHotel();

            // Open login screen
            new LoginFrame(hotel).setVisible(true);
        });
    }
}
