package blocks;

import gameobjects.Block;

/**
 * Class BasicBlock.
 */
public class BasicBlock implements BlockCreator {

    @Override
    public Block create(int x, int y) {
        return new Block(x, y);
    }

}
