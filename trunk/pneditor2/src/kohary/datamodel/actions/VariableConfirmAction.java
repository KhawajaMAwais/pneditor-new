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
public class VariableConfirmAction implements ActionListener{
    private VariableLine atbLine;
    private Dvariable variable;
    private EditVariableFrame eatbFrame;
    
    public VariableConfirmAction(EditVariableFrame eatbFrame,Dvariable variable) {
       
        this.variable=variable;
        this.eatbFrame=eatbFrame;

    }

    public void actionPerformed(ActionEvent e) {
        
        variable.setLabel(eatbFrame.getEditPanel().getLabelField().getText());
        variable.setType((String)eatbFrame.getEditPanel().getTypes().getSelectedItem());       
        eatbFrame.getAtbLine().getLabel().setText(variable.getLabel());
        eatbFrame.getAtbLine().getType().setText(variable.getType());
       

       

        eatbFrame.setVisible(false);
    }

}
