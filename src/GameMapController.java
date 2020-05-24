import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameMapController implements ActionListener {

    private Playing playing;
    private BoardView boardView;
    private GamePhase gamePhase;
    private GameFlowControl gameFlowControl;
    private ResultView resultView;

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

            if (/*playing.getAttackerLandId() == null*/gamePhase.isAttackerChose()) {
                playing.setAttackerLandId(landId);
                //boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
                //خط پایین جایگزین خط بالا است
                boardView.showAttackerLand(landId);
                boardView.returnPreviousState();
                ///////////////////////////////////////////////////////////boardView.showForeignNeighborsOfLand(landId);
                gamePhase.automaticPhaseChange();
            } else {
                playing.setDefenderLandId(landId);
                //boardView.getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
                //خط پایین جایگزین خط بالا شد
                boardView.showDefenderLand(landId);
                boardView.returnPreviousState();

                playing.attack();
                resultView = new ResultView(boardView);
                resultView.showResult();

                //boardView.showAttackerDicePanel();
                //boardView.showDefenderDicePanel();

                //boardView.showLandsWithAttackAbility();

                new ShowDice(playing, boardView,gamePhase);

            }
        } else if (gamePhase.isCanFortify()) {
            if (/*playing.getSourceId() == null*/gamePhase.isSourceChose()) {
                playing.setSourceId(landId);
                boardView.showSourceLand(playing.getSourceId());
                boardView.returnPreviousState();
                ////////////////////////////////////////////////////////boardView.showDestinations(playing.getSourceId());
                gamePhase.automaticPhaseChange();
            } else {
                playing.setDestinationId(landId);
                boardView.returnPreviousState();
                boardView.showDestinationLand(landId);
                new Suggestion(playing, boardView, gamePhase);
                // playing.fortify();
                //boardView.getLandButtonByID(playing.getSourceId()).setText(Map.getLandHashMap().get(playing.getSourceId()).getNumberSoldiers() + "");
                // boardView.getLandButtonByID(playing.getDestinationId()).setText(Map.getLandHashMap().get(playing.getDestinationId()).getNumberSoldiers() + "");
            }
        }
    }
}


class StagePanelController implements ActionListener {

    private GamePhase gamePhase;
    private BoardView boardView;
private GameBoardChecking boardChecking;
private Playing playing;

    public StagePanelController(GamePhase gamePhase, BoardView boardView,GameBoardChecking gameBoardChecking,Playing playing) {
        this.gamePhase = gamePhase;
        this.boardView = boardView;
        this.boardChecking = gameBoardChecking;
        this.playing = playing;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gamePhase.isCanAttack()) {
            gamePhase.setCanAttack(false);
            gamePhase.setManualChange(true);
            boardView.returnPreviousState();
            //////////////////////////////////////////////////////////////////////////////gamePhase.setCanFortify(true);
            boardView.updateStage();
            gamePhase.setAttackerChose(false);
            gamePhase.setDefenderChose(false);
            /////////////////////////////////////////////////////////////////////boardView.showLandsWithFortifyAbility();
            gamePhase.automaticPhaseChange();
        } else if (gamePhase.isCanFortify()) {
            boardView.returnPreviousState();
            gamePhase.setCanFortify(false);
            gamePhase.setSourceChose(false);
            gamePhase.setDestinationChose(false);
            gamePhase.setManualChange(false);
            /////
            playing.finishFortify();
            /////
            /////////////////////////////////////////////////////////////////////////////gamePhase.setCanReinforce(true);
            boardView.updateStage();
            PlayersController.findCurrentPlayer();
            boardView.updateRounds();
            boardView.showCurrentPlayer();
            boardChecking.updateNumOfSoldiersReceived(PlayersController.getCurrentPlayer());
            gamePhase.automaticPhaseChange();
            boardView.updateNumberOfReadySPanel();
            boardView.numberOfReadySoldiersPanelVisibility(true);
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