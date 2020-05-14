import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BoardView extends JFrame {

    //GameMapController gameMapController = new GameMapController(new Playing(),this,new GamePhase());

    private LandButton[][] landButtons;
    private HashMap<Integer, LandButton> landButtonMap;

    JPanel gameMap;


    private JPanel stageOfGamePanel;
    private JLabel reinforceLabel;
    private JLabel attackLabel;
    private JLabel fortifyLabel;
    private JButton nextButton;


    private JPanel numberOfSoldiersPanel;
    private JLabel numberOfReadySoldiers;
    private JLabel label;
    private ImageIcon currentPlayerIcon;


    public BoardView() {

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(false);
        this.setLayout(null);

        this.add(gameMap());
        this.add(showStageOfGamePanel());
        this.add(showNumberOfReadySoldiers());
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
                //landButtons[i][j].addActionListener(gameMapController);
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
        // nextButton.addActionListener();


        stageOfGamePanel.add(reinforceLabel);
        stageOfGamePanel.add(attackLabel);
        stageOfGamePanel.add(fortifyLabel);
        stageOfGamePanel.add(nextButton);

        stageOfGamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        return stageOfGamePanel;
    }


    public JPanel showNumberOfReadySoldiers() {


        numberOfSoldiersPanel = new JPanel();
        numberOfSoldiersPanel.setBounds(1500, 750, 220, 100);
        numberOfSoldiersPanel.setBackground(Color.BLACK);

        numberOfReadySoldiers = new JLabel();
        numberOfReadySoldiers.setBounds(30, 90, 140, 140);
        numberOfReadySoldiers.setText("ready soldiers:" + PlayersController.getCurrentPlayer().getSoldiers());
        System.out.println("number of soldiers set for label at int place");
        numberOfReadySoldiers.setFont(new Font("Algerian", Font.BOLD, 20));

        label = new JLabel();
        String iconAddress = "" + PlayersController.getCurrentPlayer().getIcon();
        currentPlayerIcon = new ImageIcon(iconAddress + ".jpg");
        label.setIcon(currentPlayerIcon);


        numberOfSoldiersPanel.add(label);
        numberOfSoldiersPanel.add(numberOfReadySoldiers);


/*
        takingSoldierButton = new JButton("ready soldier: ");
        takingSoldierButton.setActionCommand("getting soldier");
        takingSoldierButton.setFont(new Font("Algerian", Font.BOLD, 15));
        takingSoldierButton.setVerticalTextPosition(SwingConstants.CENTER);
        takingSoldierButton.setSize(140, 140);
        // takingSoldierButton.addActionListener(new PutTheBeadController());

        undoButton = new JButton("undo");
        undoButton.setActionCommand("undo");
        undoButton.setFont(new Font("Algerian", Font.BOLD, 15));
        undoButton.setVerticalTextPosition(SwingConstants.CENTER);
        undoButton.setSize(200, 140);
        //undoButton.addActionListener();

        numberOfSoldiersPanel.add(takingSoldierButton);
        numberOfSoldiersPanel.add(undoButton);


        numberOfSoldiersPanel.setLayout(new BoxLayout(numberOfSoldiersPanel, BoxLayout.Y_AXIS));
        */

        return numberOfSoldiersPanel;
    }

  /*  public JPanel showDicePanel() {

        JPanel dicePanel = new JPanel();
    }*/

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



