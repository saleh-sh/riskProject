import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskView extends JFrame {

    private JPanel menu;
    private JLabel background;
    private JButton playNow;
    private JButton exit;
    private IconsHandler iconsHandler = new IconsHandler();

    public RiskView() {

        setTitle("risk game");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new JLabel(iconsHandler.getBackGround()));
        setMenuButton();

        setVisible(true);
    }

    public void setMenuButton() {

        playNow = new JButton();
        playNow.setBounds(850, 430, 183, 80);
        Icon playNowIcon = iconsHandler.getStart();
        playNow.setIcon(playNowIcon);
        playNow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerCount playerCount = new PlayerCount();
                RiskView.this.dispose();
            }
        });

        exit = new JButton();
        exit.setBounds(1600, 900, 142, 60);
        Icon exitIcon = iconsHandler.getExit();
        exit.setIcon(exitIcon);
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(exit);
        this.add(playNow);
    }
}

class PlayerCount extends JFrame {

    private JButton backButton;
    private JButton twoPlayer;
    private JButton threePlayer;
    private JButton fourPlayer;

    private SetPlayerCountController playerCountController;
    private IconsHandler iconsHandler = new IconsHandler();

    public PlayerCount() {

        setTitle("risk game");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setContentPane(new JLabel(iconsHandler.getBackGround()));
        setPlayerCount();
        setVisible(true);
    }

    private void setPlayerCount() {

        playerCountController = new SetPlayerCountController(this);

        JLabel message = new JLabel("select the number of players");
        message.setBounds(500, 220, 900, 300);
        message.setForeground(Color.WHITE);
        message.setFont(new Font("Algerian", Font.BOLD, 50));

        twoPlayer = new JButton();
        twoPlayer.setBounds(570, 500, 115, 115);
        twoPlayer.setActionCommand("two player button");
        twoPlayer.addActionListener(playerCountController);
        ImageIcon twoPlayerIcon = iconsHandler.getTwoPlayers();
        twoPlayer.setIcon(twoPlayerIcon);

        threePlayer = new JButton();
        threePlayer.setBounds(870, 500, 115, 110);
        threePlayer.setActionCommand("three player button");
        threePlayer.addActionListener(playerCountController);
        ImageIcon threePlayerIcon = iconsHandler.getThreePlayers();
        threePlayer.setIcon(threePlayerIcon);

        fourPlayer = new JButton();
        fourPlayer.setBounds(1200, 500, 115, 115);
        fourPlayer.setActionCommand("four player button");
        fourPlayer.addActionListener(playerCountController);
        ImageIcon fourPlayerIcon = iconsHandler.getFourPlayers();
        fourPlayer.setIcon(fourPlayerIcon);

        backButton = new JButton("back");
        backButton.setBounds(100, 900, 120, 80);
        backButton.addActionListener(playerCountController);
        Icon backIcon = iconsHandler.getBack();
        backButton.setIcon(backIcon);
        backButton.setActionCommand("back button");

        this.add(message);
        this.add(twoPlayer);
        this.add(threePlayer);
        this.add(fourPlayer);
        this.add(backButton);
    }
}

class SettingPlayersName extends JFrame implements ActionListener {

    private int playerCount;

    private JTextField playerOneName;
    private JTextField playerTwoName;
    private JTextField playerThreeName;
    private JTextField playerFourName;

    private JButton nextButton;
    private JButton backButton;

    private String[] playersName;

    private Preparation preparation;
    private IconsHandler iconsHandler = new IconsHandler();

    public SettingPlayersName() {

        this.playerCount = PlayersController.getNumberOfPlayers();
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setContentPane(new JLabel(iconsHandler.getBackGround()));
        setPlayersName();
        setVisible(true);
    }

    public void setPlayersName() {

        playersName = new String[PlayersController.getNumberOfPlayers()];

        playerOneName = new JTextField("player one");
        playerOneName.setBounds(750, 400, 400, 80);
        playerOneName.addActionListener(this);
        playerOneName.setActionCommand("player one name");
        playerOneName.setFont(new Font("Algerian", Font.BOLD, 50));
        this.add(playerOneName);

        playerTwoName = new JTextField("player two");
        playerTwoName.setBounds(750, 500, 400, 80);
        playerTwoName.addActionListener(this);
        playerTwoName.setActionCommand("player two name");
        playerTwoName.setFont(new Font("Algerian", Font.BOLD, 50));
        this.add(playerTwoName);

        if (playerCount == 3 || playerCount == 4) {
            playerThreeName = new JTextField("player three");
            playerThreeName.setBounds(750, 600, 400, 80);
            playerThreeName.addActionListener(this);
            playerThreeName.setActionCommand("player three name");
            playerThreeName.setFont(new Font("Algerian", Font.BOLD, 50));
            this.add(playerThreeName);
        }

        if (playerCount == 4) {
            playerFourName = new JTextField("player four");
            playerFourName.setBounds(750, 700, 400, 80);
            playerFourName.addActionListener(this);
            playerFourName.setActionCommand("player four name");
            playerFourName.setFont(new Font("Algerian", Font.BOLD, 50));
            this.add(playerFourName);
        }

        nextButton = new JButton("next");
        nextButton.setBounds(1700, 900, 120, 80);
        nextButton.addActionListener(this);
        nextButton.setActionCommand("next button");
        Icon nextIcon = iconsHandler.getNext();
        nextButton.setIcon(nextIcon);
        this.add(nextButton);

        backButton = new JButton("back");
        backButton.setBounds(100, 900, 120, 80);
        backButton.setActionCommand("back button");
        Icon backIcon = iconsHandler.getBack();
        backButton.setIcon(backIcon);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equalsIgnoreCase("player one name")) {
            playersName[0] = playerOneName.getText();
        }
        if (actionCommand.equalsIgnoreCase("player two name")) {
            playersName[1] = playerTwoName.getText();
        }
        if (actionCommand.equalsIgnoreCase("player three name")) {
            playersName[2] = playerThreeName.getText();
        }
        if (actionCommand.equalsIgnoreCase("player four name")) {
            playersName[3] = playerFourName.getText();
        }

        if (actionCommand.equalsIgnoreCase("back button")) {
            this.dispose();
            PlayerCount playerCount = new PlayerCount();
        }


        if (actionCommand.equalsIgnoreCase("next button")) {
            preparation = new Preparation();
            preparation.gameBoardPreparation(playersName);
            this.dispose();
        }
    }
}