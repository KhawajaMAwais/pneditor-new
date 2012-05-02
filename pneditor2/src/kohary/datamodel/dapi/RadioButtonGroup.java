/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class RadioButtonGroup implements Serializable {

    private static int id = 0;
    

    

    public RadioButtonGroup() {
        id++;
    }
    public RadioButtonGroup(int id) {
        RadioButtonGroup.id=id;
    }
    
     

     //----------------------------------------------------------
    
    List<DRadioButton> radioButtons = new ArrayList<DRadioButton>();

    public List<DRadioButton> getRadioButtons() {
        return radioButtons;
    }

    public void setRadioButtons(List<DRadioButton> radioButtons) {
        this.radioButtons = radioButtons;
    }
    
    //------------------------------------------------------------
    
    public boolean containRadioButton(DRadioButton radiobutton){
        if(radioButtons.contains(radiobutton))
            return true;
        else
            return false;
    }
    
    public void setSelectedRadioButton(DRadioButton radioButton){
        for(DRadioButton radioButtonn : radioButtons){
            if(radioButtonn.equals(radioButton))
                radioButtonn.setSelected(true);
            else
                radioButtonn.setSelected(false);
        }
    }
    
    
    
    //------------------------------------------------------------
    
    public  int getId() {
        return id;
    }

    public void setId(int id) {
        RadioButtonGroup.id = id;
    }
    //------------------------------------------------------------

    private String name = "RadioButtonGroup" + id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name+id;
    }


}
