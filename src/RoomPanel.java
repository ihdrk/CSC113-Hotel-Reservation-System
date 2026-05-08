import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {

    private Hotel hotel;
    private JPanel cardsPanel;

    public RoomPanel(Hotel hotel) {
        this.hotel = hotel;

        // Basic panel settings
        setBackground(UITheme.BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        buildUI();
    }

    // Builds the rooms screen
    private void buildUI() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(UITheme.BG_DARK);
        topBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel title = new JLabel("Hotel Rooms");
        title.setFont(UITheme.TITLE);
        title.setForeground(UITheme.TEXT_WHITE);
        topBar.add(title, BorderLayout.WEST);

        JButton refreshBtn = UITheme.makeButton("Refresh");
        refreshBtn.addActionListener(e -> loadRooms());
        topBar.add(refreshBtn, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // This panel holds all room cards
        cardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        cardsPanel.setBackground(UITheme.BG_DARK);

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setBackground(UITheme.BG_DARK);
        scrollPane.getViewport().setBackground(UITheme.BG_DARK);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        loadRooms();
    }

    // Loads all rooms from the hotel object
    private void loadRooms() {
        cardsPanel.removeAll();

        for (int i = 0; i < hotel.getRooms().getSize(); i++) {
            Room r = (Room) hotel.getRooms().get(i);
            cardsPanel.add(buildRoomCard(r));
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // Creates one card for one room
    private JPanel buildRoomCard(Room r) {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(200, 175));

        JLabel numLabel = new JLabel("Room " + r.getRoomNumber());
        numLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        numLabel.setForeground(UITheme.GOLD);
        numLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        boolean isDeluxe = r instanceof DeluxeRoom;

        JLabel typeLabel = new JLabel(isDeluxe ? "Deluxe Room" : "Standard Room");
        typeLabel.setFont(UITheme.LABEL);
        typeLabel.setForeground(isDeluxe ? UITheme.GOLD : UITheme.TEXT_MUTED);
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("$" + r.getPricePerNight() + " per night");
        priceLabel.setFont(UITheme.SMALL);
        priceLabel.setForeground(UITheme.TEXT_MUTED);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel featureLabel = new JLabel(buildFeatureString(r));
        featureLabel.setFont(UITheme.SMALL);
        featureLabel.setForeground(UITheme.TEXT_MUTED);
        featureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statusLabel = new JLabel(r.isAvailable() ? "Available" : "Booked");
        statusLabel.setFont(UITheme.LABEL);
        statusLabel.setForeground(r.isAvailable() ? UITheme.SUCCESS : UITheme.ERROR);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(numLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(typeLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(priceLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(featureLabel);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(statusLabel);
        card.add(Box.createVerticalGlue());

        return card;
    }

    // Returns the room features as a text
    private String buildFeatureString(Room r) {
        if (r instanceof DeluxeRoom) {
            DeluxeRoom d = (DeluxeRoom) r;

            StringBuilder sb = new StringBuilder();

            if (d.hasTV()) {
                sb.append("TV ");
            }

            if (d.hasJacuzzi()) {
                sb.append("Jacuzzi ");
            }

            if (d.hasOceanView()) {
                sb.append("Ocean View");
            }

            return sb.toString().trim();

        } else if (r instanceof StandardRoom) {
            StandardRoom s = (StandardRoom) r;

            if (s.hasTV()) {
                return "TV";
            } else {
                return "No TV";
            }
        }

        return "";
    }
}
