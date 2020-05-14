import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMapController implements ActionListener {

    Playing playing;
    BoardView boardView;
    GamePhase gamePhase;

    public GameMapController(Playing playing, BoardView boardView, GamePhase gamePhase) {
        this.playing = playing;
        this.boardView = boardView;
        this.gamePhase = gamePhase;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gamePhase.isPutBeadPhase()) {
            int landId = Integer.parseInt(e.getActionCommand());

            playing.putTheBead(landId);
            LandButton targetButton = boardView.getLandButtonByID(landId);
            targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");


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
                //System.out.println("number of solders update");
            }
        }
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