package io;

/**
 * class ScoreInfo.
 *
 * @author Guy Shoham
 */
public class ScoreInfo implements Comparable<ScoreInfo> {
    private String name;
    private int score;

    /**
     * Class constructor.
     *
     * @param name  name
     * @param score score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return score.
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(ScoreInfo other) {
        return other.score - this.score;
    }
}