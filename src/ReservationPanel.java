import java.awt.*;
import javax.swing.*;

public class ReservationPanel extends JPanel {

    private Hotel hotel;

    private JTextField makeCustomerField;
    private JTextField roomField;

    private JSpinner checkInSpinner;
    private JSpinner checkOutSpinner;

    private JTextField cancelCustomerField;
    private JTextField cancelResIdField;

    private JTextField viewCustomerField;

    public ReservationPanel(Hotel hotel) {
        this.hotel = hotel;

        // Basic panel settings
        setBackground(UITheme.BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        buildUI();
    }

    // Builds the reservation screen
    private void buildUI() {
        JLabel title = new JLabel("Reservations");
        title.setFont(UITheme.TITLE);
        title.setForeground(UITheme.TEXT_WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel stack = new JPanel();
        stack.setLayout(new BoxLayout(stack, BoxLayout.Y_AXIS));
        stack.setBackground(UITheme.BG_DARK);

        stack.add(buildMakeSection());
        stack.add(Box.createRigidArea(new Dimension(0, 15)));
        stack.add(buildCancelSection());
        stack.add(Box.createRigidArea(new Dimension(0, 15)));
        stack.add(buildViewSection());

        JScrollPane scrollPane = new JScrollPane(stack);
        scrollPane.setBackground(UITheme.BG_DARK);
        scrollPane.getViewport().setBackground(UITheme.BG_DARK);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    // Builds the make reservation section
    private JPanel buildMakeSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("Make Reservation");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 15)));

        makeCustomerField = addLabeledField(card, "Customer ID");
        roomField = addLabeledField(card, "Room Number");

        JPanel spinnerRow = new JPanel(new GridLayout(1, 4, 10, 0));
        spinnerRow.setBackground(UITheme.BG_CARD);
        spinnerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        spinnerRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        checkInSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        checkOutSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 31, 1));

        styleSpinner(checkInSpinner);
        styleSpinner(checkOutSpinner);

        spinnerRow.add(makeSpinnerLabel("Check-in Day"));
        spinnerRow.add(checkInSpinner);
        spinnerRow.add(makeSpinnerLabel("Check-out Day"));
        spinnerRow.add(checkOutSpinner);

        card.add(spinnerRow);
        card.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton makeBtn = UITheme.makeButton("Make Reservation");
        makeBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        makeBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        makeBtn.addActionListener(e -> makeReservationClicked());
        card.add(makeBtn);

        return card;
    }

    // Changes the spinner colors to match the project theme
    private void styleSpinner(JSpinner s) {
        s.setBackground(UITheme.BG_DARK);

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) s.getEditor();
        editor.getTextField().setBackground(UITheme.BG_DARK);
        editor.getTextField().setForeground(UITheme.TEXT_WHITE);
        editor.getTextField().setFont(UITheme.BODY);
    }

    // Creates a label for the spinner row
    private JLabel makeSpinnerLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.LABEL);
        label.setForeground(UITheme.TEXT_MUTED);
        return label;
    }

    // Makes a new reservation
    private void makeReservationClicked() {
        try {
            String customerId = makeCustomerField.getText().trim();

            // This may throw NumberFormatException if the room number is not numeric
            int roomNum = parseRoomNumber(roomField.getText().trim());

            int checkIn = (int) checkInSpinner.getValue();
            int checkOut = (int) checkOutSpinner.getValue();

            // This may throw RoomUnavailableException if the room is already booked
            boolean success = hotel.makeReservation(customerId, roomNum, checkIn, checkOut);

            if (success) {
                 Customer customer = hotel.searchCustomer(customerId);
                Reservation latest = customer.getLastReservation();
                ResultsFrame.showSuccess("Reservation Confirmed",
                        "Reservation created successfully!" +
                                "\nReservation ID: " + latest.getReservationId() +
                                "\nRoom: " + roomNum +
                                "\nCheck-in: Day " + checkIn +
                                "\nCheck-out: Day " + checkOut);
            } else {
                ResultsFrame.showError("Reservation Failed",
                        "Could not create reservation.\nCheck the customer ID and dates.");
            }

        } catch (NumberFormatException e) {
            ResultsFrame.showError("Invalid Input",
                    "Room number must be a number.\nYou entered: \"" +
                            roomField.getText().trim() + "\"");

        } catch (RoomUnavailableException e) {
            ResultsFrame.showError("Room Unavailable", e.getMessage());
        }
    }

    // Parses the room number from String to int
    private int parseRoomNumber(String text) {
        return Integer.parseInt(text);
    }

    // Builds the cancel reservation section
    private JPanel buildCancelSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("Cancel Reservation");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 15)));

        cancelCustomerField = addLabeledField(card, "Customer ID");
        cancelResIdField = addLabeledField(card, "Reservation ID");

        JButton cancelBtn = UITheme.makeButton("Cancel Reservation");
        cancelBtn.setBackground(UITheme.ERROR);
        cancelBtn.setForeground(UITheme.TEXT_WHITE);
        cancelBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        cancelBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cancelBtn.addActionListener(e -> cancelClicked());
        card.add(cancelBtn);

        return card;
    }

    // Cancels an existing reservation
    private void cancelClicked() {
        String customerId = cancelCustomerField.getText().trim();
        String reservationId = cancelResIdField.getText().trim();

        if (customerId.isEmpty() || reservationId.isEmpty()) {
            ResultsFrame.showError("Missing Fields",
                    "Please enter both Customer ID and Reservation ID.");
            return;
        }

        if (hotel.cancelReservation(customerId, reservationId)) {
            ResultsFrame.showSuccess("Cancelled",
                    "Reservation " + reservationId +
                            " cancelled successfully.\nRoom is now available again.");
        } else {
            ResultsFrame.showError("Cancel Failed",
                    "Reservation not found.\nCheck the Customer ID and Reservation ID.");
        }
    }

    // Builds the view reservation section
    private JPanel buildViewSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("View Customer Reservations");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 15)));

        viewCustomerField = addLabeledField(card, "Customer ID");

        JButton viewBtn = UITheme.makeButton("View Reservations");
        viewBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        viewBtn.addActionListener(e -> viewClicked());
        card.add(viewBtn);

        return card;
    }

    // Displays reservation information for a customer
    private void viewClicked() {
        String id = viewCustomerField.getText().trim();

        if (id.isEmpty()) {
            ResultsFrame.showError("Missing Input", "Please enter a Customer ID.");
            return;
        }

        Customer c = hotel.searchCustomer(id);

        if (c == null) {
            ResultsFrame.showError("Not Found", "No customer found with ID: " + id);
            return;
        }

        String info = "Customer: " + c.getName() + " (ID: " + c.getId() + ")\n" +
                "Reservation IDs: " + c.getReservationIds() + "\n" +
                "Reservations: " + c.getNumReservations() + "\n" +
                "Services: " + c.getNumServices() + "\n" +
                "Total Bill: $" + String.format("%.2f", c.calculateBill());

        ResultsFrame.showResult("Reservations for " + c.getName(), info);
    }

    // Adds a label and text field to a panel
    private JTextField addLabeledField(JPanel parent, String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(UITheme.LABEL);
        label.setForeground(UITheme.TEXT_MUTED);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(label);

        parent.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextField field = UITheme.makeTextField();
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        parent.add(field);

        parent.add(Box.createRigidArea(new Dimension(0, 12)));

        return field;
    }
}