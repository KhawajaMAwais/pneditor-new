package net.matmas.pneditor.functions.reachability;

import java.util.ArrayList;


public class Net {
	
	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	private ArrayList<Arc> arcs;
	
	public Net(ArrayList<Place> places, ArrayList<Transition> transitions,ArrayList<Arc> arcs) {
		super();
		this.places = places;
		this.transitions = transitions;
		this.arcs = arcs;
	}
	
	public Net(){
		this.places = new ArrayList<Place>();
		this.transitions = new ArrayList<Transition>();
		this.arcs = new ArrayList<Arc>();
	}
	
	public String toString(){
		String output = "Places:\n";
		
		for(Place place : this.places){
			output += place.toString();
		}
		
		output += "\n\nTransitions:\n";
		for(Transition transition : this.transitions){
			output += transition.toString();
		}
		
		output += "\n\nArcs:\n";
		for(Arc arc : this.arcs){
			output += arc.toString();
		}
		
		return output;
	}

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public ArrayList<Arc> getArcs() {
		return arcs;
	}
	
	public void setMarking(Marking marking){
		for(Place p : this.places){
			for(Mark m : marking.getMarking()){
				if(m.getPlaceId().equals(p.getId())){
					p.setTokens(m.getTokens());
				}
			}
		}
	}
	
	public void reverseNet(){
		String destId;
		for(Arc a : this.arcs){
			destId = a.getDestinationId();
			a.setDestinationId(a.getSourceId());
			a.setSourceId(destId);
		}
	}
	
	public boolean transitionsEqualsTo(ArrayList<Transition> firings){
		if(firings.size() != this.transitions.size())
			return false;
		for(Transition t : firings){
			if(!this.transitions.contains(t))
				return false;
		}
		return true;
	}
	
}
