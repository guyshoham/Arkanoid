package drawer;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

public class FillDrawer implements Drawer {

    private Color color;


    public FillDrawer(Color color) {
        this.color = color;
    }

    @Override
    public void drawAt(DrawSurface d, Rectangle rect) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
}