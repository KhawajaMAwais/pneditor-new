/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.GraphicsTools;
import kohary.datamodel.util.StringTool;

import net.matmas.util.CachedGraphics2D;

/**
 *
 * @author Godric
 */
public class DLabel extends Text {

    public DLabel(Input input) {
        setInput(input);
    }

    public DLabel() {
    }
    private Input input;

    public Point getInputStart() {
        return input.getStart();
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public void setLocation(Graphics g) {
        setEnd(new Point(input.getStart().x - 10, input.getEnd().y));
        setStart(new Point(input.getStart().x - (StringTool.getStringWidth(g.getFontMetrics(), this.getText())+10), input.getStart().y  ));

    }

    @Override
    public void setSize(int width, int height) {
        setStart(new Point((int) getStart().getX() + width, (int) getStart().getY() + height));
    }

    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {
        setLocation(g);
        Color color = Color.black;

        g.setColor(color);
        if (getText() != null && !getText().equals("")) {

            g.drawString(getText(), getStart().x, getStart().y+StringTool.getStringHeight(g.getFontMetrics()));

        }
    }
}
