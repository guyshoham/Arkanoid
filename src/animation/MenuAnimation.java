package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

public class MenuAnimation<T> implements Menu<T> {

    private T status;
    private boolean done;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private String title;
    private List<T> menuRetVals;
    private List<String> menuNames;
    private List<String> menuKeys;


    public MenuAnimation(String title, AnimationRunner runner, KeyboardSensor keyboard) {
        this.title = title;
        this.runner = runner;
        this.keyboard = keyboard;
        this.menuKeys = new ArrayList();
        this.menuNames = new ArrayList();
        this.menuRetVals = new ArrayList();
        this.resetStatus();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        menuKeys.add(key);
        menuNames.add(message);
        menuRetVals.add(returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(300, 70, title, 40);
        d.drawText(301, 71, title, 40);
        for (int i = 0; i < 5; i++) {
            d.drawLine(0, 80 + i, 800, 80 + i);
        }

        int gap = 50;
        int tableStart = 200;
        int y;

        for (int i = 0; i < menuNames.size(); i++) {
            y = tableStart + i * gap;
            d.drawText(30, y, "(" + menuKeys.get(i) + ") " + menuNames.get(i), 30);
        }

        for (int i = 0; i < menuRetVals.size(); i++) {
            if (keyboard.isPressed(menuKeys.get(i))) {
                this.status = menuRetVals.get(i);
                this.done = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.status != null;
    }

    public void showBackground(DrawSurface d, Sprite background) {
        background.drawOn(d);
    }

    @Override
    public void resetStatus() {
        this.status = null;
        this.done = false;
    }
}
