import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMapController implements ActionListener {

    Playing playing;
    BoardView boardView;
    GamePhase gamePhase;

    public GameMapController(Playing playing, BoardView boardView) {
        this.playing = playing;
        this.boardView = boardView;
        gamePhase = new GamePhase();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gamePhase.isPutBeadPhase()) {
            int landId = Integer.parseInt(e.getActionCommand());
            playing.putTheBead(landId);
            LandButton targetButton = boardView.getLandButtonByID(landId);
            targetButton.setText(PlayersController.getCurrentPlayer().getSoldiers() + "");


            int i;
            for (i = 0; i < PlayersController.getNumberOfPlayers(); i++) {
                if (PlayersController.getPlayerList().get(i).getSoldiers() != 0) {
                    break;
                }
            }
            if (i >= PlayersController.getNumberOfPlayers()) {
                gamePhase.setPutBeadPhase(false);
            } else {
                PlayersController.findCurrentPlayer();
                boardView.getLabel().setIcon(boardView.getCurrentPlayerIcon());
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