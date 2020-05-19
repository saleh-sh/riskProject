import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Player {

    private final String name;
    private final String color;
    private int soldiers;

    private ArrayList<Integer> conqueredLands;

    public Player(String name, int soldiers,String color) {
        this.name = name;
        this.color = color;
        this.soldiers = soldiers;
        conqueredLands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return color;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void increaseSoldiers(int soldiers) {
        this.soldiers += soldiers;
    }

    public void decreaseSoldiers(int soldiers) {
        this.soldiers -= soldiers;
    }

    public void addLand(int id) {
        this.conqueredLands.add(id);
    }

    public void removeLand(int landId) {
        this.conqueredLands.remove(landId);
    }

    public ArrayList<Integer> getConqueredLands() {
        return conqueredLands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return soldiers == player.soldiers &&
                name.equals(player.name) &&
                color.equals(player.color) &&
                conqueredLands.equals(player.conqueredLands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, soldiers, conqueredLands);
    }
}

