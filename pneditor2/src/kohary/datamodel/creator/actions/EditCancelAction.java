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
public class EditCancelAction implements ActionListener{
    private EditAttributeFrame eatbFrame;
    public EditCancelAction(EditAttributeFrame eatbFrame) {
        this.eatbFrame= eatbFrame;
    }

    public void actionPerformed(ActionEvent e) {
       eatbFrame.setVisible(false);
    }

}
