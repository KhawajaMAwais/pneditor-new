package net.matmas.pneditor.commands;


import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;

/**
 *
 * @author matmas
 */
public class AddArcCommand implements Command {

	private Place place;
	private Transition transition;
	private boolean placeToTransition;
	private Arc createdArc;
	
	public AddArcCommand(Place place, Transition transition, boolean placeToTransition) {
		this.place = place;
		this.transition = transition;
		this.placeToTransition = placeToTransition;
	}
	
	public void execute() {
		createdArc = new Arc(place, transition, placeToTransition);
		place.getPetriNet().addArc(createdArc);
	}

	public void undo() {
		new DeleteElementCommand(createdArc).execute();
	}

	public void redo() {
		place.getPetriNet().addArc(createdArc);
	}

	public String getName() {
		return "Add arc";
	}
	
}
