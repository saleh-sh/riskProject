public class GamePhase {

    private boolean isPutBeadPhase;
    private boolean canReinforce;
    private boolean canAttack;
    private boolean canFortify;

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
}
