package net.matmas.pneditor.commands;

import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Place;

/**
 *
 * @author matmas
 */
public class AddTokenCommand implements Command {

	private Place place;
	private Marking marking;

	public AddTokenCommand(Place place, Marking marking) {
		this.place = place;
		this.marking = marking;
	}

	public void execute() {
		marking.setTokens(place, marking.getTokens(place) + 1);
	}

	public void undo() {
		new RemoveTokenCommand(place, marking).execute();
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Add token";
	}
	
}
