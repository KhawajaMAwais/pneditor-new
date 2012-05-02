/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.MainFrame;
import kohary.datamodel.Selection;
import kohary.datamodel.dapi.Attribute;
import kohary.datamodel.dapi.DRadioButton;
import kohary.datamodel.dapi.RadioButtonGroup;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SetDefaultRadioButtonInGroupAction extends Action {

    public SetDefaultRadioButtonInGroupAction() {
        String name = "";
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("selectedRadiobutton.gif"));
        putValue(SHORT_DESCRIPTION, "Textfield");

    }

    public void actionPerformed(ActionEvent e) {
        DataModel dataModel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
        Selection selection = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection();
        Element selectedElement = selection.get(0);
        MainFrame mainFrame = DatamodelCreator.getInstance().getMainFrame();
        
        
        
        dataModel.getRadioButtonGroups().setSelectionInGroupByDRadioButton((DRadioButton)selectedElement);
    
        mainFrame.getModellingBoard().getCanvas().repaint();

    }
}
