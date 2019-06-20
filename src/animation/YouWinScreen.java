package animation;

import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javax.imageio.ImageIO.read;

/**
 * class YouWinScreen.
 *
 * @author Guy Shoham
 */
public class YouWinScreen implements Animation {
    private int score;
    private Image image;

    /**
     * Class Constructor.
     *
     * @param score score
     */
    public YouWinScreen(int score) {
        this.score = score;
        setImage();
    }

    /**
     * set image of animation.
     */
    private void setImage() {
        InputStream stream =
                ClassLoader.getSystemClassLoader().getResourceAsStream("images/youwin.png");
        try {
            BufferedImage bufferedImage = read(stream);
            this.image = bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException("cannot load youLose image");
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawText(300, 150, "You Win!", 50);
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