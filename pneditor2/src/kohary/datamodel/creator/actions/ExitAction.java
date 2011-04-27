/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import net.matmas.pneditor.actions.Action;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class ExitAction extends Action implements ActionListener{
    private JFrame frame;
    
    public ExitAction(JFrame frame) {
        	String name = "Exit";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("datamodel/exit.png"));
		putValue(SHORT_DESCRIPTION, name);
        this.frame=frame;  
        
    }

    public void actionPerformed(ActionEvent e) {
       frame.setVisible(false);
    }

}
