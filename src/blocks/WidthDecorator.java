package blocks;


import gameobjects.Block;

/**
 * Class WidthDecorator.
 *
 * @author Guy Shoham
 */
public class WidthDecorator extends BlockCreatorDecorator {
    private int width;

    /**
     * Class constructor.
     *
     * @param decorated block creator
     * @param width     width
     */
    public WidthDecorator(BlockCreator decorated, String width) {
        super(decorated);
        this.width = Integer.parseInt(width);
    }

    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setWidth(width);
        return b;
    }

}
