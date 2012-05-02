/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DPage;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class SetBackgroundColorAction extends  Action implements ChangeListener{

    
    private JColorChooser colorChooser;
    
    public SetBackgroundColorAction() {
                  String name = "Background";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("background.png"));
		putValue(SHORT_DESCRIPTION, "Background");
		putValue(MNEMONIC_KEY, KeyEvent.VK_B);
    }
    
    

    public void actionPerformed(ActionEvent e) {
          DPage page = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getPage();
        
         colorChooser = new JColorChooser(page.getBackGroundColor());
        colorChooser.getSelectionModel().addChangeListener(this);
        colorChooser.setBorder(BorderFactory.createTitledBorder(
                "Choose Text Color"));
        JFrame colorFrame = new JFrame();
        colorFrame.add(colorChooser);
        colorFrame.pack();
        colorFrame.setVisible(true);
        
        
    }

    public void stateChanged(ChangeEvent e) {
         DPage page = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel().getPage();
         
       page.setBackGroundColor(colorChooser.getColor());
       DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }
    
    
}
