/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import kohary.datamodel.Canvas;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class RulerSwitchAction extends Action{

    public RulerSwitchAction() {
        	String name = "Ruler";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("ruler.png"));
		putValue(SHORT_DESCRIPTION, "Ruler");
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
      
    }
    
    private Canvas canvas;

    public void actionPerformed(ActionEvent e) {
       canvas= DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas();
       DatamodelCreator.getInstance().getSelectionManager().switchRuler();
       canvas.repaint();
   
    }
    
}
