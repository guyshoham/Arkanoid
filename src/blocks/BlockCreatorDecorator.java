package blocks;

import gameobjects.Block;

/**
 * Class BlockCreatorDecorator.
 *
 * @author Guy Shoham
 */
public abstract class BlockCreatorDecorator implements BlockCreator {
    private BlockCreator decorated;

    /**
     * Class Constructor.
     *
     * @param decorated blockCreator
     */
    public BlockCreatorDecorator(BlockCreator decorated) {
        this.decorated = decorated;
    }

    @Override
    public Block create(int x, int y) {
        return this.decorated.create(x, y);
    }
}
