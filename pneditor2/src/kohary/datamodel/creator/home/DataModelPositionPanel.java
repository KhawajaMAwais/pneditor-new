/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import kohary.datamodel.creator.actions.EditPlaceAction;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;
import net.matmas.pnapi.properties.RoleProperty;

/**
 *
 * @author Godric
 */
public class DataModelPositionPanel extends JPanel implements ActionListener {

    private JLabel rolLab, transLab; //roles/transitions Label
    private ListEditorModel editorModel;
    private JComboBox rolesBox, transitionsBox, cbTemp;
    private JButton addPlace,removePlace,removeAll;
    private PetriNet petriNet;
    private String[] rolesString, transitionsString = {};
    private String roleName;
    private int index = 0;
    protected List<RoleDefinitionProperty> roles;
    protected List<Transition> transitions;

    public DataModelPositionPanel(ListEditorModel editorModel,PetriNet petriNet) {
        this.petriNet=petriNet;
        this.editorModel=editorModel;
        roles=petriNet.getProperties().getFilteredByClass(RoleDefinitionProperty.class);
        transitions=petriNet.getTransitions();
        
        setLayout(new GridLayout(1, 7));
        setSize(100, 80);
        rolLab = new JLabel("Role:");
        transLab = new JLabel("Transition:");

        addPlace = new JButton("Add");
        addPlace.addActionListener(new EditPlaceAction(this,"add"));

        removePlace = new JButton("Remove");
        removePlace.addActionListener(new EditPlaceAction(this,"remove"));

        removeAll = new JButton("RemoveAll");
        removeAll.addActionListener(new EditPlaceAction(this, "removeAll"));

        rolesBox = new JComboBox(getNameRoles(roles));

        rolesBox.setSelectedItem(roles.get(0));
        transitionsBox = new JComboBox(transitionsString);
        rolesBox.addActionListener(this);

        updateSelectableTransitions((String) rolesBox.getSelectedItem());


        add(rolLab);
        add(rolesBox);
        add(transLab);
        add(transitionsBox);
        add(removePlace);
        add(removeAll);
        add(addPlace);
        setVisible(false);
    }

    public ListEditorModel getEditorModel() {
        return editorModel;
    }

    private String[] getNameRoles(List<RoleDefinitionProperty> roles) {    ///Zmena jednotlivych RoleName na String

        rolesString = new String[roles.size()];
        index = 0;
        for (RoleDefinitionProperty role : roles) {
            rolesString[index] = role.toString();
            index++;
        }
        return rolesString;
    }

    public void actionPerformed(ActionEvent e) {
        cbTemp = (JComboBox) e.getSource();
        roleName = (String) cbTemp.getSelectedItem();
        updateSelectableTransitions(roleName);

    }

    private void updateSelectableTransitions(String roleName) {

        index = 0;

        if (transitionsBox.getItemCount() > 0) {
            transitionsBox.removeAllItems();
        }
        for (RoleDefinitionProperty role : petriNet.getProperties().getFilteredByClass(RoleDefinitionProperty.class)) {

            if (role.toString().equals(roleName)) {
                  
                for (Transition transition : petriNet.getTransitions()) {


                    // System.out.println(transition.getProperties().getFilteredByClass(RoleProperty.class));
                    for (RoleProperty rolein : transition.getProperties().getFilteredByClass(RoleProperty.class)) {
                        if (rolein.getRoleId().equals(role.getId())) {

                          
                            transitionsBox.addItem(transition);
                        }


                    }
                    index++;

                }
            }
          
        }


    }

    public List<RoleDefinitionProperty> getRoles() {
        return roles;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }
    

    public JComboBox getRolesBox() {
        return rolesBox;
    }

    public JComboBox getTransitionsBox() {
        return transitionsBox;
    }
    public RoleDefinitionProperty getSelectedRole() {
        for (RoleDefinitionProperty role : roles) {
            if(role.toString().equals(this.getRolesBox().getSelectedItem()))
                return role;
        }
        System.out.println("Ziadna zhoda-Role");
        return null;       
    }
    public Transition getSelectedTransition() {
        for (Transition transition : transitions) {
            if(transition.equals(this.getTransitionsBox().getSelectedItem()))
                return transition;
        }
        System.out.println("Ziadna zhoda-Transition");
        return null;
    }
}
