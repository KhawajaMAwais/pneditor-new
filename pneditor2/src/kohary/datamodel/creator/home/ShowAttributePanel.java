/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.home;

import kohary.datamodel.creator.attribute.Attribute;
import kohary.datamodel.creator.util.DataModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Godric
 */
public class ShowAttributePanel extends JPanel{
    
    private DataModel dataModel;
    
    
    public ShowAttributePanel(DataModel dataModel) {
       // super(new ImageIcon("src/resources/datamodel/pen.jpg").getImage());
        this.dataModel=dataModel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));       
        
        setVisible(true);
        
    }
    
    public void fillShowPanel(DataModel dataModel) {
        this.removeAll();
        revalidate();
        repaint();
        if(dataModel != null) {
            
        for(Attribute attribute : dataModel.getAttributes()) {
            add(new ShowAttributeLine(attribute));
            add(Box.createRigidArea(new Dimension(5,10)));

            
        }
        }
        revalidate();
    }

}
