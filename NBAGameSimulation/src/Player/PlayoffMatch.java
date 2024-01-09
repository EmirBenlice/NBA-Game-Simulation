package Player;

public class PlayoffMatch {
    private Team team1;
    private Team team2;
    private Team winner;

    public PlayoffMatch(Team team1, Team team2, Team winner) {
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
    }

    public Team getTeam1() { 
    	return team1; }
    public Team getTeam2() { 
    	return team2; }
    public Team getWinner() { 
    	return winner; }
}