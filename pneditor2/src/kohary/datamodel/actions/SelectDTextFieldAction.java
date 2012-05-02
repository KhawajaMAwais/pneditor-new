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
public class SelectDTextFieldAction extends Action{

    public SelectDTextFieldAction() {
        	String name = "Textfield";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("textField.png"));
		putValue(SHORT_DESCRIPTION, "Textfield");
		putValue(MNEMONIC_KEY, KeyEvent.VK_F);
        
    }
    

    public void actionPerformed(ActionEvent e) {
        DatamodelCreator.getInstance().getSelectionManager().setTextField_selection();
    }
    
}
