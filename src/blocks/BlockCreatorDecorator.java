package blocks;

import gameobjects.Block;

public abstract class BlockCreatorDecorator implements BlockCreator {
    private BlockCreator decorated;

    public BlockCreatorDecorator(BlockCreator decorated) {
        this.decorated = decorated;
    }

    @Override
    public Block create(int x, int y) {
        return this.decorated.create(x, y);
    }
}
