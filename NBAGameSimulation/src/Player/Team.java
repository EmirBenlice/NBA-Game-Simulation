package Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;
    private boolean isUserTeam;
    private int wins;
    private int losses;
    private double seasonScore;
    
    
    public Team(String name, boolean isUserTeam) {
        this.name = name;
        this.isUserTeam = isUserTeam;
        this.players = new ArrayList<>();
        this.wins = 0;
        this.losses = 0;
    }
    public void calculateSeasonScore() {
        seasonScore = calculateScore();
    }

    public double getSeasonScore() {
        return seasonScore;
    }
    
    public void addPlayer(Player player) {
        players.add(player);
    }
    
    public double calculateScore() {
        double totalScore = 0;
        for (Player player : players) {
            totalScore += player.calculateScore();
        }      
        return totalScore;
    }
   
    public void recordWin() {
        wins++;
    }

    public void recordLoss() {
        losses++;
    }
    
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setUserTeam(boolean isUserTeam) {
		this.isUserTeam = isUserTeam;
	}

	public boolean isUserTeam() {
        return isUserTeam;
    }
}

