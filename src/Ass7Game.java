import backend.GameFlow;
import backend.LevelInformation;
import io.LevelSpecificationReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        runGame(args);
    }

    /**
     * this method runs a game of Arkanoid.
     *
     * @param args list of levels
     * @throws IOException exception
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
