/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.dapi.Input;

/**
 *
 * @author Godric
 */
public class DisabledAction extends Action{

   
    public DisabledAction() {
                String name = "Set Disabled";
                putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		//putValue(SMALL_ICON, GraphicsTools.getIcon());
		putValue(SHORT_DESCRIPTION, "Set Disabled");
               
		//putValue(MNEMONIC_KEY, KeyEvent.VK_F);
    }

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
      MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();        
        Element selectedElement = selection.get(0);        
        Input image = (Input) selectedElement;
        image.changeDisabled();
        
        
    }
    
}
