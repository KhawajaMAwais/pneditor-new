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
public class RightsStartFrameAction implements ActionListener{
    private RightsFrame rightFrame;

    public void actionPerformed(ActionEvent e) {
        rightFrame = new RightsFrame();
    }

}
