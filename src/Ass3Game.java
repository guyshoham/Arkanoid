import game.Game;

/**
 * class ass3Game.
 *
 * @author Guy Shoham
 */
public class Ass3Game {

    /**
     * this main method calls runGame method.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        runGame();
    }

    /**
     * this method runs a game of Arkanoid.
     */
    public static void runGame() {
        Game game = new Game();
        game.initialize();
        game.run();
    }

}
