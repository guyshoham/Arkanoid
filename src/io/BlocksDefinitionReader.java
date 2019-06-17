package io;

import blocks.BasicBlock;
import blocks.BlockCreator;
import blocks.BlocksGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
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
    private static final String HEIGHT = "height";
    private static final String HIT_POINTS = "hit_points";
    private static final String FILL = "fill";
    private static final String FILL_1 = "fill-1";


    /**
     * reading from a file and creating factory which we can creates blocks from.
     *
     * @param reader reader
     * @return blocks factory
     * @throws IOException exception
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) throws IOException {
        BlocksFromSymbolsFactory retVal = new BlocksFromSymbolsFactory();
        BlocksGenerator blocksGenerator = new BlocksGenerator();
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
                            defaultMap = pullProperties(line);
                        }

                    } else {
                        Map map;
                        if (line.startsWith(SDEF)) {
                            line = line.substring(SDEF.length()).trim();
                            map = pullProperties(line);
                            String symbol = getSymbol(map);
                            int width = Integer.parseInt((String) map.get(WIDTH));
                            if (width <= 0) {
                                throw new IOException("the width of the spacing element must be positive integer: "
                                        + width);
                            }
                            retVal.addSpacer(symbol, width);
                        } else if (line.startsWith(BDEF)) {
                            line = line.substring(BDEF.length()).trim();
                            map = pullProperties(line);
                            Map<String, String> propertiesMap = new HashMap<>(defaultMap);
                            propertiesMap.putAll(map);
                            String symbol = getSymbol(propertiesMap);

                            if (!propertiesMap.containsKey(HEIGHT) || !propertiesMap.containsKey(WIDTH)
                                    || !propertiesMap.containsKey(HIT_POINTS)) {
                                throw new IOException("one or more required properties is not defined."
                                        + propertiesMap.toString());
                            }
                            if (!propertiesMap.containsKey(FILL) && !propertiesMap.containsKey(FILL_1)) {
                                throw new IOException("one or more required properties is not defined."
                                        + propertiesMap.toString());
                            }

                            BlockCreator blockCreator = new BasicBlock();

                            for (Iterator i = propertiesMap.keySet().iterator(); i.hasNext(); i = i) {
                                key = String.valueOf(i.next());
                                blockCreator = blocksGenerator.generate(blockCreator, key, propertiesMap.get(key));
                            }
                            retVal.addBlockCreator(symbol, blockCreator);
                        }
                    }
                }
            } //end of reading file


        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return retVal;
    }

    /**
     * pops out the symbol from the map. the symbol will be a key to the map itself.
     *
     * @param map map
     * @return a symbol representing the key for the map(value).
     * @throws IOException exception.
     */
    public static String getSymbol(Map<String, String> map) throws IOException {
        String symbol = map.remove(SYMBOL);
        if (symbol.length() != 1) {
            throw new IOException("Symbol (" + symbol + ") must be a single character.");
        } else {
            return symbol;
        }
    }

    /**
     * @param str string
     * @return Map<String, String> contains key and value from str
     */
    public static Map<String, String> pullProperties(String str) {
        Map<String, String> retVal = new HashMap<>();
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