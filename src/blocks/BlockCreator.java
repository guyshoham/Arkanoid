package blocks;

import gameobjects.Block;

/**
 * interface BlockCreator.
 *
 * @author Guy Shoham
 */
public interface BlockCreator {
    /**
     * @param xpos x pos
     * @param ypos y pos
     * @return block at the specified location.
     */
    Block create(int xpos, int ypos);
}