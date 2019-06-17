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
    private int paddleSpeed, paddleWidth, numBlocks, blockStartX, blockStartY, rowHeight;
    private String levelName, blockDefinitions = "";
    private Sprite background;
    private List<Velocity> velocities = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();

    public List<LevelInformation> fromReader(Reader reader) throws IOException {
        List<LevelInformation> levels = new ArrayList<>();
        List<String> stringLevels = splitLevels(reader);
        boolean isBlockLine = false;

        //for each level
        for (String level : stringLevels) {
            if (!level.contains(LEVEL_NAME) || !level.contains(BALL_VELOCITIES)
                    || !level.contains(BACKGROUND) || !level.contains(PADDLE_SPEED)
                    || !level.contains(PADDLE_WIDTH) || !level.contains(BLOCK_DEFINITIONS)
                    || !level.contains(BLOCKS_START_X) || !level.contains(BLOCKS_START_Y)
                    || !level.contains(ROW_HEIGHT) || !level.contains(NUM_BLOCKS)
                    || !level.contains(START_BLOCKS) || !level.contains(END_BLOCKS)) {
                throw new IOException("level definition is not valid");
            }
            LevelSpecificationReader currentLevel = new LevelSpecificationReader();
            String key, value;
            int posY = 0;
            List<Block> blocks = new ArrayList<>();
            BlocksFromSymbolsFactory factory = null;
            String blockText = level.substring(level.indexOf(START_BLOCKS) + START_BLOCKS.length(),
                    level.indexOf(END_BLOCKS));
            String[] lines = level.split("\n");

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
                    } //end of switch
                }
            } // end of reading one level
            levels.add(currentLevel);
        }
        return levels;
    }

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

    public int getBlockStartX() {
        return blockStartX;
    }

    public void setBlockStartX(int newPos) {
        this.blockStartX = newPos;
    }

    public int getBlockStartY() {
        return blockStartY;
    }

    public void setBlockStartY(int newPos) {
        this.blockStartY = newPos;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int height) {
        this.rowHeight = height;
    }

    public String getBlockDefinitions() {
        return blockDefinitions;
    }

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

    public void setVelocities(List<Velocity> list) {
        this.velocities = list;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    public void setLevelName(String name) {
        this.levelName = name;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    private void setBackground(Sprite b) {
        this.background = b;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    public void setBlockList(List<Block> list) {
        this.blockList = list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

    public void setNumBlocks(int num) {
        this.numBlocks = num;
    }
}
