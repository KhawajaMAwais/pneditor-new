/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.StringTool;

/**
 *
 * @author Godric
 */
public class DTextPane extends Text implements Design {

    public DTextPane(Point start, String text) {
        super();
        setStart(start);
        this.setText(text);
        System.out.println(getId());
    }

    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {


        Color color = drawingOptions.getColorSetting().get(this);

        if (color == null) {
            color = new Color(240, 240, 240); //lightgreen
        }
        final Color colorBlack = Color.BLACK;

        g.setColor(getFontSettings().getColor());
        Font f = new Font(getFontSettings().getFamilyFont(), getFontSettings().getStyleFont(), getFontSettings().getSizeFont());
        Font oldFont = g.getFont();
        g.setFont(f);
        setSize(StringTool.getStringWidth(g.getFontMetrics(), this.getText()), (-1)*StringTool.getStringHeight(g.getFontMetrics()));
    
        g.drawString(getText(), getStart().x, getStart().y);
        g.setFont(oldFont);
        g.setColor(colorBlack);
    }
    
    
}
