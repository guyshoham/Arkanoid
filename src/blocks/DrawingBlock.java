package blocks;

import backend.ColorsParser;
import drawer.Drawer;
import drawer.FillDrawer;
import drawer.ImageDrawer;
import drawer.StrokeDrawer;
import gameobjects.Block;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static javax.imageio.ImageIO.read;

/**
 * class DrawingBlock.
 *
 * @author Guy Shoham
 */
public class DrawingBlock extends BlockCreatorDecorator {

    private static final String RGB_PREFIX = "color(RGB(";
    private static final String RGB_POSTFIX = "))";
    private static final String COLOR_PREFIX = "color(";
    private static final String COLOR_POSTFIX = ")";
    private static final String IMAGE_PREFIX = "image(";
    private static final String IMAGE_POSTFIX = ")";
    private boolean isFill;
    private Integer hitPoints;
    private Drawer drawer;


    /**
     * Class constructor.
     *
     * @param decorated block creator
     * @param value     value
     * @param isFill    is fill value
     * @param hitPoints hit points
     */
    public DrawingBlock(BlockCreator decorated, String value, boolean isFill, Integer hitPoints) {
        super(decorated);
        this.hitPoints = hitPoints;
        this.isFill = isFill;
        this.drawer = generateDrawer(value, isFill);
    }

    /**
     * generate drawer from value.
     *
     * @param value       value
     * @param isFillValue isFill
     * @return Drawer
     */
    private Drawer generateDrawer(String value, boolean isFillValue) {
        Drawer retVal;
        String param;
        InputStream stream = null;
        if (value.startsWith(RGB_PREFIX) && value.endsWith(RGB_POSTFIX)) {
            param = betterSubstring(value, RGB_PREFIX, RGB_POSTFIX);
            String[] rgb = param.split(",");
            int r = Integer.parseInt(rgb[0].trim());
            int g = Integer.parseInt(rgb[1].trim());
            int b = Integer.parseInt(rgb[2].trim());
            Color color = new Color(r, g, b);
            if (isFillValue) {
                retVal = new FillDrawer(color);
            } else {
                retVal = new StrokeDrawer(color);
            }
        } else {
            if (value.startsWith(COLOR_PREFIX) && value.endsWith(COLOR_POSTFIX)) {
                param = betterSubstring(value, COLOR_PREFIX, COLOR_POSTFIX);

                Color color;
                try {
                    color = ColorsParser.colorFromString(param);
                } catch (Exception e) {
                    throw new RuntimeException("Unsupported color name: " + param);
                }

                if (isFillValue) {
                    retVal = new FillDrawer(color);
                } else {
                    retVal = new StrokeDrawer(color);
                }
            } else {
                if (!value.startsWith(IMAGE_PREFIX) || !value.endsWith(IMAGE_POSTFIX)) {
                    throw new RuntimeException("Unsupported definition: " + value);
                }

                String imagePath = betterSubstring(value, IMAGE_PREFIX, IMAGE_POSTFIX);
                if (!isFillValue) {
                    throw new RuntimeException("Image type not supported for stroke");
                }

                try {
                    stream = ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
                    BufferedImage image = read(stream);
                    retVal = new ImageDrawer(image);
                } catch (IOException ex) {
                    throw new RuntimeException("Failed loading image: " + imagePath, ex);
                } finally {
                    if (stream != null) {
                        try {
                            stream.close();
                        } catch (IOException ex) {
                            throw new RuntimeException("Failed loading image: " + imagePath, ex);
                        }
                    }

                }
            }
        }

        return retVal;
    }

    /**
     * @param value original string.
     * @param start start of the string we want.
     * @param end   end of the string we want.
     * @return a substring between start and end.
     */
    public String betterSubstring(String value, String start, String end) {
        return value.substring(start.length(), value.length() - end.length());
    }

    @Override
    public Block create(int x, int y) {
        Block block = super.create(x, y);
        if (isFill) {
            if (hitPoints == null) {
                block.setDefaultFillDrawer(drawer);
            } else {
                block.addFillDrawer(hitPoints, drawer);
            }
        } else if (hitPoints == null) {
            block.setDefaultStrokeDrawer(drawer);
        } else {
            block.addStrokeDrawer(hitPoints, drawer);
        }

        return block;
    }
}
