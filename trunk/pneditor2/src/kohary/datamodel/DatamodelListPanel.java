/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import kohary.datamodel.actions.ChangeResolutionAction;
import kohary.datamodel.actions.RemoveDataModelAction;
import kohary.datamodel.actions.RenameDataModelAction;
import kohary.datamodel.util.DataModelList;
import kohary.datamodel.util.DataModelPopUpListener;
import kohary.datamodel.util.ListModel;

/**
 *
 * @author Godric
 */
public class DatamodelListPanel extends JPanel  {

    private DataModelList list;

    public DataModelList getList() {
        return list;
    }
  

    public DatamodelListPanel() {
        setLayout(new BorderLayout());        
        list = new DataModelList(new Datamodels());       
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane,BorderLayout.CENTER);
        setUpPopUpMenu();

    }
    
    public void setListModel(ListModel listModel){
        list.setModel(listModel);
    }
    
    //----------------------------------------
    private void setUpPopUpMenu(){
    JPopupMenu popUpMenu = new JPopupMenu();
    JMenuItem removeDataModel = new JMenuItem(new RemoveDataModelAction());
    JMenuItem changeResolution = new JMenuItem(new ChangeResolutionAction());

    JMenuItem rename = new JMenuItem(new RenameDataModelAction());
    
    JMenuItem setAttributeDataModel = new JMenuItem("Attribute setting");
    JMenuItem associateWithTransition = new JMenuItem("Associate with transition");
    
    
    popUpMenu.add(setAttributeDataModel);
    popUpMenu.add(associateWithTransition);
    popUpMenu.addSeparator();
    popUpMenu.add(rename);
    popUpMenu.add(changeResolution);
    popUpMenu.add(removeDataModel);
    
    MouseListener popupListener = new DataModelPopUpListener(list,popUpMenu);
    this.list.addMouseListener(popupListener);
    
    }
    public void refresh(){
         list.setModel(DatamodelCreator.getInstance().getDocument().getDataModels());
    }
    //----------------------------------------
     
 
}
