public class GameFlowControl {

    private GamePhase gamePhase;
    private BoardView boardView;
    private Playing playing;
    private GameBoardChecking boardChecking;

    public GameFlowControl(GamePhase gamePhase, BoardView boardView, Playing playing) {
        this.gamePhase = gamePhase;
        this.boardView = boardView;
        this.playing = playing;
        boardChecking = new GameBoardChecking(playing);
    }

    public void controlGameFLow() {

        if (gamePhase.isPutBeadPhase()) {

            int i;
            for (i = 0; i < PlayersController.getNumberOfPlayers(); i++) {
                if (PlayersController.getPlayerList().get(i).getSoldiers() > 0) {
                    break;
                }
            }
            if (i >= PlayersController.getNumberOfPlayers()) {
                gamePhase.setPutBeadPhase(false);
                gamePhase.setCanReinforce(true);
                boardView.updateStage();
                PlayersController.findCurrentPlayer();
                boardView.updateRounds();
                boardView.showCurrentPlayer();
                boardChecking.updateNumOfSoldiersReceived(PlayersController.getCurrentPlayer());
                boardView.updateNumberOfReadySPanel();
            } else {
                do {
                    PlayersController.findCurrentPlayer();
                } while (PlayersController.getCurrentPlayer().getSoldiers() <= 0);
                boardView.updateRounds();
                boardView.showCurrentPlayer();
                boardView.updateNumberOfReadySPanel();
            }
        } else if (gamePhase.isCanReinforce()) {
            if (PlayersController.getCurrentPlayer().getSoldiers() != 0) {
                boardView.updateNumberOfReadySPanel();

            } else {
                gamePhase.setCanReinforce(false);
                boardView.getNumberOfSoldiersPanel().setVisible(false);
                gamePhase.automaticPhaseChange();
                boardView.updateStage();
            }
        }
    }
}
