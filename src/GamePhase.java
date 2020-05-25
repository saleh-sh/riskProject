public class GamePhase {

    private boolean isPutBeadPhase;
    private boolean canReinforce;
    private boolean canAttack;
    private boolean canFortify;

    private GameBoardChecking boardChecking ;
    private Playing playing;
    private BoardView boardView;

    private boolean attackerChose;
    private boolean defenderChose;
    private boolean sourceChose;
    private boolean destinationChose;
    private boolean manualChange;

    public GamePhase(GameBoardChecking boardChecking, Playing playing, BoardView boardView) {
        this.boardChecking = boardChecking;
        this.playing = playing;
        this.boardView = boardView;
    }

    {
        isPutBeadPhase = true;
        canReinforce = false;
        canAttack = false;
        canFortify = false;
    }

    public void setPutBeadPhase(boolean putBeadPhase) {
        isPutBeadPhase = putBeadPhase;
    }

    public void setCanReinforce(boolean canReinforce) {
        this.canReinforce = canReinforce;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setCanFortify(boolean canFortify) {
        this.canFortify = canFortify;
    }

    public boolean isCanReinforce() {
        return canReinforce;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public boolean isCanFortify() {
        return canFortify;
    }

    public boolean isPutBeadPhase() {
        return isPutBeadPhase;
    }

    public boolean isAttackerChose() {
        return attackerChose;
    }

    public boolean isSourceChose() {
        return sourceChose;
    }

    public void setAttackerChose(boolean attackerChose) {
        this.attackerChose = attackerChose;
    }

    public void setDefenderChose(boolean defenderChose) {
        this.defenderChose = defenderChose;
    }

    public void setSourceChose(boolean sourceChose) {
        this.sourceChose = sourceChose;
    }

    public void setDestinationChose(boolean destinationChose) {
        this.destinationChose = destinationChose;
    }

    public void setManualChange(boolean manualChange) {
        this.manualChange = manualChange;
    }


    public void automaticPhaseChange() {

        if (PlayersController.getCurrentPlayer().getSoldiers() > 0) {
            canReinforce = true;
            boardView.updateStage();
            boardView.updateNumberOfReadySPanel();
            boardView.numberOfReadySoldiersPanelVisibility(true);
            return;
        }else {
            canReinforce = false;
        }
        if (boardChecking.getLandsWithAttackAbility().isEmpty() == false&& manualChange == false) {
            canAttack = true;
            boardView.updateStage();
            if (attackerChose == false) {
                boardView.showLandsWithAttackAbility();
                attackerChose = true;
                defenderChose = false;
                return;
            }
            if (defenderChose == false) {
                boardView.showForeignNeighborsOfLand(playing.getAttackerLandId());
                defenderChose = true;
                attackerChose = false;
                return;
            }
        }else {
            canAttack = false;
        }

        boardChecking.findLandsWithFortifyAbility();
        if (boardChecking.getLandsWithFortifyAbility().isEmpty() == false) {
            canFortify = true;
            boardView.updateStage();
            if (sourceChose == false) {
                boardView.showLandsWithFortifyAbility();
                sourceChose = true;
                destinationChose = false;
                return;
            }
            if (destinationChose == false) {
                boardView.showDestinations(playing.getSourceId());
                destinationChose = true;
                sourceChose = false;
                manualChange = false;
                return;
            }
        }
        manualChange = false;
        PlayersController.findCurrentPlayer();
        boardView.updateRounds();
        boardView.showCurrentPlayer();
        boardChecking.updateNumOfSoldiersReceived(PlayersController.getCurrentPlayer());
        boardView.updateNumberOfReadySPanel();
        automaticPhaseChange();
    }

}
