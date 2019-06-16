package blocks;

import gameobjects.Block;

/**
 * class HeightBlock.
 *
 * @author Guy Shoham
 */
public class HeightBlock extends BlockCreatorDecorator {
    private int height;

    /**
     * Class constructor.
     *
     * @param decorated block creator
     * @param height    height
     */
    public HeightBlock(BlockCreator decorated, String height) {
        super(decorated);
        this.height = Integer.parseInt(height);
    }

    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setHeight(height);
        return b;
    }
}
