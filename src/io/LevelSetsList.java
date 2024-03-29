package io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class LevelSetsList.
 *
 * @author Guy Shoham
 */
public class LevelSetsList {
    private List<LevelSet> levelSetList;

    /**
     * Class constructor.
     */
    public LevelSetsList() {
        this.levelSetList = new ArrayList<>();
    }

    /**
     * @param reader reader
     * @return List<LevelSet> contains level set from the file
     * @throws IOException exception
     */
    public static LevelSetsList fromReader(Reader reader) throws IOException {
        LevelSetsList retVal = new LevelSetsList();
        LevelSet currentSet = null;
        LineNumberReader lineReader = null;

        try {
            lineReader = new LineNumberReader(reader);
            String line;

            while ((line = lineReader.readLine()) != null) {
                if (lineReader.getLineNumber() % 2 != 0) {
                    currentSet = new LevelSet();
                    String[] split = line.split(":");
                    currentSet.setKey(split[0]);
                    currentSet.setMessage(split[1]);
                } else {
                    currentSet.setFilePath(line.trim());
                    retVal.addLevelSet(currentSet);
                    currentSet = null;
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (lineReader != null) {
                lineReader.close();
            }
        }
        return retVal;
    }

    /**
     * add level set to the list.
     *
     * @param levelSet level set
     */
    public void addLevelSet(LevelSet levelSet) {
        levelSetList.add(levelSet);
    }

    /**
     * @return levelSet list
     */
    public List<LevelSet> getLevelSetList() {
        return levelSetList;
    }
}
