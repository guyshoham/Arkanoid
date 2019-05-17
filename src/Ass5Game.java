import backend.GameLevel;
import levels.LevelDirectHit;
import levels.LevelFinalFour;
import levels.LevelGreenThree;
import levels.LevelWideEasy;

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
        //GameLevel game = new GameLevel(new LevelDirectHit());
        GameLevel game = new GameLevel(new LevelWideEasy());
        //GameLevel game = new GameLevel(new LevelGreenThree());
        //GameLevel game = new GameLevel(new LevelFinalFour());
        game.initialize();
        game.run();
    }

}
