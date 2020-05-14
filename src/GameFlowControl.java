import javax.swing.*;
/*
public class GameFlowControl {

    GamePhase gamePhase;
    BoardView boardView;

    public GameFlowControl(GamePhase gamePhase, BoardView boardView) {
        this.gamePhase = gamePhase;
        this.boardView = boardView;
    }

    public void controlGameFLow(){
        if (gamePhase.isPutBeadPhase()){

            int i;
            for (i = 0; i < PlayersController.getNumberOfPlayers(); i++) {
                if (PlayersController.getPlayerList().get(i).getSoldiers() > 0) {
                    break;
                }
            }
            if (i >= PlayersController.getNumberOfPlayers()) {
                gamePhase.setPutBeadPhase(false);
                System.out.println("if block");
            } else {
                PlayersController.findCurrentPlayer();
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers" + PlayersController.getCurrentPlayer().getSoldiers());
                System.out.println("soldiers count update in put the bead phase");
            }
        }

    }



}
*/