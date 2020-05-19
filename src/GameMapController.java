import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMapController implements ActionListener {

    private Playing playing;
    private BoardView boardView;
    private GamePhase gamePhase;
    private GameFlowControl gameFlowControl;

    public GameMapController(Playing playing, BoardView boardView, GamePhase gamePhase) {
        this.playing = playing;
        this.boardView = boardView;
        this.gamePhase = gamePhase;
        gameFlowControl = new GameFlowControl(gamePhase, boardView, playing);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int landId = Integer.parseInt(e.getActionCommand());
        if (gamePhase.isPutBeadPhase() || gamePhase.isCanReinforce()) {
            Player currentPlayer = PlayersController.getCurrentPlayer();
if (currentPlayer.getConqueredLands().contains(landId) == true && currentPlayer.getSoldiers() > 0){
            playing.putTheBead(landId);
            LandButton targetButton = boardView.getLandButtonByID(landId);
            targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");
            gameFlowControl.controlGameFLow();}

        } else if (gamePhase.isCanAttack()) {

            if (playing.getAttackerLandId() == null) {
                playing.setAttackerLandId(landId);
                boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
                //boardView.showForeignNeighborsOfLand(landId);
            } else {
                playing.setDefenderLandId(landId);
                boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
                //boardView.returnPreviousState();
                playing.attack();
            }
        } else if (gamePhase.isCanFortify()) {
            if (playing.getSourceId() == null) {
                playing.setSourceId(landId);
                boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
                boardView.returnPreviousState();
                boardView.showDestinations(playing.getSourceId());
            } else {
                playing.setDestinationId(landId);
                boardView.returnPreviousState();
                boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
                playing.fortify();
                boardView.getLandButtonByID(playing.getSourceId()).setText(Map.getLandHashMap().get(playing.getSourceId()).getNumberSoldiers() + "");
                boardView.getLandButtonByID(playing.getDestinationId()).setText(Map.getLandHashMap().get(playing.getDestinationId()).getNumberSoldiers() + "");
            }
        }
    }
}

class StagePanelController implements ActionListener {

    private GamePhase gamePhase;
    private BoardView boardView;

    public StagePanelController(GamePhase gamePhase, BoardView boardView) {
        this.gamePhase = gamePhase;
        this.boardView = boardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gamePhase.isCanAttack()) {
            gamePhase.setCanAttack(false);
            gamePhase.setCanFortify(true);
            //boardView.showLandsWithFortifyAbility();
        }
    }
}

class dicePanelController implements ActionListener {

    private BoardView boardView;
    private Playing playing;

    public dicePanelController(BoardView boardView, Playing playing) {
        this.boardView = boardView;
        this.playing = playing;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int index;
        String actionCommand = e.getActionCommand();
        ImageIcon diceIcon;
        if (actionCommand.equalsIgnoreCase("attacker button")) {
            index = Arrays.asList(boardView.getAttackerRollsB()).indexOf(e.getSource());
            diceIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(index) + ".jpg");
            boardView.getAttackerRollsB()[index].setText(null);
            boardView.getAttackerRollsB()[index].setIcon(diceIcon);


            if (index + 1 < boardView.getAttackerRollsB().length) {
                boardView.getAttackerRollsB()[index + 1].setEnabled(true);
            }else {
                boardView.getDefenderRollsB()[0].setEnabled(true);
            }

        }

        if (actionCommand.equalsIgnoreCase("defender button")) {
            index = Arrays.asList(boardView.getDefenderRollsB()).indexOf(e.getSource());
            diceIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getDefenderRolls().get(index) + ".jpg");
            boardView.getDefenderRollsB()[index].setText(null);
            boardView.getDefenderRollsB()[index].setIcon(diceIcon);

            if (index + 1 < boardView.getDefenderRollsB().length) {
                boardView.getDefenderRollsB()[index + 1].setEnabled(true);
            } else {
                boardView.showRollResult();
            }
        }
    }
}