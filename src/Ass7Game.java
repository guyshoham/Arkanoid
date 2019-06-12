import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import backend.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import io.HighScoresTable;
import io.LevelSpecificationReader;
import io.ScoreInfo;
import backend.LevelInformation;

import java.io.*;
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
        //test();
        runGame(args);
    }

    private static void test() throws IOException {
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        Reader reader = new FileReader(new File("resources/definitions/level_definitions.txt"));
        levelSpecificationReader.fromReader(reader);
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

        File fileLevel = new File("resources/definitions/level_definitions.txt");
        Reader readerLevel = new BufferedReader(new FileReader(fileLevel));

        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        List<LevelInformation> levels = levelSpecificationReader.fromReader(readerLevel);
        gameFlow.showMenu(levels);
    }
}
