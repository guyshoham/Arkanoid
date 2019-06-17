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
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacersMap;
    private Map<String, BlockCreator> blockCreatorsMap;

    /**
     * Class constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacersMap = new HashMap<>();
        this.blockCreatorsMap = new HashMap<>();
    }

    /**
     * @param s string
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return spacersMap.containsKey(s);
    }

    /**
     * @param s string
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return blockCreatorsMap.containsKey(s);
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
            return blockCreatorsMap.get(s).create(xpos, ypos);
        } else {
            return null;
        }
    }

    /**
     * @param s string
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return spacersMap.get(s);
    }

    /**
     * add spacer map.
     *
     * @param key   key
     * @param width width
     */
    public void addSpacer(String key, int width) {
        spacersMap.put(key, width);
    }

    /**
     * add block creator to map.
     *
     * @param key     key
     * @param creator block creator
     */
    public void addBlockCreator(String key, BlockCreator creator) {
        blockCreatorsMap.put(key, creator);
    }

    /**
     * @return spacers map
     */
    public Map<String, Integer> getSpacersMap() {
        return spacersMap;
    }

    /**
     * @return block creators map
     */
    public Map<String, BlockCreator> getBlockCreatorsMap() {
        return blockCreatorsMap;
    }

}