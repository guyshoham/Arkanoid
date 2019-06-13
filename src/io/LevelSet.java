package io;

public class LevelSet {

    private String key;
    private String message;
    private String filePath;

    public String getKey() {
        return this.key;
    }

    public String getMessage() {
        return this.message;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}