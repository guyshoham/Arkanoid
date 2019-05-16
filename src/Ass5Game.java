import backend.GameLevel;

/**
 * class ass3Game.
 *
 * @author Guy Shoham
 */
public class Ass5Game {

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
        GameLevel game = new GameLevel();
        game.initialize();
        game.run();
    }

}
