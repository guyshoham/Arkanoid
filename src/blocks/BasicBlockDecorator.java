package blocks;

import gameobjects.Block;

import java.awt.Color;

/**
 * Class BasicBlockDecorator.
 */
public class BasicBlockDecorator implements BlockCreator {
    private Color color;

    @Override
    public Block create(int x, int y) {
        return new Block(x, y);
    }

}
