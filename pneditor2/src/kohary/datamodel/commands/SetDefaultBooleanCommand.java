/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DCheckBox;

/**
 *
 * @author Godric
 */
public class SetDefaultBooleanCommand implements Command{
    
      private Attribute attribute;
    private boolean newValue;
    private boolean oldValue;

    public SetDefaultBooleanCommand(Attribute attribute, boolean newValue) {
        this.attribute = attribute;
        this.newValue = newValue;
    }
    
    

    public void execute() {
        DCheckBox checkBox = (DCheckBox) attribute.getInput();
        this.oldValue = checkBox.getDefaultValue();
        checkBox.setDefaultValue(newValue);
    }

    public void undo() {
         DCheckBox checkBox = (DCheckBox) attribute.getInput();
         checkBox.setDefaultValue(newValue);
    }

    public void redo() {
        execute();
    }

    public String actionName() {
        return "Set default value for chcekbox" ;
    }
    
}
