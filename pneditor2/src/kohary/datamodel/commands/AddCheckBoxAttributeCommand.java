/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.DLabel;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class AddCheckBoxAttributeCommand implements Command{
    
            private Attribute attribute;
    private Point location;
    private DataModel dataModel;

    public AddCheckBoxAttributeCommand(Point location, DataModel dataModel) {
       this.location=location;
        this.dataModel = dataModel;
    }    

     public void execute() {
        DCheckBox checkBox  = new DCheckBox(location);       
        attribute = new Attribute(checkBox, new DLabel(checkBox));        
        dataModel.getAttributes().add(attribute);
    }

    public void undo() {
        new DeleteAttributeCommand(attribute, dataModel).execute();
    }

    public void redo() {
        dataModel.getAttributes().add(attribute);
    }

    public String actionName() {
       return "Add Button";
    }
    
}
