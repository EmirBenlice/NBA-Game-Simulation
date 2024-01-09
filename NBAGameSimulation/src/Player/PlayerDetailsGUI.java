package Player;

import javax.swing.*;
import java.awt.*;


public class PlayerDetailsGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;

    public PlayerDetailsGUI(Player player) {
        this.player = player;
        initializeUI();
    }

    private void initializeUI() {
    	setResizable(false);
        setTitle("Player Details: " + player.name);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JTextArea playerDetailsArea = new JTextArea();
        playerDetailsArea.setEditable(false);

        playerDetailsArea.append("Name: " + player.name + "\n");
        playerDetailsArea.append("Position: " + player.position + "\n");
        playerDetailsArea.append("Points (PTS): " + player.pts + "\n");
        playerDetailsArea.append("Total Rebounds (TRB): " + player.trb + "\n");
        playerDetailsArea.append("Assists (AST): " + player.ast + "\n");
        playerDetailsArea.append("Blocks (BLK): " + player.blk + "\n");
        playerDetailsArea.append("Steals (STL): " + player.stl + "\n");

        JScrollPane scrollPane = new JScrollPane(playerDetailsArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> this.dispose());
        add(backButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
