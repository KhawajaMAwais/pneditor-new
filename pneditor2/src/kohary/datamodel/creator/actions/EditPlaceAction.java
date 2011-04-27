/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.DataModelPositionPanel;
import kohary.datamodel.creator.home.InterfaceOfDataModels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class EditPlaceAction implements ActionListener {

    private InterfaceOfDataModels root;
    private DataModelPositionPanel placePanel;
    private Transition transition;
    private RoleDefinitionProperty role;
    private Set<Transition> transitions;
    private String action;

    public EditPlaceAction(DataModelPositionPanel placePanel, String action) {
        this.placePanel = placePanel;
        this.root = placePanel.getEditorModel();
        this.action = action;

    }

    public void actionPerformed(ActionEvent e) {
        transitions = new HashSet<Transition>();
        role = (RoleDefinitionProperty) placePanel.getSelectedRole();
        transition = (Transition) placePanel.getSelectedTransition();
        //System.out.println(role.getId()+""+transition.getId());
        if (placePanel.getEditorModel().getDataModels().getSize() > 0) {
            if ((role != null) || (transition != null)) {
               if(root.getSelectedDataModel() != null) {          //Kontrola ci je oznaceny neaky datamodel
                    if (!root.getSelectedDataModel().containRole(role)) {
                        if(action.equals("add")){
                        transitions.add(transition);
                    
                        //Vlozenie place-u do Datamodelu
                        root.getSelectedDataModel().getPosition().put(role, transitions);
                        //update showPlace(zobrazovacieho) panelu
                       
                  
                        }


                    } else {
                         if (action.equals("add")) {
               
                        root.getSelectedDataModel().getPosition().get(role).add(transition);
 
                         }
                         else if(action.equals("remove")){
                             if(root.getSelectedDataModel().getPosition().get(role).size()>1)
                                 root.getSelectedDataModel().getPosition().get(role).remove(transition);
                             else
                                 root.getSelectedDataModel().getPosition().remove(role).clear();
                         }
                         else if(action.equals("removeAll")) {
                          
                                root.getSelectedDataModel().getPosition().clear();
                            
                         }


                    }
                     placePanel.getEditorModel().getDataModelSelectPanel().getShowModelPanel().getPlace().upDatePlace(root.getSelectedDataModel());
               }
               else {
                    JOptionPane.showMessageDialog(placePanel, "Označte Datamodel!");
               }


            }
        } else {
            JOptionPane.showMessageDialog(placePanel, "Vytvorte datamodel!");

        }
    }
}
