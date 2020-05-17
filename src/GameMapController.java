import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMapController implements ActionListener {

    Playing playing;
    BoardView boardView;
    GamePhase gamePhase;
    GameFlowControl gameFlowControl;

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
            } else {
                PlayersController.findCurrentPlayer();
                boardView.getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
                boardView.getNumberOfReadySoldiers().setText("ready soldiers" + PlayersController.getCurrentPlayer().getSoldiers());
                System.out.println("soldiers count update in put the bead phase");
            }*/
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

    BoardView boardView;
    Playing playing;

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

        } else {
            index = Arrays.asList(boardView.getDefenderRollsB()).indexOf(e.getSource());
            diceIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getDefenderRolls().get(index) + ".jpg");
            boardView.getDefenderRollsB()[index].setText(null);
            boardView.getDefenderRollsB()[index].setIcon(diceIcon);
        }
    }
}