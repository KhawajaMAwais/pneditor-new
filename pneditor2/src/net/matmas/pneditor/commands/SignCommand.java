/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.matmas.pneditor.commands;

import java.util.ArrayList;
import java.util.Set;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author JONNY
 */
public class SignCommand implements Command {
    private PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
    private  ArrayList<Place> places = new ArrayList<Place>();
    private  ArrayList<Transition> transition = new ArrayList<Transition>();

    public SignCommand(ArrayList<Place> places,ArrayList<Transition> tran) {
		this.places.addAll(places);
                this.transition = tran;

	}

    public void execute() {
		for (Place p : petriNet.getPlaces()) {
                       for (Place pp : places) {
                         if(pp.equals(p)){
			 p.getLabel().setText(p.getId());
                           }
                       }
		}
                for (Transition t : petriNet.getTransitions()) {
			for (Transition tt : transition) {
                          if(tt.equals(t)){
                            t.getLabel().setText(t.getId());
                           }
                       }
		}
	}

	public void undo() {
		for (Place p : petriNet.getPlaces()) {
                       for (Place pp : places) {
                         if(pp.equals(p)){
			 p.getLabel().setText(null);
                           }
                       }
		}
                for (Transition t : petriNet.getTransitions()) {
			for (Transition tt : transition) {
                          if(tt.equals(t)){
                            t.getLabel().setText(null);
                           }
                       }
		}
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Sign";
	}

}
