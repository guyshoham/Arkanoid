package blocks;

import gameobjects.Block;

/**
 * class HeightDecorator.
 *
 * @author Guy Shoham
 */
public class HeightDecorator extends BlockCreatorDecorator {
    private int height;

    public HeightDecorator(BlockCreator decorated, String propertyValue) {
        super(decorated);
        this.height = Integer.parseInt(propertyValue);
    }

    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setHeight(height);
        return b;
    }
}
