import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPlayerCountController implements ActionListener {

    private PlayerCount playerCount;
    private SettingPlayersName playersName;

    public SetPlayerCountController(PlayerCount playerCount) {
        this.playerCount = playerCount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        if (actionCommand.equalsIgnoreCase("two player button")) {
            PlayersController.setNumberOfPlayers(2);
        }
        if (actionCommand.equalsIgnoreCase("three player button")) {
            PlayersController.setNumberOfPlayers(3);
        }
        if (actionCommand.equalsIgnoreCase("four player button")) {
            PlayersController.setNumberOfPlayers(4);
        }
        if (actionCommand.equalsIgnoreCase("back button")) {
            playerCount.dispose();
        }
        playersName = new SettingPlayersName();
    }
}

class SettingPlayersNameController implements ActionListener{

    private SettingPlayersName playersName;

    public SettingPlayersNameController(SettingPlayersName playersName) {
        this.playersName = playersName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equalsIgnoreCase("back button")){
            playersName.dispose();
        }
        if (actionCommand.equalsIgnoreCase("next button")){

            String[] names = new String[PlayersController.getNumberOfPlayers()];

        }
    }
}
