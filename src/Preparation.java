import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Preparation {

    PlayersController playersController;
    BoardView boardView;

    public void gameBoardPreparation(String[] playersName) {
        System.out.println("prepration method");
        playersController = new PlayersController();
        playersController.createPlayers(playersName);
        Map.createLands();
        Map.setSeas();
        PlayersController.findCurrentPlayer();
        divideLands();

        boardView = new BoardView();
        boardView.showTheDivisionOfSoldiers();
        boardView.showCurrentPlayer();
    }

    public void divideLands() {

        ArrayList<Land> lands = new ArrayList<>(Map.getLandHashMap().values());
        ///////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Player> players = new ArrayList<>(PlayersController.getPlayerList());
        ////////////////////////////////////////////////////////////////////////////////////////
        Collections.shuffle(lands);
        Collections.shuffle(players);

        for (int i = 0; i < lands.size(); i++) {
            int playerIndex = i % PlayersController.getNumberOfPlayers();

            players.get(playerIndex).addLand(lands.get(i).getLandID());
            players.get(playerIndex).decreaseSoldiers(1);
            lands.get(i).increaseSoldiers(1);
            lands.get(i).setConqueror(players.get(playerIndex));
        }

    }
}
