package io;

import blocks.BlockCreator;
import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class BlocksDefinitionReader .
 *
 * @author Guy Shoham
 */
public class BlocksDefinitionReader {
    private static final String DEFAULT = "default";
    private static final String BDEF = "bdef";
    private static final String SDEF = "sdef";
    private static final String SYMBOL = "symbol";
    private static final String WIDTH = "width";
    private static final String HIT_POINTS = "hit_points";
    private static final String FILL = "fill";
    private static final String STROKE = "stroke";
    private static final String HEIGHT = "height";

    public static BlocksFromSymbolsFactory fromReader(Reader reader) throws IOException {
        BlocksFromSymbolsFactory retVal = new BlocksFromSymbolsFactory();
        BufferedReader br = null;
        try {
            br = new BufferedReader(reader);
            Map<String, String> defaultMap = new HashMap<>();
            String key, value;
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    if (!line.startsWith(SDEF) && !line.startsWith(BDEF)) {
                        if (!line.startsWith(DEFAULT)) {
                            throw new RuntimeException("Unsupported line format (" + line + ")");
                        } else {
                            line = line.substring(DEFAULT.length()).trim();
                            defaultMap = parseProperties(line);
                        }

                    } else {
                        Map map;
                        if (line.startsWith(SDEF)) {
                            line = line.substring(SDEF.length()).trim();
                            map = parseProperties(line);
                            String symbol = getSymbol(map);
                            int width = Integer.parseInt((String) map.get(WIDTH));
                            retVal.addSpacer(symbol, width);
                        } else if (line.startsWith(BDEF)) {
                            line = line.substring(BDEF.length()).trim();
                            map = parseProperties(line);
                            Map<String, String> propertiesMap = new HashMap(defaultMap);
                            propertiesMap.putAll(map);
                            String symbol = getSymbol(propertiesMap);

                            BlocksFromSymbolsFactory blockCreator = new BlocksFromSymbolsFactory();
                            //todo: complete put keys and values
                            blockCreator.addBlockCreator(symbol, new BlockCreator() {

                                @Override
                                public Block create(int xpos, int ypos) {
                                    int width = Integer.parseInt(propertiesMap.get(WIDTH));
                                    int height = Integer.parseInt(propertiesMap.get(HEIGHT));
                                    int hitPoints = Integer.parseInt(propertiesMap.get(HIT_POINTS));
                                    //todo: change color.BLACK
                                    Color color = Color.BLACK;

                                    return new Block(new Rectangle(new Point(xpos, ypos), width, height), color, hitPoints);
                                }
                            });

                            retVal.addBlockCreator(symbol, blockCreator);
                        }
                    }
                }
            }


        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (br != null) {
                br.close();
            }
        }


        return retVal;
    }

    public static String getSymbol(Map<String, String> map) {
        String symbol = map.get(SYMBOL);
        if (symbol.length() != 1) {
            throw new RuntimeException("Symbol (" + symbol + ") must be a single character.");
        } else {
            return symbol;
        }
    }

    public static Map<String, String> parseProperties(String str) {
        Map<String, String> retVal = new HashMap();
        String[] keyValues = str.split(" ");

        for (String keyValue : keyValues) {
            String[] pair = keyValue.split(":");
            if (pair.length != 2) {
                throw new RuntimeException("Incorrect Properties format (" + str + ")");
            } else {
                retVal.put(pair[0], pair[1]);
            }
        }
        return retVal;
    }

}