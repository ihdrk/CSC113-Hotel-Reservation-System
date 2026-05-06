import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainDashboardFrame extends JFrame {

    private Hotel hotel;
    private JPanel contentArea;

    public MainDashboardFrame(Hotel hotel) {

        this.hotel = hotel;

        setTitle("KSU Hotel Management");
        setSize(1100, 700);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setLayout(new BorderLayout());

        buildTopBar();
        buildSidebar();
        buildContentArea();

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                saveData();
                System.exit(0);

            }
        });
    }

    private void buildTopBar() {

        JPanel topBar = new JPanel(new BorderLayout());

        topBar.setBackground(UITheme.BG_SIDEBAR);
        topBar.setPreferredSize(new Dimension(0, 45));

        topBar.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JLabel titleLabel = new JLabel("KSU Hotel");

        titleLabel.setFont(UITheme.HEADING);
        titleLabel.setForeground(UITheme.GOLD);

        topBar.add(titleLabel, BorderLayout.WEST);

        JButton saveBtn = UITheme.makeButton("Save Data");

        saveBtn.addActionListener(e -> saveData());

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 7));

        rightPanel.setBackground(UITheme.BG_SIDEBAR);

        rightPanel.add(saveBtn);

        topBar.add(rightPanel, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);
    }

    private void buildSidebar() {

        JPanel sidebar = new JPanel();

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.setBackground(UITheme.BG_SIDEBAR);

        sidebar.setPreferredSize(new Dimension(180, 0));

        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        String[] labels = {
                "Dashboard",
                "Customers",
                "Rooms",
                "Reservations",
                "Services",
                "Staff"
        };

        for (String label : labels) {

            JButton btn = new JButton(label);

            btn.setBackground(UITheme.BG_SIDEBAR);
            btn.setForeground(UITheme.TEXT_WHITE);

            btn.setFont(UITheme.BODY);

            btn.setHorizontalAlignment(SwingConstants.LEFT);

            btn.setBorderPainted(false);

            btn.setFocusPainted(false);

            btn.setOpaque(true);

            btn.setMaximumSize(new Dimension(180, 45));

            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e) {

                    btn.setBackground(UITheme.BG_CARD);

                }

                public void mouseExited(MouseEvent e) {

                    btn.setBackground(UITheme.BG_SIDEBAR);

                }
            });

            btn.addActionListener(e -> navigate(label));

            sidebar.add(btn);
        }

        sidebar.add(Box.createVerticalGlue());

        add(sidebar, BorderLayout.WEST);
    }

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

    private void buildContentArea() {

        contentArea = new JPanel(new BorderLayout());

        contentArea.setBackground(UITheme.BG_DARK);

        add(contentArea, BorderLayout.CENTER);

        showPanel(new DashboardPanel(hotel));
    }

    private void showPanel(JPanel panel) {

        contentArea.removeAll();

        contentArea.add(panel, BorderLayout.CENTER);

        contentArea.revalidate();

        contentArea.repaint();
    }

    private void saveData() {

        try {

            HotelFileManager.saveHotel(hotel);

            JOptionPane.showMessageDialog(
                    this,
                    "Data saved successfully.",
                    "Saved",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Save failed: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
