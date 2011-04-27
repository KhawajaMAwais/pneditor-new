/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.home;

import kohary.datamodel.creator.position.PlaceSetFrame;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModels;
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

/**
 *
 * @author Godric
 */
public class ShowPositionPanel extends JPanel implements ActionListener {

    private InterfaceOfDataModels root;
    private JLabel placeLabel;
    protected JComboBox rolePlaceBox, transitionsBox, cbTemp;
    private JButton specifyPlace;
    private RoleDefinitionProperty role;
    private DataModel dataModel;
    private int index = 0;
    private PetriNet petriNet;
    private DataModels dataModels;
    public ShowPositionPanel(InterfaceOfDataModels root,PetriNet petriNet) {
        this.root=root;
        this.petriNet=petriNet;
        
        setLayout(new GridLayout(1, 4));
        placeLabel = new JLabel();
        placeLabel.setText("FOR THIS POSITION:");

        rolePlaceBox = new JComboBox();
        transitionsBox = new JComboBox();
        specifyPlace = new JButton("Visual");

        rolePlaceBox.addActionListener(this);
        specifyPlace.addActionListener(this);

       // add()
        add(placeLabel);
        add(rolePlaceBox);
        add(transitionsBox);
       //add(specifyPlace);
        setVisible(false);

    }

    public void upDatePlace(DataModel dataModel) {
        if (!dataModel.getPosition().isEmpty()) {
            rolePlaceBox.removeAllItems();
            transitionsBox.removeAllItems();
            
            this.dataModel = dataModel;
            setRoleComboBox();
            setTransitionComboBox((RoleDefinitionProperty) rolePlaceBox.getSelectedItem());
            revalidate();
            repaint();
        } else {
            rolePlaceBox.removeAllItems();
            transitionsBox.removeAllItems();
        }
    }

    public void setRoleComboBox() {
        for (RoleDefinitionProperty role : dataModel.getPosition().keySet()) {
            rolePlaceBox.addItem(role);
        }
        rolePlaceBox.setSelectedIndex(0);
    }

    public void setTransitionComboBox(RoleDefinitionProperty role) {
        index = 0;

        if (transitionsBox.getItemCount() > 0) {
            transitionsBox.removeAllItems();
        }
        for (Transition transition : dataModel.getPosition().get(role)) {
            transitionsBox.addItem(transition);
        }


    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass().equals(JComboBox.class)) {       
        cbTemp = (JComboBox) e.getSource();
        role = (RoleDefinitionProperty) cbTemp.getSelectedItem();
        if(role != null) {
        System.out.println(role.toString());
        setTransitionComboBox(role);
        }
        }
        if(e.getSource().getClass().equals(JButton.class)) {
            PlaceSetFrame frame=new PlaceSetFrame(petriNet,root);
            frame.getCanvas().loadArcs();
        }
    }

    public InterfaceOfDataModels getRoot() {
        return root;
    }
}
