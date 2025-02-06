import javax.swing.*;

public class ScanCard extends JFrame {
    public ScanCard(String role) {
        setTitle(role + " Window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome " + role));
        add(panel);

        setVisible(true);
    }
}

