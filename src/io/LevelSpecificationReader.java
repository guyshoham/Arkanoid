package io;

import backend.LevelInformation;
import backend.Sprite;
import backend.UtilFunctions;
import backend.Velocity;
import backgrounds.BackgroundImage;
import backgrounds.BackgroundSingleColor;
import gameobjects.Block;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static javax.imageio.ImageIO.read;

/**
 * Class LevelSpecificationReader.
 *
 * @author Guy Shoham
 */
public class LevelSpecificationReader implements LevelInformation {
    private static final String START_LEVEL = "START_LEVEL";
    private static final String END_LEVEL = "END_LEVEL";
    private static final String START_BLOCKS = "START_BLOCKS";
    private static final String END_BLOCKS = "END_BLOCKS";
    private static final String BALL_VELOCITIES = "ball_velocities";
    private static final String LEVEL_NAME = "level_name";
    private static final String BACKGROUND = "background";
    private static final String PADDLE_SPEED = "paddle_speed";
    private static final String PADDLE_WIDTH = "paddle_width";
    private static final String BLOCK_DEFINITIONS = "block_definitions";
    private static final String BLOCKS_START_X = "blocks_start_x";
    private static final String BLOCKS_START_Y = "blocks_start_y";
    private static final String ROW_HEIGHT = "row_height";
    private static final String NUM_BLOCKS = "num_blocks";
    private static final String RGB_PREFIX = "color(RGB(";
    private static final String RGB_POSTFIX = "))";
    private static final String COLOR_PREFIX = "color(";
    private static final String COLOR_POSTFIX = ")";
    private static final String IMAGE_PREFIX = "image(";
    private static final String IMAGE_POSTFIX = ")";
    private static final String IMAGE = "image";
    private int paddleSpeed = -1, paddleWidth = -1, blockStartX = -1, blockStartY = -1, rowHeight = -1, numBlocks = -1;
    private String levelName, blockDefinitions = "";
    private Sprite background;
    private List<Velocity> velocities;
    private List<Block> blockList = new ArrayList<>();

    /**
     * @param reader reader
     * @return List<LevelInformation> contains levelInformations from text
     * @throws IOException exception
     */
    public List<LevelInformation> fromReader(Reader reader) throws IOException {
        List<LevelInformation> levels = new ArrayList<>();
        List<String> stringLevels = splitLevels(reader);
        boolean isBlockLine = false;

        //for each level
        for (String level : stringLevels) {
            LevelSpecificationReader currentLevel = new LevelSpecificationReader();
            String key, value;
            int posY = 0;
            List<Block> blocks = new ArrayList<>();
            BlocksFromSymbolsFactory factory = null;
            String[] lines = level.split("\n");
            checkBlocksTagsValidation(lines);
            String blockText = level.substring(level.indexOf(START_BLOCKS) + START_BLOCKS.length(),
                    level.indexOf(END_BLOCKS));

            //for each line in level
            for (String line : lines) {
                if (isBlockLine && !line.equals(END_BLOCKS)) {
                    int posX = blockStartX;

                    for (int i = 0; i < line.length(); i++) {
                        String symbol = String.valueOf(line.charAt(i));
                        if (factory.isSpaceSymbol(symbol)) {
                            posX += factory.getSpaceWidth(symbol);
                        } else if (factory.isBlockSymbol(symbol)) {
                            Block b = factory.getBlock(symbol, posX, posY);
                            if (b == null) {
                                throw new RuntimeException("Failed creating block (type '" + symbol + "'");
                            }
                            blocks.add(b);
                            posX += b.getCollisionRectangle().getWidth();
                        }
                    } //done reading line of blocks
                    posY += rowHeight;

                } else if (line.equals(START_BLOCKS)) {
                    isBlockLine = true;
                    if (blockDefinitions.equals("")) {
                        throw new IOException("'block definitions' file is missing");
                    }
                    Reader blockDefinitionsReader = new FileReader(new File("resources/" + blockDefinitions));
                    factory = BlocksDefinitionReader.fromReader(blockDefinitionsReader);
                } else if (line.equals(END_BLOCKS)) {
                    isBlockLine = false;
                    currentLevel.setBlockList(blocks);
                } else {
                    key = line.substring(0, line.indexOf(":"));
                    value = line.substring(line.indexOf(":") + 1);
                    switch (key) {
                        case LEVEL_NAME:
                            levelName = value;
                            currentLevel.setLevelName(value);
                            break;
                        case BALL_VELOCITIES:
                            List<Velocity> tempVelocities = new ArrayList<>();
                            String[] split1 = value.split(" ");
                            for (String s : split1) {
                                int angle = Integer.parseInt(s.substring(0, s.indexOf(",")));
                                int speed = Integer.parseInt(s.substring(s.indexOf(",") + 1));
                                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                                tempVelocities.add(v);
                            }
                            currentLevel.setVelocities(tempVelocities);
                            break;
                        case BACKGROUND:
                            if (value.startsWith(RGB_PREFIX) && value.endsWith(RGB_POSTFIX)) {
                                //RGB
                                String rgb = UtilFunctions.trimString(value, RGB_PREFIX, RGB_POSTFIX);
                                String[] colors = rgb.split(",");
                                int r = Integer.parseInt(colors[0]);
                                int g = Integer.parseInt(colors[1]);
                                int b = Integer.parseInt(colors[2]);
                                Color color = new Color(202, 202, 202);
                                currentLevel.setBackground(new BackgroundSingleColor(color));
                            } else if (value.startsWith(COLOR_PREFIX) && value.endsWith(COLOR_POSTFIX)) {
                                //color
                                String rgb = UtilFunctions.trimString(value, COLOR_PREFIX, COLOR_POSTFIX);
                                Color color;
                                try {
                                    color = UtilFunctions.colorFromString(rgb);
                                    currentLevel.setBackground(new BackgroundSingleColor(color));
                                } catch (Exception ex) {
                                    throw new IOException(ex);
                                }
                            } else if (value.substring(0, 5).equals(IMAGE)) {
                                //image
                                String imagePath = UtilFunctions.trimString(value, IMAGE_PREFIX, IMAGE_POSTFIX);
                                try {
                                    InputStream stream =
                                            ClassLoader.getSystemClassLoader().getResourceAsStream(imagePath);
                                    BufferedImage image = read(stream);
                                    currentLevel.setBackground(new BackgroundImage(image));
                                } catch (IOException ex) {
                                    throw new IOException(ex);
                                }
                            }
                            break;
                        case PADDLE_SPEED:
                            paddleSpeed = Integer.parseInt(value);
                            currentLevel.setPaddleSpeed(Integer.parseInt(value));
                            break;
                        case PADDLE_WIDTH:
                            paddleWidth = Integer.parseInt(value);
                            currentLevel.setPaddleWidth(Integer.parseInt(value));
                            break;
                        case BLOCK_DEFINITIONS:
                            blockDefinitions = value;
                            currentLevel.setBlockDefinitions(blockDefinitions);
                            break;
                        case BLOCKS_START_X:
                            blockStartX = Integer.parseInt(value);
                            currentLevel.setBlockStartX(Integer.parseInt(value));
                            break;
                        case BLOCKS_START_Y:
                            blockStartY = Integer.parseInt(value);
                            currentLevel.setBlockStartY(Integer.parseInt(value));
                            posY = blockStartY;
                            break;
                        case ROW_HEIGHT:
                            rowHeight = Integer.parseInt(value);
                            currentLevel.setRowHeight(Integer.parseInt(value));
                            break;
                        case NUM_BLOCKS:
                            numBlocks = Integer.parseInt(value);
                            currentLevel.setNumBlocks(Integer.parseInt(value));
                            break;
                        default:
                            throw new IOException("illegal tag: " + key);
                    } //end of switch
                }
            } // end of reading one level

            checkTagsValidation(currentLevel);
            levels.add(currentLevel);
        }
        return levels;
    }

    /**
     * checks if one of the tags is missing. if does missing - throws IOException.
     *
     * @param currentLevel level
     * @throws IOException exception
     */
    private void checkTagsValidation(LevelSpecificationReader currentLevel) throws IOException {
        if (currentLevel.levelName() == null) {
            throw new IOException(LEVEL_NAME + " tag is missing");
        }
        if (currentLevel.initialBallVelocities() == null) {
            throw new IOException(BALL_VELOCITIES + " tag is missing");
        }
        if (currentLevel.getBackground() == null) {
            throw new IOException(BACKGROUND + " tag is missing");
        }
        if (currentLevel.paddleSpeed() < 0) {
            throw new IOException(PADDLE_SPEED + " tag is missing or value is not valid");
        }
        if (currentLevel.paddleWidth() < 0) {
            throw new IOException(PADDLE_WIDTH + " tag is missing or value is not valid");
        }
        if (currentLevel.getBlockStartX() < 0) {
            throw new IOException(BLOCKS_START_X + " tag is missing or value is not valid");
        }
        if (currentLevel.getBlockStartY() < 0) {
            throw new IOException(BLOCKS_START_Y + " tag is missing or value is not valid");
        }
        if (currentLevel.getRowHeight() < 0) {
            throw new IOException(ROW_HEIGHT + " tag is missing or value is not valid");
        }
        if (currentLevel.numberOfBlocksToRemove() < 0) {
            throw new IOException(NUM_BLOCKS + " tag is missing or value is not valid");
        }
    }

    /**
     * checks if both START_BLOCKS and END_BLOCKS tags is in list. if not - throws IOException.
     *
     * @param lines list of lines
     * @throws IOException exception
     */
    private void checkBlocksTagsValidation(String[] lines) throws IOException {
        boolean isValid;
        isValid = false;
        for (String line : lines) {
            if (line.startsWith(START_BLOCKS)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new IOException(START_BLOCKS + " tag is missing or value is not valid");
        }
        isValid = false;
        for (String line : lines) {
            if (line.startsWith(END_BLOCKS)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new IOException(END_BLOCKS + " tag is missing or value is not valid");
        }
    }

    /**
     * @param reader reader
     * @return List<String>. each item contains one level without START_LEVEL and END_LEVEL
     * @throws IOException exception
     */
    public List<String> splitLevels(Reader reader) throws IOException {
        boolean done;
        List<String> levels = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(reader);
            String s;
            StringBuilder level;
            while ((s = br.readLine()) != null) {
                if (s.equals(START_LEVEL)) {
                    level = new StringBuilder();
                    done = false;
                    while (!done) {
                        s = br.readLine();
                        if (s == null) {
                            throw new IOException(END_LEVEL + " tag is missing");
                        } else if (s.equals(END_LEVEL)) {
                            done = true;
                        } else {
                            level.append(s);
                            level.append("\n");
                        }
                    }
                    levels.add(String.valueOf(level));
                }
            }
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return levels;
    }

    /**
     * @return blockStartX
     */
    public int getBlockStartX() {
        return blockStartX;
    }

    /**
     * @param newPos x position
     */
    public void setBlockStartX(int newPos) {
        this.blockStartX = newPos;
    }

    /**
     * @return blockStartY
     */
    public int getBlockStartY() {
        return blockStartY;
    }

    /**
     * @param newPos y position
     */
    public void setBlockStartY(int newPos) {
        this.blockStartY = newPos;
    }

    /**
     * @return rowHeight
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * @param height height
     */
    public void setRowHeight(int height) {
        this.rowHeight = height;
    }

    /**
     * @return blockDefinitions
     */
    public String getBlockDefinitions() {
        return blockDefinitions;
    }

    /**
     * @param fileName file name
     */
    public void setBlockDefinitions(String fileName) {
        this.blockDefinitions = fileName;
    }

    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * @param list velocities list
     */
    public void setVelocities(List<Velocity> list) {
        this.velocities = list;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @param speed speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @param width width
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @param name name
     */
    public void setLevelName(String name) {
        this.levelName = name;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @param b background
     */
    private void setBackground(Sprite b) {
        this.background = b;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * @param list block list
     */
    public void setBlockList(List<Block> list) {
        this.blockList = list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

    /**
     * @param num num of blocks
     */
    public void setNumBlocks(int num) {
        this.numBlocks = num;
    }
}
