package backend;

import animation.*;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import io.HighScoresTable;
import io.ScoreInfo;
import levels.LevelInformation;
import tasks.ExitTask;
import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * class GameFlow.
 *
 * @author Guy Shoham
 */
public class GameFlow {

    private static final String GAME_TITLE = "Arkanoid";
    private static final String FILE_PATH = "test.txt";
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
    private File file = new File(FILE_PATH);

    /**
     * Class Constructor.
     */
    public GameFlow() throws IOException {
        this.score = new Counter();
        this.lives = new Counter(LIVES);
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();
        this.dialog = gui.getDialogManager();
        handleScoresTable();
    }

    private void handleScoresTable() throws IOException {
        if (!file.exists()) {
            //no file. create one
            this.highScoresTable = new HighScoresTable(TABLE_SIZE);
            highScoresTable.save(file);
        } else {
            //load file
            this.highScoresTable = HighScoresTable.loadFromFile(file);
        }
    }

    /**
     * run the levels one by one, until user is winning or losing the whole game.
     *
     * @param levels levels
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        //run levels one by one
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.runner, score, lives);
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
        gui.close();
    }

    public void showMenu() {
        Menu<Task<Void>> menu = new MenuAnimation<>(GAME_TITLE, runner, keyboard);
        //menu.addSelection("s", "new game", "option a");
        menu.addSelection("h", "High Scores Table", new Task<Void>() {
            @Override
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(highScoresTable)));
                return null;
            }
        });
        menu.addSelection("q", "Quit", new ExitTask());
        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            menu.resetStatus();
        }
    }
}