/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;

import java.awt.Graphics;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;

/**
 *
 * @author Godric
 */
public class DataModelsFeature extends Feature{

    public DataModelsFeature(Canvas canvas) {
        super(canvas);
    }
      @Override
    public void drawMainLayer(Graphics g) {
     
      }

    @Override
    public void drawBackground(Graphics g) {
        DataModel selectedDataModel =(DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();          
          selectedDataModel.getPage().draw(g, canvas.getDrawingOptions());
    }

  

    @Override
    public void drawForeground(Graphics g) {
         DataModel selectedDataModel =(DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();          
          selectedDataModel.draw(g,canvas.getDrawingOptions());
    }
    
}
