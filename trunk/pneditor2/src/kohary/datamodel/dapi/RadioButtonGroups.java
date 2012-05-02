/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Godric
 */
public class RadioButtonGroups implements Serializable {

    public RadioButtonGroups() {
    }
    
    private List<RadioButtonGroup> radioButtonGroups = new ArrayList<RadioButtonGroup>();

    public List<RadioButtonGroup> getRadioButtonGroups() {
        return radioButtonGroups;
    }

    public void setRadioButtonGroups(List<RadioButtonGroup> radioButtonGroups) {
        this.radioButtonGroups = radioButtonGroups;
    }

    public RadioButtonGroup getRadioButtonGroupByDRadioButton(DRadioButton radioButton){
        for(RadioButtonGroup radioButtonGroup: radioButtonGroups){
            if(radioButtonGroup.containRadioButton(radioButton))
                return radioButtonGroup;
        }
        return null;
    }
    
    public void setSelectionInGroupByDRadioButton(DRadioButton radioButton){
        RadioButtonGroup radioButtonGroup = getRadioButtonGroupByDRadioButton(radioButton);
        radioButtonGroup.setSelectedRadioButton(radioButton);
    }
    
    public RadioButtonGroup AddNewWithReturn(){
        RadioButtonGroup radioButtonGroup = new RadioButtonGroup();
        radioButtonGroups.add(radioButtonGroup);
        return radioButtonGroup;
    }
    public void AddNew(){
      
        radioButtonGroups.add( new RadioButtonGroup());

    }


}
