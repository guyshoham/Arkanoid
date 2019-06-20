package animation;

import biuoop.DrawSurface;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javax.imageio.ImageIO.read;

/**
 * class PauseScreen.
 *
 * @author Guy Shoham
 */
public class PauseScreen implements Animation {

    private Image image;

    /**
     * Class Constructor.
     */
    public PauseScreen() {
        setImage();

    }

    /**
     * set image of pause animation.
     */
    private void setImage() {
        InputStream stream =
                ClassLoader.getSystemClassLoader().getResourceAsStream("images/hourglass.png");
        try {
            BufferedImage bufferedImage = read(stream);
            this.image = bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException("cannot load pause image");
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(330, 150, "Paused", 40);
        d.drawImage(300, 200, image);
        d.drawText(250, 500, "Press Space to Continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}