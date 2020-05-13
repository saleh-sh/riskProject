import java.awt.*;

public class Land {
    private final int landID;
    private final Point coordinates;
    private int soldiers;
    private boolean isConquered;
    private Player conqueror;


    public Land(int landID) {
        this.landID = landID;
        this.coordinates = new Point(landID / Map.getMapLength(), landID / Map.getMapWidth());
        this.soldiers = 0;
        this.isConquered = false;
    }

    public int getLandID() {
        return landID;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setNumberSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public void increaseSoldiers(int soldiers) {
        this.soldiers += soldiers;
    }

    public void decreaseSoldiers(int soldiers) {
        this.soldiers -= soldiers;
    }

    public void setConqueror(Player conqueror) {
        this.conqueror = conqueror;
        this.isConquered = true;
    }

    public int getNumberSoldiers() {
        return soldiers;
    }

    public boolean isConquered() {
        return isConquered;
    }

    public Player getConqueror() {
        return conqueror;
    }

}
