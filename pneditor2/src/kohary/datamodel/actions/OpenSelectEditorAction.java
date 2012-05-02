/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.ComboBoxDialog;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.DComboBox;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class OpenSelectEditorAction extends Action {

    public OpenSelectEditorAction() {
        String name = "Item edit";
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        //putValue(SMALL_ICON, GraphicsTools.getIcon("delete16.gif"));
        putValue(SHORT_DESCRIPTION, "Items");
    }

    public void actionPerformed(ActionEvent e) {
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        Element selectedElement = selection.get(0);
        DComboBox comboBox = (DComboBox) selectedElement;
        new ComboBoxDialog(mainFrame, comboBox);
    }
}
