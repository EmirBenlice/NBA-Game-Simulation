package Player;

import javax.swing.*;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SeasonStandingsGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DefaultListModel<String> standingsListModel;
    private final JList<String> standingsList;
    private final JButton pauseButton;
    private boolean isPaused = false;
    private GameSimulator gameSimulator;
    private JButton viewTeamButton;
    
    public SeasonStandingsGUI(List<Team> teams,GameSimulator gameSimulator) {
    	setResizable(false);
    	
    	setTitle("Season Standings");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        standingsListModel = new DefaultListModel<>();
        updateStandings(teams);

        standingsList = new JList<>(standingsListModel);
        standingsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(standingsList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        viewTeamButton = new JButton("View Team");
        viewTeamButton.addActionListener(e -> {
            togglePauseResume();
            viewUserTeam();
        });
        getContentPane().add(viewTeamButton, BorderLayout.NORTH);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> togglePauseResume());
        getContentPane().add(pauseButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
        this.gameSimulator = gameSimulator;
        gameSimulator.setGameUpdateListener(() -> {
        SwingUtilities.invokeLater(() -> updateStandings(teams));
                
        });
    }
    
    private void viewUserTeam() {
        Team userTeam = findUserTeam();
        if (userTeam != null) {
            new TeamDetailsGUI(userTeam).setVisible(true);
        }
    }

    private Team findUserTeam() {
        for (Team team : gameSimulator.getTeams()) {
            if (team.isUserTeam()) {
                return team;
            }
        }
        return null;
    }

    public void updateStandings(List<Team> teams) {
        Comparator<Team> compareByWins = new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2) {
                return Integer.compare(team2.getWins(), team1.getWins());
            }
        };
        Collections.sort(teams, compareByWins);
        standingsListModel.clear();
        for (Team team : teams) {
            String teamInfo = String.format("%s - Wins: %d, Losses: %d", 
                                            team.getName(), team.getWins(), team.getLosses());
            standingsListModel.addElement(teamInfo);
        }
    }
    
    private void togglePauseResume() {
        gameSimulator.togglePause();
        isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Resume" : "Pause");
    }

    public boolean isPaused() {
        return isPaused;
    }
    
    
}

