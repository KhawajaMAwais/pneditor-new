/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class RemoveDataModelAction extends Action{

    public RemoveDataModelAction() {
              String name = "";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("delete.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }
    

    public void actionPerformed(ActionEvent e) {
        DatamodelCreator.getInstance().getDocument().getDataModels().removeSelected();
        DatamodelCreator.getInstance().getMainFrame().validate();
        DatamodelCreator.getInstance().getMainFrame().repaint();
        
    }
    
}
