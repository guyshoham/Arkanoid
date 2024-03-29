package animation;

import biuoop.DrawSurface;
import geometry.Rectangle;
import io.HighScoresTable;
import io.ScoreInfo;

import java.awt.Color;
import java.util.List;

/**
 * class HighScoresAnimation.
 *
 * @author Guy Shoham
 */
public class HighScoresAnimation implements Animation {

    private static int count = 0;
    private HighScoresTable scores;


    /**
     * Class Constructor.
     *
     * @param scores scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        List<ScoreInfo> scoreInfos = scores.getHighScores();

        Rectangle background = new Rectangle(800, 600);
        background.setColor(Color.BLACK);
        background.drawOn(d);

        d.setColor(Color.ORANGE);
        d.drawText(300, 70, "High Scores:", 40);
        d.drawText(301, 71, "High Scores:", 40);
        for (int i = 0; i < 5; i++) {
            d.drawLine(0, 80 + i, 800, 80 + i);
        }

        int gap = 30;
        int tableStart = 200;
        int y;
        if (scores.getHighScores().size() != 0) {
            for (int i = 0; i < scores.getHighScores().size(); i++) {
                y = tableStart + i * gap;
                d.drawText(10, y, (i + 1) + ". " + scoreInfos.get(i).getName(), 20);
                d.drawText(400, y, String.valueOf(scoreInfos.get(i).getScore()), 20);
                d.drawLine(0, y + 5, 800, y + 5);
            }
        }

        if (count / 50 % 2 == 0) {
            d.drawText(250, 500, "Press Space to Continue", 30);
        }
        count++;
        //avoid reaching MAX_INT_VALUE
        if (count > 1000) {
            count = 0;
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
