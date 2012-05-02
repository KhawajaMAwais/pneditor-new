/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.dapi.Resolution;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class ChangeResolutionAction extends Action{

    public ChangeResolutionAction() {
        
            String name = "Change resolution";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("settings.png"));
	
    }
    
    

    public void actionPerformed(ActionEvent e) {
        DataModel dataModel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
        
         
         
            Resolution resolution = (Resolution) JOptionPane.showInputDialog(
                        null,
                        "Select resolution for page of datamodel " + dataModel.getName(),
                        "Resolution",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        DatamodelCreator.getInstance().getDefaultSettingsManager().getResolutions().getResolutions().toArray(),
                        1);
            
            dataModel.getPage().setResolution(resolution);
            DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }
    
}
