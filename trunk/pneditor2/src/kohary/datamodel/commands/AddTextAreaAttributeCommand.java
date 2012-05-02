/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

/**
 *
 * @author Godric
 */

import java.awt.Point;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DLabel;
import kohary.datamodel.dapi.DTextArea;

import kohary.datamodel.dapi.DataModel;


public class AddTextAreaAttributeCommand implements Command {

    private Attribute attribute;
    private Point location;
    private DataModel dataModel;

    public AddTextAreaAttributeCommand(Point location, DataModel dataModel) {
        this.location = location;
        this.dataModel = dataModel;
    }
    
    

    public void execute() {
        DTextArea textArea = new DTextArea(location);       
        attribute = new Attribute(textArea, new DLabel(textArea));        
        dataModel.getAttributes().add(attribute);
    }

    public void undo() {
        new DeleteAttributeCommand(attribute, dataModel).execute();
    }

    public void redo() {
        dataModel.getAttributes().add(attribute);
    }

    public String actionName() {
        return "Add TextArea";
    }
}
