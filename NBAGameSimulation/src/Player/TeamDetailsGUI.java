package Player;

import javax.swing.*;
import java.awt.*;


public class TeamDetailsGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Team team;

    public TeamDetailsGUI(Team team) {
        this.team = team;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Team Details");
        setSize(400, 300);
        setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Player player : team.getPlayers()) {
            listModel.addElement(player.name);
        }

        JList<String> playerList = new JList<>(listModel);
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedPlayerName = playerList.getSelectedValue();
                Player player = findPlayerByName(selectedPlayerName);
                if (player != null) {
                    new PlayerDetailsGUI(player);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(playerList);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> this.dispose());
        add(backButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private Player findPlayerByName(String playerName) {
        for (Player player : team.getPlayers()) {
            if (player.name.equals(playerName)) {
                return player;
            }
        }
        return null;
    }
}

