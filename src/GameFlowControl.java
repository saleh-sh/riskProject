import javax.swing.*;

public class GameFlowControl {

    private GamePhase gamePhase;
    private BoardView boardView;
    private Playing playing;
    private GameBoardChecking boardChecking = new GameBoardChecking();

    public GameFlowControl(GamePhase gamePhase, BoardView boardView, Playing playing) {
        this.gamePhase = gamePhase;
        this.boardView = boardView;
        this.playing = playing;
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
                System.out.println("put the bead false");
                gamePhase.setCanReinforce(true);
                PlayersController.findCurrentPlayer();
                boardChecking.updateNumOfSoldiersReceived(PlayersController.getCurrentPlayer());
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers: " + PlayersController.getCurrentPlayer().getSoldiers());

            } else {
                PlayersController.findCurrentPlayer();
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers" + PlayersController.getCurrentPlayer().getSoldiers());
                System.out.println("soldiers count update in put the bead phase");
            }
        } else if (gamePhase.isCanReinforce()) {
            if (PlayersController.getCurrentPlayer().getSoldiers() != 0) {
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers" + PlayersController.getCurrentPlayer().getSoldiers());
                System.out.println("soldiers count update in rein force phase");
            } else {
                gamePhase.setCanReinforce(false);
                System.out.println("reinforce false");


                gamePhase.setCanAttack(true);
                //boardView.showLandsWithAttackAbility();
                boardView.getNumberOfSoldiersPanel().setVisible(false);
            }
        } else if (gamePhase.isCanAttack()) {

        }

    }

}
