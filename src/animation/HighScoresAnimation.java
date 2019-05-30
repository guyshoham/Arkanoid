package animation;

import biuoop.DrawSurface;
import io.HighScoresTable;
import io.ScoreInfo;

import java.util.List;

/**
 * class HighScoresAnimation.
 *
 * @author Guy Shoham
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;

    /**
     * Class Constructor.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        List<ScoreInfo> scoreInfos = scores.getHighScores();

        d.drawText(10, 70, "High Scores:", 20);

        for (int i = 0; i < scores.size(); i++) {
            d.drawText(10, 100 + i * 20, (i + 1) + ". " +
                    scoreInfos.get(i).getName() + ":" + scoreInfos.get(i).getScore(), 20);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
