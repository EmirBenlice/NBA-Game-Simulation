package Player;

import java.util.*;
import java.util.stream.Collectors;

import User.FileManager;

public class DraftManager {
    private List<Team> teams;
    private List<Player> availablePlayers;
    private int playersPerTeam;
    private Scanner scanner;
    private Map<Team, Set<String>> pickedPositions;
    private String lastPickedPosition;

    public List<Team> getTeams() {
        return teams;
    }
    public int getPlayersPerTeam() {
        return playersPerTeam;
    }
    private boolean userHasPicked = false;

    public DraftManager(List<Player> players, List<Team> teams, int playersPerTeam) {
        this.availablePlayers = new ArrayList<>(players);
        this.teams = teams;
        this.playersPerTeam = playersPerTeam;
        this.scanner = new Scanner(System.in);
        pickedPositions = new HashMap<>();
        for (Team team : teams) {
            pickedPositions.put(team, new HashSet<>());
        }
        
    }
    public List<Player> getAvailablePlayers() {
        return availablePlayers;
    }
    public void userDraftPickFromGUI(String playerName, Team userTeam) {
        Player selectedPlayer = findPlayerByName(playerName);
        if (selectedPlayer != null && canPickPosition(userTeam, selectedPlayer.getPosition())) {
            availablePlayers.remove(selectedPlayer);
            userTeam.addPlayer(selectedPlayer);
            pickedPositions.get(userTeam).add(selectedPlayer.getPosition());
            lastPickedPosition = selectedPlayer.getPosition();
        }
    }
    private void computerDraftPick(Team team) {
        List<Player> filteredPlayers = filterPlayersByPosition(team, availablePlayers);
        if (!filteredPlayers.isEmpty()) {
            List<Player> samePositionPlayers = filteredPlayers.stream()
                .filter(p -> p.getPosition().equals(lastPickedPosition))
                .collect(Collectors.toList());

            if (!samePositionPlayers.isEmpty()) {
                int randomIndex = new Random().nextInt(samePositionPlayers.size());
                Player selectedPlayer = samePositionPlayers.get(randomIndex);
                availablePlayers.remove(selectedPlayer);
                team.addPlayer(selectedPlayer);
                pickedPositions.get(team).add(selectedPlayer.getPosition());
            } else {                
                int randomIndex = new Random().nextInt(filteredPlayers.size());
                Player selectedPlayer = filteredPlayers.get(randomIndex);
                availablePlayers.remove(selectedPlayer);
                team.addPlayer(selectedPlayer);
                pickedPositions.get(team).add(selectedPlayer.getPosition());
            }
        }
    }
    private boolean canPickPosition(Team team, String position) {
        return !pickedPositions.get(team).contains(position);
    }

    public List<Player> filterPlayersByPosition(Team team, List<Player> players) {
        Set<String> positionsPicked = pickedPositions.get(team);
        List<Player> filteredPlayers = new ArrayList<>();
        for (Player player : players) {
            if (!positionsPicked.contains(player.getPosition())) {
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }
    
    private Player findPlayerByName(String playerName) {
        for (Player player : availablePlayers) {
            if (player.name.equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public void startDraft() {
        Collections.shuffle(teams);

        int totalRounds = playersPerTeam * teams.size();
        for (int round = 0; round < totalRounds; round++) {
            Team currentTeam = teams.get(round % teams.size());
            if (currentTeam.getPlayers().size() < playersPerTeam) {
                if (currentTeam.isUserTeam()) {
                    waitForUserPick();
                } else {
                    computerDraftPick(currentTeam);
                }
            }

            if (allTeamsFull()) {
            	
                break;
            }

            if ((round + 1) % teams.size() == 0) {
                Collections.reverse(teams);
            }
        }
        FileManager.logDraftedTeams(teams);
    }

    private boolean allTeamsFull() {
        for (Team team : teams) {
            if (team.getPlayers().size() < playersPerTeam) {
                return false;
            }
        }
        return true;
    }
    
    public void waitForUserPick() {
        while (!userHasPicked) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        userHasPicked = false;
    }
    
    public void notifyUserPickComplete() {
        userHasPicked = true;
    }
    public void continueDraftAfterUserPick() {
    	
        int userTeamIndex = teams.indexOf(findUserTeam());
        for (int i = userTeamIndex + 1; i < teams.size(); i++) {
            Team team = teams.get(i);
            if (team.getPlayers().size() < playersPerTeam && !team.isUserTeam()) {
                computerDraftPick(team);
            }
        }

        Collections.reverse(teams);
        lastPickedPosition = null;
    }
    private Team findUserTeam() {
        for (Team team : teams) {
            if (team.isUserTeam()) {
                return team;
            }
        }
        return null;
    }
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}