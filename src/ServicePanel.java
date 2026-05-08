import javax.swing.*;
import java.awt.*;

public class ServicePanel extends JPanel {

    private Hotel hotel;

    private JTextField addCustomerIdField;
    private JTextField removeCustomerIdField;
    private JTextField removeServiceNameField;

    public ServicePanel(Hotel hotel) {
        this.hotel = hotel;

        // Basic panel settings
        setBackground(UITheme.BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        buildUI();
    }

    // Builds the service screen
    private void buildUI() {
        JLabel title = new JLabel("Services");
        title.setFont(UITheme.TITLE);
        title.setForeground(UITheme.TEXT_WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 20, 0));
        center.setBackground(UITheme.BG_DARK);

        center.add(buildAddSection());
        center.add(buildRemoveSection());

        add(center, BorderLayout.CENTER);
    }

    // Builds the add service section
    private JPanel buildAddSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("Add Service to Customer");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 15)));

        addCustomerIdField = addLabeledField(card, "Customer ID");

        JLabel selectLabel = new JLabel("Select a service:");
        selectLabel.setFont(UITheme.LABEL);
        selectLabel.setForeground(UITheme.TEXT_MUTED);
        selectLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(selectLabel);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel serviceGrid = new JPanel(new GridLayout(2, 3, 10, 10));
        serviceGrid.setBackground(UITheme.BG_CARD);
        serviceGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        serviceGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        addServiceButton(serviceGrid, "Breakfast", 30.0);
        addServiceButton(serviceGrid, "Lunch", 45.0);
        addServiceButton(serviceGrid, "Dinner", 50.0);
        addServiceButton(serviceGrid, "Laundry", 25.0);
        addServiceButton(serviceGrid, "Spa", 100.0);
        addServiceButton(serviceGrid, "Pickup", 80.0);

        card.add(serviceGrid);
        card.add(Box.createVerticalGlue());

        return card;
    }

    // Creates a service button
    private void addServiceButton(JPanel grid, String name, double price) {
        JButton btn = UITheme.makeButton(
                "<html><center>" + name + "<br>$" + (int) price + "</center></html>"
        );

        btn.addActionListener(e -> serviceButtonClicked(name, price));

        grid.add(btn);
    }

    // Adds the selected service to a customer
    private void serviceButtonClicked(String name, double price) {
        String id = addCustomerIdField.getText().trim();

        if (id.isEmpty()) {
            ResultsFrame.showError("Missing Input", "Please enter a Customer ID first.");
            return;
        }

        Customer c = hotel.searchCustomer(id);

        if (c == null) {
            ResultsFrame.showError("Not Found", "No customer found with ID: " + id);
            return;
        }

        if (!c.hasReservations()) {
            ResultsFrame.showError("No Reservation",
                    "Customer must have an active reservation before adding services.");
            return;
        }

        c.addService(new Service(name, price));

        ResultsFrame.showSuccess("Service Added",
                name + " added successfully.\nCost: $" +
                        String.format("%.2f", price) +
                        "\nNew Total Bill: $" +
                        String.format("%.2f", c.calculateBill()));
    }

    // Builds the remove service section
    private JPanel buildRemoveSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("Remove Service");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 15)));

        removeCustomerIdField = addLabeledField(card, "Customer ID");
        removeServiceNameField = addLabeledField(card, "Service Name");

        JButton removeBtn = UITheme.makeButton("Remove Service");
        removeBtn.setBackground(UITheme.ERROR);
        removeBtn.setForeground(UITheme.TEXT_WHITE);
        removeBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        removeBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        removeBtn.addActionListener(e -> removeClicked());
        card.add(removeBtn);

        card.add(Box.createVerticalGlue());

        return card;
    }

    // Removes a service from a customer
    private void removeClicked() {
        String id = removeCustomerIdField.getText().trim();
        String serviceName = removeServiceNameField.getText().trim();

        if (id.isEmpty() || serviceName.isEmpty()) {
            ResultsFrame.showError("Missing Fields",
                    "Please enter both Customer ID and Service Name.");
            return;
        }

        Customer c = hotel.searchCustomer(id);

        if (c == null) {
            ResultsFrame.showError("Not Found", "No customer found with ID: " + id);
            return;
        }

        if (c.removeService(serviceName)) {
            ResultsFrame.showSuccess("Removed",
                    serviceName + " removed successfully." +
                            "\nUpdated Total Bill: $" +
                            String.format("%.2f", c.calculateBill()));
        } else {
            ResultsFrame.showError("Not Found",
                    "Service \"" + serviceName + "\" not found for this customer.");
        }
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
