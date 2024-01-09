package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginPage extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private WelcomePage welcomePage;
    private String selectedLogoPath;

    public LoginPage(WelcomePage welcomePage) {
    	setResizable(false);
        this.welcomePage = welcomePage;
        setTitle("User Login");
        setSize(970, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel label = new JLabel("Username:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label.setBounds(248, 279, 196, 74);
        getContentPane().add(label);
        txtUsername = new JTextField();
        txtUsername.setBounds(391, 300, 272, 36);
        getContentPane().add(txtUsername);

        JLabel label_1 = new JLabel("Password:");
        label_1.setForeground(new Color(0, 0, 0));
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
        label_1.setBounds(248, 355, 196, 74);
        getContentPane().add(label_1);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(391, 376, 270, 36);
        getContentPane().add(txtPassword);
      
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(245, 235, 230));
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnLogin.setBounds(371, 660, 215, 83);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        getContentPane().add(btnLogin);

        setVisible(true);
                
        JLabel lblNewLabe2 = new JLabel("Your Label Text");
        lblNewLabe2.setBounds(-381, -165, 1427, 1085);
        lblNewLabe2.setIcon(new ImageIcon(WelcomePage.class.getResource("/images/login.png")));
        getContentPane().add(lblNewLabe2);
    }
    
    private void loginUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (isValidCredentials(username, password)) {
        	
            JOptionPane.showMessageDialog(this, "Login successful!");
            welcomePage.onUserLoggedIn(username,selectedLogoPath);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }
    private boolean isValidCredentials(String username, String password) {
        List<User> users = FileManager.readUsersFromFile();
        for (User user : users) {
            if (user.getNickname().equals(username) && user.getPassword().equals(password)) {
            	selectedLogoPath = user.getSelectedLogoPath();
                return true;
            }
        }
        return false;
    }
 
}
