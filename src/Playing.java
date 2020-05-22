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
    private Player attacker;
    private Player defender;
    private int attackerDice;
    private int defenderDice;
    private ArrayList<Integer> attackerRolls = new ArrayList<>();
    private ArrayList<Integer> defenderRolls = new ArrayList<>();
    private int numberOfSoldierSent;
    private Integer sourceId;
    private Integer destinationId;

    public void setNumberOfSoldierSent(int numberOfSoldierSent) {
        this.numberOfSoldierSent = numberOfSoldierSent;
    }

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

    public void setAttackerLandId(Integer attackerLandId) {
        this.attackerLandId = attackerLandId;
    }

    public void setDefenderLandId(Integer defenderLandId) {
        this.defenderLandId = defenderLandId;
    }


    public void setAttackerDice(int attackerDice) {
        this.attackerDice = attackerDice;
    }

    public void setDefenderDice(int defenderDice) {
        this.defenderDice = defenderDice;
    }

    ////////////////////////////////////////////////////////
    public void putTheBead(int landId) {

        Player currentPlayer = PlayersController.getCurrentPlayer();
        if (Map.getLandHashMap().get(landId).isConquered() == false) {

            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            Map.getLandHashMap().get(landId).setConqueror(currentPlayer);
            currentPlayer.decreaseSoldiers(1);
            currentPlayer.addLand(landId);

        } else if (currentPlayer.getConqueredLands().contains(landId) == true && currentPlayer.getSoldiers() > 0) {
            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            currentPlayer.decreaseSoldiers(1);
            System.out.println(currentPlayer.getSoldiers());
        } else {
            System.out.println("can not place the bead");
        }
    }

    public void attack() {

        boardChecking = new GameBoardChecking(this);
        if (boardChecking.getLandsWithAttackAbility().contains(attackerLandId)) {
            if (boardChecking.getForeignNeighbors(attackerLandId).contains(defenderLandId)) {

                boardChecking.calcuteAttackerDice();
                boardChecking.calcuteDefenderDice();
                attackerLosses = 0;
                defenderLosses = 0;
                dice = new Dice();
                attackerRolls = dice.roll(attackerDice);
                defenderRolls = dice.roll(defenderDice);
                Collections.sort(attackerRolls);
                Collections.reverse(attackerRolls);
                Collections.sort(defenderRolls);
                Collections.reverse(defenderRolls);
                System.out.println("defender dice" + defenderDice);
                System.out.println("attacker dice" + attackerDice);
                System.out.println("defender rolls" + defenderRolls);
                System.out.println("attacker rolls" + attackerRolls);
                for (int i = 0; i < defenderDice && i < attackerDice; i++) {
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

                attacker = Map.getLandHashMap().get(attackerLandId).getConqueror();
                defender = Map.getLandHashMap().get(defenderLandId).getConqueror();

                if (defenderLand.getNumberSoldiers() <= 0) {
                    defenderLand.setConqueror(attacker);
                    defenderLand.setNumberSoldiers(attackerDice - attackerLosses);
                    attackerLand.decreaseSoldiers(attackerDice);
                    //سرزمین از دست رفته در لیست سرزمین های بازیکن باید حذف شود
                    attacker.addLand(defenderLandId);
                    defender.removeLand(defenderLandId);
                }

                //finishTheAttack();

                // Player defender = Map.getLandHashMap().get(defenderLandId).getConqueror();
            }
        }

    }

    public void fortify() {
        System.out.println("fortify");
         System.out.println("number of sent: "+ numberOfSoldierSent);
        Land source = Map.getLandHashMap().get(sourceId);
        Land destination = Map.getLandHashMap().get(destinationId);
        System.out.println(source.getNumberSoldiers());
        source.decreaseSoldiers(numberOfSoldierSent);
        System.out.println(source.getNumberSoldiers());
        System.out.println(destination.getNumberSoldiers());
        destination.increaseSoldiers(numberOfSoldierSent);
        System.out.println(destination.getNumberSoldiers());
    }

/*
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
    }*/

    public void finishTheAttack() {
        this.attackerLandId = null;
        this.defenderLandId = null;
    }

    public void finishFortify(){
        this.sourceId = null;
        this.destinationId = null;
    }
}
