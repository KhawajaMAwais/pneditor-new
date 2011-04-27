/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.position;

import kohary.datamodel.creator.actions.PlaceSetComleteAction;
import kohary.datamodel.creator.home.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class PlaceSetFrame extends JFrame implements ActionListener{

    
    private Canvas canvas;
    private PetriNet petriNet;
    private JScrollPane pane;
    private InterfaceOfDataModels root;
    private JToolBar toolBar;
    private JButton confirmButton;
    
    protected JComboBox roleBox,cbTemp;

    public PlaceSetFrame(PetriNet petriNet, InterfaceOfDataModels root) {
        this.petriNet = petriNet;
        this.root = root;
        setSize(600, 800);
        setLayout(new BorderLayout());
        setTitle("Set position");

        toolBar = new JToolBar();
        confirmButton = new JButton("Complete");
        confirmButton.addActionListener(new PlaceSetComleteAction(this, root));


        roleBox = new JComboBox();
        roleBox.setPreferredSize(new Dimension(30, 20));
        roleBox.addActionListener(this);


        toolBar.add(roleBox);
        toolBar.add(confirmButton);

        canvas = new Canvas(root.getDataModels(), petriNet, this);
        setUpRoleBox();
        pane = new JScrollPane(canvas);
        add(pane, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);
        setVisible(true);
    }

    public void setUpRoleBox() {
        
        for (RoleDefinitionProperty role : petriNet.getProperties().getFilteredByClass(RoleDefinitionProperty.class)) {
            roleBox.addItem(role);            
           
            roleBox.setSelectedItem(role);
            canvas.setVisualPlaces();
        }
       
    }



    public Canvas getCanvas() {
        return canvas;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public void actionPerformed(ActionEvent e) {
       canvas.setVisualPlaces();
    }

    public JComboBox getRoleBox() {
        return roleBox;
    }
}
