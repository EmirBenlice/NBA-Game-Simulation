package Player;

import javax.swing.*;

import User.WelcomePage;

import java.awt.*;
import java.util.List;

public class PlayoffBracketGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PlayoffMatch> playoffMatches;
    private JLabel championLabel;

    public PlayoffBracketGUI(List<PlayoffMatch> playoffMatches, Team champion) {
        this.playoffMatches = playoffMatches;
        initializeUI(champion);
    }

    private void initializeUI(Team champion) {
    	setResizable(false);
    	
        setTitle("Playoff Bracket");
        setSize(1192, 913);
        getContentPane().setLayout(null);
        

        JPanel bracketPanel = new JPanel();
        bracketPanel.setBounds(37, 349, 1107, 219);
        getContentPane().add(bracketPanel);
        
        JLabel quarterfinalsLabel = new JLabel("Quarterfinals", SwingConstants.CENTER);
        quarterfinalsLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        quarterfinalsLabel.setBounds(42, 0, 930, 31);
        
        JLabel semifinalsLabel = new JLabel("Semifinals", SwingConstants.CENTER);
        semifinalsLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        semifinalsLabel.setBounds(42, 92, 930, 31);
        
        JLabel finalLabel = new JLabel("Final", SwingConstants.CENTER);
        finalLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        finalLabel.setBounds(42, 158, 930, 20);
    
        JPanel firstRoundPanel = new JPanel(new GridLayout(1, 4));
        firstRoundPanel.setBounds(0, 29, 1107, 65);
        JPanel semiFinalsPanel = new JPanel(new GridLayout(1, 2));
        semiFinalsPanel.setBounds(0, 121, 1097, 38);
        JPanel finalPanel = new JPanel(new GridLayout(1, 1));
        finalPanel.setBounds(0, 182, 1097, 31);
        bracketPanel.setLayout(null);
        
        bracketPanel.add(quarterfinalsLabel);
        bracketPanel.add(firstRoundPanel);
        bracketPanel.add(semifinalsLabel);
        bracketPanel.add(semiFinalsPanel);
        bracketPanel.add(finalLabel);
        bracketPanel.add(finalPanel);
        
        for (int i = 0; i < playoffMatches.size(); i++) {
            if (i < 4) {
                firstRoundPanel.add(createMatchPanel(playoffMatches.get(i)));
            } else if (i < 6) {
                semiFinalsPanel.add(createMatchPanel(playoffMatches.get(i)));
            } else {
                finalPanel.add(createMatchPanel(playoffMatches.get(i)));
            }
        }
        championLabel = new JLabel("Champion: " + (champion != null ? champion.getName() : "TBD"));
        championLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        championLabel.setForeground(new Color(255, 255, 255));
        championLabel.setBounds(582, 579, 596, 78);
        championLabel.setHorizontalAlignment(SwingConstants.LEFT);
        getContentPane().add(championLabel);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 1290, 1024);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon(WelcomePage.class.getResource("/images/platoffs2.png")));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createMatchPanel(PlayoffMatch match) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JLabel teamsLabel = new JLabel(match.getTeam1().getName() + " vs " + match.getTeam2().getName());
        teamsLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
        panel.add(teamsLabel);

        JLabel winnerLabel = new JLabel("Winner: " + (match.getWinner() != null ? match.getWinner().getName() : "TBD"));
        winnerLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
        panel.add(winnerLabel);

        return panel;
    }

}

    


