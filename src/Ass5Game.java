import backend.GameFlow;
import levels.*;

import java.util.ArrayList;
import java.util.List;

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
        GameFlow gameFlow = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        //levels.add(new LevelDirectHit());
        //levels.add(new LevelWideEasy());
        //levels.add(new LevelGreenThree());
        levels.add(new LevelFinalFour());
        gameFlow.runLevels(levels);
    }
}
