import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

            if (currentPlayer.getConqueredLands().contains(landId) == true && currentPlayer.getSoldiers() > 0) {
                playing.putTheBead(landId);
                /*
                LandButton targetButton = boardView.getLandButtonByID(landId);
                targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");*/
                ///خط پایین جایگزین دو خط بالا است
                boardView.updateGameMap(landId);
                gameFlowControl.controlGameFLow();
            }

        } else if (gamePhase.isCanAttack()) {

            if (playing.getAttackerLandId() == null) {
                playing.setAttackerLandId(landId);
                //boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
                //خط پایین جایگزین خط بالا است
                boardView.showAttackerLand(landId);
                boardView.returnPreviousState();
                boardView.showForeignNeighborsOfLand(landId);
            } else {
                playing.setDefenderLandId(landId);
                //boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
                //خط پایین جایگزین خط بالا شد
                boardView.showDefenderLand(landId);
                boardView.returnPreviousState();

                playing.attack();

                //boardView.showAttackerDicePanel();
                //boardView.showDefenderDicePanel();

                //boardView.showLandsWithAttackAbility();

                 new ShowDice(playing,boardView);


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
                Suggestion suggestion = new Suggestion(playing);
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

        String actionCommand = e.getActionCommand();

        if (actionCommand.equalsIgnoreCase("attacker button1")) {
            boardView.getAttackerRollB1().setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(0) + ".jpg"));
        }
        if (actionCommand.equalsIgnoreCase("attacker button2")) {
            boardView.getAttackerRollB2().setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(1) + ".jpg"));
        }
        if (actionCommand.equalsIgnoreCase("attacker button3")) {
            boardView.getAttackerRollB3().setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(2) + ".jpg"));
        }

        if (actionCommand.equalsIgnoreCase("defender button1")) {
            boardView.getDefenderRollB1().setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getDefenderRolls().get(0) + ".jpg"));
        }

        if (actionCommand.equalsIgnoreCase("defender button2")) {
            boardView.getDefenderRollB2().setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getDefenderRolls().get(1) + ".jpg"));
        }

    }
}