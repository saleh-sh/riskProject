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
                boardView.updateGameMap(landId);
                gameFlowControl.controlGameFLow();
            }

        } else if (gamePhase.isCanAttack()) {

            if (gamePhase.isAttackerChose()) {
                playing.setAttackerLandId(landId);
                boardView.showAttackerLand(landId);
                boardView.returnPreviousState();
                gamePhase.automaticPhaseChange();
            } else {
                playing.setDefenderLandId(landId);
                boardView.showDefenderLand(landId);
                boardView.returnPreviousState();
                playing.attack();

                new ShowDice(playing, boardView, gamePhase);

                //resultView = new ResultView(boardView);
                //resultView.showResult();
            }
        } else if (gamePhase.isCanFortify()) {
            if (gamePhase.isSourceChose()) {
                playing.setSourceId(landId);
                boardView.showSourceLand(playing.getSourceId());
                boardView.returnPreviousState();
                gamePhase.automaticPhaseChange();
            } else {
                playing.setDestinationId(landId);
                boardView.returnPreviousState();
                boardView.showDestinationLand(landId);
                new Suggestion(playing, boardView, gamePhase);
            }
        }
    }
}


class StagePanelController implements ActionListener {

    private GamePhase gamePhase;
    private BoardView boardView;
    private GameBoardChecking boardChecking;
    private Playing playing;

    public StagePanelController(GamePhase gamePhase, BoardView boardView, GameBoardChecking gameBoardChecking, Playing playing) {
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
            boardView.updateStage();
            gamePhase.setAttackerChose(false);
            gamePhase.setDefenderChose(false);
            gamePhase.automaticPhaseChange();
        } else if (gamePhase.isCanFortify()) {
            boardView.returnPreviousState();
            gamePhase.setCanFortify(false);
            gamePhase.setSourceChose(false);
            gamePhase.setDestinationChose(false);
            gamePhase.setManualChange(false);
            playing.finishFortify();
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
    private IconsHandler iconsHandler;

    public dicePanelController(BoardView boardView, Playing playing) {
        this.boardView = boardView;
        this.playing = playing;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();
        iconsHandler = new IconsHandler();

        if (actionCommand.equalsIgnoreCase("attacker button1")) {
            boardView.getAttackerRollB1().setIcon(iconsHandler.getDiceIconByNumber(playing.getAttackerRolls().get(0)));
        }
        if (actionCommand.equalsIgnoreCase("attacker button2")) {
            boardView.getAttackerRollB2().setIcon(iconsHandler.getDiceIconByNumber(playing.getAttackerRolls().get(1)));
        }
        if (actionCommand.equalsIgnoreCase("attacker button3")) {
            boardView.getAttackerRollB3().setIcon(iconsHandler.getDiceIconByNumber(playing.getAttackerRolls().get(2)));
        }

        if (actionCommand.equalsIgnoreCase("defender button1")) {
            boardView.getDefenderRollB1().setIcon(iconsHandler.getDiceIconByNumber(playing.getDefenderRolls().get(0)));
        }

        if (actionCommand.equalsIgnoreCase("defender button2")) {
            boardView.getDefenderRollB2().setIcon(iconsHandler.getDiceIconByNumber(playing.getDefenderRolls().get(1)));
        }
    }
}
