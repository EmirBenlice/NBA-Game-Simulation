package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateUserPage extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtAge, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnUpdate;
    private String username;

    public UpdateUserPage(String username) {
        this.username = username;
        setResizable(false);

        setTitle("Update User Information");
        setSize(970, 785);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("  New Password:");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label.setBackground(new Color(255, 128, 0));
        label.setBounds(261, 255, 227, 33);
        getContentPane().add(label);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(495, 256, 244, 33);
        txtPassword.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtPassword);

        JLabel label_1 = new JLabel("  New Age:");
        label_1.setBackground(new Color(128, 128, 64));
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBounds(261, 335, 211, 33);
        getContentPane().add(label_1);
        txtAge = new JTextField();
        txtAge.setBounds(495, 335, 244, 33);
        txtAge.setBackground(new Color(255, 255, 255));
        getContentPane().add(txtAge);

        JLabel label_2 = new JLabel("  New Email:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setBounds(273, 420, 199, 33);
        getContentPane().add(label_2);
        txtEmail = new JTextField();
        txtEmail.setForeground(new Color(255, 255, 255));
        txtEmail.setBounds(495, 417, 250, 33);
        txtEmail.setBackground(new Color(0, 0, 0));
        getContentPane().add(txtEmail);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnUpdate.setBounds(409, 675, 185, 62);
        btnUpdate.setBackground(new Color(192, 192, 192));
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });
        getContentPane().add(btnUpdate);
        
        JLabel lblNewLabel = new JLabel("Your Label Text");
        lblNewLabel.setBounds(-381, -142, 1337, 1085);
        lblNewLabel.setIcon(new ImageIcon(WelcomePage.class.getResource("/images/game.png")));
        getContentPane().add(lblNewLabel);

        setVisible(true);
    }

    private void updateUser() {
        String password = new String(txtPassword.getPassword());
        String ageText = txtAge.getText();
        String email = txtEmail.getText();
        int age = ageText.isEmpty() ? -1 : Integer.parseInt(ageText);

        if (isValidUpdateInput(password, age, email)) {
            updateUserInFile(username, password, age, email);
            JOptionPane.showMessageDialog(this, "User information updated successfully!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }

    private boolean isValidUpdateInput(String password, int age, String email) {
        if (!password.isEmpty() && !password.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}")) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters and include letters, numbers, and special characters.");
            return false;
        }
        if (age != -1 && age < 12) {
            JOptionPane.showMessageDialog(this, "Age must be at least 12.");
            return false;
        }
        if (!email.isEmpty() && !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Email address is not in a valid format.");
            return false;
        }
        return true;
    }

    private void updateUserInFile(String username, String password, int age, String email) {
        List<User> users = FileManager.readUsersFromFile();
        for (User user : users) {
            if (user.getNickname().equals(username)) {
                user.setPassword(password);
                if (age > 0) user.setAge(age);
                user.setEmail(email);
                break;
            }
        }
        FileManager.writeUsersToFile(users);
    }   
}
