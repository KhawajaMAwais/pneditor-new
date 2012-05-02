/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kohary.datamodel.variables.EditVariableFrame;


/**
 *
 * @author Godric
 */
public class VariableCancelAction implements ActionListener{
    private EditVariableFrame eatbFrame;
    public VariableCancelAction(EditVariableFrame eatbFrame) {
        this.eatbFrame= eatbFrame;
    }

    public void actionPerformed(ActionEvent e) {
       eatbFrame.setVisible(false);
    }

}
