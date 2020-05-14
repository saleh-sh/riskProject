import java.util.ArrayList;
import java.util.Collections;

public class Preparation {


    public void divideLands(){

        ArrayList<Land> lands = new ArrayList<>(Map.getLandHashMap().values());
        //ArrayList<Player> players = PlayersController.getPlayerList();
        ///////////////////////////////////////////////////////////////////////////////////////
        ArrayList<Player> players = new ArrayList<>(PlayersController.getPlayersMap().values());
        ////////////////////////////////////////////////////////////////////////////////////////

        Collections.shuffle(lands);
        Collections.shuffle(players);

        for(int i=0;i<lands.size();i++){
            int playerIndex = i%PlayersController.getNumberOfPlayers();

            players.get(playerIndex).addLand(lands.get(i).getLandID());
            players.get(playerIndex).decreaseSoldiers(1);
            lands.get(i).increaseSoldiers(1);
            lands.get(i).setConqueror(players.get(playerIndex));
        }

    }
}
