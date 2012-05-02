/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Element;
import kohary.datamodel.properties.PropertiesDialog;

/**
 *
 * @author Godric
 */
public class OpenPropertiesAction extends Action{

    public OpenPropertiesAction() {
       
        String name = "Properties";
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
        //putValue(SMALL_ICON, GraphicsTools.getIcon("delete16.gif"));
        putValue(SHORT_DESCRIPTION, "Properties");
    }
    
    

    public void actionPerformed(ActionEvent e) {
        Element element = DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().getSelection().get(0);
        DataModel dataModel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
        new PropertiesDialog(dataModel, element);
        
       
    }
    
}
