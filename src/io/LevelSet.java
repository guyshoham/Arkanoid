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

    public void setKey(String newKey) {
        this.key = newKey;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public void setFilePath(String path) {
        this.filePath = path;
    }
}