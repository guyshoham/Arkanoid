package blocks;

import gameobjects.Block;

/**
 * class HitPointsDecorator.
 *
 * @author Guy Shoham
 */
public class HitPointsDecorator extends BlockCreatorDecorator {
    private int hitPoints;

    /**
     * Class constructor.
     *
     * @param decorated block creator
     * @param hitPoints hit points
     */
    public HitPointsDecorator(BlockCreator decorated, String hitPoints) {
        super(decorated);
        this.hitPoints = Integer.parseInt(hitPoints);
    }

    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        b.setNumberOfHits(hitPoints);
        return b;
    }
}
