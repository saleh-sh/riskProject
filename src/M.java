public class M {
    public static void main(String[] args) {

        String [] names = {"saleh","soroush","parastou","hadi"};

        Preparation p = new Preparation();
        PlayersController playersController = new PlayersController();
        playersController.setNumberOfPlayers(4);
        playersController.createPlayers(names);
        PlayersController.findCurrentPlayer();
        System.out.println(PlayersController.getCurrentPlayer().getName());
        Map.createLands();
        Map.setSeas();
        p.divideLands();
        BoardView b = new BoardView();
        b.showTheDivisionOfSoldiers();
    }
}
