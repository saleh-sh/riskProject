import java.util.ArrayList;
import java.util.Collections;

public class Playing {


    private GameBoardChecking boardChecking;
    ////////////////////////////////////////////////////////
    private Dice dice;

    private int defenderLosses;
    private int attackerLosses;
    private Integer attackerLandId;
    private Integer defenderLandId;
    private int attackerDice;
    private int defenderDice;
    private ArrayList<Integer> attackerRolls;
    private ArrayList<Integer> defenderRolls;

    private Integer sourceId;
    private Integer destinationId;

    public ArrayList<Integer> getAttackerRolls() {
        return attackerRolls;
    }

    public ArrayList<Integer> getDefenderRolls() {
        return defenderRolls;
    }

    public int getAttackerDice() {
        return attackerDice;
    }

    public int getDefenderDice() {
        return defenderDice;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getAttackerLandId() {
        return attackerLandId;
    }

    public Integer getDefenderLandId() {
        return defenderLandId;
    }

    public void setAttackerLandId(int attackerLandId) {
        this.attackerLandId = attackerLandId;
    }

    public void setDefenderLandId(int defenderLandId) {
        this.defenderLandId = defenderLandId;
    }

    ////////////////////////////////////////////////////////
    public void putTheBead(int landId) {

        Player currentPlayer = PlayersController.getCurrentPlayer();
        if (Map.getLandHashMap().get(landId).isConquered() == false) {

            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            Map.getLandHashMap().get(landId).setConqueror(currentPlayer);
            currentPlayer.decreaseSoldiers(1);
            currentPlayer.addLand(landId);
            System.out.println("method:put the bead :first if done");

        } else if (currentPlayer.getConqueredLands().contains(landId) == true && currentPlayer.getSoldiers() > 0) {
            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            currentPlayer.decreaseSoldiers(1);
            System.out.println(currentPlayer.getSoldiers());
            System.out.println("method:put the bead :second if done");
        } else {
            System.out.println("can not place the bead");
        }

    }

    public void attack() {

        if (boardChecking.getLandsWithAttackAbility().contains(attackerLandId)) {
            if (boardChecking.getForeignNeighbors(attackerLandId).contains(defenderLandId)) {

                calcuteAttackerDice();
                calcuteDefenderDice();
                attackerLosses = 0;
                defenderLosses = 0;
                dice = new Dice();
                attackerRolls = dice.roll(attackerDice);
                defenderRolls = dice.roll(defenderDice);
                Collections.sort(attackerRolls);
                Collections.reverse(attackerRolls);
                Collections.sort(defenderRolls);
                Collections.reverse(defenderRolls);

                for (int i = 0; i < defenderDice; i++) {
                    if (defenderRolls.get(i) >= attackerRolls.get(i)) {
                        attackerLosses++;
                    } else {
                        defenderLosses++;
                    }
                }

                Land attackerLand = Map.getLandHashMap().get(attackerLandId);
                Land defenderLand = Map.getLandHashMap().get(defenderLandId);
                attackerLand.decreaseSoldiers(attackerLosses);
                defenderLand.decreaseSoldiers(defenderLosses);

                Player attacker = Map.getLandHashMap().get(attackerLandId).getConqueror();
                if (defenderLand.getNumberSoldiers() <= 0) {
                    defenderLand.setConqueror(attacker);
                    defenderLand.setNumberSoldiers(attackerDice - attackerLosses);
                }

                // Player defender = Map.getLandHashMap().get(defenderLandId).getConqueror();
            }
        }

    }

    public void fortify() {

        Land source = Map.getLandHashMap().get(sourceId);
        Land destination = Map.getLandHashMap().get(destinationId);

        source.decreaseSoldiers(1);
        destination.increaseSoldiers(1);
    }

    public void calcuteAttackerDice() {

        if (Map.getLandHashMap().get(attackerLandId).getNumberSoldiers() >= 4) {
            attackerDice = 3;
        } else {
            attackerDice = Map.getLandHashMap().get(attackerLandId).getNumberSoldiers() - 1;
        }
    }

    public void calcuteDefenderDice() {

        if (Map.getLandHashMap().get(defenderLandId).getNumberSoldiers() >= 2) {
            defenderDice = 2;
        } else {
            defenderDice = 1;
        }
    }

}
