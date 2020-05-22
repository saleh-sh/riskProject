import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayersController {

    private static ArrayList<Player> playerList;
    private static int numberOfPlayers;
    private static Player currentPlayer;

    public PlayersController() {
    }


    public void createPlayers(String[] playersNames) {

        playerList = new ArrayList<>();
        String[] icons = {"yellow", "green", "orange", "blue"};
        Color[] colors = {Color.YELLOW, Color.GREEN, Color.ORANGE, Color.BLUE};
        for (int i = 0; i < playersNames.length; i++) {
            playerList.add(new Player(playersNames[i], getDefaultNumOfSoldiers(), icons[i], colors[i]));
        }
    }

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public static void setNumberOfPlayers(int numberOfPlayers) {
        PlayersController.numberOfPlayers = numberOfPlayers;
    }


    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static void findCurrentPlayer() {
        playerList.add(playerList.get(0));
        // currentPlayer = playerList.get(0);
        currentPlayer = playerList.remove(0);

    }

    public static int getDefaultNumOfSoldiers() {

        if (PlayersController.getNumberOfPlayers() == 2) {
            return 30;
        } else if (PlayersController.getNumberOfPlayers() == 3) {
            return 25;
        }
        return 20;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

}
