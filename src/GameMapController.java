import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMapController implements ActionListener {

    Playing playing;
    BoardView boardView;
    GamePhase gamePhase;
GameFlowControl gameFlowControl;
    public GameMapController(Playing playing, BoardView boardView, GamePhase gamePhase) {
        this.playing = playing;
        this.boardView = boardView;
        this.gamePhase = gamePhase;
        gameFlowControl = new GameFlowControl(gamePhase,boardView,playing);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int landId = Integer.parseInt(e.getActionCommand());
        if (gamePhase.isPutBeadPhase() || gamePhase.isCanReinforce()) {

            playing.putTheBead(landId);
            LandButton targetButton = boardView.getLandButtonByID(landId);
            targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");
            gameFlowControl.controlGameFLow();

/*
            int i;
            for (i = 0; i < PlayersController.getNumberOfPlayers(); i++) {
                if (PlayersController.getPlayerList().get(i).getSoldiers() > 0) {
                    break;
                }
            }
            if (i >= PlayersController.getNumberOfPlayers()) {
                gamePhase.setPutBeadPhase(false);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                gamePhase.setCanReinforce(true);
                PlayersController.findCurrentPlayer();
                boardChecking.updateNumOfSoldiersReceived(PlayersController.getCurrentPlayer());
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers: " + PlayersController.getCurrentPlayer().getSoldiers());

                /////////////////////////////////////////////////////////////////////////////////////////////////////////
                System.out.println("if block");
            } else {
                PlayersController.findCurrentPlayer();
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers" + PlayersController.getCurrentPlayer().getSoldiers());
                System.out.println("soldiers count update in put the bead phase");
            }*/
        }else if (gamePhase.isCanAttack()){

            if (playing.getAttackerLandId() == null){
                playing.setAttackerLandId(landId);
                boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.BLACK,8));
            }else {
                playing.setDefenderLandId(landId);
            }
        }
////////////////////////////////////////////////////////////
       /* else if(gamePhase.isCanReinforce()){
            int landId = Integer.parseInt(e.getActionCommand());
            playing.putTheBead(landId);
            LandButton targetButton = boardView.getLandButtonByID(landId);
            targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");
            gameFlowControl.controlGameFLow();
/*
            if(PlayersController.getCurrentPlayer().getSoldiers() != 0){
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers" + PlayersController.getCurrentPlayer().getSoldiers());
                System.out.println("soldiers count update in rein force phase");
            }else {
                gamePhase.setCanReinforce(false);
            }
        }*/
////////////////////////////////////////////////////////////
    }
}




/*
class PutTheBeadController implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("getting soldier")){
           // sourceButton.setBorder(BorderFactory.createLineBorder(Color.RED));
           // sourceButton.setEnabled(false);
            System.out.println("takingSoldierButton button");
        }else if (e.getActionCommand().equalsIgnoreCase("undo")){
            
        }

    }
}*/