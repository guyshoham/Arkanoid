package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class HighScoresTable.
 *
 * @author Guy Shoham
 */
public class HighScoresTable {

    //private static final String FILE_PATH = "highscores.txt";
    private int size;
    //private File filename;
    private ArrayList<ScoreInfo> highScores;

    /**
     * Class Constructor.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size size of table.
     */
    //
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<>();
    }

    // Add a high-score.
    public void add(ScoreInfo score) {
        if (highScores.size() < size) {
            highScores.add(score);
        } else {
            int rank = getRank(score.getScore());
            if (rank <= size) {
                highScores.set(size - 1, score);
                sortTable();
            }
        }
    }

    /**
     * @return table size.
     */
    public int size() {
        return size;
    }

    /**
     * @return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        sortTable();
        return this.highScores;
    }

    /**
     * @param score score
     * @return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     */
    public int getRank(int score) {
        sortTable();
        if (highScores.isEmpty()) {
            return 1;
        }
        for (int i = 0; i < highScores.size(); i++) {
            if (score > highScores.get(i).getScore()) {
                return i + 1;
            }
        }
        return size + 1;
    }

    /**
     * Clears the table
     */
    public void clear() {
        highScores.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        try {
            FileReader output = new FileReader(filename);
            int c = output.read();
            while (c != -1) {
                System.out.print(((char) c));
                if (c == ';') {
                    System.out.println();
                }
                c = output.read();
            }
            output.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        try {
            FileWriter output = new FileWriter(filename);
            for (int i = 0; i < size; i++) {
                output.write(highScores.get(i).getName() + ":" + highScores.get(i).getScore() + ";");
            }
            output.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        ArrayList<ScoreInfo> scores = new ArrayList<>();

        /*String s=""
        try {
            FileReader output = new FileReader(filename);
            int c = output.read();
            while (c != -1) {
                System.out.print(((char) c));
                if (c == ';') {
                    System.out.println();
                }
                c = output.read();
            }
            output.close();
        }*/
        return null;
    }

    /**
     * Sorting the high scores table, so the highest score will be first in list.
     */
    public void sortTable() {
        Collections.sort(highScores);
    }
}