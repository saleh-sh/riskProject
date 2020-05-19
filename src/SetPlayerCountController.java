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
            playersName = new SettingPlayersName();
        }
        if (actionCommand.equalsIgnoreCase("three player button")) {
            PlayersController.setNumberOfPlayers(3);
            playersName = new SettingPlayersName();
        }
        if (actionCommand.equalsIgnoreCase("four player button")) {
            PlayersController.setNumberOfPlayers(4);
            playersName = new SettingPlayersName();
        }
        if (actionCommand.equalsIgnoreCase("back button")) {
            playerCount.dispose();
        }

    }
}


