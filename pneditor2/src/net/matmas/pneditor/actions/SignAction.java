/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.commands.SignCommand;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author JONNY
 */
public class SignAction extends Action {

	public SignAction() {
		String name = "Token flow";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Sign.png"));
		putValue(SHORT_DESCRIPTION, name);
	}

        public void actionPerformed(ActionEvent e) {
           ArrayList<Place> places = new ArrayList<Place>();
           ArrayList<Transition> transitions = new ArrayList<Transition>();;
            PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
            for(Transition t : petriNet.getTransitions()){
                if(t.getLabel().getText()==null){
                   transitions.add(t);
                }
            }
            for(Place p : petriNet.getPlaces()){
                if(p.getLabel().getText()==null){
                   places.add(p);
                }
            }
              PNEditor.getInstance().getUndoManager().executeCommand(new SignCommand(places, transitions));

	}


}
