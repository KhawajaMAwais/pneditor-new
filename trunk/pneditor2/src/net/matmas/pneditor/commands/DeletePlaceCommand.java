package net.matmas.pneditor.commands;

import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Place;

/**
 *
 * @author matmas
 */
public class DeletePlaceCommand implements Command {
	
	private Place place;
	private Set<Command> deleteAllArcs = new HashSet<Command>();
	
	public DeletePlaceCommand(Place place) {
		this.place = place;
		Set<Arc> connectedArcs = new HashSet<Arc>(place.getConnectedArcs());
		for (Arc arc : connectedArcs) {
			deleteAllArcs.add(new DeleteArcCommand(arc));
		}
	}
	
	public void execute() {
		for (Command deleteArc : deleteAllArcs) {
			deleteArc.execute();
		}
		place.getPetriNet().removePlace(place);
	}

	public void undo() {
		for (Command deleteArc : deleteAllArcs) {
			deleteArc.undo();
		}
		place.getPetriNet().addPlace(place);
	}

	public void redo() {
		for (Command deleteArc : deleteAllArcs) {
			deleteArc.redo();
		}
		place.getPetriNet().removePlace(place);
	}

	public String getName() {
		return "Delete place";
	}
	
}
