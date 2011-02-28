package net.matmas.pneditor.commands;

import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Subnet;
import net.matmas.pnapi.Transition;

/**
 *
 * @author matmas
 */
public class DeleteElementCommand implements Command {
	
	private Element element;
	private Command deleteElement;

	public DeleteElementCommand(Element elementToDelete) {
		this.element = elementToDelete;
		if (element instanceof Place) {
			Place place = (Place)element;
			deleteElement = new DeletePlaceCommand(place);
		}
		else if (element instanceof Transition) {
			Transition transition = (Transition)element;
			deleteElement = new DeleteTransitionCommand(transition);
		}
		else if (element instanceof Arc) {
			Arc arc = (Arc)element;
			deleteElement = new DeleteArcCommand(arc);
		}
		else if (element instanceof Subnet) {
			Subnet subnet = (Subnet)element;
			deleteElement = new DeleteSubnetCommand(subnet);
		}
	}
	
	public void execute() {
		if (deleteElement != null) {
			deleteElement.execute();
		}
	}

	public void undo() {
		if (deleteElement != null) {
			deleteElement.undo();
		}
	}

	public void redo() {
		if (deleteElement != null) {
			deleteElement.redo();
		}
	}
	
	public String getName() {
		return deleteElement.getName();
	}
}
