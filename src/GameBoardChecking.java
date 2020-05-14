import java.util.ArrayList;

public class GameBoardChecking {

    public void updateNumOfSoldiersReceived(Player currentPlayer) {

        System.out.println("game board checking : update num of soldiers recived method");

        if (searchForAsia(currentPlayer)) {
            currentPlayer.increaseSoldiers(Map.getAsia().getPrizeSoldier());
        }

        if (searchForAfrica(currentPlayer)) {
            currentPlayer.increaseSoldiers(Map.getAfrica().getPrizeSoldier());
        }

        if (searchForAmerica(currentPlayer)) {
            currentPlayer.increaseSoldiers(Map.getAmerica().getPrizeSoldier());
        }

        if (searchForEurope(currentPlayer)) {
            currentPlayer.increaseSoldiers(Map.getEurope().getPrizeSoldier());
        }

        currentPlayer.increaseSoldiers(currentPlayer.getConqueredLands().size() / 3);
    }

    public boolean searchForAsia(Player currentPlayer) {

        for (int i = 0; i < Map.getAsia().getLands().size(); i++) {
            if (!(currentPlayer.getConqueredLands().contains(Map.getAsia().getLands().get(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean searchForAfrica(Player currentPlayer) {

        for (int i = 0; i < Map.getAfrica().getLands().size(); i++) {
            if (!(currentPlayer.getConqueredLands().contains(Map.getAfrica().getLands().get(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean searchForAmerica(Player currentPlayer) {

        for (int i = 0; i < Map.getAmerica().getLands().size(); i++) {
            if (!(currentPlayer.getConqueredLands().contains(Map.getAmerica().getLands().get(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean searchForEurope(Player currentPlayer) {

        for (int i = 0; i < Map.getEurope().getLands().size(); i++) {
            if (!(currentPlayer.getConqueredLands().contains(Map.getEurope().getLands().get(i)))) {
                return false;
            }
        }
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void canAttack(Player currentPlayer) {

        ArrayList<Integer> landsWithAttackAbility = new ArrayList<>();
        for (int i = 0; i < currentPlayer.getConqueredLands().size(); i++) {
            int landIdInIndexI = currentPlayer.getConqueredLands().get(i);
            if (Map.getLandHashMap().get(landIdInIndexI).getNumberSoldiers() > 1) {
                for (int neighborID : Map.getNeighbors(landIdInIndexI)) {
                    if (currentPlayer.getConqueredLands().contains(neighborID) == false) {
                        landsWithAttackAbility.add(landIdInIndexI);
                        System.out.println("add land with attack ability");
                    }
                }
            }
        }
    }


    public ArrayList<Integer> getForeignNeighbors(int landId) {
        ArrayList<Integer> landsWithForeignNeighbor = new ArrayList<>();

        for (int neighborId : Map.getNeighbors(landId)) {
            if (Map.getLandHashMap().get(landId).getConqueror().equals(Map.getLandHashMap().get(neighborId).getConqueror()) == false) {
                landsWithForeignNeighbor.add(neighborId);
            }
        }
        System.out.println("add foreign neighbor for the land");
        return landsWithForeignNeighbor;
    }

}
