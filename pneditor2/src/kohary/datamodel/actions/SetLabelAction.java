/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.commands.Command;
import kohary.datamodel.commands.SetLabelCommand;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SetLabelAction extends Action {

    public SetLabelAction() {
        String name = "Set label";
        putValue(NAME, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("label.gif"));
        putValue(SHORT_DESCRIPTION, name);
    }

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        Attribute attribute = (Attribute) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributeByElement(selectedElement);
        
        if (selectedElement != null) {

            String label = attribute.getLabel().getText();


            String newLabel = JOptionPane.showInputDialog(mainFrame, "New label:", label);
            if (newLabel != null) {


                Command command = new SetLabelCommand(attribute, newLabel);
                DatamodelCreator.getInstance().getUndoManager().executeCommand(command);
                DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
                
            }

        }
    }
}
