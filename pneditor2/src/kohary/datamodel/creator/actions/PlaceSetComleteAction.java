/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.actions;

import kohary.datamodel.creator.home.InterfaceOfDataModels;
import kohary.datamodel.creator.position.ConnectArc;
import kohary.datamodel.creator.position.PlaceSetFrame;
import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;

/**
 *
 * @author Godric
 */
public class PlaceSetComleteAction implements ActionListener {

    protected PlaceSetFrame frame;
    protected InterfaceOfDataModels root;
    protected Set<Transition> transitions;

    public PlaceSetComleteAction(PlaceSetFrame frame, InterfaceOfDataModels root) {
        this.frame = frame;
        this.root = root;
    }

    public void actionPerformed(ActionEvent e) {

        importPlaceFromArcsToDataModels();
    }

    public void importPlaceFromArcsToDataModels() {

        for (DataModel dataModel : root.getDataModels()) {
            for (RoleDefinitionProperty role : frame.getPetriNet().getProperties().getFilteredByClass(RoleDefinitionProperty.class)) {
                transitions = new HashSet<Transition>();
                for (ConnectArc arc : frame.getCanvas().getArcs()) {
                    if ((arc.getRole().equals(role)) && (arc.getDataModel().equals(dataModel))) {
                        transitions.add(arc.getTransition());
                    }
                }
                
                if (!transitions.isEmpty()) {
                    dataModel.getPosition().put(role, transitions);
                   // dataModel.getPosition().get(role).addAll(transitions);

                }
            }
        }

        frame.setVisible(false);
    }
}
