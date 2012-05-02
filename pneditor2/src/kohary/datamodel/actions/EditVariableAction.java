/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kohary.datamodel.variables.VariableLine;
import kohary.datamodel.variables.EditVariableFrame;
import kohary.datamodel.variables.Dvariable;

/**
 *
 * @author Godric
 */
public class EditVariableAction implements ActionListener{
    private EditVariableFrame eatbFrame;
    private Dvariable attribute;
    private VariableLine atbLine;
    
    public EditVariableAction(VariableLine atbLine,Dvariable attribute) {
        this.attribute = attribute;
        this.atbLine=atbLine;
    }

    public void actionPerformed(ActionEvent e) {
        eatbFrame = new EditVariableFrame(atbLine,attribute);
        eatbFrame.getEditPanel().getLabelField().setText(attribute.getLabel());
        eatbFrame.getEditPanel().getTypes().setSelectedItem(attribute.getType());
      
    }

}
