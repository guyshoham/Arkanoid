import backend.GameFlow;
import levels.LevelDirectHit;
import levels.LevelFinalFour;
import levels.LevelGreenThree;
import levels.LevelInformation;
import levels.LevelWideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * class ass3Game.
 *
 * @author Guy Shoham
 */
public class Ass6Game {

    /**
     * this main method calls runGame method.
     *
     * @param args list of levels
     */
    public static void main(String[] args) {
        runGame(args);
    }

    /**
     * this method runs a game of Arkanoid.
     * @param args list of levels
     */
    public static void runGame(String[] args) {
        GameFlow gameFlow = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        for (String str : args) {
            switch (str) {
                case "1":
                    levels.add(new LevelDirectHit());
                    break;
                case "2":
                    levels.add(new LevelWideEasy());
                    break;
                case "3":
                    levels.add(new LevelGreenThree());
                    break;
                case "4":
                    levels.add(new LevelFinalFour());
                    break;
                default:
                    break;
            }
        }
        gameFlow.runLevels(levels);
    }
}
