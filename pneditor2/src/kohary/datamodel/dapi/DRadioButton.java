/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.awt.Point;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class DRadioButton extends Input {

    private String value = "undefined";
    private static int counterGen = 0;
    private boolean isDefault = true;
    private boolean selected = false;

    public DRadioButton(Point start) {
        setStart(start);
        setSize(17, 17);
        setIsResizable(false);
        counterGen++;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean isSelected) {
        this.selected = isSelected;
    }

    public void setValue(String value) {
        if (value != null) {
            if (value.equals("")) {
                isDefault = true;
            } else {
                this.value = value;
                isDefault = false;
            }
        }
    }

    public String getValue() {
        if (isDefault) {
            return getDefaultValue();
        } else {
            return value;
        }
    }

    private String getDefaultValue() {
        return value + Integer.toString(counterGen);
    }

    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {
        DatamodelCreator.getInstance().getDefaultSettingsManager().getDefaultTemplate().drawDRadioButton(g, drawingOptions, this);


    }
}
