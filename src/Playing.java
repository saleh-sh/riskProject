import java.util.ArrayList;

public class Playing {

    GameBoardChecking boardChecking;


    public void putTheBead(int landId){
        Player currentPlayer = PlayersController.getCurrentPlayer();

        if(Map.getLandHashMap().get(landId).isConquered() == false){

            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            Map.getLandHashMap().get(landId).setConqueror(currentPlayer);
            currentPlayer.decreaseSoldiers(1);
            currentPlayer.addLand(landId);
            System.out.println("method:put the bead :first if done");

        }else if(currentPlayer.getConqueredLands().contains(landId)==true && currentPlayer.getSoldiers()>0){
            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            currentPlayer.decreaseSoldiers(1);
            System.out.println(currentPlayer.getSoldiers());
            //System.out.println("method:put the bead :second if done");
        }

        else {
            System.out.println("can not place the bead");
        }

    }
}
