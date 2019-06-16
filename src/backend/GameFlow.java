package backend;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.Menu;
import animation.MenuAnimation;
import animation.YouLoseScreen;
import animation.YouWinScreen;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import io.HighScoresTable;
import io.LevelSet;
import io.LevelSetsList;
import io.LevelSpecificationReader;
import io.ScoreInfo;
import tasks.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * class GameFlow.
 *
 * @author Guy Shoham
 */
public class GameFlow {

    private static final String GAME_TITLE = "Arkanoid";
    private static final String HIGH_SCORE_FILE_PATH = "highscore.txt";
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int LIVES = 7;
    private static final int TABLE_SIZE = 5;
    private GUI gui = new GUI(GAME_TITLE, GUI_WIDTH, GUI_HEIGHT);
    private Counter score, lives;
    private DialogManager dialog;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private HighScoresTable highScoresTable;
    private File file = new File(HIGH_SCORE_FILE_PATH);

    /**
     * Class Constructor.
     *
     * @throws IOException exception
     */
    public GameFlow() throws IOException {
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();
        this.dialog = gui.getDialogManager();
        handleScoresTable();
    }

    /**
     * if table exist, load the table. if not, create one.
     *
     * @throws IOException exception
     */
    private void handleScoresTable() throws IOException {
        if (!file.exists()) {
            //no file. create one
            highScoresTable = new HighScoresTable(TABLE_SIZE);
            highScoresTable.save(file);
        } else {
            //load file
            highScoresTable = HighScoresTable.loadFromFile(file);
        }
    }

    /**
     * run the levels one by one, until user is winning or losing the whole game.
     *
     * @param levels levels
     * @throws IOException exception
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        score = new Counter();
        lives = new Counter(LIVES);

        //run levels one by one
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboard, runner, score, lives);
            level.initialize();
            level.run();
        }
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        System.out.println(name);
        highScoresTable.add(new ScoreInfo(name, score.getValue()));
        highScoresTable.save(file);
        //check if user won or lost, and show proper screen.
        if (lives.getValue() != 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new YouWinScreen(score.getValue())));
        } else {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new YouLoseScreen(score.getValue())));
        }
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(highScoresTable)));
    }

    /**
     * display menu to user.
     *
     * @param filePath file path
     */
    public void showMenu(String filePath) {
        LevelSetsList levelSetsList;
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath);
            levelSetsList = LevelSetsList.fromReader(new InputStreamReader(stream));
        } catch (IOException ex) {
            throw new RuntimeException("Failed loading level sets");
        }

        Menu<Task<Void>> levelsMenu = new MenuAnimation<>("Pick Difficulty", runner, keyboard);
        List<LevelSet> list = levelSetsList.getLevelSetList();
        for (LevelSet set : list) {
            levelsMenu.addSelection(set.getKey(), set.getMessage(), new Task<Void>() {
                @Override
                public Void run() {
                    try {
                        File fileLevel = new File(set.getFilePath());
                        Reader readerLevel = new BufferedReader(new FileReader(fileLevel));

                        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                        List<LevelInformation> levels = levelSpecificationReader.fromReader(readerLevel);

                        runLevels(levels);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        return null;
                    }
                }
            });
        }

        Menu<Task<Void>> mainMenu = new MenuAnimation<>(GAME_TITLE, runner, keyboard);
        mainMenu.addSubMenu("s", "New Game", levelsMenu);
        mainMenu.addSelection("h", "High Scores Table", new Task<Void>() {
            @Override
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(highScoresTable)));
                return null;
            }
        });
        mainMenu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });

        while (true) {
            runner.run(mainMenu);
            // wait for user selection
            Task<Void> task = mainMenu.getStatus();
            task.run();
            mainMenu.resetStatus();
        }
    }
}