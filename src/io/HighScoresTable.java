package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class HighScoresTable.
 *
 * @author Guy Shoham
 */
public class HighScoresTable implements Serializable {

    //private static final String FILE_PATH = "highscores.txt";
    //private File filename;
    private ArrayList<ScoreInfo> highScores;
    private int size;

    /**
     * Class Constructor.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size size of table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<>();
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename filename
     * @return high scores table
     * @throws IOException exception
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        //todo: use load() and than return retVal
        HighScoresTable retVal = new HighScoresTable(5);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                int delimiter = line.indexOf(":");
                String name = line.substring(0, delimiter);
                int score = Integer.parseInt(line.substring(delimiter + 1));
                retVal.add(new ScoreInfo(name, score));
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException(ex.toString());
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return retVal;
    }

    /**
     * Add a high-score.
     *
     * @param score score
     */
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
     * Clears the table.
     */
    public void clear() {
        highScores.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename filename
     * @throws IOException exception
     */
    public void load(File filename) throws IOException {
        clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                int delimiter = line.indexOf(":");
                String name = line.substring(0, delimiter);
                int score = Integer.parseInt(line.substring(delimiter + 1));
                highScores.add(new ScoreInfo(name, score));
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException(ex.toString());
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename filename
     * @throws IOException exception
     */
    public void save(File filename) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(filename);
            if (!highScores.isEmpty()) {
                for (int i = 0; i < highScores.size(); i++) {
                    writer.write(highScores.get(i).getName() + ":" + highScores.get(i).getScore() + "\n");
                }
            }
            writer.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    /**
     * Sorting the high scores table, so the highest score will be first in list.
     */
    public void sortTable() {
        Collections.sort(highScores);
    }
}