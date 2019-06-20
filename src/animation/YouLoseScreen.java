package animation;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javax.imageio.ImageIO.read;

/**
 * class YouLoseScreen.
 *
 * @author Guy Shoham
 */
public class YouLoseScreen implements Animation {
    private int score;
    private Image image;

    /**
     * Class Constructor.
     *
     * @param score score
     */
    public YouLoseScreen(int score) {
        this.score = score;
        setImage();
    }

    /**
     * set image of animation.
     */
    private void setImage() {
        InputStream stream =
                ClassLoader.getSystemClassLoader().getResourceAsStream("images/gameover.png");
        try {
            BufferedImage bufferedImage = read(stream);
            this.image = bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException("cannot load youLose image");
        }
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText(275, 150, "Game Over!", 50);
        d.setColor(Color.BLACK);
        d.drawText(250, 230, "Your Score is " + score, 40);
        d.drawImage(300, 250, image);
        d.drawText(250, 500, "Press Space to Continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}