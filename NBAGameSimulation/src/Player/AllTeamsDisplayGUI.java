package Player;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class AllTeamsDisplayGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String selectedLogoPath;

    public AllTeamsDisplayGUI(List<Team> teams, String selectedLogoPath) {
    	this.selectedLogoPath= selectedLogoPath;
    	
    	
        setTitle("All Teams");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        setLayout(new GridLayout(4, 8, 10, 10));

        for (int i = 0; i < 8; i++) {
            addTeamLogo(teams.get(i));
        }

        for (int i = 0; i < 8; i++) {
            addTeamPanel(teams.get(i));
        }

        for (int i = 8; i < teams.size(); i++) {
            addTeamLogo(teams.get(i));
        }

        for (int i = 8; i < teams.size(); i++) {
            addTeamPanel(teams.get(i));
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTeamLogo(Team team) {
        JLabel logoLabel = new JLabel();

        if (team.isUserTeam() && selectedLogoPath != null && !selectedLogoPath.isEmpty()) {
            URL imageUrl = getClass().getResource(selectedLogoPath);
            if (imageUrl != null) {
                logoLabel.setIcon(new ImageIcon(imageUrl));
            } else {
                logoLabel.setIcon(new ImageIcon(getClass().getResource("/images/user128.png")));
            }
        } else {
            URL imageUrl = getClass().getResource("/teams/" + team.getName() + "Logo.png");
            
                logoLabel.setIcon(new ImageIcon(imageUrl));
            
        }
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(logoLabel);
    }

    private void addTeamPanel(Team team) {
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BorderLayout());

        JLabel teamNameLabel = new JLabel(team.getName(), SwingConstants.CENTER);
        teamNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        teamPanel.add(teamNameLabel, BorderLayout.NORTH);

        JTextArea playerInfo = new JTextArea();
        playerInfo.setEditable(false);
        StringBuilder playersText = new StringBuilder();
        for (Player player : team.getPlayers()) {
            playersText.append(player.getName()).append("\n");
        }
        playerInfo.setText(playersText.toString());
        teamPanel.add(playerInfo, BorderLayout.CENTER);

        add(teamPanel);
    }
}















 



