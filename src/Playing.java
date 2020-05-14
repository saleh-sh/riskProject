import java.util.ArrayList;

public class Playing {

    GameBoardChecking boardChecking;


    public void putTheBead(int landId){

        if(Map.getLandHashMap().get(landId).isConquered() == false){

            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            Map.getLandHashMap().get(landId).setConqueror(PlayersController.getCurrentPlayer());
            PlayersController.getCurrentPlayer().decreaseSoldiers(1);
            PlayersController.getCurrentPlayer().addLand(landId);
            System.out.println("method:put the bead :first if done");

        }else if(PlayersController.getCurrentPlayer().getConqueredLands().contains(landId)==true){
            Map.getLandHashMap().get(landId).increaseSoldiers(1);
            PlayersController.getCurrentPlayer().decreaseSoldiers(1);
            System.out.println(PlayersController.getCurrentPlayer().getSoldiers());
            System.out.println("method:put the bead :second if done");
        }

        else {
            System.out.println("can not place the bead");
        }

    }
}
