package blocks;

public class DecoratorsFactory {

    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String HIT_POINTS = "hit_points";
    private static final String FILL = "fill";
    private static final String STROKE = "stroke";

    public BlockCreator decorate(BlockCreator creator, String key, String value) {
        if (key.equals(HEIGHT)) {
            return new HeightDecorator(creator, value);
        } else if (key.equals(WIDTH)) {
            return new WidthDecorator(creator, value);
        } else if (key.equals(HIT_POINTS)) {
            return new HitPointsDecorator(creator, value);
        } else if (!key.startsWith(FILL) && !key.startsWith(STROKE)) {
            throw new RuntimeException("Unsupported property: " + key + " with value:" + value);
        } else {
            Integer hitPointsValue = null;
            boolean isFill = key.startsWith(FILL);
            int dividerIndex = key.indexOf("-");
            if (dividerIndex != -1) {
                hitPointsValue = Integer.parseInt(key.substring(dividerIndex + 1));
            }

            return new DrawingDecorator(creator, value, isFill, hitPointsValue);
        }
    }
}
