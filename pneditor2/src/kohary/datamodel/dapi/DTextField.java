/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.DrawingOptions;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class DTextField extends Input{
    private JTextField textField;
        
    public DTextField(Point start){
        setStart(start);
        setSize(200,25);
    }
    
	public void draw(Graphics g,DrawingOptions drawingOptions) {
            
            DatamodelCreator.getInstance().getDefaultSettingsManager().getDefaultTemplate().drawDTextField(g, drawingOptions, this);
}
    
}
