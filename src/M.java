public class M {
    public static void main(String[] args) {

        PlayersController playersController = new PlayersController();
        playersController.setNumberOfPlayers(2);
        String [] names = {"saleh","soroush"};
        playersController.createPlayers(names);
        Map.createLands();
        Map.setSeas();
        PlayersController.findCurrentPlayer();

        Preparation p = new Preparation();
        p.divideLands();

        BoardView b = new BoardView();
        b.showTheDivisionOfSoldiers();
    }
}
