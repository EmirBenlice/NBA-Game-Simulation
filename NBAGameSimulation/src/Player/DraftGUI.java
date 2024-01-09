package Player;

import javax.swing.*;

import User.UpdateUserPage;
import User.WelcomePage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class DraftGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> playerComboBox;
    private JButton selectButton;
    private DraftManager draftManager;
    private JTextArea teamsDisplay;
    private JButton viewTeamButton;
    private int gamesPerTeam;
    private JButton endDraftButton;
    private JButton btnUpdateUser;
    private String loggedInUsername;
    private JLabel lblUsername;
    private JButton viewAllTeamsButton;
    private String selectedLogoPath;

    public DraftGUI(DraftManager draftManager,int gamesPerTeam, String loggedInUsername,String selectedLogoPath) {
        this.draftManager = draftManager;
        this.gamesPerTeam = gamesPerTeam;
        this.loggedInUsername = loggedInUsername;
        this.selectedLogoPath = selectedLogoPath;
        
        
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Draft Players");
        setSize(1000, 900);
        setResizable(false);
        
        ImageIcon userIcon;
        if (selectedLogoPath != null && !selectedLogoPath.isEmpty()) {
            userIcon = new ImageIcon(getClass().getResource(selectedLogoPath));
        } else {
            userIcon = new ImageIcon(getClass().getResource("/images/user128.png"));
        }
        btnUpdateUser = new JButton("Update User Information");
        btnUpdateUser.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnUpdateUser.setBounds(4, 207, 183, 45);
        btnUpdateUser.setBackground(new Color(245, 235, 230));
        btnUpdateUser.addActionListener(e -> openUpdateUserPage());
        getContentPane().setLayout(null);
        
        viewAllTeamsButton = new JButton("View All Teams");
        viewAllTeamsButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        viewAllTeamsButton.setSize(183, 43);
        viewAllTeamsButton.setBackground(new Color(245, 235, 230));
        viewAllTeamsButton.setLocation(4, 263);
        viewAllTeamsButton.addActionListener(e -> viewAllTeams());
        
        teamsDisplay = new JTextArea(45, 120);
        JScrollPane scrollPane = new JScrollPane(teamsDisplay);
        scrollPane.setBounds(197, 88, 591, 639);
        getContentPane().add(scrollPane);
        
        teamsDisplay.setEditable(false);
        getContentPane().add(viewAllTeamsButton);
        getContentPane().add(btnUpdateUser);
        
        lblUsername = new JLabel("Logged in as: " + loggedInUsername);
        lblUsername.setForeground(new Color(255, 0, 0));
        lblUsername.setBounds(4, 4, 290, 61);
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 22));
        getContentPane().add(lblUsername);

        playerComboBox = new JComboBox<>();
        playerComboBox.setBounds(420, 798, 183, 41);
        populatePlayerComboBox();
        
        endDraftButton = new JButton("End Draft and Start Season");
        endDraftButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        endDraftButton.setBounds(747, 32, 229, 45);
        endDraftButton.setBackground(new Color(245, 235, 230));
        endDraftButton.addActionListener(e -> startRegularSeason());
        getContentPane().add(endDraftButton);
        
        
        viewTeamButton = new JButton("View Team");
        viewTeamButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        viewTeamButton.setBackground(new Color(245, 235, 230));
        viewTeamButton.setBounds(197, 798, 209, 41);
        viewTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Team userTeam = findUserTeam();
                if (userTeam != null) {
                    new TeamDetailsGUI(userTeam);
                }
            }
        });
        getContentPane().add(viewTeamButton);
    
        selectButton = new JButton("Select Player");
        selectButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        selectButton.setBounds(623, 798, 201, 41);
        selectButton.setBackground(new Color(245, 235, 230));
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String selectedInfo = (String) playerComboBox.getSelectedItem();
                Team userTeam = findUserTeam();
                if (userTeam != null && userTeam.getPlayers().size() < draftManager.getPlayersPerTeam()) {
                	 String[] parts = selectedInfo.split(" - ");
                     String selectedPlayerName = parts[0];
                    draftManager.userDraftPickFromGUI(selectedPlayerName, userTeam);
                    populatePlayerComboBox();
                    draftManager.notifyUserPickComplete();
                    draftManager.continueDraftAfterUserPick();
                    updateTeamsDisplay();
                    draftManager.continueDraftAfterUserPick();
                    updateTeamsDisplay();
                }
            }
        });

        getContentPane().add(playerComboBox);
        getContentPane().add(selectButton);
               
        JLabel iconLabel = new JLabel(userIcon);
        iconLabel.setBounds(-64, 47, 350, 160);
        getContentPane().add(iconLabel);
              
        JLabel lblNewLabe3 = new JLabel("Your Label Text");
        lblNewLabe3.setBounds(-436, -17, 1701, 1035);
        lblNewLabe3.setIcon(new ImageIcon(WelcomePage.class.getResource("/images/draftplayers.png")));
        getContentPane().add(lblNewLabe3);
        
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    private void openUpdateUserPage() {
        
    	 new UpdateUserPage(loggedInUsername).setVisible(true);
    }
     
    private void viewAllTeams() {
        new AllTeamsDisplayGUI(draftManager.getTeams(),selectedLogoPath).setVisible(true);
    }
    
    private void updateTeamsDisplay() {
        StringBuilder displayText = new StringBuilder();
        for (Team team : draftManager.getTeams()) {
            displayText.append(team.getName()).append(":\n");
            for (Player player : team.getPlayers()) {
                displayText.append(" - ").append(player.name).append("\n");
            }
            displayText.append("\n");
        }
        teamsDisplay.setText(displayText.toString());
    }
    private Team findUserTeam() {
        for (Team team : draftManager.getTeams()) {
            if (team.isUserTeam()) {
                return team;
            }
        }
        return null;
    }

    private void populatePlayerComboBox() {
        playerComboBox.removeAllItems();
        Team userTeam = findUserTeam();
        if (userTeam != null) {
            List<Player> availablePlayers = draftManager.filterPlayersByPosition(userTeam, draftManager.getAvailablePlayers());
            for (Player player : availablePlayers) {
                playerComboBox.addItem(player.getName()+" - " + player.getPosition());
            }
        }
    }
    public void startRegularSeason() {
        this.dispose();
        GameSimulator gameSimulator = new GameSimulator(draftManager.getTeams(), gamesPerTeam);
        gameSimulator.prepareForSeason();
        SeasonStandingsGUI standingsGUI = new SeasonStandingsGUI(draftManager.getTeams(), gameSimulator);
        standingsGUI.setVisible(true);
        new Thread(() -> {
        	gameSimulator.playRegularSeasonGames();
            SwingUtilities.invokeLater(() -> standingsGUI.updateStandings(gameSimulator.getTeams()));
        }).start(); 
    } 
}