/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DButton;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SetNextDataModelAfterFireTransition extends  Action{


    public SetNextDataModelAfterFireTransition() {
          String name = "Action";
      		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("next.jpg"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        
    }

    public void actionPerformed(ActionEvent e) {
          Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        Attribute attribute = (Attribute) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributeByElement(selectedElement);
        
        if(attribute.getInput() instanceof DButton){
        DButton button = (DButton)attribute.getInput();
        button.getFireActionMethod().chooseFromAvaible();
        }
    }


}
