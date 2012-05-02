/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import kohary.datamodel.template.basic.image.BasicTemplate;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class DPage implements Design,Serializable {

    private List<Design> designElements = new ArrayList<Design>();

    public List<Design> getDesignElements() {
        return designElements;
    }
    //---------------------------------------------------------------
    private BasicTemplate template = null;

    public BasicTemplate getTemplate() {
        return template;
    }

    public void setTemplate(BasicTemplate template) {
        this.template = template;
    }
    
    //-----------------------------------------------------------
    
    private Resolution resolution = new Resolution(0, 0);

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }
    
    
    //-----------------------------------------------------------
    
    private Color backGroundColor = Color.white;

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
    
    
    

    //-----------------------------------------------------------
    public void draw(Graphics g, DrawingOptions drawingOptions) {
        if(template != null){
            template.draw(g, drawingOptions);
        }

        for (Design designElement : designElements) {
            designElement.draw(g, drawingOptions);
        }

    }
}
