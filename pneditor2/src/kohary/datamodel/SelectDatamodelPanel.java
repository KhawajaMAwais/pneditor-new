/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.awt.BorderLayout;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import kohary.datamodel.actions.AddDataModelAction;
import kohary.datamodel.actions.ChangeResolutionAction;
import kohary.datamodel.actions.RemoveDataModelAction;

/**
 *
 * @author Godric
 */
public class SelectDatamodelPanel extends JPanel{
   
    
    public SelectDatamodelPanel() {
        setLayout(new BorderLayout());
       // setPreferredSize(new Dimension(100, 300));
        
        setUpDataModelListPanel();
        setUpDataModelToolBar();
    }
    
    //---------------------------------------------------
    
     private DatamodelListPanel listPanel;

    public DatamodelListPanel getListPanel() {
        return listPanel;
    }
     
     
    
    //----------------------------------------------
    
    
    private void setUpDataModelListPanel(){
        listPanel = new DatamodelListPanel();
        add(listPanel,BorderLayout.CENTER);
    }
    
    
    private Action addDataModelAction = new AddDataModelAction();
    private Action removeDataModelAction = new RemoveDataModelAction();
   
    
    public JToggleButton addDataModel = new JToggleButton(addDataModelAction);
    public JToggleButton removeDatamodel = new JToggleButton(removeDataModelAction);
   // public JToggleButton dButtonButton = new JToggleButton();
    
    //--------------------------------------------
    private JToolBar dataModelToolbar = new JToolBar();
    private void setUpDataModelToolBar(){
        dataModelToolbar.add(addDataModelAction);
        dataModelToolbar.add(removeDatamodel);
        this.add(dataModelToolbar,BorderLayout.NORTH);
    }
    
}
