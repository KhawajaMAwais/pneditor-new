/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kohary.datamodel.util.DrawingOptions;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author Godric
 */
public class DataModel implements Serializable{

    //----------------------------------------------------
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //---------------------------------------------
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    //---------------------------------------------
    private   List<String> positions = new LinkedList<String> ();

    public List<String> getPosition() {
        return positions;
    }

    public void setPosition(List<String> positions) {
        this.positions = positions;
    }
        

    //----------------------------------------------
    private  List<Attribute> attributes = new LinkedList<Attribute>();

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
    //---------------------------------------------------
    
    
    RadioButtonGroups radioButtonGroups = new RadioButtonGroups();

    public RadioButtonGroups getRadioButtonGroups() {
        return radioButtonGroups;
    }
   
    //-------------------------------------------------------------- 
    
     DPage page = new DPage();

    public DPage getPage() {
        return page;
    }    
    
    //--------------------------------------------------------------
    public void draw(Graphics g,DrawingOptions drawingOptions) {

        
        // DRAWING DESIGN is called in DatamodelFeature as Background
        
        for (Attribute attribute : attributes) {
            attribute.draw(g,drawingOptions);
            
        }
        
        
//		((Graphics2D)g).scale(1.0 / drawingOptions.getZoom(), 1.0 / drawingOptions.getZoom());
    }

    public Attribute getAttributeByElement(Element element) {

        for (Attribute attribute : attributes) {
            if (element instanceof Input) {
                if(attribute.getInput().equals((Input) element))
                    return attribute;
            }
            if(element instanceof Text){
                 if(attribute.getLabel().equals((Text) element))
                    return attribute;
            }
        }
        return null;
    }

    public Element getElementByLocation(Point location) {

        for (Attribute attribute : attributes) {
            
           
            if (attribute.getInput().containsPoint(location)) {
                return attribute.getInput();
            }
        }
        for (Design designElement : page.getDesignElements()){
            if(designElement instanceof DTextPane){
                DTextPane textPane= (DTextPane)designElement;
                if(textPane.containsPoint(location))
                    return textPane;
            }
            if(designElement instanceof DImage){
                DImage image=  (DImage)designElement;
                if(image.containsPoint(location))
                    return image;
            }
        }

        return null;
    }

    //--------------------------------------
    @Override
    public String toString() {
        return name;
    }
    
    public static Transition getTransitionById(String Id){
        return PNEditor.getInstance().getDocument().getPetriNet().getTransitionById(Id);
    }
}
