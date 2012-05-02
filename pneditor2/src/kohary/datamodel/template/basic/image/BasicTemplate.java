/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.template.basic.image;

import java.awt.Color;
import java.awt.Graphics;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.DTextArea;
import kohary.datamodel.dapi.DTextField;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.StringTool;

/**
 *
 * @author Godric
 */
public class BasicTemplate implements Template {

    private String name = "basic";

    public String getName() {
        return name;
    }

    public void draw(Graphics g, DrawingOptions drawingOptions) {
    }

    public void drawDButton(Graphics g, DrawingOptions drawingOptions, DButton button) {
        Color color = drawingOptions.getColorSetting().get(button);
        Color colorBorder = Color.RED;
        if (color == null) {
            color = new Color(240, 240, 240); //lightgreen
            colorBorder = Color.BLACK;
        }

        g.setColor(color);
        g.fillRect((int) button.getStart().getX(), (int) button.getStart().getY(), button.getWidth() - 1, button.getHeight() - 1);
        g.setColor(colorBorder);
        g.drawRect((int) button.getStart().getX(), (int) button.getStart().getY(), button.getWidth(), button.getHeight());
        g.drawString(button.getValue(), button.getCenter().x - (StringTool.getStringWidth(g.getFontMetrics(), button.getValue()) / 2), button.getCenter().y + (StringTool.getStringHeight(g.getFontMetrics()) / 4));

    }

    public void drawDCheckBox(Graphics g, DrawingOptions drawingOptions, DCheckBox checkBox) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawDComboBox(Graphics g, DrawingOptions drawingOptions, DComboBox comboBox) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawDRadioButton(Graphics g, DrawingOptions drawingOptions, DRadioButton radioButton) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawDTextArea(Graphics g, DrawingOptions drawingOptions, DTextArea textArea) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void drawDTextField(Graphics g, DrawingOptions drawingOptions, DTextField textField) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
