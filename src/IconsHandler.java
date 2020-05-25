import javax.swing.*;
import java.util.HashMap;

public class IconsHandler {

    private HashMap<Integer, ImageIcon> diceIcons;
    private HashMap<String, ImageIcon> playersIcon;

    private ImageIcon back = new ImageIcon("back.png");
    private ImageIcon next = new ImageIcon("next.png");

    private ImageIcon start = new ImageIcon("start.jpg");
    private ImageIcon exit = new ImageIcon("exit.jpg");

    private ImageIcon backGround = new ImageIcon("backG.jpg");
    private ImageIcon dialogBackG = new ImageIcon("DbackG.jpg");

    private ImageIcon arrows = new ImageIcon("arrows.png");

    private ImageIcon twoPlayers = new ImageIcon("twoPlayer.png");
    private ImageIcon threePlayers = new ImageIcon("threePlayer.png");
    private ImageIcon fourPlayers = new ImageIcon("fourPlayer.png");


    public IconsHandler() {
        addDiceIcons();
        addPlayersIcon();
    }

    public void addDiceIcons() {
        diceIcons = new HashMap<>();
        diceIcons.put(1, new ImageIcon("1.jpg"));
        diceIcons.put(2, new ImageIcon("2.jpg"));
        diceIcons.put(3, new ImageIcon("3.jpg"));
        diceIcons.put(4, new ImageIcon("4.jpg"));
        diceIcons.put(5, new ImageIcon("5.jpg"));
        diceIcons.put(6, new ImageIcon("6.jpg"));
    }

    public void addPlayersIcon() {
        playersIcon = new HashMap<>();
        playersIcon.put("yellow", new ImageIcon("yellow.jpg"));
        playersIcon.put("green", new ImageIcon("green.jpg"));
        playersIcon.put("orange", new ImageIcon("orange.jpg"));
        playersIcon.put("blue", new ImageIcon("blue.jpg"));
    }

    public ImageIcon getDiceIconByNumber(int number){
        return diceIcons.get(number);
    }

    public ImageIcon getPlayersIconByName(String iconName){
        return playersIcon.get(iconName);
    }

    public ImageIcon getBack() {
        return back;
    }

    public ImageIcon getNext() {
        return next;
    }

    public ImageIcon getStart() {
        return start;
    }

    public ImageIcon getExit() {
        return exit;
    }

    public ImageIcon getBackGround() {
        return backGround;
    }

    public ImageIcon getDialogBackG() {
        return dialogBackG;
    }

    public ImageIcon getArrows() {
        return arrows;
    }

    public ImageIcon getTwoPlayers() {
        return twoPlayers;
    }

    public ImageIcon getThreePlayers() {
        return threePlayers;
    }

    public ImageIcon getFourPlayers() {
        return fourPlayers;
    }
}
