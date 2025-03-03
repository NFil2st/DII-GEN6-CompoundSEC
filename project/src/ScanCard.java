import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScanCard extends JFrame {
    private JTextField roomField, floorField;
    private JButton button , viewLogsButton , backHome, de;
    private static ArrayList<String> accessLogs = new ArrayList<>();
    private static final int CARD_VALIDITY_SECONDS = 15;
    private static TimeBasedEncryption timeBasedEncryption = new TimeBasedEncryption(CARD_VALIDITY_SECONDS);

    public ScanCard(String role, Object card, ArrayList<String> accessLogs) {
        timeBasedEncryption.createCard();
        this.accessLogs = accessLogs;
        setTitle(role + " Window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome " + role, SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);

        JPanel roomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roomPanel.add(new JLabel("Room: "));
        roomField = new JTextField(30);
        roomPanel.add(roomField);
        mainPanel.add(roomPanel);

        JPanel floorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        floorPanel.add(new JLabel("Floor: "));
        floorField = new JTextField(30);
        floorPanel.add(floorField);
        mainPanel.add(floorPanel);

        button = new JButton("Submit");
        de = new JButton("revoke");
        backHome = new JButton("Home");
        viewLogsButton = new JButton("View Logs");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        de.setAlignmentX(Component.CENTER_ALIGNMENT);
        backHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewLogsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(button);
        mainPanel.add(de);
        mainPanel.add(backHome);
        if (role.equals("Admin")) {
            mainPanel.add(viewLogsButton);
        }


        add(mainPanel);
        viewLogsButton.addActionListener(e -> showAccessLogs());

        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Framerole frame = new Framerole();
                frame.setVisible(true);
                dispose();
            }
        });

        de.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Framerole frame = new Framerole();
                frame.setVisible(true);
                dispose();
                TimeBasedEncryption.count--;
            }
        });
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomString = roomField.getText();
                String floorString = floorField.getText();
                int roomNumber = Integer.parseInt(roomString);
                int floorNumber = Integer.parseInt(floorString);
                if (timeBasedEncryption.isCardValid()) {
                    if (card instanceof Admin) {
                        if ((((Admin) card).comeRoom(roomNumber)) == true) {
                            if ((((Admin) card).comeFloor(floorNumber)) == true) {
                                JOptionPane.showMessageDialog(null, "Room Rights / Floor Rights");
                            } else {
                                JOptionPane.showMessageDialog(null, "Room Rights / Floor No Rights");
                            }
                        } else if ((((Admin) card).comeFloor(floorNumber)) == true) {
                            JOptionPane.showMessageDialog(null, "Room No Rights / Floor Rights");

                        } else {
                            JOptionPane.showMessageDialog(null, "Room No Rights / Floor No Rights");

                        }
                    } else if (card instanceof Customer) {
                        if ((((Customer) card).comeRoom(roomNumber)) == true) {
                            if ((((Customer) card).comeFloor(floorNumber)) == true) {
                                JOptionPane.showMessageDialog(null, "Room Rights / Floor Rights");
                            } else {
                                JOptionPane.showMessageDialog(null, "Room Rights / Floor No Rights");
                            }
                        } else if ((((Customer) card).comeFloor(floorNumber)) == true) {
                            JOptionPane.showMessageDialog(null, "Room No Rights / Floor Rights");

                        } else {
                            JOptionPane.showMessageDialog(null, "Room No Rights / Floor No Rights");

                        }
                    } else if (card instanceof Manager) {
                        if ((((Manager) card).comeRoom(roomNumber)) == true) {
                            if ((((Manager) card).comeFloor(floorNumber)) == true) {
                                JOptionPane.showMessageDialog(null, "Room Rights / Floor Rights");
                            } else {
                                JOptionPane.showMessageDialog(null, "Room Rights / Floor No Rights");
                            }
                        } else if ((((Manager) card).comeFloor(floorNumber)) == true) {
                            JOptionPane.showMessageDialog(null, "Room No Rights / Floor Rights");

                        } else {
                            JOptionPane.showMessageDialog(null, "Room No Rights / Floor No Rights");

                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Time out");
                    Framerole frame = new Framerole();
                    frame.setVisible(true);
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private void showAccessLogs() {
        if (timeBasedEncryption.isCardValid()){}
        JFrame logWindow = new JFrame("Access Logs");
        logWindow.setSize(400, 300);
        logWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logWindow.setLayout(new BorderLayout());

        JPanel countPanel = new JPanel(new GridLayout(2, 1));
        JLabel countLabel = new JLabel("Active now : " + TimeBasedEncryption.count);
        countPanel.add(countLabel);
        logWindow.add(countPanel, BorderLayout.NORTH);

        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setText(String.join("\n", accessLogs));

        JScrollPane scrollPane = new JScrollPane(logArea);
        logWindow.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        backHome = new JButton("Home");
        buttonPanel.add(backHome);
        logWindow.add(buttonPanel, BorderLayout.SOUTH);

        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Framerole frame = new Framerole();
                frame.setVisible(true);
                logWindow.dispose();
            }
        });

        logWindow.setLocationRelativeTo(this);
        logWindow.setVisible(true);
    }
}
