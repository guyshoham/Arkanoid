package io;

import backend.ColorsParser;
import backgrounds.BackgroundImage;
import backgrounds.BackgroundSingleColor;
import gameobjects.Block;
import geometry.Velocity;
import levels.LevelInformation;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
    private int paddle_speed, paddle_width, num_blocks, block_start_x, block_start_y, row_height;
    private String level_name, block_definitions;
    private Sprite background;
    private List<Velocity> velocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    public List<LevelInformation> fromReader(Reader reader) throws IOException {
        List<LevelInformation> levels = new ArrayList<>();
        List<String> StringLevels = splitLevels(reader);
        Sprite background;
        LevelSpecificationReader currentLevel;

        for (String level : StringLevels) {
            currentLevel = new LevelSpecificationReader();
            String delimiter = ":";
            String key, value;
            String[] lines = level.split("\n");

            for (String line : lines) {
                if (line.equals(START_BLOCKS)) {
                    break;
                } else {
                    key = line.substring(0, line.indexOf(delimiter));
                    value = line.substring(line.indexOf(delimiter) + 1);
                    switch (key) {
                        case LEVEL_NAME:
                            level_name = value;
                            currentLevel.setLevel_name(value);
                            break;
                        case BALL_VELOCITIES:
                            velocities.clear();
                            String[] split1 = value.split(" ");
                            for (String s : split1) {
                                int dx = Integer.parseInt(s.substring(0, s.indexOf(",")));
                                int dy = Integer.parseInt(s.substring(s.indexOf(",") + 1));
                                Velocity v = new Velocity(dx, dy);
                                velocities.add(v);
                            }
                            currentLevel.setVelocities(velocities);
                            break;
                        case BACKGROUND:
                            if (value.startsWith("color(RGB(") && value.endsWith("))")) {
                                String rgb = betterSubstring(value, "color(RGB(", "))");
                                String[] colors = rgb.split(",");
                                int r = Integer.parseInt(colors[0]);
                                int g = Integer.parseInt(colors[1]);
                                int b = Integer.parseInt(colors[2]);
                                Color color = new Color(r, g, b);
                                currentLevel.setBackground(new BackgroundSingleColor(color));
                            } else if (value.startsWith("color(") && value.endsWith(")")) {
                                String rgb = betterSubstring(value, "color(", ")");
                                Color color;
                                try {
                                    color = ColorsParser.colorFromString(rgb);
                                    currentLevel.setBackground(new BackgroundSingleColor(color));
                                } catch (Exception ex) {
                                    throw new IOException(ex);
                                }
                            } else if (value.substring(0, 5).equals("image")) {
                                String imagePath = betterSubstring(value, "image(", ")");
                                try {
                                    Image image = ImageIO.read(new File(imagePath));
                                    currentLevel.setBackground(new BackgroundImage(image));
                                } catch (IOException ex) {
                                    throw new IOException(ex);
                                }
                            }
                            break;
                        case PADDLE_SPEED:
                            paddle_speed = Integer.parseInt(value);
                            currentLevel.setPaddle_speed(Integer.parseInt(value));
                            break;
                        case PADDLE_WIDTH:
                            paddle_width = Integer.parseInt(value);
                            currentLevel.setPaddle_width(Integer.parseInt(value));
                            break;
                        case BLOCK_DEFINITIONS:
                            block_definitions = value;
                            break;
                        case BLOCKS_START_X:
                            block_start_x = Integer.parseInt(value);
                            currentLevel.setBlock_start_x(Integer.parseInt(value));
                            break;
                        case BLOCKS_START_Y:
                            block_start_y = Integer.parseInt(value);
                            currentLevel.setBlock_start_y(Integer.parseInt(value));
                            break;
                        case ROW_HEIGHT:
                            row_height = Integer.parseInt(value);
                            currentLevel.setRow_height(Integer.parseInt(value));
                            break;
                        case NUM_BLOCKS:
                            num_blocks = Integer.parseInt(value);
                            currentLevel.setNum_blocks(Integer.parseInt(value));
                            break;
                    }//end of switch
                }
            }// end of reading one level
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

    /**
     * @param value original string.
     * @param start start of the string we want.
     * @param end   end of the string we want.
     * @return a substring between start and end.
     */
    public String betterSubstring(String value, String start, String end) {
        return value.substring(start.length(), value.length() - end.length());
    }

    private void setBackground(Sprite b) {
        this.background = b;
    }

    public void setPaddle_speed(int paddle_speed) {
        this.paddle_speed = paddle_speed;
    }

    public void setPaddle_width(int paddle_width) {
        this.paddle_width = paddle_width;
    }

    public void setNum_blocks(int num_blocks) {
        this.num_blocks = num_blocks;
    }

    public void setBlock_start_x(int block_start_x) {
        this.block_start_x = block_start_x;
    }

    public void setBlock_start_y(int block_start_y) {
        this.block_start_y = block_start_y;
    }

    public void setRow_height(int row_height) {
        this.row_height = row_height;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public void setBlock_definitions(String block_definitions) {
        this.block_definitions = block_definitions;
    }

    public void setVelocities(List<Velocity> velocities) {
        this.velocities = velocities;
    }

    public int getPaddle_speed() {
        return paddle_speed;
    }

    public int getPaddle_width() {
        return paddle_width;
    }

    public int getNum_blocks() {
        return num_blocks;
    }

    public int getBlock_start_x() {
        return block_start_x;
    }

    public int getBlock_start_y() {
        return block_start_y;
    }

    public int getRow_height() {
        return row_height;
    }

    public String getLevel_name() {
        return level_name;
    }

    public String getBlock_definitions() {
        return block_definitions;
    }

    public List<Velocity> getVelocities() {
        return velocities;
    }

    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddle_speed;
    }

    @Override
    public int paddleWidth() {
        return this.paddle_width;
    }

    @Override
    public String levelName() {
        return this.level_name;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.num_blocks;
    }
}
