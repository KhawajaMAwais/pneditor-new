/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class RenameDataModelAction extends Action{

    public RenameDataModelAction() {
              String name = "Rename";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);		
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
    }

    public void actionPerformed(ActionEvent e) {
        String str;
               str = JOptionPane.showInputDialog(null, "Enter new Datamodel name : ",
                "Datamodel name", 1);

        if ((str != null)&&(!str.equals(""))) {
         DataModel datamodel=(DataModel) DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
         datamodel.setName(str);
         
        DatamodelCreator.getInstance().getMainFrame().validate();
        DatamodelCreator.getInstance().getMainFrame().repaint();
        }
  
    }
    
    
}
