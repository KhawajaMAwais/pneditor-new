/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.attribute;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;

/**
 *
 * @author Godric
 */
public class AttributeRightsLine extends JPanel implements ActionListener{

    private Attribute attribute;
    private AttributePanel atbPanel;
    private JComboBox transiotionBox, rightsBox;
    private final String[] rightString = {"Read-Write", "Read-Only","None"};
    private PetriNet petriNet;
    private Dimension maxSize;

    public AttributeRightsLine(Attribute attribute, AttributePanel atbPanel) {
        this.attribute = attribute;
        this.atbPanel = atbPanel;
        this.petriNet = atbPanel.getAtbEditor().getMfdModel().getListModel().getDataModelSelectPanel().getPetriNet();
        setLayout(new GridLayout(1, 2));
        maxSize = new Dimension(350, 20);

        setMaximumSize(maxSize);
        transiotionBox = new JComboBox();
        transiotionBox.setActionCommand("transit");
        transiotionBox.addActionListener(this);

        rightsBox = new JComboBox(rightString);
        rightsBox.setSelectedIndex(0);
        rightsBox.setActionCommand("right");
        rightsBox.addActionListener(this);
        
        setUpCombo(petriNet.getTransitions());

        add(transiotionBox);
        add(rightsBox);


        setVisible(true);


    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setUpCombo(List<Transition> transitions) {
      // getTransiotionBox().removeAllItems();
        for (Transition transition : transitions) {
            getTransiotionBox().addItem(transition.toString());

        }
        getTransiotionBox().setSelectedIndex(0);

    }

    public JComboBox getTransiotionBox() {
        return transiotionBox;
    }

    public JComboBox getRightsBox() {
        return rightsBox;
    }

    public void actionPerformed(ActionEvent e) {
        /*if(e.getActionCommand().equals("right")) {
        if(!attribute.getRights().containsKey((String)getTransiotionBox().getSelectedItem())) {
        attribute.getRights().put((String)getTransiotionBox().getSelectedItem(), (String)getRightsBox().getSelectedItem());
        }
        }*/
    }
}
