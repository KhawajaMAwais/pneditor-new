/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.position;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import kohary.datamodel.actions.SetNextDataModelAfterFireTransition;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class PlaceSetFrame extends JFrame implements ActionListener{

    
    private PositionCanvas canvas;
    private PetriNet petriNet;
    private JScrollPane pane;  
    private JToolBar toolBar;
    private JButton confirmButton;
    
    protected JComboBox roleBox,cbTemp;

    public PlaceSetFrame(PetriNet petriNet) {
        this.petriNet = petriNet;        
        setSize(300, 800);
        setLayout(new BorderLayout());
        setTitle("Set position");

  
        canvas = new PositionCanvas(petriNet, this);
        
        pane = new JScrollPane(canvas);
        add(pane, BorderLayout.CENTER);
      
        setVisible(true);
        setResizable(false);
    }




    public PositionCanvas getCanvas() {
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
