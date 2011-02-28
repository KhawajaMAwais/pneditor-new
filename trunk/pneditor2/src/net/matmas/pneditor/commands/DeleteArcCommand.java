package net.matmas.pneditor.commands;

import net.matmas.pnapi.Arc;

/**
 *
 * @author matmas
 */
public class DeleteArcCommand implements Command {

	private Arc arc;
	private boolean isAlreadyDeleted;
	
	public DeleteArcCommand(Arc arc) {
		this.arc = arc;
	}
	
	public void execute() {
		isAlreadyDeleted = !arc.getPetriNet().getArcs().contains(arc);
		if ( !isAlreadyDeleted) {
			arc.getPetriNet().removeArc(arc);
		}
	}

	public void undo() {
		if ( !isAlreadyDeleted) {
			arc.getPetriNet().addArc(arc);
		}
	}

	public void redo() {
		isAlreadyDeleted = !arc.getPetriNet().getArcs().contains(arc);
		if ( !isAlreadyDeleted) {
			arc.getPetriNet().removeArc(arc);
		}
	}
	
	public String getName() {
		return "Delete arc";
	}
	
}
