/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;

import java.awt.Color;
import java.awt.Graphics;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.Resolution;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class RulerFeature extends Feature {
    private int width;
    private int height;
    public RulerFeature(Canvas canvas) {
        super(canvas);
      
    }


    private void drawPixelRuler(Graphics g) {
        g.setColor(new Color(220, 220, 220));
        GraphicsTools.setDashedStroke(g);


        for (int i = 0; i < width; i++) {
            if(i%25==0){
                g.drawLine(i, 0, i, height);
            }
        }
        for (int i = 0; i < height; i++) {
            if(i%25==0){
                g.drawLine(0,i,width,i);
            }
        }
        GraphicsTools.setDefaultStroke(g);
           for (int i = 0; i < width; i++) {
            if(i%50==0){
                g.drawLine(i, 0, i, height);
                g.drawString(i+"px", i+5, 15);
            }
        }
        for (int i = 0; i < height; i++) {
            if(i%50==0){
                g.drawLine(0,i,width,i);
                if(i!=0)
                 g.drawString(i+"px", 5, i+15);
            }
        }
        g.setColor(Color.red);
        Resolution resolution = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getPage().getResolution();
        if(resolution != null){
                g.drawLine(resolution.getWidth(), 0, resolution.getWidth(), resolution.getHeight());
                g.drawLine(0, resolution.getHeight(), resolution.getWidth(),resolution.getHeight() );
        }
    
        
    }

    @Override
    public void drawBackground(Graphics g) {
   
    }

    @Override
    public void drawMainLayer(Graphics g) {
              if(DatamodelCreator.getInstance().getSelectionManager().isRulerSwitchOn()){
         width = canvas.getWidth();
         height = canvas.getHeight();
         drawPixelRuler(g);
         }
    }

    @Override
    public void drawForeground(Graphics g) {
    }
}
