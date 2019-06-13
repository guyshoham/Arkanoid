import backend.GameFlow;

import java.io.IOException;

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
        gameFlow.showMenu();
    }
}
