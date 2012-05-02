/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.DLabel;
import kohary.datamodel.dapi.DTextField;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class AddComboBoxCommand implements Command{
       private Attribute attribute;
    private Point location;
    private DataModel dataModel;

    public AddComboBoxCommand(Point location, DataModel dataModel) {
        this.location = location;
        this.dataModel = dataModel;
    }

   
    
    
    

    public void execute() {
        DComboBox comboBox = new DComboBox(location);
       
        attribute = new Attribute(comboBox, new DLabel(comboBox));
        
        dataModel.getAttributes().add(attribute);
    }

    public void undo() {
        new DeleteAttributeCommand(attribute, dataModel).execute();
    }

    public void redo() {
        dataModel.getAttributes().add(attribute);
    }

    public String actionName() {
        return "Add Combobox";
    }
}
