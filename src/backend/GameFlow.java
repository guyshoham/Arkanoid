package backend;

import animation.AnimationRunner;
import animation.EndScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

public class GameFlow {

    private Counter score, lives;
    private GUI gui = new GUI("Game Run", GUI_WIDTH, GUI_HEIGHT);
    private AnimationRunner runner;
    private KeyboardSensor keyboard;


    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    public GameFlow() {
        this.score = new Counter();
        this.lives = new Counter(7);
        this.runner = new AnimationRunner(gui);
        this.keyboard = gui.getKeyboardSensor();

    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.runner, score, lives, gui);
            level.initialize();
            level.run();
        }
        boolean isWon;
        isWon = lives.getValue() != 0;
        this.runner.run(new EndScreen(this.keyboard, isWon, score.getValue()));

        gui.close();
    }
}