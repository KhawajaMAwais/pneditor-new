/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SelectDCheckBoxAction extends Action{

    public SelectDCheckBoxAction() {
            
        String name = "Checkbox";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("checkBox.png"));
		putValue(SHORT_DESCRIPTION, "Checkbox");
		//putValue(MNEMONIC_KEY, KeyEvent.VK_);
    }
    
    

    public void actionPerformed(ActionEvent e) {
       DatamodelCreator.getInstance().getSelectionManager().setCheckBox_selection();
    }
    
}
