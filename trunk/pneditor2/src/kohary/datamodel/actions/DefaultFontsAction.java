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
public class DefaultFontsAction extends Action{

    public DefaultFontsAction() {
             String name = "Default font";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("settings.png"));
		putValue(SHORT_DESCRIPTION, "");
		
    }
    
    

    public void actionPerformed(ActionEvent e) {
        if(!DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getFontSelectorPanel().isVisible()){
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getFontSelectorPanel().setDefaultFont(DatamodelCreator.getInstance().getDefaultSettingsManager().getFontSetings());
    }else{
            DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getFontSelectorPanel().setVisible(false);
        }
    }
    
}
