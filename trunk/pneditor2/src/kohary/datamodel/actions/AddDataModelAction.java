/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.dapi.DataModel;
import kohary.datamodel.util.DataModelList;
import kohary.datamodel.util.GraphicsTools;

/**
 *
 * @author Godric
 */
public class AddDataModelAction extends Action{

    public AddDataModelAction() {
                String name = "Add datamodel";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("add.gif"));
		putValue(SHORT_DESCRIPTION, "");
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
    }
    
    

    public void actionPerformed(ActionEvent e) {
        DatamodelCreator.getInstance().getDocument().getDataModels().addNew();
        DatamodelCreator.getInstance().getMainFrame().getSelectDataModelPanel().getListPanel().setListModel(DatamodelCreator.getInstance().getDocument().getDataModels());
       
        DataModelList list= DatamodelCreator.getInstance().getMainFrame().getSelectDataModelPanel().getListPanel().getList();
        list.setSelectedIndex(list.getComponentCount()-1);
        
      
       
        
    }
    
}
