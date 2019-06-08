package blocks;

import gameobjects.Block;

public class HeightDecorator extends BlockCreatorDecorator {
    private int height;

    public HeightDecorator(BlockCreator decorated, String propertyValue) {
        super(decorated);
        this.height = Integer.parseInt(propertyValue);
    }

    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setHeight(height);
        return b;
    }
}
