package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTextField txtNickname, txtRealName, txtSurname, txtAge, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister, btnColorChooser;
    private WelcomePage welcomePage;
    private JLabel lblNewLabel;
    private JLabel selectedLogoLabel;
    private String selectedLogoPath;
      
    public RegisterPage(WelcomePage welcomePage) {
    	setResizable(false);
    	getContentPane().setBackground(new Color(245, 235, 230));
    	this.welcomePage = welcomePage;
        setTitle("Register New User");
        setSize(900, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(WelcomePage.class.getResource("/images/nbauser.png"));
        
        JLabel lblFormalUser = new JLabel("", SwingConstants.CENTER);
        lblFormalUser.setBounds(-5, 132, 314, 68);
        ImageIcon formalIcon = new ImageIcon(WelcomePage.class.getResource("/images/formaluser.png"));
        getContentPane().setLayout(null);
        
        selectedLogoLabel = new JLabel();
        selectedLogoLabel.setBounds(32, 211, 184, 185);
        getContentPane().add(selectedLogoLabel);
        
        lblNewLabel = new JLabel("", SwingConstants.CENTER);
        lblNewLabel.setBounds(-89, 132, 314, 68);
        lblNewLabel.setIcon(icon);
        getContentPane().add(lblNewLabel);
        getContentPane().add(lblNewLabel);
        lblFormalUser.setIcon(formalIcon);
        getContentPane().add(lblFormalUser);

        JButton btnChooseLogo = new JButton("Choose Profile Photo");
        btnChooseLogo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnChooseLogo.setBounds(10, 407, 215, 68);
        btnChooseLogo.setBackground(new Color(245, 235, 230));
        btnChooseLogo.addActionListener(e -> openLogoChooser());
        getContentPane().add(btnChooseLogo);

        
        JLabel lblNickname = new JLabel("  Nickname:");
        lblNickname.setForeground(new Color(255, 255, 255));
        lblNickname.setBounds(239, 132, 159, 68);
        lblNickname.setFont(new Font("Times New Roman", Font.BOLD, 25));
        getContentPane().add(lblNickname);
        txtNickname = new JTextField();
        txtNickname.setBounds(408, 151, 314, 39);
        txtNickname.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtNickname);
        
        JLabel lblPassword = new JLabel("  Password:");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setBounds(239, 211, 159, 68);
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 25));
        getContentPane().add(lblPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(408, 230, 304, 39);
        txtPassword.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtPassword);
        
        JLabel label_1 = new JLabel("  Real Name:");
        label_1.setBackground(new Color(0, 128, 0));
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBounds(239, 296, 184, 68);
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        getContentPane().add(label_1);
        txtRealName = new JTextField();
        txtRealName.setBounds(408, 315, 304, 39);
        txtRealName.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtRealName);
        
        JLabel label_3 = new JLabel("  Surname:");
        label_3.setForeground(new Color(255, 255, 255));
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label_3.setBounds(239, 375, 169, 68);
        getContentPane().add(label_3);
        txtSurname = new JTextField();
        txtSurname.setBounds(408, 393, 304, 39);
        txtSurname.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtSurname);
                
        JLabel label_2 = new JLabel("  Age:");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label_2.setBounds(239, 448, 169, 68);
        getContentPane().add(label_2);
        txtAge = new JTextField();
        txtAge.setBounds(408, 467, 304, 39);
        txtAge.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtAge);
                             
        JLabel label = new JLabel("  Email:");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label.setBounds(239, 525, 131, 62);
        label.setBackground(new Color(255, 255, 255));
        getContentPane().add(label);
        txtEmail = new JTextField();
        txtEmail.setBounds(408, 541, 304, 39);
        txtEmail.setBackground(new Color(137, 163, 145));
        getContentPane().add(txtEmail);
        
        btnColorChooser = new JButton("Choose Background Color");
        btnColorChooser.setBounds(10, 486, 215, 68);
        btnColorChooser.setBackground(new Color(245, 235, 230));
        btnColorChooser.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnColorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(RegisterPage.this, "Choose Background Color", txtNickname.getBackground());
                if (newColor != null) {
                    updateBackgroundColor(newColor);
                }
            }
        });
        
        btnRegister = new JButton("Register");
        btnRegister.setBounds(288, 872, 314, 68);
        btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnRegister.setBackground(new Color(245, 235, 230));
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        getContentPane().add(btnRegister);
        getContentPane().add(btnColorChooser);
        
        JLabel lblNewLabe3 = new JLabel(".");
        lblNewLabe3.setBounds(-111, 11, 987, 941);
        lblNewLabe3.setIcon(new ImageIcon(WelcomePage.class.getResource("/images/register.png")));
        getContentPane().add(lblNewLabe3);
        
        setVisible(true);
    }
    private void updateBackgroundColor(Color color) {
    	getContentPane().setBackground(color);
    	
    }
    
    private void openLogoChooser() {
        JDialog logoChooserDialog = new JDialog(this, "Choose a Logo", true);
        logoChooserDialog.getContentPane().setLayout(new FlowLayout());
        logoChooserDialog.setSize(800, 600);
        logoChooserDialog.getContentPane().setBackground(new Color(245,235, 230));
      
        JButton logo1Button = new JButton(new ImageIcon(getClass().getResource("/images/user128.png")));
        JButton logo2Button = new JButton(new ImageIcon(getClass().getResource("/images/user1.png")));
        JButton logo3Button = new JButton(new ImageIcon(getClass().getResource("/images/user2.png")));
        JButton logo4Button = new JButton(new ImageIcon(getClass().getResource("/images/user3.png")));
        JButton logo5Button = new JButton(new ImageIcon(getClass().getResource("/images/user4.png")));
        JButton logo6Button = new JButton(new ImageIcon(getClass().getResource("/images/user5.png")));
        JButton logo7Button = new JButton(new ImageIcon(getClass().getResource("/images/user6.png")));
        JButton logo8Button = new JButton(new ImageIcon(getClass().getResource("/images/user7.png")));
        
        customizeButton(logo1Button);
        customizeButton(logo2Button);
        customizeButton(logo3Button);
        customizeButton(logo4Button);
        customizeButton(logo5Button);
        customizeButton(logo6Button);
        customizeButton(logo7Button);
        customizeButton(logo8Button);
       
        logo1Button.addActionListener(e -> {
            setSelectedLogo("/images/user128.png");
            logoChooserDialog.dispose();
        });
        logo2Button.addActionListener(e -> {
            setSelectedLogo("/images/user1.png");
            logoChooserDialog.dispose();
        });
        logo3Button.addActionListener(e -> {
            setSelectedLogo("/images/user2.png");
            logoChooserDialog.dispose();
        });
        logo4Button.addActionListener(e -> {
            setSelectedLogo("/images/user3.png");
            logoChooserDialog.dispose();
        });
        logo5Button.addActionListener(e -> {
            setSelectedLogo("/images/user4.png");
            logoChooserDialog.dispose();
        });
        logo6Button.addActionListener(e -> {
            setSelectedLogo("/images/user5.png");
            logoChooserDialog.dispose();
        });
        logo7Button.addActionListener(e -> {
            setSelectedLogo("/images/user6.png");
            logoChooserDialog.dispose();
        });
        logo8Button.addActionListener(e -> {
            setSelectedLogo("/images/user7.png");
            logoChooserDialog.dispose();
        });

        logoChooserDialog.getContentPane().add(logo1Button);
        logoChooserDialog.getContentPane().add(logo2Button);
        logoChooserDialog.getContentPane().add(logo3Button);
        logoChooserDialog.getContentPane().add(logo4Button);
        logoChooserDialog.getContentPane().add(logo5Button);
        logoChooserDialog.getContentPane().add(logo6Button);
        logoChooserDialog.getContentPane().add(logo7Button);
        logoChooserDialog.getContentPane().add(logo8Button);
        
        logoChooserDialog.setVisible(true);
    }
    private void customizeButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    private void setSelectedLogo(String logoPath) {
        selectedLogoLabel.setIcon(new ImageIcon(getClass().getResource(logoPath)));
        this.selectedLogoPath = logoPath;
    }

    private void registerUser() {
        String nickname = txtNickname.getText();
        String password = new String(txtPassword.getPassword());
        String realName = txtRealName.getText();
        String surname = txtSurname.getText();
        int age = Integer.parseInt(txtAge.getText());
        String email = txtEmail.getText();
        

        if (FileManager.usernameExists(nickname)) {
            JOptionPane.showMessageDialog(this, "This username is already taken. Please choose another one.");
            return;
        }
        if (isValidUserInput(nickname, password, realName, surname, age, email)) {
            User newUser = new User(nickname, password, realName, surname, age, email,selectedLogoPath);
            FileManager.writeUserToFile(newUser);
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            this.dispose();
            new LoginPage(welcomePage).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }

    private boolean isValidUserInput(String nickname, String password, String realName, String surname, int age, String email) {
        if (!nickname.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Nickname must contain only letters and numbers.");
            return false;
        }
        
        if (!password.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}")) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters and include letters, numbers, and special characters.");
            return false;
        }

        if (realName.length() < 3 || !realName.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Real name must have at least 3 letters and contain only letters.");
            return false;
        }
        if (surname.length() < 3 || !surname.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Surname must have at least 3 letters and contain only letters.");
            return false;
        }

        if (age < 12) {
            JOptionPane.showMessageDialog(this, "Age must be at least 12.");
            return false;
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Email address is not in a valid format.");
            return false;
        }
        return true;
    }
  
}
