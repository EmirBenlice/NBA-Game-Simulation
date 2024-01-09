package Player;
import User.FileManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import Player.Player.GameUpdateListener;

public class GameSimulator {
    private List<Team> teams;
    private Random random;
    private Map<String, String> scheduledGames;
    private volatile boolean isPaused = false;
    private GameUpdateListener gameUpdateListener;
    private List<PlayoffMatch> playoffMatches = new ArrayList<>();
    

    public GameSimulator(List<Team> teams, int gamesPerTeam) {
        this.teams = teams;
        this.random = new Random();
        this.scheduledGames = new HashMap<>();
    }
    public void setGameUpdateListener(GameUpdateListener listener) {
        this.gameUpdateListener = listener;
    }
    public void prepareForSeason() {
        for (Team team : teams) {
            team.calculateSeasonScore();
        }
    }

    public Team selectRandomOpponent(Team team, Set<Team> existingOpponents) {
        List<Team> possibleOpponents = teams.stream()
                                            .filter(t -> !t.equals(team) && !existingOpponents.contains(t))
                                            .collect(Collectors.toList());
        if (possibleOpponents.isEmpty()) return null;

        return possibleOpponents.get(random.nextInt(possibleOpponents.size()));
    }
    
    public void playRegularSeasonGames() {
        List<Pair<Team, Team>> schedule = createSchedule();
        
        for (Pair<Team, Team> game : schedule) {
            Team team1 = game.getFirst();
            Team team2 = game.getSecond();
            playGame(team1, team2);
            recordGame(team1, team2);

            if (gameUpdateListener != null) {
                gameUpdateListener.onGameCompleted();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            checkForPause();
        }      
        startPlayoffs();
    }

    private List<Pair<Team, Team>> createSchedule() {
        List<Pair<Team, Team>> schedule = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                schedule.add(new Pair<>(teams.get(i), teams.get(j)));
                schedule.add(new Pair<>(teams.get(j), teams.get(i)));
            }
        }
        Collections.shuffle(schedule);
        return schedule;
    }
    
    public void playGame(Team homeTeam, Team awayTeam) {
    	double homeScore = homeTeam.getSeasonScore();
        double awayScore = awayTeam.getSeasonScore();
        
        homeScore = (double) (homeScore * 1.05);
        if (homeScore > awayScore) {
            homeTeam.recordWin();
            awayTeam.recordLoss();
        } else if (awayScore > homeScore) {
            awayTeam.recordWin();
            homeTeam.recordLoss();
        } 
        FileManager.recordGame(homeTeam, awayTeam, homeScore, awayScore);
        if (gameUpdateListener != null) {
            gameUpdateListener.onGameCompleted();
        }   
    }
    
    public void recordGame(Team homeTeam, Team awayTeam) {
        String matchup = homeTeam.getName() + " vs " + awayTeam.getName();
        String result = homeTeam.getWins() > awayTeam.getWins() ? "Win" : "Loss";
        scheduledGames.put(matchup, result);       
    }
    
    public List<Team> getTopTeams(int number) {
        return teams.stream()
                    .sorted(Comparator.comparingInt(Team::getWins).reversed())
                    .limit(number)
                    .collect(Collectors.toList());
    }
    
    public void startPlayoffs() {   
    	List<Team> topTeams = getTopTeams(8);
        conductPlayoffs(topTeams);
    }
    
    private void conductPlayoffs(List<Team> remainingTeams) {
        while (remainingTeams.size() > 1) {
            List<Team> winners = new ArrayList<>();
            Collections.shuffle(remainingTeams);
            for (int i = 0; i < remainingTeams.size(); i += 2) {
                if (i + 1 < remainingTeams.size()) {
                    Team team1 = remainingTeams.get(i);
                    Team team2 = remainingTeams.get(i + 1);
                    Team winner = playPlayoffGame(team1, team2);
                    winners.add(winner);
                    playoffMatches.add(new PlayoffMatch(team1, team2, winner));
                    boolean isFinalGame = remainingTeams.size() == 2;
                    FileManager.logPlayoffGame(team1.getName(), team2.getName(), winner.getName(), (winner == team1) ? team2.getName() : team1.getName(), isFinalGame);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                checkForPause();
            }
            remainingTeams = winners;
        }
        if (!remainingTeams.isEmpty()) {
            Team champion = remainingTeams.get(0);
            FileManager.logChampion(champion.getName());
            List<PlayoffMatch> topMatches = playoffMatches;
            SwingUtilities.invokeLater(() -> new PlayoffBracketGUI(topMatches, champion).setVisible(true));
        }
    }
    public List<PlayoffMatch> getPlayoffMatches() {
        return playoffMatches;
    }
    public void checkForPause() {
        while (isPaused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    public void togglePause() {
        isPaused = !isPaused;
    }
    private Team playPlayoffGame(Team team1, Team team2) {
        double team1Score = team1.getSeasonScore();
        double team2Score = team2.getSeasonScore();

        if (random.nextBoolean()) {
            team1Score = (team1Score * 1.05);
        } else {
            team2Score = (team2Score * 1.05);
        }

        return team1Score > team2Score ? team1 : team2;
    }
    public List<Team> getTeams() {
        return teams;
    }
}

