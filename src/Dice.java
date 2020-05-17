import java.util.ArrayList;
import java.util.Random;

public class Dice {

    private Random random;
    //private ArrayList<Integer> diceList;

    public ArrayList<Integer> roll(int numberOfDice) {
        ArrayList<Integer> diceList = new ArrayList<>();
        random = new Random();
        //diceList = new ArrayList<>();
        for (int i = 0; i < numberOfDice ; i++) {
            diceList.add(random.nextInt(5)+1);
        }
        return diceList;
    }
}
