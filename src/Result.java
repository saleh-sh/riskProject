import java.util.ArrayList;

public class Result {

    private ArrayList<Player> losers = new ArrayList<>();
    private Player winner;

    public ArrayList<Player> getLosers() {
        return losers;
    }

    public Player getWinner() {
        return winner;
    }

    public void findResult() {

        for (int i = PlayersController.getPlayerList().size() - 1; i >= 0; i--) {
            if (PlayersController.getPlayerList().get(i).getConqueredLands().size() == 0) {
                losers.add(PlayersController.getPlayerList().get(i));
                PlayersController.getPlayerList().remove(i);
                PlayersController.setNumberOfPlayers(PlayersController.getPlayerList().size());
            }
            if (PlayersController.getNumberOfPlayers() == 1){
                winner = PlayersController.getPlayerList().get(0);
            }
        }

    }

}
