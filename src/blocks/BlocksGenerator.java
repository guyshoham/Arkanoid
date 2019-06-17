package blocks;

import java.io.IOException;

/**
 * class DecoratorFactory.
 *
 * @author Guy Shoham
 */
public class BlocksGenerator {

    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String HIT_POINTS = "hit_points";
    private static final String FILL = "fill";
    private static final String STROKE = "stroke";

    /**
     * @param creator block creator
     * @param key     key
     * @param value   value
     * @return block creator out from the key and value
     * @throws IOException exception.
     */
    public BlockCreator generate(BlockCreator creator, String key, String value) throws IOException {
        switch (key) {
            case HEIGHT:
                if (Integer.parseInt(value) <= 0) {
                    throw new IOException("the height of the block must be positive integer: " + value);
                }
                return new HeightBlock(creator, value);
            case WIDTH:
                if (Integer.parseInt(value) <= 0) {
                    throw new IOException("the width of the block must be positive integer: " + value);
                }
                return new WidthBlock(creator, value);
            case HIT_POINTS:
                if (Integer.parseInt(value) <= 0) {
                    throw new IOException("the number of hit-points of the block must be positive integer: " + value);
                }
                return new HitPointsBlock(creator, value);
            default:
                if (!key.startsWith(FILL) && !key.startsWith(STROKE)) {
                    throw new RuntimeException("Unsupported property: " + key + " with value:" + value);

                } else {
                    Integer hitPointsValue = null;
                    boolean isFill = key.startsWith(FILL);
                    int dividerIndex = key.indexOf("-");
                    if (dividerIndex != -1) {
                        hitPointsValue = Integer.parseInt(key.substring(dividerIndex + 1));
                    }
                    return new DrawingBlock(creator, value, isFill, hitPointsValue);
                }
        } // end of switch
    }
}
