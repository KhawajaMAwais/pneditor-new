/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class DCheckBox extends Input implements IsBooleanType{    
    
   
    
    public DCheckBox(Point start) {
        setStart(start);
        setSize(15  , 15);
        setIsResizable(false);
    }

    @Override
   	public void draw(Graphics g,DrawingOptions drawingOptions) {
	DatamodelCreator.getInstance().getDefaultSettingsManager().getDefaultTemplate().drawDCheckBox(g, drawingOptions, this);
	}

    public void setDefaultValue(boolean value) {
        this.defaultValue=value;
    }

    public boolean getDefaultValue() {
        return defaultValue;
    }
}
