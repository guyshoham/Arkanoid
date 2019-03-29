public class Ass3Game {

    public static void main(String[] args) {
        runGame();
    }


    public static void runGame() {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
