/* *********** Pledge of Honor ************************************************ *

I hereby certify that I have completed this lab assignment on my own
without any help from anyone else. I understand that the only sources of authorized
information in this lab assignment are (1) the course textbook, (2) the
materials posted at the course website and (3) any study notes handwritten by myself.
I have not used, accessed or received any information from any other unauthorized
source in taking this lab assignment. The effort in the assignment thus belongs
completely to me.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: Ahmet Emir Benlice,83657
********************************************************************************/

package User;
import Player.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

 
public class WelcomePage extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnRegister;
	private JLabel lblNewLabel;
	private JButton btnLogin;
	
    public WelcomePage() {
    	setResizable(false);
    	Color backgroundColor = new Color(230, 230, 230);
        Color primaryColor = new Color(0, 121, 181);
        Color buttonColor = new Color(245, 235, 230);
    	getContentPane().setBackground(backgroundColor);
        setTitle("Welcome to NBAGameSimulation");
        setSize(970, 808);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               
        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnLogin.setForeground(primaryColor);
        btnLogin.setBackground(buttonColor);
        btnLogin.setBorder(BorderFactory.createLineBorder(primaryColor));
        btnLogin.setBounds(552, 620, 247, 83);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage(WelcomePage.this);
                loginPage.setVisible(true);
                WelcomePage.this.setVisible(false);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnRegister.setForeground(primaryColor);
        btnRegister.setBackground(buttonColor);
        btnRegister.setBorder(BorderFactory.createLineBorder(primaryColor));
        btnRegister.setBounds(144, 620, 247, 83);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPage registerPage = new RegisterPage(WelcomePage.this);
                registerPage.setVisible(true);
                WelcomePage.this.setVisible(false);
            }
        });
        getContentPane().add(btnRegister);
        
        lblNewLabel = new JLabel("Your Label Text");
        lblNewLabel.setBounds(-118, -202, 1096, 1016);
        lblNewLabel.setIcon(new ImageIcon(WelcomePage.class.getResource("/images/welcome.png")));
        getContentPane().add(lblNewLabel);

        setVisible(true);
    }
    public void onUserLoggedIn(String userNickname,String selectedLogoPath) {
        PlayerManager playerManager = new PlayerManager();
        playerManager.readPlayersFromCSV("src\\Player\\Stats.csv");
        playerManager.removeDuplicatesAndAverageStats();
        
        String[] nbaTeamNames = {
                "Users team string", "LA Lakers", "Miami Heat", "Boston Celtics",
                "Golden State Warriors", "Brooklyn Nets", "New York Knicks",
                "Houston Rockets", "Dallas Mavericks", "Philadelphia 76ers",
                "Toronto Raptors", "San Antonio Spurs", "Milwaukee Bucks",
                "Denver Nuggets", "Utah Jazz", "Phoenix Suns"
            };
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < nbaTeamNames.length; i++) {
            boolean isUserTeam = (i == 0);
            String teamName = isUserTeam ? userNickname + "'s Team" : nbaTeamNames[i];
            teams.add(new Team(teamName, isUserTeam));
        }
        int gamesPerTeam = 30;
        DraftManager draftManager = new DraftManager(playerManager.getPlayers(), teams, 5);
        DraftGUI draftGui = new DraftGUI(draftManager, gamesPerTeam, userNickname, selectedLogoPath);
        draftGui.setVisible(true);
        this.setVisible(false);
    }
    public static void main(String[] args) {
        new WelcomePage();
    }
}

