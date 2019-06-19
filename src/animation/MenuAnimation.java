package animation;

import backend.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * interface MenuAnimation.
 *
 * @param <T> general type
 * @author Guy Shoham
 */
public class MenuAnimation<T> implements Menu<T> {

    private static int titlePosX = 300;
    private static int ballPosX = 625;
    private static int ballPosY = 400;
    private static int ballSpeed = 1;
    private static int randomBound = 20;
    private static boolean moveRight = true, ballDown = true;
    private static Color titleColor;
    private T status;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private String title;
    private List<T> menuRetVals;
    private List<String> menuNames;
    private List<String> menuKeys;
    private List<Color> menuColors;
    private List<Boolean> isSub;
    private List<Menu> subMenus;
    private boolean doingLevels = false;
    private Random rnd = new Random();


    /**
     * Class constructor.
     *
     * @param title    title
     * @param runner   animation runner
     * @param keyboard keyboard
     */
    public MenuAnimation(String title, AnimationRunner runner, KeyboardSensor keyboard) {
        this.title = title;
        this.runner = runner;
        this.keyboard = keyboard;
        this.menuKeys = new ArrayList();
        this.menuNames = new ArrayList();
        this.menuRetVals = new ArrayList();
        this.menuColors = new ArrayList();
        this.isSub = new ArrayList();
        this.subMenus = new ArrayList();
        this.resetStatus();
        changeTitleColor();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        menuKeys.add(key);
        menuNames.add(message);
        menuRetVals.add(returnVal);
        isSub.add(false);
        subMenus.add(null);
        menuColors.add(Color.WHITE);
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        menuKeys.add(key);
        menuNames.add(message);
        menuRetVals.add(null);
        isSub.add(true);
        subMenus.add(subMenu);
        menuColors.add(Color.WHITE);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Rectangle background = new Rectangle(800, 600);
        background.setColor(Color.BLACK);
        background.drawOn(d);
        //title animation
        if (moveRight) {
            if (titlePosX > 500) {
                moveRight = false;
                changeTitleColor();
            }
            d.setColor(titleColor);
            d.drawText(titlePosX, 70, title, 40);
            titlePosX++;

        } else {
            if (titlePosX < 100) {
                moveRight = true;
                changeTitleColor();
            }
            d.setColor(titleColor);
            d.drawText(titlePosX, 70, title, 40);
            titlePosX--;
        }

        for (int i = 0; i < 5; i++) {
            d.setColor(Color.WHITE);
            d.drawLine(0, 80 + i, 800, 80 + i);
        }

        //menu
        int gap = 50;
        int tableStart = 200;
        int y;

        for (int i = 0; i < menuNames.size(); i++) {
            if (rnd.nextInt(randomBound) == 7) {
                int r = rnd.nextInt(255);
                int g = rnd.nextInt(255);
                int b = rnd.nextInt(255);
                menuColors.set(i, new Color(r, g, b));
            }
            d.setColor(menuColors.get(i));
            y = tableStart + i * gap;
            d.drawText(30, y, "(" + menuKeys.get(i) + ") " + menuNames.get(i), 30);
        }


        for (int i = 0; i < menuRetVals.size(); i++) {
            if (keyboard.isPressed(menuKeys.get(i))) {
                if (!isSub.get(i)) {
                    if (!doingLevels) {
                        //key is not belong to sub
                        status = menuRetVals.get(i);
                    }
                } else {
                    //is belong to sub
                    doingLevels = true;
                    Menu sub = subMenus.get(i);
                    runner.run(sub);
                    status = (T) sub.getStatus();
                    sub.resetStatus();
                }
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.status != null;
    }

    /**
     * @param d          surface
     * @param background background
     */
    public void showBackground(DrawSurface d, Sprite background) {
        background.drawOn(d);
    }

    @Override
    public void resetStatus() {
        this.status = null;
    }

    /**
     * change title color.
     */
    public void changeTitleColor() {
        int r = rnd.nextInt(255);
        int g = rnd.nextInt(255);
        int b = rnd.nextInt(255);
        titleColor = new Color(r, g, b);
    }
}
