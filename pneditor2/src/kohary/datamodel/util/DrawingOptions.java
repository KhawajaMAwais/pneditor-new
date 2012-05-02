/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.util;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Map;
import kohary.datamodel.dapi.Element;


/**
 *
 * @author Godric
 */
public class DrawingOptions {
    
    private Map<Element, Color> colorSetting = new Hashtable<Element,Color>();

    public Map<Element, Color> getColorSetting() {
        return colorSetting;
    }

    public void setColorSetting(Map<Element, Color> colorSetting) {
        this.colorSetting = colorSetting;
    }
    
    
    
}
