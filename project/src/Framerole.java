import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import javax.swing.*;

public class Framerole extends JFrame {
    private JComboBox<String> roleComboBox;
    private JButton submitButton, sureButton, changeButton;
    private JPasswordField passwordadminField, passwordmanagerField;

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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRoleSelection();
            }
        });
    }

    private void handleRoleSelection() {
        int role = roleComboBox.getSelectedIndex();

        if (role == 1) {
            openAdminWindow();
        } else if (role == 2) {
            JOptionPane.showMessageDialog(this, "Welcome Customer!");
            card = new Customer();
            new ScanCard("Customer", card);
            this.dispose();
        } else if (role == 3) {
            openManagerWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid role.");
        }
    }

    private void openAdminWindow() {
        JFrame adminWindow = new JFrame("Admin Window");
        adminWindow.setTitle("Admin Window");
        adminWindow.setSize(400, 300);
        adminWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Enter Password (Admin only): "));
        passwordadminField = new JPasswordField();
        passwordadminField = new JPasswordField(10);
        centerPanel.add(passwordadminField);

        JPanel footerPanel = new JPanel();
        sureButton = new JButton("Submit");
        footerPanel.add(sureButton);
        adminWindow.add(footerPanel, BorderLayout.SOUTH);

        sureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordadminField.getPassword());
                if (info.passwordAdmin.equals(password)) {
                    JOptionPane.showMessageDialog(adminWindow, "Welcome Admin!");
                    card = new Admin();
                    new ScanCard("Admin", card);
                    adminWindow.dispose();
                } else {
                    JOptionPane.showMessageDialog(adminWindow, "Incorrect password.");
                }
            }
        });

        adminWindow.add(centerPanel, BorderLayout.CENTER);
        adminWindow.setLocationRelativeTo(this);
        adminWindow.setVisible(true);
        this.setVisible(false);
    }

    private void openManagerWindow() {
        JFrame managerWindow = new JFrame("Manager Window");
        managerWindow.setTitle("Manager Window");
        managerWindow.setSize(400, 300);
        managerWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Enter Password (Manager only): "));
        passwordmanagerField = new JPasswordField();
        passwordmanagerField = new JPasswordField(10);
        centerPanel.add(passwordmanagerField);

        JPanel footerPanel = new JPanel();
        sureButton = new JButton("Submit");
        footerPanel.add(sureButton);
        managerWindow.add(footerPanel, BorderLayout.SOUTH);

        sureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordmanagerField.getPassword());
                if (info.passwordManager.equals(password)) {
                    JOptionPane.showMessageDialog(managerWindow, "Welcome Manager!");
                    openChangeWindow();
                    managerWindow.dispose();

                } else {
                    JOptionPane.showMessageDialog(managerWindow, "Incorrect password.");
                }

            }
        });

        managerWindow.add(centerPanel, BorderLayout.CENTER);
        managerWindow.setLocationRelativeTo(this);
        managerWindow.setVisible(true);
        this.setVisible(false);
    }

    private void openChangeWindow() {
        JFrame openChangeWindow = new JFrame("Manager Window");
        openChangeWindow.setTitle("Manager Window");
        openChangeWindow.setSize(400, 300);
        openChangeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Enter New Room: "));
        JTextField inputroom = new JTextField(10);
        centerPanel.add(inputroom);
        centerPanel.add(new JLabel("Enter New Floor: "));
        JTextField inputfloor = new JTextField(10);
        centerPanel.add(inputfloor);

        JPanel footerPanel = new JPanel();
        changeButton = new JButton("Submit");
        footerPanel.add(changeButton);
        openChangeWindow.add(footerPanel, BorderLayout.SOUTH);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Set<Integer> newallowedRooms = Arrays.stream(inputroom.getText().split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());

                    Set<Integer> newallowedFloors = Arrays.stream(inputfloor.getText().split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());
                    card = new ManagerCardDecorator(new EmployeeKeycard(newallowedRooms, newallowedFloors));

                    JOptionPane.showMessageDialog(openChangeWindow, "Updated successfully!");

                    new ScanCard("Manager", card);
                    openChangeWindow.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(openChangeWindow, "Invalid input! Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        openChangeWindow.add(centerPanel, BorderLayout.CENTER);
        openChangeWindow.setLocationRelativeTo(this);
        openChangeWindow.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Framerole().setVisible(true);
            }
        });
    }

}
