import java.awt.*;
import javax.swing.*;

public class CustomerPanel extends JPanel {

    private Hotel hotel;

    private JTextField nameField;
    private JTextField idField;
    private JTextField phoneField;

    private JTextField searchIdField;

    public CustomerPanel(Hotel hotel) {
        this.hotel = hotel;

        // Basic panel settings
        setBackground(UITheme.BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        buildUI();
    }

    // Builds the customer screen
    private void buildUI() {
        JLabel title = new JLabel("Customers");
        title.setFont(UITheme.TITLE);
        title.setForeground(UITheme.TEXT_WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 20, 0));
        center.setBackground(UITheme.BG_DARK);

        center.add(buildRegisterSection());
        center.add(buildSearchSection());

        add(center, BorderLayout.CENTER);
    }

    // Builds the register customer section
    private JPanel buildRegisterSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("Register New Customer");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 20)));

        nameField = addLabeledField(card, "Full Name");
        idField = addLabeledField(card, "Customer ID");
        phoneField = addLabeledField(card, "Phone Number");

        card.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton registerBtn = UITheme.makeButton("Register Customer");
        registerBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        registerBtn.addActionListener(e -> registerClicked());
        card.add(registerBtn);

        card.add(Box.createVerticalGlue());

        return card;
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

    // Registers a new customer
    private void registerClicked() {
        // Read and clean user input from text fields
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        String phone = phoneField.getText().trim();
        // checking if all fields are filled
        if (name.isEmpty() || id.isEmpty() || phone.isEmpty()) {
            ResultsFrame.showError("Missing Fields", "Please fill in all fields.");
            return;
        }
        // checking name is only letters
        if (!isValidName(name)) {
            ResultsFrame.showError("Invalid Name",
            "Name must contain letters and spaces only.");
            return;
        }
        // checking id is only digits
        if (!isOnlyDigits(id)) {
            ResultsFrame.showError("Invalid ID",
            "Customer ID must contain numbers only.");
            return;
        }
        // checking phon numeber is only digits
        if (!isOnlyDigits(phone)) {
            ResultsFrame.showError("Invalid Phone",
            "Phone number must contain numbers only.");
            return;
        }
        // Create new customer object using entered information
        Customer customer = new Customer(name, id, phone);
        // Add customer to hotel linked list if ID is unique
        if (hotel.addCustomer(customer)) {
            ResultsFrame.showSuccess("Registered",
                    "Customer registered successfully.\nName: " + name + "\nID: " + id);

            nameField.setText("");// Clear text fields after successful registration
            idField.setText("");
            phoneField.setText("");

        } else {
            ResultsFrame.showError("Registration Failed",
                    "A customer with ID \"" + id + "\" already exists.");
        }
    }

    // Builds the search customer section
    private JPanel buildSearchSection() {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel("Find Customer");
        sectionTitle.setFont(UITheme.HEADING);
        sectionTitle.setForeground(UITheme.TEXT_WHITE);
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(sectionTitle);

        card.add(Box.createRigidArea(new Dimension(0, 20)));

        searchIdField = addLabeledField(card, "Customer ID");

        JButton findBtn = UITheme.makeButton("Find Customer");
        findBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        findBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        findBtn.addActionListener(e -> findCustomerClicked());
        card.add(findBtn);

        card.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton viewAllBtn = UITheme.makeButton("View All Customers");
        viewAllBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewAllBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        viewAllBtn.addActionListener(e -> viewAllClicked());
        card.add(viewAllBtn);

        card.add(Box.createVerticalGlue());

        return card;
    }

    // Finds one customer by ID
    private void findCustomerClicked() {
        String id = searchIdField.getText().trim();

        if (id.isEmpty()) {
            ResultsFrame.showError("Missing Input", "Please enter a Customer ID.");
            return;
        }

        Customer c = hotel.searchCustomer(id);// Search for customer using entered ID

        if (c == null) {
            ResultsFrame.showError("Not Found", "No customer found with ID: " + id);
            return;
        }
        // Build customer information text for results window
        String info = "Name: " + c.getName() + "\n" +
                "ID: " + c.getId() + "\n" +
                "Phone: " + c.getPhone() + "\n" +
                "Reservations: " + c.getNumReservations() + "\n" +
                "Services: " + c.getNumServices() + "\n" +
                "Total Bill: $" + String.format("%.2f", c.calculateBill());

        ResultsFrame.showResult("Customer Found", info);
    }

    // Displays all customers
    private void viewAllClicked() {
        if (hotel.getCustomers().getSize() == 0) {
            ResultsFrame.showError("No Customers", "No customers registered yet.");
            return;
        }
        // StringBuilder used to efficiently build customer list text
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < hotel.getCustomers().getSize(); i++) {// Traverse customer linked list and display all customers
            Customer c = (Customer) hotel.getCustomers().get(i);

            sb.append("Name: ").append(c.getName())
                    .append(" | ID: ").append(c.getId())
                    .append(" | Reservations: ").append(c.getNumReservations())
                    .append(" | Bill: $").append(String.format("%.2f", c.calculateBill()));

            if (i < hotel.getCustomers().getSize() - 1) {
                sb.append("\n--------------------\n");
            }
        }

        ResultsFrame.showResult("All Customers (" + hotel.getCustomers().getSize() + ")",
                sb.toString());
    }



    private boolean isOnlyDigits(String text) {// Checks if text contains numbers only
    for (int i = 0; i < text.length(); i++) {
        if (!Character.isDigit(text.charAt(i))) {
            return false;
        }
    }
    return true;
}

    private boolean isValidName(String name) {// Checks if name contains only letters and spaces
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {
            return false;
        }
    }
    return true;
}
}