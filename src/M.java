public class M {
    public static void main(String[] args) {

        String [] names = {"saleh","soroush"};

        Preparation p = new Preparation();
        PlayersController playersController = new PlayersController();
        playersController.setNumberOfPlayers(2);
        playersController.createPlayers(names);
        PlayersController.findCurrentPlayer();
        Map.createLands();
        Map.setSeas();
        p.divideLands();
        BoardView b = new BoardView();
        b.showTheDivisionOfSoldiers();
    }
}
