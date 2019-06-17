package io;

/**
 * Class LevelSet.
 *
 * @author Guy Shoham
 */
public class LevelSet {

    private String key;
    private String message;
    private String filePath;

    /**
     * @return key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param newKey key
     */
    public void setKey(String newKey) {
        this.key = newKey;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param msg message
     */
    public void setMessage(String msg) {
        this.message = msg;
    }

    /**
     * @return file path
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * @param path file path
     */
    public void setFilePath(String path) {
        this.filePath = path;
    }
}