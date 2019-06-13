package blocks;

import gameobjects.Block;

/**
 * class HitPointsDecorator.
 *
 * @author Guy Shoham
 */
public class HitPointsDecorator extends BlockCreatorDecorator {
    private int hitPoints;

    public HitPointsDecorator(BlockCreator decorated, String propertyValue) {
        super(decorated);
        this.hitPoints = Integer.parseInt(propertyValue);
    }

    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setNumberOfHits(hitPoints);
        return b;
    }
}
