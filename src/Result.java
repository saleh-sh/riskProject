public class Result {

    private Player winner;
    private Player loser;
    private boolean playerLose = false;
    private boolean playerWon = false;

    public Player getLoser() {
        return loser;
    }

    public boolean isPlayerLose() {
        return playerLose;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public Player getWinner() {
        return winner;
    }

    public void findResult() {

        for (int i = PlayersController.getPlayerList().size() - 1; i >= 0; i--) {
            if (PlayersController.getPlayerList().get(i).getConqueredLands().size() == 0) {
                loser = PlayersController.getPlayerList().get(i);
                playerLose = true;
                PlayersController.getPlayerList().remove(i);
                PlayersController.setNumberOfPlayers(PlayersController.getPlayerList().size());
            }
            if (PlayersController.getNumberOfPlayers() == 1) {
                winner = PlayersController.getPlayerList().get(0);
                playerWon = true;
            }
        }

    }

}
