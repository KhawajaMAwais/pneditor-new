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
public class SelectSelectionAction extends Action{

    public SelectSelectionAction() {
        
          String name = "Select";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("select.gif"));
		putValue(SHORT_DESCRIPTION, "Textpane");
		putValue(MNEMONIC_KEY, KeyEvent.VK_L);
    }
    
    

    public void actionPerformed(ActionEvent e) {
        DatamodelCreator.getInstance().getSelectionManager().setSelect_selection();
    }
    
}
