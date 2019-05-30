import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import backend.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import io.HighScoresTable;
import io.ScoreInfo;
import levels.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class ass7Game.
 *
 * @author Guy Shoham
 */
public class Ass7Game {

    private static final String FILE_PATH = "test.txt";
    private static final int TABLE_SIZE = 5;

    /**
     * this main method calls runGame method.
     *
     * @param args list of levels
     */
    public static void main(String[] args) throws IOException {
        //ioTest();
        runGame(args);
    }

    private static void ioTest() throws IOException {
        File file = new File(FILE_PATH);
        HighScoresTable highScores = new HighScoresTable(TABLE_SIZE);
        highScores.add(new ScoreInfo("Dor", 200));
        highScores.add(new ScoreInfo("Guy", 100));
        highScores.add(new ScoreInfo("David", 400));
        highScores.add(new ScoreInfo("Sarit", 500));
        highScores.add(new ScoreInfo("Ben", 300));
        highScores.add(new ScoreInfo("Best", 3000));
        highScores.sortTable();
        highScores.save(file);


        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(HighScoresTable.loadFromFile(file))));
        gui.close();
    }

    /**
     * this method runs a game of Arkanoid.
     *
     * @param args list of levels
     */
    public static void runGame(String[] args) throws IOException {
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
        if (levels.isEmpty()) {
            levels.add(new LevelDirectHit());
            levels.add(new LevelWideEasy());
            levels.add(new LevelGreenThree());
            levels.add(new LevelFinalFour());
        }
        gameFlow.runLevels(levels);
    }
}