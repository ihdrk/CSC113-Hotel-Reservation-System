import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainDashboardFrame extends JFrame {

    // Shared hotel object used across all panels
    private Hotel hotel;

    // The area where panels will appear and switch
    private JPanel contentArea;

    // Constructor
    public MainDashboardFrame(Hotel hotel) {

        // Store hotel reference
        this.hotel = hotel;

        // Frame title
        setTitle("KSU Hotel Management");

        // Frame size
        setSize(1100, 700);

        // Center frame on screen
        setLocationRelativeTo(null);

        // Prevent automatic closing because we want auto-save first
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Main frame layout
        setLayout(new BorderLayout());

        // Build top navigation bar
        buildTopBar();

        // Build left sidebar
        buildSidebar();

        // Build center content area
        buildContentArea();

        // Window listener for auto-save before closing
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                // Save data before exit
                saveData();

                // Close application
                System.exit(0);
            }
        });
    }

    // Creates the top bar
    private void buildTopBar() {

        // Top bar panel with BorderLayout
        JPanel topBar = new JPanel(new BorderLayout());

        topBar.setBackground(UITheme.BG_SIDEBAR);

        topBar.setPreferredSize(new Dimension(0, 45));

        topBar.setBorder(
                BorderFactory.createEmptyBorder(0, 15, 0, 15)
        );

        // Hotel title label
        JLabel titleLabel = new JLabel("KSU Hotel");

        titleLabel.setFont(UITheme.HEADING);

        titleLabel.setForeground(UITheme.GOLD);

        // Add title to left side
        topBar.add(titleLabel, BorderLayout.WEST);

        // Save button
        JButton saveBtn = UITheme.makeButton("Save Data");

        // Save button action
        saveBtn.addActionListener(e -> saveData());

        // Right-side panel for save button
        JPanel rightPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 0, 7)
        );

        rightPanel.setBackground(UITheme.BG_SIDEBAR);

        rightPanel.add(saveBtn);

        // Add right panel to top bar
        topBar.add(rightPanel, BorderLayout.EAST);

        // Add top bar to frame
        add(topBar, BorderLayout.NORTH);
    }

    // Creates the left sidebar navigation
    private void buildSidebar() {

        JPanel sidebar = new JPanel();

        // Vertical layout for buttons
        sidebar.setLayout(
                new BoxLayout(sidebar, BoxLayout.Y_AXIS)
        );

        sidebar.setBackground(UITheme.BG_SIDEBAR);

        sidebar.setPreferredSize(new Dimension(180, 0));

        sidebar.setBorder(
                BorderFactory.createEmptyBorder(10, 0, 0, 0)
        );

        // Sidebar button labels
        String[] labels = {
                "Dashboard",
                "Customers",
                "Rooms",
                "Reservations",
                "Services",
                "Staff"
        };

        // Create all navigation buttons
        for (String label : labels) {

            JButton btn = new JButton(label);

            // Button styling
            btn.setBackground(UITheme.BG_SIDEBAR);

            btn.setForeground(UITheme.TEXT_WHITE);

            btn.setFont(UITheme.BODY);

            btn.setHorizontalAlignment(SwingConstants.LEFT);

            btn.setBorderPainted(false);

            btn.setFocusPainted(false);

            btn.setOpaque(true);

            btn.setMaximumSize(new Dimension(180, 45));

            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Hover effect
            btn.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {

                    btn.setBackground(UITheme.BG_CARD);
                }

                @Override
                public void mouseExited(MouseEvent e) {

                    btn.setBackground(UITheme.BG_SIDEBAR);
                }
            });

            // Navigation action
            btn.addActionListener(e -> navigate(label));

            // Add button to sidebar
            sidebar.add(btn);
        }

        // Push buttons upward
        sidebar.add(Box.createVerticalGlue());

        // Add sidebar to left side
        add(sidebar, BorderLayout.WEST);
    }

    // Handles switching between panels
    private void navigate(String panel) {

        switch (panel) {

            case "Dashboard":
                showPanel(new DashboardPanel(hotel));
                break;

            case "Customers":
                showPanel(new CustomerPanel(hotel));
                break;

            case "Rooms":
                showPanel(new RoomPanel(hotel));
                break;

            case "Reservations":
                showPanel(new ReservationPanel(hotel));
                break;

            case "Services":
                showPanel(new ServicePanel(hotel));
                break;

            case "Staff":
                showPanel(new StaffPanel());
                break;
        }
    }

    // Creates the center content area
    private void buildContentArea() {

        // Main panel container
        contentArea = new JPanel(new BorderLayout());

        contentArea.setBackground(UITheme.BG_DARK);

        // Add content area to center
        add(contentArea, BorderLayout.CENTER);

        // Default startup panel
        showPanel(new DashboardPanel(hotel));
    }

    // Replaces current panel with a new panel
    private void showPanel(JPanel panel) {

        // Remove old panel
        contentArea.removeAll();

        // Add new panel
        contentArea.add(panel, BorderLayout.CENTER);

        // Refresh UI
        contentArea.revalidate();

        contentArea.repaint();
    }

    // Saves hotel data to file
    private void saveData() {

        try {

            // Save hotel object
            HotelFileManager.saveHotel(hotel);

            // Success message
            JOptionPane.showMessageDialog(
                    this,
                    "Data saved successfully.",
                    "Saved",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            // Error message if save fails
            JOptionPane.showMessageDialog(
                    this,
                    "Save failed: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
