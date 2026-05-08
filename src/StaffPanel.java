import javax.swing.*;
import java.awt.*;

public class StaffPanel extends JPanel {

    public StaffPanel() {
        // Basic panel settings
        setBackground(UITheme.BG_DARK);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        buildUI();
    }

    // Builds the staff screen
    private void buildUI() {
        // Employees are hardcoded because they do not need to be saved in the file
        Employee emp1 = new Employee("Ahmed Al-Rashid", "E1", "0512345678",
                "Receptionist", 5000);

        Employee emp2 = new Employee("Majed Al-Qahtani", "E2", "0587654321",
                "Manager", 9000);

        JLabel title = new JLabel("Hotel Staff");
        title.setFont(UITheme.TITLE);
        title.setForeground(UITheme.TEXT_WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        cardsPanel.setBackground(UITheme.BG_DARK);

        cardsPanel.add(buildEmployeeCard(emp1));
        cardsPanel.add(buildEmployeeCard(emp2));

        add(cardsPanel, BorderLayout.CENTER);
    }

    // Creates one employee card
    private JPanel buildEmployeeCard(Employee emp) {
        JPanel card = UITheme.makeCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(250, 210));

        JLabel badge = new JLabel("Staff");
        badge.setFont(UITheme.SMALL);
        badge.setForeground(UITheme.GOLD);
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(emp.getName());
        nameLabel.setFont(UITheme.HEADING);
        nameLabel.setForeground(UITheme.TEXT_WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel posLabel = new JLabel(emp.getPosition());
        posLabel.setFont(UITheme.BODY);
        posLabel.setForeground(UITheme.GOLD);
        posLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel idLabel = new JLabel("ID: " + emp.getId());
        idLabel.setFont(UITheme.SMALL);
        idLabel.setForeground(UITheme.TEXT_MUTED);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel phoneLabel = new JLabel(emp.getPhone());
        phoneLabel.setFont(UITheme.SMALL);
        phoneLabel.setForeground(UITheme.TEXT_MUTED);
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel salaryLabel = new JLabel("Salary: $" + (int) emp.getSalary());
        salaryLabel.setFont(UITheme.SMALL);
        salaryLabel.setForeground(UITheme.SUCCESS);
        salaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(badge);
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(nameLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(posLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(idLabel);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(phoneLabel);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(salaryLabel);
        card.add(Box.createVerticalGlue());

        return card;
    }
}