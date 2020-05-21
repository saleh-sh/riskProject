import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BoardView extends JFrame {
    private Playing playing = new Playing();
    private GamePhase gamePhase = new GamePhase();
    private GameBoardChecking boardChecking = new GameBoardChecking(playing);


    GameMapController gameMapController = new GameMapController(playing, this, gamePhase);


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
    private JPanel attackerDicePanel;
    private JPanel defenderDicePanel;
    JButton attackerRollB1;
    JButton attackerRollB2;
    JButton attackerRollB3;
    JButton defenderRollB1;
    JButton defenderRollB2;
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

    private boolean attackerShown = false;
    private boolean defenderShown = false;


    public JButton getAttackerRollB1() {
        return attackerRollB1;
    }

    public JButton getAttackerRollB2() {
        return attackerRollB2;
    }

    public JButton getAttackerRollB3() {
        return attackerRollB3;
    }

    public JButton getDefenderRollB1() {
        return defenderRollB1;
    }

    public JButton getDefenderRollB2() {
        return defenderRollB2;
    }

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

    public JPanel getAttackerDicePanel() {
        return attackerDicePanel;
    }


    public BoardView() {

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setLayout(null);
        this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\backG.jpg")));
        this.add(gameMap());
        this.add(showStageOfGamePanel());
        this.add(showNumberOfReadySoldiers());
        //this.add(showDicePanel());
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
                    landButtons[i][j].setBorder(null);
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

    public void showAttackerDicePanel() {

        attackerDicePanel = new JPanel();
        attackerDicePanel.setBounds(10, 80, 150, 200);
        attackerDicePanel.setBackground(Color.DARK_GRAY);
        GridLayout gridLayout = new GridLayout(3, 1);

        attackerRollB1 = new JButton("attacker");
        attackerRollB1.setActionCommand("attacker button1");
        attackerRollB1.addActionListener(new dicePanelController(this, playing));
        attackerDicePanel.add(attackerRollB1);

        if (playing.getAttackerDice() == 2 || playing.getAttackerDice() == 3) {
            attackerRollB2 = new JButton("attacker");
            attackerRollB2.setActionCommand("attacker button2");
            attackerRollB2.addActionListener(new dicePanelController(this, playing));
            attackerDicePanel.add(attackerRollB2);
        }

        if (playing.getAttackerDice() == 3) {
            attackerRollB3 = new JButton("attacker");
            attackerRollB3.setActionCommand("attacker button3");
            attackerRollB3.addActionListener(new dicePanelController(this, playing));
            attackerDicePanel.add(attackerRollB3);
        }

        attackerDicePanel.setLayout(gridLayout);
        this.add(attackerDicePanel);
    }


    public void showDefenderDicePanel() {

        defenderDicePanel = new JPanel();
        defenderDicePanel.setBounds(180, 80, 150, 180);
        defenderDicePanel.setBackground(Color.DARK_GRAY);
        GridLayout gridLayout = new GridLayout(2, 1);

        defenderRollB1 = new JButton("defender");
        defenderRollB1.setActionCommand("defender button1");
        defenderRollB1.addActionListener(new dicePanelController(this, playing));
        defenderDicePanel.add(defenderRollB1);

        if (playing.getDefenderDice() == 2) {
            defenderRollB2 = new JButton("defender");
            defenderRollB2.setActionCommand("defender button2");
            defenderRollB2.addActionListener(new dicePanelController(this, playing));
            defenderDicePanel.add(defenderRollB2);
        }

        defenderDicePanel.setLayout(gridLayout);
        this.add(defenderDicePanel);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showRollsResult() {

        JPanel attackerRollsPanel = new JPanel();
        attackerRollsPanel.setBounds(20, 500, 150, 280);
        attackerRollsPanel.setBackground(Color.RED);
        GridLayout gridLayout = new GridLayout(3, 1);

        JPanel defenderRollsPanel = new JPanel();
        defenderRollsPanel.setBounds(180, 500, 150, 190);
        defenderRollsPanel.setBackground(Color.ORANGE);
        GridLayout gridLayout1 = new GridLayout(2, 1);


        JLabel[] AResultRolls = new JLabel[playing.getAttackerDice()];
        JLabel[] DResultRolls = new JLabel[playing.getDefenderDice()];
        ArrayList<Integer> attackerRolls = playing.getAttackerRolls();
        ArrayList<Integer> defenderRolls = playing.getDefenderRolls();

        for (int i = 0; i < attackerRolls.size() && i < defenderRolls.size(); i++) {
            AResultRolls[i] = new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + attackerRolls.get(i) + ".jpg"));
            DResultRolls[i] = new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + defenderRolls.get(i) + ".jpg"));
            if (attackerRolls.get(i) <= defenderRolls.get(i)) {
                AResultRolls[i].setBackground(Color.RED);
                DResultRolls[i].setBackground(Color.green);
            } else {
                AResultRolls[i].setBackground(Color.green);
                DResultRolls[i].setBackground(Color.RED);
            }

            attackerRollsPanel.add(AResultRolls[i]);
            defenderRollsPanel.add(DResultRolls[i]);
        }

        attackerRollsPanel.setLayout(gridLayout);
        defenderRollsPanel.setLayout(gridLayout1);
        this.add(attackerRollsPanel);
        this.add(defenderRollsPanel);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showLandsWithAttackAbility() {

        boardChecking = new GameBoardChecking(playing);
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


    public void showForeignNeighborsOfLand(int landId) {
        //////////////////////////
        ArrayList<Integer> neighborsId = boardChecking.getForeignNeighbors(landId);
        //////////////////////////
        for (int i = 0; i < neighborsId.size(); i++) {
            landButtonMap.get(neighborsId.get(i)).setBackground(Color.DARK_GRAY);
        }
    }

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


    public void updateGameMap(int landId) {
        LandButton targetButton = this.getLandButtonByID(landId);
        targetButton.setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");
    }

    public void updateNumberOfReadySPanel() {
        getLabel().setIcon(new ImageIcon(PlayersController.getCurrentPlayer().getIcon() + ".jpg"));
        getNumberOfReadySoldiers().setText("ready soldiers: " + PlayersController.getCurrentPlayer().getSoldiers());
    }

    public void showAttackerLand(int landId) {
        getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.BLACK, 8));
    }

    public void showDefenderLand(int landId) {
        getLandButtonByID(landId).setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
    }

    public void dicePanelsVisibility(boolean state) {
        attackerDicePanel.setVisible(state);
        defenderDicePanel.setVisible(state);

    }

    public void updateLandsAfterAttack(int landId) {
        landButtonMap.get(landId).setBorder(new CompoundBorder());
        landButtonMap.get(landId).setText(Map.getLandHashMap().get(landId).getNumberSoldiers() + "");
        landButtonMap.get(landId).setIcon(new ImageIcon(Map.getLandHashMap().get(landId).getConqueror().getIcon() + ".jpg"));
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
    private Playing playing;

    public Suggestion(Playing playing) {
        this.playing = playing;
        boardChecking = new GameBoardChecking(playing);

        setBounds(800, 300, 500, 282);
        setUndecorated(true);
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\DbackG.jpg")));
        setLayout(null);

        doneButton = new JButton("done");
        doneButton.setBounds(0, 235, 100, 40);
        doneButton.setFont(new Font("Algerian", Font.BOLD, 20));
        doneButton.setForeground(Color.BLACK);
        doneButton.setBackground(Color.DARK_GRAY);
        doneButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Suggestion.this.playing.setNumberOfSoldiersSent(numOfSoldiers.getValue());
            }
        });

        showSlider = new JLabel();
        showSlider.setBounds(240, 50, 200, 60);
        showSlider.setFont(new Font("Algerian", Font.BOLD, 50));
        showSlider.setForeground(Color.WHITE);

        numOfSoldiers = new JSlider(1, boardChecking.getMaxSoldierForFortify());
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

class ShowDice extends JDialog implements ActionListener {
    private JPanel attackerDicePanel;
    private JPanel defenderDicePanel;
    JButton attackerRollB1;
    JButton attackerRollB2;
    JButton attackerRollB3;
    JButton defenderRollB1;
    JButton defenderRollB2;

    Playing playing;
    BoardView boardView;
    JButton done;

    public ShowDice(Playing playing, BoardView boardView) {
        this.playing = playing;
        this.boardView = boardView;

        this.setBounds(30, 150, 300, 280);
        setUndecorated(true);
        this.setLayout(null);
        this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\DbackG.jpg")));
        this.addDoneButton();
        this.showAttackerDicePanel();
        this.showDefenderDicePanel();
        this.setVisible(true);
    }

    public void addDoneButton() {

        done = new JButton("done");
        done.setBounds(180, 225, 100, 50);
        done.setFont(new Font("Algerian", Font.BOLD, 20));
        done.addActionListener(this);
        done.setActionCommand("done button");
        this.add(done);
    }

    public void showAttackerDicePanel() {

        attackerDicePanel = new JPanel();
        attackerDicePanel.setBounds(10, 20, 90, 200);
        attackerDicePanel.setBackground(Color.DARK_GRAY);
        GridLayout gridLayout = new GridLayout(3, 1);

        attackerRollB1 = new JButton("attacker");
        attackerRollB1.setActionCommand("attacker button1");
        attackerRollB1.addActionListener(this);
        attackerDicePanel.add(attackerRollB1);

        if (playing.getAttackerDice() == 2 || playing.getAttackerDice() == 3) {
            attackerRollB2 = new JButton("attacker");
            attackerRollB2.setActionCommand("attacker button2");
            attackerRollB2.addActionListener(this);
            attackerDicePanel.add(attackerRollB2);
        }

        if (playing.getAttackerDice() == 3) {
            attackerRollB3 = new JButton("attacker");
            attackerRollB3.setActionCommand("attacker button3");
            attackerRollB3.addActionListener(this);
            attackerDicePanel.add(attackerRollB3);
        }

        attackerDicePanel.setLayout(gridLayout);
        this.add(attackerDicePanel);
    }


    public void showDefenderDicePanel() {

        defenderDicePanel = new JPanel();
        defenderDicePanel.setBounds(150, 20, 90, 160);
        defenderDicePanel.setBackground(Color.DARK_GRAY);
        GridLayout gridLayout = new GridLayout(2, 1);

        defenderRollB1 = new JButton("defender");
        defenderRollB1.setActionCommand("defender button1");
        defenderRollB1.addActionListener(this);
        defenderDicePanel.add(defenderRollB1);

        if (playing.getDefenderDice() == 2) {
            defenderRollB2 = new JButton("defender");
            defenderRollB2.setActionCommand("defender button2");
            defenderRollB2.addActionListener(this);
            defenderDicePanel.add(defenderRollB2);
        }

        defenderDicePanel.setLayout(gridLayout);
        this.add(defenderDicePanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.equalsIgnoreCase("attacker button1")) {
            this.attackerRollB1.setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(0) + ".jpg"));
        }
        if (actionCommand.equalsIgnoreCase("attacker button2")) {
            this.attackerRollB2.setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(1) + ".jpg"));
        }
        if (actionCommand.equalsIgnoreCase("attacker button3")) {
            this.attackerRollB3.setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getAttackerRolls().get(2) + ".jpg"));
        }

        if (actionCommand.equalsIgnoreCase("defender button1")) {
            this.defenderRollB1.setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getDefenderRolls().get(0) + ".jpg"));
        }

        if (actionCommand.equalsIgnoreCase("defender button2")) {
            this.defenderRollB2.setIcon(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\" + playing.getDefenderRolls().get(1) + ".jpg"));
        }

        if (actionCommand.equalsIgnoreCase("done button")) {
            this.dispose();
            boardView.showLandsWithAttackAbility();
            boardView.updateLandsAfterAttack(playing.getAttackerLandId());
            boardView.updateLandsAfterAttack(playing.getDefenderLandId());
            playing.finishTheAttack();
        }
    }
}

