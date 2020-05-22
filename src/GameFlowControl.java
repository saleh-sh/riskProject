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
                /////
                boardView.updateStage();
                PlayersController.findCurrentPlayer();
                /////////
                boardView.showCurrentPlayer();
                boardChecking.updateNumOfSoldiersReceived(PlayersController.getCurrentPlayer());
                /*
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers: " + PlayersController.getCurrentPlayer().getSoldiers());
*/
                boardView.updateNumberOfReadySPanel();
            } else {
                do {
                    PlayersController.findCurrentPlayer();
                } while (PlayersController.getCurrentPlayer().getSoldiers() <= 0);
                boardView.showCurrentPlayer();
                /*
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers:" + PlayersController.getCurrentPlayer().getSoldiers());
                */
                //خط پایین جایگزین دو خط بالا شد
                boardView.updateNumberOfReadySPanel();
            }
        } else if (gamePhase.isCanReinforce()) {
            if (PlayersController.getCurrentPlayer().getSoldiers() != 0) {
                /*
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers:" + PlayersController.getCurrentPlayer().getSoldiers());
                */
                boardView.updateNumberOfReadySPanel();

            } else {
                gamePhase.setCanReinforce(false);
                boardView.getNumberOfSoldiersPanel().setVisible(false);


                gamePhase.setCanAttack(true);
                boardView.updateStage();
                boardView.showLandsWithAttackAbility();
            }
        } else if (gamePhase.isCanAttack()) {

        }

    }

}
