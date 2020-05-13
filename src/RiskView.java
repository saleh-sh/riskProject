import javax.swing.*;
import java.awt.*;

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
        ///با بستن این فریم برنامه متوقف می شود
//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(menu());
        setVisible(true);
    }


    public JPanel menu() {

        menu = new JPanel();

        background = new JLabel();
        Icon backgroundIMG = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\Logo.jpg");
        background.setIcon(backgroundIMG);

        playNow = new JButton();
        playNow.setBounds(840, 500, 140, 130);
        Icon playNowIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\playNow.png");
        playNow.setIcon(playNowIcon);
        //************************
        //playNow.setLayout(null);
        //************************
        playNow.setVisible(true);

        exit = new JButton();
        exit.setBounds(1600, 900, 300, 140);
        Icon exitIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\exit.png");
        exit.setIcon(exitIcon);
        //************************
        //exit.setLayout(null);
        //************************
        exit.setVisible(true);

        background.add(exit);
        background.add(playNow);
        menu.add(background);

        return menu;
    }
}

//**********************************************************************************************************************

class PlayerCount extends JFrame {

    private JPanel PCoountPanel;
    private JComboBox playerCount;
    private JLabel background;

    public PlayerCount() {

        setTitle("risk game");
        setExtendedState(MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        // use for chage the location of a panel****************
        // setLayout(new FlowLayout(1000,900,480));

        add(registering());//,BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel registering() {

        PCoountPanel = new JPanel();


        // registering.setPreferredSize(new Dimension(500,500));
        // registering.setLocation(2000,100);
        PCoountPanel.setBackground(Color.BLACK);
        background = new JLabel();
        Icon backgroundIMG = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\Logo.jpg");
        background.setIcon(backgroundIMG);


        Integer[] numberOfPlayers = {2, 3, 4};
        playerCount = new JComboBox(numberOfPlayers);
        //for FlowLaout in frame
        //for chage the size of a comboBox*********************
        // playerCount.setPreferredSize(new Dimension(100,30));
        playerCount.setBounds(950, 400, 100, 40);
        playerCount.setVisible(true);

        JLabel measage = new JLabel("select the number of players");
        measage.setBounds(730, 220, 600, 300);
        measage.setFont(new Font("Calibri", Font.BOLD, 50));
        measage.setVisible(true);

        JButton nextButton = new JButton("next");
        nextButton.setBounds(950, 500, 105, 100);
        Icon nextIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\next.png");
        nextButton.setIcon(nextIcon);
        nextButton.setVisible(true);

        JButton backButton = new JButton("back");
        backButton.setBounds(100, 900, 105, 120);
        Icon backIcon = new ImageIcon("C:\\Users\\Soroushiravany\\Desktop\\back.png");
        backButton.setIcon(backIcon);
        backButton.setVisible(true);


        background.add(playerCount);
        background.add(measage);
        background.add(nextButton);
        background.add(backButton);
        PCoountPanel.add(background);
        // registering.add(playerCount);
        return PCoountPanel;
    }
}

class Registering {
    private int playerCount;
    String[] playersName;

    public Registering() {

        this.playerCount = PlayersController.getNumberOfPlayers();
        playersName = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            playersName[i] = JOptionPane.showInputDialog("Enter the name of player " + (i + 1) + ":");
            System.out.println(playersName[i]);
        }
    }
}






