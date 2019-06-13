package blocks;


import backend.ColorsParser;
import gameobjects.Block;

import java.awt.Color;

public class ColorDecorator extends BlockCreatorDecorator {
    private Color color;

    public ColorDecorator(BlockCreator decorated, String propertyValue) {
        super(decorated);
        try {
            this.color = ColorsParser.colorFromString(propertyValue);
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
