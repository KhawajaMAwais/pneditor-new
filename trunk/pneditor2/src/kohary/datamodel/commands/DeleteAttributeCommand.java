/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class DeleteAttributeCommand implements Command{
    
       private Attribute attribute;
   
    private DataModel dataModel;

    public DeleteAttributeCommand(Attribute attribute, DataModel dataModel) {
        this.attribute = attribute;
        this.dataModel = dataModel;
    }
   
    

    public void execute() {
        dataModel.getAttributes().remove(attribute);
    }

    public void undo() {
        dataModel.getAttributes().add(attribute);
    }

    public void redo() {
         dataModel.getAttributes().remove(attribute);
    }

    public String actionName() {
        return "Delete attribute";
    }
    
}
