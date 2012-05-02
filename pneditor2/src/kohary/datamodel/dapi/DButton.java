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
import kohary.datamodel.util.StringTool;

/**
 *
 * @author Godric
 */
public class DButton extends Input {

    public DButton(Point start) {
        setStart(start);
        setSize(80, 25);
    }
    private String value = "Submit";
    private DButtonActionMethod fireActionMethod = new DButtonActionMethod();
    
    //------------------------------------------------------------------

    public DButtonActionMethod getFireActionMethod() {
        return fireActionMethod;
    }

    public void setFireActionMethod(DButtonActionMethod fireActionMethod) {
        this.fireActionMethod = fireActionMethod;
    }
    
    

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;        
    }

    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {
        DatamodelCreator.getInstance().getDefaultSettingsManager().getDefaultTemplate().drawDButton(g, drawingOptions,this);
    }
}
