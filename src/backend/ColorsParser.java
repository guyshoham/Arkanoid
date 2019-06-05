package backend;

import java.awt.*;
import java.lang.reflect.Field;

public class ColorsParser {

    /**
     * parse color definition and return the specified color.
     *
     * @param s string
     * @return Color
     * @throws Exception e
     */
    public static Color colorFromString(String s) throws Exception {
        try {
            Field field = Class.forName("java.awt.Color").getField(s);
            return (Color) field.get(null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
