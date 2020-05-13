import java.util.ArrayList;

public class Continent {
    private final String name;
    private final ArrayList<Integer> landsId;
    private final int prizeSoldier;

    public Continent(String name ,int prizeSoldier,ArrayList<Integer> landsId) {
        this.name = name;
        this.prizeSoldier = prizeSoldier;
        this.landsId = landsId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getLands() {
        return landsId;
    }

    public int getPrizeSoldier() {
        return prizeSoldier;
    }
}
