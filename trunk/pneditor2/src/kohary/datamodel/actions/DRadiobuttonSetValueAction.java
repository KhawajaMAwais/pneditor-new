/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.lang.String;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class DRadiobuttonSetValueAction extends Action{

    public DRadiobuttonSetValueAction() {
        
            String name = "Set value";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("settings.png"));
		putValue(SHORT_DESCRIPTION, "");
    }
    
    

    public void actionPerformed(ActionEvent e) {
                Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
         MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
         Element selectedElement = selection.get(0);
        Attribute attribute = (Attribute) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributeByElement(selectedElement);
         DRadioButton button  = (DRadioButton) attribute.getInput();
     String value = (String)JOptionPane.showInputDialog(
                    mainFrame,
                    "Insert returning value for this choice",
                    "Radio button",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    button.getValue());
     
     button.setValue(value);
    }
    
    
}
