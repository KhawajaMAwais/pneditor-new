/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import kohary.datamodel.export.ForWorkflowExporter;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class CreateHtmlAction extends Action{

    public CreateHtmlAction() {
                String name = "Create workflow export";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_H);
    }
    
        

    public void actionPerformed(ActionEvent e) {
        try {
            new ForWorkflowExporter();
        } catch (Exception ex) {
            Logger.getLogger(CreateHtmlAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
