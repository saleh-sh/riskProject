import java.util.ArrayList;
import java.util.HashSet;

public class GameBoardChecking {

    private Playing playing;
    private ArrayList<Integer> landsWithForeignNeighbor;
    private HashSet<Integer> landsWithFortifyAbility;
    private HashSet<Integer> destinationsID = new HashSet<>();

    public GameBoardChecking(Playing playing) {
        this.playing = playing;
    }

    public HashSet<Integer> getDestinationsID() {
        return destinationsID;
    }

    public HashSet<Integer> getLandsWithFortifyAbility() {
        return landsWithFortifyAbility;
    }

    //////////////////////////////////////////////////
    public ArrayList<Integer> getLandsWithForeignNeighbor() {
        return landsWithForeignNeighbor;
    }

    public void updateNumOfSoldiersReceived(Player currentPlayer) {

        if (searchForAsia(currentPlayer)&& currentPlayer.getConqueredContinents().contains(Map.getAsia()) == false) {
            currentPlayer.increaseSoldiers(Map.getAsia().getPrizeSoldier());
            currentPlayer.getConqueredContinents().add(Map.getAsia());
        }

        if (searchForAfrica(currentPlayer)&&currentPlayer.getConqueredContinents().contains(Map.getAfrica())== false) {
            currentPlayer.increaseSoldiers(Map.getAfrica().getPrizeSoldier());
            currentPlayer.getConqueredContinents().add(Map.getAfrica());
        }

        if (searchForAmerica(currentPlayer)&& currentPlayer.getConqueredContinents().contains(Map.getAmerica())==false) {
            currentPlayer.increaseSoldiers(Map.getAmerica().getPrizeSoldier());
            currentPlayer.getConqueredContinents().add(Map.getAmerica());
        }

        if (searchForEurope(currentPlayer)&& currentPlayer.getConqueredContinents().contains(Map.getEurope())== false) {
            currentPlayer.increaseSoldiers(Map.getEurope().getPrizeSoldier());
            currentPlayer.getConqueredContinents().add(Map.getEurope());
        }

        currentPlayer.increaseSoldiers(currentPlayer.getConqueredLands().size() / 3);
        System.out.println("new soldiers: " + PlayersController.getCurrentPlayer().getSoldiers());
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

    public ArrayList<Integer> getLandsWithAttackAbility() {

        Player currentPlayer = PlayersController.getCurrentPlayer();
        ArrayList<Integer> landsWithAttackAbility = new ArrayList<>();

        for (int i = 0; i < currentPlayer.getConqueredLands().size(); i++) {
            int landIdInIndexI = currentPlayer.getConqueredLands().get(i);
            if (Map.getLandHashMap().get(landIdInIndexI).getNumberSoldiers() > 1) {
                for (int neighborID : Map.getNeighbors(landIdInIndexI)) {
                    if (currentPlayer.getConqueredLands().contains(neighborID) == false) {
                        landsWithAttackAbility.add(landIdInIndexI);
                        break;
                    }
                }
            }
        }
        return landsWithAttackAbility;
    }


    public ArrayList<Integer> getForeignNeighbors(int landId) {
        landsWithForeignNeighbor = new ArrayList<>();

        for (int neighborId : Map.getNeighbors(landId)) {
            if (Map.getLandHashMap().get(landId).getConqueror().equals(Map.getLandHashMap().get(neighborId).getConqueror()) == false) {
                landsWithForeignNeighbor.add(neighborId);
            }
        }
        return landsWithForeignNeighbor;
    }

    public void findLandsWithFortifyAbility() {

        landsWithFortifyAbility = new HashSet<>();
        for (int i = 0; i < Map.getMapLength(); i++) {
            for (int j = 0; j < Map.getMapWidth(); j++) {
                if (Map.getLands()[i][j] != null)
                    if (Map.getLands()[i][j].getConqueror().equals(PlayersController.getCurrentPlayer())
                            && Map.getLands()[i][j].getNumberSoldiers()>1) {
                        findThePath(Map.getLands()[i][j].getLandID());
                    }
            }
        }
    }

    public void findThePath(int landId) {

        for (int neighborId : Map.getNeighbors(landId)) {
            if (Map.getLandHashMap().get(landId).getConqueror().equals(Map.getLandHashMap().get(neighborId).getConqueror())) {
                landsWithFortifyAbility.add(landId);
                if (landsWithFortifyAbility.contains(neighborId) == false&&Map.getLandHashMap().get(neighborId).getNumberSoldiers()>1) {
                    landsWithFortifyAbility.add(neighborId);
                    findThePath(neighborId);
                }
            }
        }
    }

    public void findDestinations(int sourceId) {

        for (int neighborId : Map.getNeighbors(sourceId)) {
            if (Map.getLandHashMap().get(sourceId).getConqueror().equals(Map.getLandHashMap().get(neighborId).getConqueror())) {
                if (destinationsID.contains(neighborId) == false) {
                    destinationsID.add(neighborId);
                    findDestinations(neighborId);
                }
            }
        }
    }

    public boolean canFortify(int sourceId, int destinationId) {
        findLandsWithFortifyAbility();
        if (!landsWithFortifyAbility.contains(sourceId)) {
            return false;
        }
        findDestinations(sourceId);
        if (!destinationsID.contains(destinationId)) {
            return false;
        }
        return true;
    }


    public int getMaxSoldierForFortify() {

        int numOfSoldiersInSource = Map.getLandHashMap().get(playing.getSourceId()).getNumberSoldiers();
        return numOfSoldiersInSource - 1;
    }

    public void calcuteAttackerDice() {

        if (Map.getLandHashMap().get(playing.getAttackerLandId()).getNumberSoldiers() >= 4) {
            playing.setAttackerDice(3);
        } else {
            playing.setAttackerDice( Map.getLandHashMap().get(playing.getAttackerLandId()).getNumberSoldiers() - 1);
        }
    }

    public void calcuteDefenderDice() {

        if (Map.getLandHashMap().get(playing.getDefenderLandId()).getNumberSoldiers() >= 2) {
            playing.setDefenderDice(2);
        } else {
            playing.setDefenderDice(1);
        }
    }
}
