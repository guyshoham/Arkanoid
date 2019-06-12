package blocks;

import backend.ColorsParser;
import drawer.Drawer;
import drawer.FillDrawer;
import drawer.ImageDrawer;
import drawer.StrokeDrawer;
import gameobjects.Block;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class DrawingDecorator extends BlockCreatorDecorator {

    private static final String RGB_PREFIX = "color(RGB(";
    private static final String RGB_POSTFIX = "))";
    private static final String COLOR_PREFIX = "color(";
    private static final String COLOR_POSTFIX = ")";
    private static final String IMAGE_PREFIX = "image(";
    private static final String IMAGE_POSTFIX = ")";
    private boolean isFill;
    private Integer hitPoints;
    private Drawer drawer;


    public DrawingDecorator(BlockCreator decorated, String value, boolean isFill, Integer hitPoints) {
        super(decorated);
        this.hitPoints = hitPoints;
        this.isFill = isFill;
        this.drawer = this.parseDrawer(value, isFill);
    }

    private Drawer parseDrawer(String value, boolean isFill) {
        Drawer result;
        String param;
        if (value.startsWith(RGB_PREFIX) && value.endsWith(RGB_POSTFIX)) {
            param = betterSubstring(value, RGB_PREFIX, RGB_POSTFIX);
            String[] rgb = param.split(",");
            int r = Integer.parseInt(rgb[0].trim());
            int g = Integer.parseInt(rgb[1].trim());
            int b = Integer.parseInt(rgb[2].trim());
            Color color = new Color(r, g, b);
            if (isFill) {
                result = new FillDrawer(color);
            } else {
                result = new StrokeDrawer(color);
            }
        } else {
            InputStream is;
            if (value.startsWith(COLOR_PREFIX) && value.endsWith(COLOR_POSTFIX)) {
                param = betterSubstring(value, COLOR_PREFIX, COLOR_POSTFIX);
                is = null;

                Color color;
                try {
                    color = ColorsParser.colorFromString(param);
                } catch (Exception e) {
                    throw new RuntimeException("Unsupported color name: " + param);
                }

                if (isFill) {
                    result = new FillDrawer(color);
                } else {
                    result = new StrokeDrawer(color);
                }
            } else {
                if (!value.startsWith(IMAGE_PREFIX) || !value.endsWith(IMAGE_POSTFIX)) {
                    throw new RuntimeException("Unsupported definition: " + value);
                }

                param = betterSubstring(value, IMAGE_PREFIX, IMAGE_POSTFIX);
                if (!isFill) {
                    throw new RuntimeException("Image type not supported for stroke");
                }

                is = null;

                try {
                    is = ClassLoader.getSystemClassLoader().getResourceAsStream(param);
                    BufferedImage e = ImageIO.read(is);
                    result = new ImageDrawer(e);
                } catch (IOException var19) {
                    throw new RuntimeException("Failed loading image: " + param, var19);
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException var18) {
                            throw new RuntimeException("Failed loading image: " + param, var18);
                        }
                    }

                }
            }
        }

        return result;
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
