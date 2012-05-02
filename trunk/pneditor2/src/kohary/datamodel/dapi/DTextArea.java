/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;

/**
 *
 * @author Godric
 */
public class DTextArea extends Input {

    public DTextArea(Point start) {
        setStart(start);
        setSize(150, 120);
        setIsResizable(true);
    }

    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {
        DatamodelCreator.getInstance().getDefaultSettingsManager().getDefaultTemplate().drawDTextArea(g, drawingOptions, this);

    }
}
