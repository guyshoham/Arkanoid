package backend;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.YouLoseScreen;
import animation.YouWinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * class GameFlow.
 *
 * @author Guy Shoham
 */
public class GameFlow {

    private Counter score, lives;
    private GUI gui = new GUI(GAME_TITLE, GUI_WIDTH, GUI_HEIGHT);
    private AnimationRunner runner;
    private KeyboardSensor keyboard;


    private static final String GAME_TITLE = "Arkanoid";
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private static final int LIVES = 7;

    /**
     * Class Constructor.
     */
    public GameFlow() {
        this.score = new Counter();
        this.lives = new Counter(LIVES);
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * run the levels one by one, until user is winning or losing the whole game.
     *
     * @param levels levels
     */
    public void runLevels(List<LevelInformation> levels) {
        //run levels one by one
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.runner, score, lives);
            level.initialize();
            level.run();
        }
        //check if user won or lost, and show proper screen.
        if (lives.getValue() != 0) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new YouWinScreen(score.getValue())));
        } else {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new YouLoseScreen(score.getValue())));
        }
        gui.close();
    }
}