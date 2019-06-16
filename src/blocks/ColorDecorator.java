package blocks;


import backend.ColorsParser;
import gameobjects.Block;

import java.awt.Color;

/**
 * class ColorDecorator.
 *
 * @author Guy Shoham
 */
public class ColorDecorator extends BlockCreatorDecorator {
    private Color color;

    /**
     * Class constructor.
     *
     * @param decorated block creator
     * @param color color
     */
    public ColorDecorator(BlockCreator decorated, String color) {
        super(decorated);
        try {
            this.color = ColorsParser.colorFromString(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setColor(color);
        return b;
    }

}
