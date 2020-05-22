import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskView extends JFrame {

    private JPanel menu;
    private JLabel background;
    private JButton playNow;
    private JButton exit;

    public RiskView() {

        setTitle("risk game");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\backG.jpg")));

        setMenuButton();

        setVisible(true);
    }

    public void setMenuButton() {

        playNow = new JButton();
        playNow.setBounds(850, 430, 183, 80);
        Icon playNowIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\start.jpg");
        playNow.setIcon(playNowIcon);
        playNow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerCount playerCount = new PlayerCount();
            }
        });

        exit = new JButton();
        exit.setBounds(1600, 900, 142, 60);
        Icon exitIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\exit.jpg");
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


    public PlayerCount() {

        setTitle("risk game");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\backG.jpg")));
        setPlayerCount();
        setVisible(true);
    }

    private void setPlayerCount() {

        playerCountController = new SetPlayerCountController(this);

        JLabel measage = new JLabel("select the number of players");
        measage.setBounds(500, 220, 900, 300);
        measage.setForeground(Color.WHITE);
        measage.setFont(new Font("Algerian", Font.BOLD, 50));

        twoPlayer = new JButton();
        twoPlayer.setBounds(570, 500, 115, 115);
        twoPlayer.setActionCommand("two player button");
        twoPlayer.addActionListener(playerCountController);
        ImageIcon twoPlayerIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\twoPlayer.png");
        twoPlayer.setIcon(twoPlayerIcon);

        threePlayer = new JButton();
        threePlayer.setBounds(870, 500, 115, 110);
        threePlayer.setActionCommand("three player button");
        threePlayer.addActionListener(playerCountController);
        ImageIcon threePlayerIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\threePlayer.png");
        threePlayer.setIcon(threePlayerIcon);

        fourPlayer = new JButton();
        fourPlayer.setBounds(1200, 500, 115, 115);
        fourPlayer.setActionCommand("four player button");
        fourPlayer.addActionListener(playerCountController);
        ImageIcon fourPlayerIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\fourPlayer.png");
        fourPlayer.setIcon(fourPlayerIcon);
/*
        nextButton = new JButton("next");
        nextButton.setBounds(870, 700, 114, 105);
        nextButton.setActionCommand("next button");
        nextButton.addActionListener(playerCountController);
        Icon nextIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\next.png");
        nextButton.setIcon(nextIcon);
*/
        backButton = new JButton("back");
        backButton.setBounds(100, 900, 114, 95);
        backButton.addActionListener(playerCountController);
        Icon backIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\back.png");
        backButton.setIcon(backIcon);
        backButton.setActionCommand("back button");

        this.add(measage);
        //  this.add(nextButton);
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

    public SettingPlayersName() {


        this.playerCount = PlayersController.getNumberOfPlayers();
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\backG.jpg")));
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
        nextButton.setBounds(1700, 900, 114, 95);
        nextButton.addActionListener(this);
        nextButton.setActionCommand("next button");
        Icon nextIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\next.png");
        nextButton.setIcon(nextIcon);
        this.add(nextButton);

        backButton = new JButton("back");
        backButton.setBounds(100, 900, 114, 95);
        backButton.setActionCommand("back button");
        Icon backIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\back.png");
        backButton.setIcon(backIcon);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equalsIgnoreCase("player one name")){
            playersName[0] = playerOneName.getText();
        }
        if (actionCommand.equalsIgnoreCase("player two name")){
            playersName[1] = playerTwoName.getText();
        }
        if (actionCommand.equalsIgnoreCase("player three name")){
            playersName[2] = playerThreeName.getText();
        }
        if (actionCommand.equalsIgnoreCase("player four name")){
            playersName[3] = playerFourName.getText();
        }
        if (actionCommand.equalsIgnoreCase("back button")){
            this.dispose();
        }
        if (actionCommand.equalsIgnoreCase("next button")){
            preparation = new Preparation();
            preparation.gameBoardPreparation(playersName);
        }
    }
}
