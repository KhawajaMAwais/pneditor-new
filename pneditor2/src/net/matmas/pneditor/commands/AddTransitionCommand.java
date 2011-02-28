package net.matmas.pneditor.commands;

import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class AddTransitionCommand implements Command {
	
	private Point location;
	private Transition createdTransition;
	private PetriNet petriNet;

	public AddTransitionCommand(Point location, PetriNet petriNet) {
		this.location = location;
		this.petriNet = petriNet;
	}
	
	public void execute() {
		createdTransition = new Transition();
		createdTransition.setCenter(location);
		petriNet.addTransition(createdTransition);
	}

	public void undo() {
		new DeleteElementCommand(createdTransition).execute();
	}

	public void redo() {
		petriNet.addTransition(createdTransition);
	}

	public String getName() {
		return "Add transition";
	}
	
}
