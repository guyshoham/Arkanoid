package backend;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * Class ColorParser.
 *
 * @author Guy Shoham
 */
public class UtilFunctions {

    /**
     * parse color definition and return the specified color.
     *
     * @param s string
     * @return Color
     * @throws Exception exception
     */
    public static Color colorFromString(String s) throws Exception {
        try {
            Field field = Class.forName("java.awt.Color").getField(s);
            return (Color) field.get(null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * @param value original string.
     * @param start start of the string we want.
     * @param end   end of the string we want.
     * @return a substring between start and end.
     */
    public static String trimString(String value, String start, String end) {
        return value.substring(start.length(), value.length() - end.length());
    }
}
