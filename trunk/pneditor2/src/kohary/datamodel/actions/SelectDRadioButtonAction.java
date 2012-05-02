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
public class SelectDRadioButtonAction extends Action{

    public SelectDRadioButtonAction() {
            
        String name = "Radiobutton";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("radiobutton.png"));
		putValue(SHORT_DESCRIPTION, "Radiobutton");
		//putValue(MNEMONIC_KEY, KeyEvent.VK_F);
    }

    public void actionPerformed(ActionEvent e) { 
        DatamodelCreator.getInstance().getSelectionManager().setRadioButton_selection();
    }
    
    
    

    
}
