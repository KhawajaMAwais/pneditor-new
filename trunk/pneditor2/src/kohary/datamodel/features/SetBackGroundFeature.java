/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.features;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DPage;

/**
 *
 * @author Godric
 */
public class SetBackGroundFeature extends Feature {

    public SetBackGroundFeature(Canvas canvas) {
        super(canvas);
    }

    private void drawBackgroundColor(Graphics g) {
        DPage page = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getPage();

        Color color = page.getBackGroundColor();
        if (color != Color.white) {
            int width = page.getResolution().getWidth();
            int height = page.getResolution().getHeight();

            Color oldColor = g.getColor();
            g.setColor(color);
            g.fillRect(0, 0, width, height);
            g.setColor(oldColor);
        }

    }

    @Override
    public void drawBackground(Graphics g) {
        drawBackgroundColor(g);

    }

    @Override
    public void drawMainLayer(Graphics g) {
    }

    @Override
    public void drawForeground(Graphics g) {
    }
}
