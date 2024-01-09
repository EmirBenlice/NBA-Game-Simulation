package User;

import Player.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {
	static {
        createFileIfNotExists();
    }
	public static void createFileIfNotExists() {
	    File file = new File("src\\users.txt");
	    if (!file.exists()) {
	        try {
	            boolean created = file.createNewFile();
	            if (created) {
	                
	            } else {
	                
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static List<User> readUsersFromFile() {
	    List<User> users = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader("src\\users.txt"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] details = line.split(",");
	            if (details.length == 7) {
	                users.add(new User(details[0], details[1], details[2], details[3], Integer.parseInt(details[4]), details[5], details[6]));
	            } else {
	                
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return users;
	}


    public static void writeUserToFile(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\users.txt", true))) {
            bw.write(user.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeUsersToFile(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\users.txt"))) {
            for (User user : users) {
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean usernameExists(String username) {
        List<User> users = readUsersFromFile();
        for (User user : users) {
            if (user.getNickname().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
    public static void logDraftResults(List<Team> teams) {
        try (FileWriter fw = new FileWriter("src\\draft_results.txt");
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Team team : teams) {
                out.println(team.getName() + ": " + team.getPlayers().stream()
                        .map(Player::getName)
                        .collect(Collectors.joining(", ")));
            }
        } catch (IOException e) {
            System.err.println("Error writing!!!!!! draft_results.txt: " + e.getMessage());
        }
    }
    public static void recordGame(Team homeTeam, Team awayTeam, double homeScore, double awayScore) {
    	homeScore = (double) (homeScore * 1.05); 
    	String result;
         if (homeScore > awayScore) {
             result = "Win";
         } else if (awayScore > homeScore) {
             result = "Loss";
         } else {
             result = "Tie"; 
         }

         logMatch(homeTeam.getName(), awayTeam.getName(), homeScore, awayScore, result);
    }
    public static void logMatch(String homeTeam, String awayTeam, double homeScore, double awayScore, String result) {
        try (FileWriter fw = new FileWriter("src\\match_results.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.printf("%s vs %s: %f - %f, %s%n", homeTeam, awayTeam, homeScore, awayScore, result);
        } catch (IOException e) {
            System.err.println("Error writing to match_results.txt: " + e.getMessage());
        }
    }
    public static void logChampion(String champion) {
        try (FileWriter fw = new FileWriter("src\\last_champion_team.txt");
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Champion: " + champion);
        } catch (IOException e) {
            System.err.println("Error writing playoff_results.txt: " + e.getMessage());
        }
    }
    public static void logDraftedTeams(List<Team> teams) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\teams.txt"))) {
            for (Team team : teams) {
                bw.write(team.getName() + ": " + team.getPlayers().stream()
                        .map(Player::getName)
                        .collect(Collectors.joining(", ")));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logMatchResult(String homeTeam, String awayTeam, double homeScore, double awayScore) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\match_results.txt", true))) {
            bw.write(homeTeam + " (" + homeScore + ") vs " + awayTeam + " (" + awayScore + ")");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logPlayoffGame(String homeTeam, String awayTeam, String winner, String loser, boolean isFinalGame) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\playoffs.txt", true))) {
            bw.write(homeTeam + " vs " + awayTeam + " - Winner: " + winner + ", Loser: " + loser);
            bw.newLine();

            if (isFinalGame) {
                bw.write("Champion: " + winner);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	FileManager.createFileIfNotExists();}
}