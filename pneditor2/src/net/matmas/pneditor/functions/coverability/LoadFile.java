package net.matmas.pneditor.functions.coverability;
import java.util.ArrayList;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;



public class LoadFile {

	public static Net load() {
            ArrayList<net.matmas.pneditor.functions.coverability.Place> places = new ArrayList<Place>();
            ArrayList<net.matmas.pneditor.functions.coverability.Transition> transitions = new ArrayList<Transition>();
            ArrayList<Arc> arcs = new ArrayList<Arc>();
            PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
            for(net.matmas.pnapi.Place p : petriNet.getPlaces()){
            
            net.matmas.pnapi.Marking mark = petriNet.getMarking();
            places.add(new Place(p.getId(), p.getCenter().getX(),p.getCenter().getY(), mark.getTokens(p), p.isStatic(),p.getLabel().toString()));
            }
            for(net.matmas.pnapi.Transition t : petriNet.getTransitions()){
            if(t.getLabel().getText()==null){
               t.getLabel().setText(t.getId());
            }
            transitions.add(new Transition(t.getId(), t.getCenter().getX(),t.getCenter().getY(), t.getLabel().getText()));
            }

            for(net.matmas.pnapi.Arc a : petriNet.getArcs()){
           
            arcs.add(new Arc(a.getSource().getId(),a.getDestination().getId(), a.getMultiplicity()));
            }
            Net net = new Net(places, transitions, arcs);
            return net;



	}

}