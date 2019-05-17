package backend;

import biuoop.GUI;
import levels.LevelInformation;

import java.util.List;

public class GameFlow {

    private Counter score, lives;
    private GUI gui = new GUI("Game Run", GUI_WIDTH, GUI_HEIGHT);

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    public GameFlow() {
        this.score = new Counter();
        this.lives = new Counter(7);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, score, lives, gui);
            level.initialize();
            level.run();
        }
        gui.close();
    }
}