/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class Attribute implements Serializable{
    static int idCounter=0;
   
    
    public Attribute(Input input, DLabel label) {
        this.input = input;
        this.label = label;
        this.id= idCounter;
        idCounter++;
        
    }
    //-----------------------------------------------------
     private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Attribute.idCounter = id;
    }     
    //------------------------------------------------------    
 
    
    private Input input;

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
    
    
    //------------------------------------------------------
    private DLabel label;
    
    
    public DLabel getLabel() {
        return label;
    }

    public void setLabel(DLabel label) {
        this.label = label;
    }
    
    //------------------------------------------------------
    

    
    
    
    public void draw(Graphics g,DrawingOptions drawingOptions){
        input.draw(g,drawingOptions);
        label.draw(g,drawingOptions);
    }
    
   

    
    
    
}
