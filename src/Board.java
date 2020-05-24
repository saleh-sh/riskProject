import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    HashMap<Integer,Land> landHashMap;


   /* public ArrayList<Continent> getContinents(){
        return new ArrayList<>(continentHashMap.values()) ;
    }

    public Continent getContinentByName(String continentName){
        return continentHashMap.get(continentName);
    }*/

    public ArrayList<Land> getLands(){
        return new ArrayList<Land>(landHashMap.values());
    }

    public Land getLandById(int id){
        return landHashMap.get(id);
    }

   /* public int prizeSoldiers(String continentName){
        return continentHashMap.get(continentName).getPrizeSoldier();
    }

    public ArrayList<Land> getLandsOfContinent(String continentName){
        return continentHashMap.get(continentName).getLands();
    }*/

    public void setConqueror(String landName , Player conqueror){
        landHashMap.get(landName).setConqueror(conqueror);
    }

    public Player getConqueror(String landName){
        return landHashMap.get(landName).getConqueror();
    }

    public void setNumberSoldiersOfLand(String landName , int soldiers){
        landHashMap.get(landName).setNumberSoldiers(soldiers);
    }

    public int getNumberSoldiersOfLand(String landName){
        return landHashMap.get(landName).getNumberSoldiers();
    }

}
