import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JFrame {

    // Input fields
    private JTextField nameField;
    private JTextField courseField;
    private JTextField sectionField;
    private JButton okButton;
    private JButton clearButton;

    public StudentDashboard() {
        // Setup the main window
        setTitle("Student Dashboard");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(245, 247, 250));

        // Header
        JLabel headerLabel = new JLabel("Student Details Entry", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerLabel.setForeground(new Color(51, 51, 51));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 247, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        nameField = createTextField();
        formPanel.add(nameField, gbc);

        // Course
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Course:"), gbc);
        gbc.gridx = 1;
        courseField = createTextField();
        formPanel.add(courseField, gbc);

        // Section
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(createLabel("Section:"), gbc);
        gbc.gridx = 1;
        sectionField = createTextField();
        formPanel.add(sectionField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(245, 247, 250));

        okButton = createButton("OK", new Color(76, 175, 80));
        clearButton = createButton("Clear", new Color(158, 158, 158));

        buttonPanel.add(okButton);
        buttonPanel.add(clearButton);

        // Add components to main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Button actions
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(66, 66, 66));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(220, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        return button;
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        String section = sectionField.getText().trim();

        // Validation
        if (name.isEmpty() || course.isEmpty() || section.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please fill in all fields before submitting.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Show Thank You screen
        showThankYouScreen(name, course, section);
    }

    private void showThankYouScreen(String name, String course, String section) {
        // Create a new dialog window
        JDialog thankYouDialog = new JDialog(this, "Submission Successful", true);
        thankYouDialog.setSize(400, 300);
        thankYouDialog.setLocationRelativeTo(this);
        thankYouDialog.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Thank you message
        JLabel thankYouLabel = new JLabel("Thank You for Submitting!", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        thankYouLabel.setForeground(new Color(76, 175, 80));

        // Submitted details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        detailsPanel.add(createDetailLabel("Name: " + name));
        detailsPanel.add(createDetailLabel("Course: " + course));
        detailsPanel.add(createDetailLabel("Section: " + section));

        // Close button
        JButton closeButton = createButton("Close", new Color(76, 175, 80));
        closeButton.addActionListener(e -> {
            thankYouDialog.dispose();
            clearFields();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(closeButton);

        panel.add(thankYouLabel, BorderLayout.NORTH);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        thankYouDialog.add(panel);
        thankYouDialog.setVisible(true);
    }

    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(80, 80, 80));
        return label;
    }

    private void clearFields() {
        nameField.setText("");
        courseField.setText("");
        sectionField.setText("");
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new StudentDashboard().setVisible(true);
        });
    }
}