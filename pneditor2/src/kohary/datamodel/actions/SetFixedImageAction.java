/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.DImage;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class SetFixedImageAction extends Action {

    public SetFixedImageAction() {
                   String name = "Set fix";
                putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		//putValue(SMALL_ICON, GraphicsTools.getIcon());
		putValue(SHORT_DESCRIPTION, "Set fix");
		//putValue(MNEMONIC_KEY, KeyEvent.VK_F);
    }

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);        
        DImage image = (DImage) selectedElement;
        image.changeFix();       
    }
}
