public class RoundsCounter {

    private int turnCounter;

    public RoundsCounter() {
        this.turnCounter = 0;
    }

    public void increaseNumberOfTurns() {
        turnCounter++;
    }

    public int countRounds() {
        int numberOfPlayers = PlayersController.getNumberOfPlayers();
        return (turnCounter / numberOfPlayers) + 1;
    }
}
