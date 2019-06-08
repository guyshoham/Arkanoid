package blocks;


import gameobjects.Block;

public class WidthDecorator extends BlockCreatorDecorator {
    private int width;

    public WidthDecorator(BlockCreator decorated, String propertyValue) {
        super(decorated);
        this.width = Integer.parseInt(propertyValue);
    }

    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setWidth(width);
        return b;
    }

}
