import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScanCard extends JFrame {
    private JPasswordField roomField, floorField;
    private JButton button;

    informationCard info = new informationCard();

    public ScanCard(String role, Object card) {
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
        roomField = new JPasswordField(30);
        roomPanel.add(roomField);
        mainPanel.add(roomPanel);

        JPanel floorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        floorPanel.add(new JLabel("Floor: "));
        floorField = new JPasswordField(30);
        floorPanel.add(floorField);
        mainPanel.add(floorPanel);

        button = new JButton("Submit");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(button);

        add(mainPanel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomString = new String(roomField.getPassword());
                String floorString = new String(floorField.getPassword());
                int roomNumber = Integer.parseInt(roomString);
                int floorNumber = Integer.parseInt(floorString);
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
            }
        });

        setVisible(true);
    }

    /*
     * public static void main(String[] args) {
     * new ScanCard("test", null); // ไว้สำหรับเทสหน้านี้ครับ
     * }
     */
}
