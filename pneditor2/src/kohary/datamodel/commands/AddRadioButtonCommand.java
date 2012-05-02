/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DLabel;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.RadioButtonGroup;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class AddRadioButtonCommand implements Command {

    private Point location;
    private DataModel dataModel;
    private Attribute attribute;
    private RadioButtonGroup radioButtonGroup;

    public AddRadioButtonCommand(Point location, DataModel dataModel, RadioButtonGroup radioButtonGroup) {
        this.location = location;
        this.dataModel = dataModel;
        this.radioButtonGroup = radioButtonGroup;
    }

    public void execute() {
        DRadioButton radioButton = new DRadioButton(location);
        attribute = new Attribute(radioButton, new DLabel(radioButton));
        radioButtonGroup.getRadioButtons().add(radioButton);
        dataModel.getAttributes().add(attribute);
    }

    public void undo() {
        radioButtonGroup.getRadioButtons().remove((DRadioButton) attribute.getInput());
        new DeleteAttributeCommand(attribute, dataModel).execute();
    }

    public void redo() {
        dataModel.getAttributes().add(attribute);
        radioButtonGroup.getRadioButtons().add((DRadioButton) attribute.getInput());
    }

    public String actionName() {
        return "Add RadioButton";
    }
}
