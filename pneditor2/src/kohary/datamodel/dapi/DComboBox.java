/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class DComboBox extends Input {

    public DComboBox(Point start) {
        setStart(start);
        setSize(150, 25);
    }
    List<ComboBoxItem> items = new ArrayList<ComboBoxItem>();

    public List<ComboBoxItem> getItems() {
        return items;
    }

    @Override
    public void draw(Graphics g, DrawingOptions drawingOptions) {

        DatamodelCreator.getInstance().getDefaultSettingsManager().getDefaultTemplate().drawDComboBox(g, drawingOptions, this);


    }
}