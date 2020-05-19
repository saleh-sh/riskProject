import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class BoardView extends JFrame {
    Playing playing = new Playing();
    GamePhase gamePhase = new GamePhase();
    GameMapController gameMapController = new GameMapController(playing, this, gamePhase);
    private GameBoardChecking boardChecking = new GameBoardChecking();

    private LandButton[][] landButtons;
    private HashMap<Integer, LandButton> landButtonMap;

    private JPanel gameMap;


    private JPanel stageOfGamePanel;
    private JLabel reinforceLabel;
    private JLabel attackLabel;
    private JLabel fortifyLabel;
    private JButton nextButton;


    private JPanel numberOfSoldiersPanel;
    private JLabel numberOfReadySoldiers;
    private JLabel label;
    private ImageIcon currentPlayerIcon;
    /////////////////////////////////////////////////
    private JPanel dicePanel;
    private JButton[] attackerRollsB;
    private JButton[] defenderRollsB;
    /////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private JPanel timePanel;
    private JLabel secondsLabel;
    private JLabel minutesLabel;
    private JLabel hoursLabel;
    private MeasurementOfTimeElapsed MTimeElapsed = new MeasurementOfTimeElapsed(this);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private JPanel playersNamePanel;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JLabel playerThreeLabel;
    private JLabel playerFourLabel;


    public Playing getPlaying() {
        return playing;
    }

    public JLabel getSecondsLabel() {
        return secondsLabel;
    }

    public JLabel getMinutesLabel() {
        return minutesLabel;
    }

    public JLabel getHoursLabel() {
        return hoursLabel;
    }

    public JPanel getDicePanel() {
        return dicePanel;
    }

    public JButton[] getAttackerRollsB() {
        return attackerRollsB;
    }

    public JButton[] getDefenderRollsB() {
        return defenderRollsB;
    }


    public BoardView() {

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setLayout(null);
        this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\backG.jpg")));
        this.add(gameMap());
        this.add(showStageOfGamePanel());
        this.add(showNumberOfReadySoldiers());
        this.add(showDicePanel());
        this.add(showElapsedTime());
        this.add(getPlayersNamePanel());
        this.setVisible(true);
    }

    ////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public JLabel getNumberOfReadySoldiers() {
        return numberOfReadySoldiers;
    }

    public JLabel getLabel() {
        return label;
    }

    public ImageIcon getCurrentPlayerIcon() {
        return currentPlayerIcon;
    }

    public JPanel getNumberOfSoldiersPanel() {
        return numberOfSoldiersPanel;
    }

    /////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public LandButton getLandButtonByID(int id) {
        return landButtonMap.get(id);
    }

    public JPanel gameMap() {

        gameMap = new JPanel();
        gameMap.setBounds(350, 150, 1300, 500);
        landButtons = new LandButton[Map.getMapLength()][Map.getMapWidth()];
        landButtonMap = new HashMap<>();
        int i, j;
        int id = 1;
        for (i = 0; i < Map.getMapLength(); i++) {
            for (j = 0; j < Map.getMapWidth(); j++) {
                landButtons[i][j] = new LandButton(id);
                //////
                landButtonMap.put(id, landButtons[i][j]);
                //////
                landButtons[i][j].setActionCommand("" + id);
                landButtons[i][j].addActionListener(gameMapController);
                if (Map.getAsia().getLands().contains(id)) {
                    landButtons[i][j].setBackground(Color.green);
                } else if (Map.getAfrica().getLands().contains(id)) {
                    landButtons[i][j].setBackground(Color.YELLOW);
                } else if (Map.getAmerica().getLands().contains(id)) {
                    landButtons[i][j].setBackground(Color.BLACK);
                } else if (Map.getEurope().getLands().contains(id)) {
                    landButtons[i][j].setBackground(Color.RED);
                } else {
                    landButtons[i][j].setBackground(Color.BLUE);
                    landButtons[i][j].setEnabled(false);
                }
                gameMap.add(landButtons[i][j]);
                id++;
            }
        }
        gameMap.setLayout(new GridLayout(7, 6));
        return gameMap;
    }

    ////////////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void showTheDivisionOfSoldiers() {
        for (int i = 0; i < Map.getMapLength(); i++) {
            for (int j = 0; j < Map.getMapWidth(); j++) {
                if (Map.getLands()[i][j] != null) {
                    String icon = Map.getLandByCoordinates(i, j).getConqueror().getIcon();
                    String text = "" + Map.getLandByCoordinates(i, j).getNumberSoldiers();
                    ImageIcon imageIcon = new ImageIcon(icon + ".jpg");
                    landButtons[i][j].setIcon(imageIcon);
                    landButtons[i][j].setText(text);
                    landButtons[i][j].setFont(new Font("Algerian", Font.BOLD, 20));
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public JPanel showStageOfGamePanel() {

        stageOfGamePanel = new JPanel();
        stageOfGamePanel.setBounds(600, 900, 700, 71);
        stageOfGamePanel.setBackground(Color.DARK_GRAY);

        reinforceLabel = new JLabel("reinforce");
        reinforceLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        reinforceLabel.setPreferredSize(new Dimension(160, 60));

        attackLabel = new JLabel("attack");
        attackLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        //attackLabel.setBorder(BorderFactory.);
        attackLabel.setPreferredSize(new Dimension(160, 60));

        fortifyLabel = new JLabel("fortify");
        fortifyLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        fortifyLabel.setPreferredSize(new Dimension(160, 60));

        nextButton = new JButton("next stage");
        nextButton.setFont(new Font("Algerian", Font.BOLD, 20));
        nextButton.setPreferredSize(new Dimension(200, 60));
        nextButton.setActionCommand("nextStage");
        //
        //nextButton.addActionListener(new StagePanelController(gamePhase,this));


        stageOfGamePanel.add(reinforceLabel);
        stageOfGamePanel.add(attackLabel);
        stageOfGamePanel.add(fortifyLabel);
        stageOfGamePanel.add(nextButton);

        stageOfGamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return stageOfGamePanel;
    }

    public JPanel showElapsedTime() {

        timePanel = new JPanel();
        timePanel.setBounds(1700, 50, 130, 50);
        timePanel.setBackground(Color.GRAY);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);

        secondsLabel = new JLabel();
        secondsLabel.setText("" + MTimeElapsed.getSecond());
        secondsLabel.setFont(new Font("Algerian", Font.BOLD, 20));

        minutesLabel = new JLabel();
        minutesLabel.setText("" + MTimeElapsed.getMinutes());
        minutesLabel.setFont(new Font("Algerian", Font.BOLD, 20));

        hoursLabel = new JLabel();
        hoursLabel.setText("" + MTimeElapsed.getHour());
        hoursLabel.setFont(new Font("Algerian", Font.BOLD, 20));


        timePanel.add(hoursLabel);
        timePanel.add(minutesLabel);
        timePanel.add(secondsLabel);

        timePanel.setLayout(flowLayout);

        return timePanel;
    }

    public JPanel showNumberOfReadySoldiers() {


        numberOfSoldiersPanel = new JPanel();
        numberOfSoldiersPanel.setBounds(1500, 750, 220, 100);
        numberOfSoldiersPanel.setBackground(Color.BLACK);

        numberOfReadySoldiers = new JLabel();
        numberOfReadySoldiers.setBounds(30, 90, 140, 140);
        numberOfReadySoldiers.setText("ready soldiers:" + PlayersController.getCurrentPlayer().getSoldiers());
        numberOfReadySoldiers.setFont(new Font("Algerian", Font.BOLD, 20));

        label = new JLabel();
        String iconAddress = "" + PlayersController.getCurrentPlayer().getIcon();
        currentPlayerIcon = new ImageIcon(iconAddress + ".jpg");
        label.setIcon(currentPlayerIcon);

        numberOfSoldiersPanel.add(label);
        numberOfSoldiersPanel.add(numberOfReadySoldiers);

        return numberOfSoldiersPanel;
    }

    public JPanel getPlayersNamePanel() {

        ArrayList<Player> players = new ArrayList(PlayersController.getPlayerList());
        int numberOfPlayers = PlayersController.getNumberOfPlayers();

        playersNamePanel = new JPanel();
        playersNamePanel.setBounds(1700, 250, 200, 210);
        playersNamePanel.setBackground(Color.black);

        playerOneLabel = new JLabel(players.get(0).getName());
        playerOneLabel.setBounds(10, 0, 200, 40);
        playerOneLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        playerOneLabel.setForeground(Color.WHITE);
        playersNamePanel.add(playerOneLabel);

        playerTwoLabel = new JLabel(players.get(1).getName());
        playerTwoLabel.setFont(new Font("Algerian", Font.BOLD, 20));
        playerTwoLabel.setForeground(Color.WHITE);
        playerTwoLabel.setBounds(10, 60, 200, 20);
        playersNamePanel.add(playerTwoLabel);

        if (numberOfPlayers == 3 || numberOfPlayers == 4) {
            playerThreeLabel = new JLabel(players.get(2).getName());
            playerThreeLabel.setFont(new Font("Algerian", Font.BOLD, 20));
            playerThreeLabel.setForeground(Color.WHITE);
            playerThreeLabel.setBounds(10, 120, 200, 20);
            playersNamePanel.add(playerThreeLabel);
        }

        if (numberOfPlayers == 4) {
            playerFourLabel = new JLabel(players.get(3).getName());
            playerFourLabel.setFont(new Font("Algerian", Font.BOLD, 20));
            playerFourLabel.setForeground(Color.WHITE);
            playerFourLabel.setBounds(10, 180, 200, 20);
            playersNamePanel.add(playerFourLabel);
        }

        playersNamePanel.setLayout(null);
        return playersNamePanel;
    }

    public void showCurrentPlayer() {

        String name = PlayersController.getCurrentPlayer().getName();
        Icon arrowIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\arrows.png");
        if (name.equalsIgnoreCase(playerOneLabel.getText())) {
            playerOneLabel.setIcon(arrowIcon);
        }

        if (name.equalsIgnoreCase(playerTwoLabel.getText())) {
            playerTwoLabel.setIcon(arrowIcon);
        }

        try {
            if (name.equalsIgnoreCase(playerThreeLabel.getText())) {
                playerThreeLabel.setIcon(arrowIcon);
            }

            if (name.equalsIgnoreCase(playerFourLabel.getText())) {
                playerFourLabel.setIcon(arrowIcon);
            }
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }

    }

    public JPanel showDicePanel() {

        dicePanel = new JPanel();
        dicePanel.setBounds(20, 80, 260, 280);
        dicePanel.setBackground(Color.BLACK);
        dicePanel.setLayout(null);

        attackerRollsB = new JButton[playing.getAttackerDice()];
        defenderRollsB = new JButton[playing.getDefenderDice()];

        for (int i = 0; i < attackerRollsB.length; i++) {
            attackerRollsB[i] = new JButton("attacker");
            attackerRollsB[i].setBounds(30, 70 * (i + 1), 83, 60);
            attackerRollsB[i].setActionCommand("attacker button");
            attackerRollsB[i].addActionListener(new dicePanelController(this, playing));
            attackerRollsB[i].setEnabled(false);
            dicePanel.add(attackerRollsB[i]);
        }
        // attackerRollsB[0].setEnabled(true);
        for (int j = 0; j < defenderRollsB.length; j++) {
            defenderRollsB[j] = new JButton("defender");
            defenderRollsB[j].setBounds(140, 70 * (j + 1), 84, 60);
            defenderRollsB[j].setActionCommand("defender button");
            defenderRollsB[j].addActionListener(new dicePanelController(this, playing));
            defenderRollsB[j].setEnabled(false);
            dicePanel.add(defenderRollsB[j]);
        }

        return dicePanel;
    }


    public void showLandsWithAttackAbility() {

        //boardChecking = new GameBoardChecking();
        //boardChecking.canAttack(PlayersController.getCurrentPlayer());
        ArrayList<Integer> landsWithAttackAbility = boardChecking.getLandsWithAttackAbility();
        for (int i = 0; i < Map.getMapLength(); i++) {
            for (int j = 0; j < Map.getMapWidth(); j++) {
                if (landsWithAttackAbility.contains(landButtons[i][j].getId())) {
                    landButtons[i][j].setBackground(Color.WHITE);
                }
            }

        }
    }

    public void returnPreviousState() {
        for (int i = 0; i < Map.getMapLength(); i++) {
            for (int j = 0; j < Map.getMapWidth(); j++) {
                if (landButtons[i][j].getBackground().equals(Color.WHITE) || landButtons[i][j].getBackground().equals(Color.DARK_GRAY)) {
                    int landButtonsId = landButtons[i][j].getId();
                    if (Map.getAsia().getLands().contains(landButtonsId)) {
                        landButtons[i][j].setBackground(Color.green);
                    } else if (Map.getAfrica().getLands().contains(landButtonsId)) {
                        landButtons[i][j].setBackground(Color.yellow);
                    } else if (Map.getAmerica().getLands().contains(landButtonsId)) {
                        landButtons[i][j].setBackground(Color.black);
                    } else if (Map.getEurope().getLands().contains(landButtonsId)) {
                        landButtons[i][j].setBackground(Color.RED);
                    }
                }
            }

        }
    }

    /*
        public void showForeignNeighborsOfLand(int landId) {
            //////////////////////////
            //ArrayList<Integer> neighborsId = boardChecking.getForeignNeighbors(landId);
            //////////////////////////
            for (int i = 0; i < neighborsId.size(); i++) {
                landButtonMap.get(neighborsId.get(i)).setBackground(Color.DARK_GRAY);
            }
        }
    */
    public void showLandsWithFortifyAbility() {
        boardChecking.findLandsWithFortifyAbility();
        HashSet<Integer> landsWithFortifyAbility = boardChecking.getLandsWithFortifyAbility();
        for (int i = 0; i < Map.getMapLength(); i++) {
            for (int j = 0; j < Map.getMapWidth(); j++) {
                if (landsWithFortifyAbility.contains(landButtons[i][j].getId())) {
                    landButtons[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void showDestinations(int sourceId) {
        boardChecking.findDestinations(sourceId);
        HashSet<Integer> destinationsId = boardChecking.getDestinationsID();
        for (int destinationId : destinationsId) {
            landButtonMap.get(destinationId).setBackground(Color.DARK_GRAY);
        }
    }

    public void showRollResult() {

        for (int i = 0; i < defenderRollsB.length; i++) {
            if (playing.getDefenderRolls().get(i) >= playing.getAttackerRolls().get(i)) {
                defenderRollsB[i].setBackground(Color.green);
                attackerRollsB[i].setBackground(Color.RED);
            } else {
                defenderRollsB[i].setBackground(Color.RED);
                attackerRollsB[i].setBackground(Color.green);
            }
        }
    }

    public void updateGameMap(int landId){
        LandButton targetButton = this.getLandButtonByID(landId);
        targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");
    }

    public void updateNumberOfReadySPanel(){
        getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
        getNumberOfReadySoldiers().setText("ready soldiers: " + PlayersController.getCurrentPlayer().getSoldiers());
    }

}


class LandButton extends JButton {
    private final int id;

    public LandButton(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}


class Suggestion extends JDialog {

    private JButton doneButton;
    private JSlider numOfSoldiers;
    private JLabel showSlider;
    private GameBoardChecking boardChecking;

    public Suggestion() {

        setBounds(800, 300, 500, 282);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\DbackG.jpg")));
        setLayout(null);

        doneButton = new JButton("done");
        doneButton.setBounds(0, 235, 100, 40);
        doneButton.setFont(new Font("Algerian", Font.BOLD, 20));
        doneButton.setForeground(Color.BLACK);
        doneButton.setBackground(Color.DARK_GRAY);

        showSlider = new JLabel();
        showSlider.setBounds(240, 50, 200, 60);
        showSlider.setFont(new Font("Algerian", Font.BOLD, 50));
        showSlider.setForeground(Color.WHITE);

        numOfSoldiers = new JSlider(1, 10);//boardChecking.getMaxSoldierForFortify());
        numOfSoldiers.setBounds(105, 110, 300, 50);
        numOfSoldiers.setMajorTickSpacing(1);
        numOfSoldiers.setFont(new Font("Algerian", Font.BOLD, 20));
        numOfSoldiers.setBackground(Color.darkGray);
        numOfSoldiers.setForeground(Color.BLACK);
        numOfSoldiers.setPaintTicks(true);
        numOfSoldiers.setPaintLabels(true);
        numOfSoldiers.setPaintTrack(false);
        numOfSoldiers.setBorder(new EtchedBorder());
        numOfSoldiers.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                showSlider.setText("" + numOfSoldiers.getValue());
            }
        });

        add(numOfSoldiers);
        add(doneButton);
        add(showSlider);
        setVisible(true);
    }
}

