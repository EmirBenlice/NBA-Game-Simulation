
package Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void readPlayersFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String name = values[1];
                String position = values[2];
                double pts = Double.parseDouble(values[22]);
                double trb = Double.parseDouble(values[23]);
                double ast = Double.parseDouble(values[24]);
                double blk = Double.parseDouble(values[25]);
                double stl = Double.parseDouble(values[26]);

                switch (position) {
                    case "PG":
                        players.add(new PointGuard(name, pts, trb, ast, blk, stl));
                        break;
                    case "SG":
                        players.add(new ShootingGuard(name, pts, trb, ast, blk, stl));
                        break;
                    case "SF":
                        players.add(new SmallForward(name, pts, trb, ast, blk, stl));
                        break;
                    case "PF":
                        players.add(new PowerForward(name, pts, trb, ast, blk, stl));
                        break;
                    case "C":
                        players.add(new Center(name, pts, trb, ast, blk, stl));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeDuplicatesAndAverageStats() {
        Map<String, Player> playerMap = new HashMap<>();

        for (Player player : players) {
            if (playerMap.containsKey(player.name)) {
                Player existingPlayer = playerMap.get(player.name);
                averagePlayerStats(existingPlayer, player);
            } else {
                playerMap.put(player.name, player);
            }
        }
        players = new ArrayList<>(playerMap.values());
    }
    private void averagePlayerStats(Player existingPlayer, Player newPlayer) {
        existingPlayer.pts = (existingPlayer.pts + newPlayer.pts) / 2;
        existingPlayer.trb = (existingPlayer.trb + newPlayer.trb) / 2;
        existingPlayer.ast = (existingPlayer.ast + newPlayer.ast) / 2;
        existingPlayer.blk = (existingPlayer.blk + newPlayer.blk) / 2;
        existingPlayer.stl = (existingPlayer.stl + newPlayer.stl) / 2;
    }

    public static void main(String[] args) {
        PlayerManager manager = new PlayerManager();     
        String filePath = "src\\Player\\Stats.csv"; 

        manager.readPlayersFromCSV(filePath);
    }   
}
