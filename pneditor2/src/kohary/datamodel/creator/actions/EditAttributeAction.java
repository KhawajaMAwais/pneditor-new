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
public class EditAttributeAction implements ActionListener{
    private EditAttributeFrame eatbFrame;
    private Attribute attribute;
    private AttributeLine atbLine;
    
    public EditAttributeAction(AttributeLine atbLine,Attribute attribute) {
        this.attribute = attribute;
        this.atbLine=atbLine;
    }

    public void actionPerformed(ActionEvent e) {
        eatbFrame = new EditAttributeFrame(atbLine,attribute);
        eatbFrame.getEditPanel().getLabelField().setText(attribute.getLabel());
        eatbFrame.getEditPanel().getTypes().setSelectedItem(attribute.getType());
        eatbFrame.getEditPanel().getInterpreter().setSelectedItem(attribute.getInterpreter());
    }

}
