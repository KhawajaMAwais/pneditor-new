/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.commands.Command;
import kohary.datamodel.commands.SetDefaultBooleanCommand;
import kohary.datamodel.commands.SetLabelCommand;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DCheckBox;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SetDefaultBooleanValueAction extends Action {

    public SetDefaultBooleanValueAction() {

        String name = "Set default value";
        putValue(NAME, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("setbool.gif"));
        putValue(SHORT_DESCRIPTION, name);
    }

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        Attribute attribute = (Attribute) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getAttributeByElement(selectedElement);
        DCheckBox checkBox = (DCheckBox) attribute.getInput();
        if (selectedElement != null) {

            String label = "";

            Boolean[] values = {true, false};

            Boolean newValue = (Boolean) JOptionPane.showInputDialog(
                    mainFrame,
                    "Set default value\n",
                    "Set default value",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    values,
                    checkBox.getDefaultValue());
            if (newValue != null) {
                Command command = new SetDefaultBooleanCommand(attribute, newValue);
                DatamodelCreator.getInstance().getUndoManager().executeCommand(command);
                DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
            }

        }
    }
}
