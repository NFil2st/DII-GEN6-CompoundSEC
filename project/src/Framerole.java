import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class Framerole extends JFrame {
    private JComboBox<String> roleComboBox;
    private JButton submitButton;
    private JPasswordField passwordadminField, passwordmanagerField;
    private JTextField changeRoom, changeFloor;
    private static ArrayList<String> accessLogs = new ArrayList<>();

    informationCard info = informationCard.getInstance();
    Object card = null;

    public Framerole() {
        setTitle("Role Selection System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("Select Role: "));
        roleComboBox = new JComboBox<>(new String[] { "Select", "Admin", "Customer", "Manager" });
        headerPanel.add(roleComboBox);
        add(headerPanel, BorderLayout.NORTH);

        JPanel footerPanel = new JPanel();
        submitButton = new JButton("Submit");
        footerPanel.add(submitButton);
        add(footerPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> handleRoleSelection());
    }

    private void handleRoleSelection() {
        int role = roleComboBox.getSelectedIndex();
        String timestamp = getCurrentTime();

        if (role == 1) {
            openAdminWindow();
        } else if (role == 2) {
            logAccess("Customer", timestamp);
            JOptionPane.showMessageDialog(this, "Welcome Customer!\nAccess Time: " + timestamp);
            card = new Customer();
            new ScanCard("Customer", card, accessLogs);
            this.dispose();
        } else if (role == 3) {
            openManagerWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid role.");
        }
    }

    private void openAdminWindow() {
        JFrame adminWindow = new JFrame("Admin Window");
        adminWindow.setSize(400, 300);
        adminWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Enter Password (Admin only): "));
        passwordadminField = new JPasswordField(10);
        centerPanel.add(passwordadminField);

        JPanel footerPanel = new JPanel();
        JButton sureButton = new JButton("Submit");
        footerPanel.add(sureButton);
        adminWindow.add(footerPanel, BorderLayout.SOUTH);

        sureButton.addActionListener(e -> {
            String password = new String(passwordadminField.getPassword());
            if (info.passwordAdmin.equals(password)) {
                String timestamp = getCurrentTime();
                logAccess("Admin", timestamp);
                JOptionPane.showMessageDialog(adminWindow, "Welcome Admin!\nAccess Time: " + timestamp);
                card = new Admin();
                new ScanCard("Admin", card, accessLogs);
                adminWindow.dispose();
            } else {
                JOptionPane.showMessageDialog(adminWindow, "Incorrect password.");
            }
        });

        adminWindow.add(centerPanel, BorderLayout.CENTER);
        adminWindow.setLocationRelativeTo(this);
        adminWindow.setVisible(true);
        this.dispose();
    }

    private void openManagerWindow() {
        JFrame managerWindow = new JFrame("Manager Window");
        managerWindow.setSize(400, 300);
        managerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Enter Password (Manager only): "));
        passwordmanagerField = new JPasswordField(10);
        centerPanel.add(passwordmanagerField);

        JPanel footerPanel = new JPanel();
        JButton sureButton = new JButton("Submit");
        footerPanel.add(sureButton);
        managerWindow.add(footerPanel, BorderLayout.SOUTH);

        sureButton.addActionListener(e -> {
            String password = new String(passwordmanagerField.getPassword());
            if (info.passwordManager.equals(password)) {
                String timestamp = getCurrentTime();
                logAccess("Manager", timestamp);
                JOptionPane.showMessageDialog(managerWindow, "Welcome Manager!\nAccess Time: " + timestamp);
                changeManagerWindow();
                managerWindow.dispose();
            } else {
                JOptionPane.showMessageDialog(managerWindow, "Incorrect password.");
            }
        });

        managerWindow.add(centerPanel, BorderLayout.CENTER);
        managerWindow.setLocationRelativeTo(this);
        managerWindow.setVisible(true);
        this.dispose();
    }

    private void changeManagerWindow() {
        JFrame changeManagerWindow = new JFrame("Manager Window");
        changeManagerWindow.setSize(400, 300);
        changeManagerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Change Room : "));
        changeRoom = new JPasswordField(10);
        centerPanel.add(changeRoom);
        centerPanel.add(new JLabel("Change Floor : "));
        changeFloor = new JPasswordField(10);
        centerPanel.add(changeFloor);

        JPanel footerPanel = new JPanel();
        JButton ChangeButton = new JButton("Submit");
        footerPanel.add(ChangeButton);
        changeManagerWindow.add(footerPanel, BorderLayout.SOUTH);

        ChangeButton.addActionListener(e -> {
            String roomText = new String(changeRoom.getText());
            try {
                int newroomNumber = Integer.parseInt(roomText);
                info.roomCustomer = new int[]{newroomNumber};
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter number");
            }
            String floorText = new String(changeFloor.getText());
            try {
                int newfloorNumber = Integer.parseInt(floorText);
                info.floorCustomer = new int[]{newfloorNumber};
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter number");
            }
            card = new Manager();
            new ScanCard("Manager", card, accessLogs);
            changeManagerWindow.dispose();
        });

        changeManagerWindow.add(centerPanel, BorderLayout.CENTER);
        changeManagerWindow.setLocationRelativeTo(this);
        changeManagerWindow.setVisible(true);
        this.dispose();
    }

    private void logAccess(String role, String timestamp) {
        accessLogs.add(role + " accessed at " + timestamp);
        accessLogs.add("----------------------------------------------------------------");
    }

    private String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dtf);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Framerole().setVisible(true));
    }
}

