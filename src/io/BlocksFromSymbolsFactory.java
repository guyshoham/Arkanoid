package io;

import blocks.BlockCreator;
import gameobjects.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * Class BlocksFromSymbolsFactory.
 *
 * @author Guy Shoham
 */
public class BlocksFromSymbolsFactory  {
    private static final String DEFAULT = "default";
    private static final String BDEF = "bdef";
    private static final String SDEF = "sdef";
    private static final String SYMBOL = "symbol";
    private static final String WIDTH = "width";
    private static final String HIT_POINTS = "hit_points";
    private static final String FILL = "fill";
    private static final String STROKE = "stroke";
    private static final String HEIGHT = "height";
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new HashMap<>();
        this.blockCreators = new HashMap<>();
    }

    /**
     * @param s string
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * @param s string
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * @param s    string
     * @param xpos x pos
     * @param ypos y pos
     * @return a block according to the definitions associated with symbol s.
     * The block will be located at position (xpos, ypos).
     */
    public Block getBlock(String s, int xpos, int ypos) {
        if (isBlockSymbol(s)) {
            return blockCreators.get(s).create(xpos, ypos);
        } else {
            return null;
        }
    }

    /**
     * @param s string
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return spacerWidths.get(s);
    }

    public void addSpacer(String key, int width) {
        spacerWidths.put(key, width);
    }

    public void addBlockCreator(String key, BlockCreator creator) {
        blockCreators.put(key, creator);
    }

    public Map<String, Integer> getSpacerWidths() {
        return spacerWidths;
    }

    public Map<String, BlockCreator> getBlockCreators() {
        return blockCreators;
    }

}