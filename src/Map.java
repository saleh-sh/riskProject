import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Map {

    private static final Land[][] lands;
    private static HashMap<Integer, Land> landHashMap;
    private static final int mapWidth = 6;
    private static final int mapLength = 7;
    private static final int numberOfParts = 42;
    private static final int[] seasID = {14, 20, 21, 26, 29, 30, 35, 36, 37, 38, 40, 41, 42};
    private static final Continent america;
    private static final Continent asia;
    private static final Continent africa;
    private static final Continent europe;


    static {
        lands = new Land[mapLength][mapWidth];
        landHashMap = new HashMap<>();
        america = new Continent("america", 3, addLandAmerica());
        asia = new Continent("asia", 4, addLandsAsia());
        africa = new Continent("africa", 2, addLandsAfrica());
        europe = new Continent("europe", 4, addLandEurope());
    }

    public static void createLands() {

        int id = 1;
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapWidth; j++) {
                lands[i][j] = new Land(id);
                landHashMap.put(id, lands[i][j]);
                id++;
            }
        }
    }

    public static void setSeas() {
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapWidth; j++) {
                for (int h = 0; h < seasID.length; h++) {
                    if (lands[i][j].getLandID() == seasID[h]) {
                        landHashMap.remove(lands[i][j].getLandID());
                        lands[i][j] = null;
                        break;
                    }
                }
            }
        }
    }

    public static ArrayList<Integer> getNeighbors(int id) {

        ArrayList<Integer> neighborsId = new ArrayList<>();
        if (landHashMap.get(id).getCoordinates().x - 1 >= 0 && landHashMap.containsKey(id - mapWidth)) {
            neighborsId.add(id - mapWidth);
        }
        if (landHashMap.get(id).getCoordinates().x + 1 <= 6 && landHashMap.containsKey(id + mapWidth)) {
            neighborsId.add(id + mapWidth);
        }
        if (landHashMap.get(id).getCoordinates().y - 1 >= 0 && landHashMap.containsKey(id - 1)) {
            neighborsId.add(id - 1);
        }
        if (landHashMap.get(id).getCoordinates().y + 1 <= 5 && landHashMap.containsKey(id + 1)) {
            neighborsId.add(id + 1);
        }
        return neighborsId;
    }

    public static Land[][] getLands() {
        return lands;
    }

    public static ArrayList<Integer> addLandsAsia() {

        Integer[] asiaLandId = {5, 6, 11, 12, 17, 18, 23, 24};
        return new ArrayList(Arrays.asList(asiaLandId));
    }

    public static ArrayList<Integer> addLandsAfrica() {
        Integer[] africaLandId = {22, 27, 28, 33, 34, 39};
        return new ArrayList(Arrays.asList(africaLandId));
    }

    public static ArrayList<Integer> addLandAmerica() {
        Integer[] americaLandId = {1, 2, 7, 8, 13, 19, 25, 31, 32};
        return new ArrayList(Arrays.asList(americaLandId));
    }

    public static ArrayList<Integer> addLandEurope() {
        Integer[] europeLandId = {3, 4, 9, 10, 15, 16};
        return new ArrayList(Arrays.asList(europeLandId));
    }

    public static Land getLandByCoordinates(int x , int y){
        return lands[x][y];
    }



    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getMapLength() {
        return mapLength;
    }

    public static HashMap<Integer, Land> getLandHashMap() {
        return landHashMap;
    }

    public static Continent getAsia() {
        return asia;
    }

    public static Continent getAmerica() {
        return america;
    }

    public static Continent getAfrica() {
        return africa;
    }

    public static Continent getEurope() {
        return europe;
    }

}
