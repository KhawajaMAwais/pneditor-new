/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.attribute.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Godric
 */
public class EditConfirmAction implements ActionListener{
    private AttributeLine atbLine;
    private Attribute attribute;
    private EditAttributeFrame eatbFrame;
    
    public EditConfirmAction(EditAttributeFrame eatbFrame,Attribute attribute) {
       
        this.attribute=attribute;
        this.eatbFrame=eatbFrame;

    }

    public void actionPerformed(ActionEvent e) {
        
        attribute.setLabel(eatbFrame.getEditPanel().getLabelField().getText());
        attribute.setType((String)eatbFrame.getEditPanel().getTypes().getSelectedItem());
        attribute.setInterpreter((String) eatbFrame.getEditPanel().getInterpreter().getSelectedItem());
        eatbFrame.getAtbLine().getLabel().setText(attribute.getLabel());
        eatbFrame.getAtbLine().getType().setText(attribute.getType());
        eatbFrame.getAtbLine().getInterpreter().setText(attribute.getInterpreter());

       

        eatbFrame.setVisible(false);
    }

}
