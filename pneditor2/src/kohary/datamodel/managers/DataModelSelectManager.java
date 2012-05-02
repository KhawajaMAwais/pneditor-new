/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.managers;

import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class DataModelSelectManager {
    
        
    private DataModel currentDataModel = new DataModel();

    public DataModel getCurrentDataModel() {
        return currentDataModel;
    }

    public void setCurrentDataModel(DataModel currentDataModel) {
        DatamodelCreator.getInstance().getMainFrame().getLayerUI().changeDataModel();
        this.currentDataModel = currentDataModel;  
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().setPreferredSize(currentDataModel.getPage().getResolution().getDimension());                
        revalidateList();
    }
    public void revalidateList(){
        DatamodelCreator.getInstance().getMainFrame().getSelectDataModelPanel().getListPanel().getList().revalidate();
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().revalidate();
    }
    
    
    
    
    
}
