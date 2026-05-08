import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private Hotel hotel;

    public DashboardPanel(Hotel hotel) {
        this.hotel = hotel;

        // Basic panel settings
        setBackground(UITheme.BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        buildUI();
    }

    // Builds the dashboard screen
    private void buildUI() {
        JLabel title = new JLabel("Dashboard");
        title.setFont(UITheme.TITLE);
        title.setForeground(UITheme.TEXT_WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Grid for the statistic cards
        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setBackground(UITheme.BG_DARK);

        grid.add(makeStatCard("Total Rooms",
                String.valueOf(hotel.getRooms().getSize()),
                UITheme.TEXT_WHITE));

        grid.add(makeStatCard("Available Rooms",
                String.valueOf(getAvailableRooms()),
                UITheme.SUCCESS));

        grid.add(makeStatCard("Registered Customers",
                String.valueOf(hotel.getCustomers().getSize()),
                UITheme.GOLD));

        grid.add(makeStatCard("Total Revenue",
                String.format("$%.2f", hotel.totalRevenueRecursive(0)),
                UITheme.GOLD));

        add(grid, BorderLayout.CENTER);
    }

    // Creates one statistic card
    private JPanel makeStatCard(String label, String value, Color valueColor) {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLabel.setForeground(valueColor);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(UITheme.BODY);
        nameLabel.setForeground(UITheme.TEXT_MUTED);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(valueLabel);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(nameLabel);
        card.add(Box.createVerticalGlue());

        return card;
    }

    // Counts the available rooms
    private int getAvailableRooms() {
        int count = 0;

        for (int i = 0; i < hotel.getRooms().getSize(); i++) {
            Room r = (Room) hotel.getRooms().get(i);

            if (r.isAvailable()) {
                count++;
            }
        }

        return count;
    }
}
