package net.matmas.pneditor.commands;

import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Transition;

/**
 *
 * @author matmas
 */
public class DeleteTransitionCommand implements Command {
	
	private Transition transition;
	private Set<Command> deleteAllArcs = new HashSet<Command>();
	
	public DeleteTransitionCommand(Transition transition) {
		this.transition = transition;
		Set<Arc> connectedArcs = new HashSet<Arc>(transition.getConnectedArcs());
		for (Arc arc : connectedArcs) {
			deleteAllArcs.add(new DeleteArcCommand(arc));
		}
	}
	
	public void execute() {
		for (Command deleteArc : deleteAllArcs) {
			deleteArc.execute();
		}
		transition.getPetriNet().removeTransition(transition);
	}

	public void undo() {
		for (Command deleteArc : deleteAllArcs) {
			deleteArc.undo();
		}
		transition.getPetriNet().addTransition(transition);
	}

	public void redo() {
		for (Command deleteArc : deleteAllArcs) {
			deleteArc.redo();
		}
		transition.getPetriNet().removeTransition(transition);
	}

	public String getName() {
		return "Delete transition";
	}
}
