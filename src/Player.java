import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Player {

    private final String name;
    private final String icon;
    private int soldiers;
    private Color color;
    private ArrayList<Integer> conqueredLands;
    private final HashSet<Continent> conqueredContinents;

    public Player(String name, int soldiers, String icon, Color color) {
        this.name = name;
        this.icon = icon;
        this.soldiers = soldiers;
        this.color = color;
        conqueredLands = new ArrayList<>();
        conqueredContinents = new HashSet<>();
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
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
        int index = conqueredLands.indexOf(landId);
        this.conqueredLands.remove(index);
    }

    public ArrayList<Integer> getConqueredLands() {
        return conqueredLands;
    }

    public HashSet<Continent> getConqueredContinents() {
        return conqueredContinents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return soldiers == player.soldiers &&
                name.equals(player.name) &&
                icon.equals(player.icon) &&
                conqueredLands.equals(player.conqueredLands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, icon, soldiers, conqueredLands);
    }
}

