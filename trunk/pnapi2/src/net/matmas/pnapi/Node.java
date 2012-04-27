package net.matmas.pnapi;

import net.matmas.pnapi.editor.Movable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author matmas
 */
public abstract class Node extends Element implements Cloneable, Movable, Comparable<Node> {

	public Node() {
		setSize(30, 30);
		label = new Label(this);
	}

	@Override
	public void setPetriNet(PetriNet petriNet) {
		super.setPetriNet(petriNet);
		petriNet.getIdGenerator().setUniqueId(this);
	}

        
	public void setPetriNet(PetriNet petriNet, String id) {
		super.setPetriNet(petriNet);
		this.id = id;
	}

	// -------------------------------------------------------------------------
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// -------------------------------------------------------------------------

	private Label label;

	public Label getLabel() {
		return label;
	}

	// -------------------------------------------------------------------------

	@Override
	public String toString() {
		if (label.getText() != null && !getLabel().getText().equals("")) {
			return label.getText();
		}
		else {
			return id;
		}
	}

	// -------------------------------------------------------------------------
	
        
	public Set<Arc> getConnectedArcs() {
		Set<Arc> connectedArcs = new HashSet<Arc>();
		for (Arc arc : getPetriNet().getArcs()) {
			if (arc.getPlace() == this || arc.getTransition() == this) {
				connectedArcs.add(arc);
			}
		}
		return connectedArcs;
	}

        //----------------------- dynSound
        public Set<Arc> getConnectedArcs(boolean placeToTransition,PetriNet petriNet) {
                PetriNet pnet = petriNet;
		Set<Arc> connectedArcs = new HashSet<Arc>();
		for (Arc arc : pnet.getArcs()) {
			if ((arc.getSource() == this || arc.getDestination() == this) && arc.isPlaceToTransition() == placeToTransition) {
				connectedArcs.add(arc);
			}
		}
		return connectedArcs;
	}

        public Arc getConnectedArcTP(Node node,boolean placeToTransition,PetriNet petriNet) {
                PetriNet pnet = petriNet;
                for (Arc arc : pnet.getArcs()) {
			if (arc.getSource() == node && arc.getDestination() == this && arc.isPlaceToTransition() == placeToTransition) {
                            System.out.println(arc.getDestination().getLabel().getText());
                            return arc;
			}
		}
		return null;
	}

        public Arc getConnectedArcPT(Node node,boolean placeToTransition,PetriNet petriNet) {
                PetriNet pnet = petriNet;
                for (Arc arc : pnet.getArcs()) {
			if (arc.getSource() == this && arc.getDestination() == node && arc.isPlaceToTransition() == placeToTransition) {
                            System.out.println(arc.getDestination().getLabel().getText());
                            return arc;
			}
		}
		return null;
	}


        // ---------------------------------------------WORKFLOW anal.

	public Arc getConnectedArcNext() {
                Arc nextArc = new Arc();
		for (Arc arc : getPetriNet().getArcs()) {
			if (arc.getPlace() == this) {
                            if(arc.isPlaceToTransition()){
				nextArc =arc ;
                            }
			}
                        if (arc.getTransition() == this) {
                            if(!arc.isPlaceToTransition()){
				nextArc =arc ;
                            }
			}
		}
		return nextArc;
	}

        // --------------------------------------------WORKFLOW anal.

	public Set<Arc> getConnectedArcsNext() {
		Set<Arc> connectedArcs = new HashSet<Arc>();
		for (Arc arc : getPetriNet().getArcs()) {
			if (arc.getPlace() == this) {
                            if(arc.isPlaceToTransition()){
				connectedArcs.add(arc);
                            }
			}
                        if (arc.getTransition() == this) {
                            if(!arc.isPlaceToTransition()){
				connectedArcs.add(arc);
                            }
			}
		}
		return connectedArcs;
	}
        
        public Arc getConnectedArcToNode(Node node) {
		for (Arc arc : getPetriNet().getArcs()) {
			if (arc.getSource() == this && arc.getDestination() == node) {
				return arc;
			}
		}
		return null;
	}

	// -------------------------------------------------------------------------

	@Override
	public Node getClone() {
		Node node = (Node)super.getClone();
		node.id = this.id;

		node.label = this.label.getClone();
		node.label.setNode(node);

		return node;
	}

	// -------------------------------------------------------------------------
	public int compareTo(Node node) {
		if (this.getLabel().getText() != null && node.getLabel().getText() != null && !(this.getLabel().getText().equals("") && node.getLabel().getText().equals("")) ) {
			return this.getLabel().getText().compareTo(node.getLabel().getText());
		}
		else {
			return this.getId().compareTo(node.getId());
		}
	}
}
