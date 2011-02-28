package net.matmas.pneditor.commands;

import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Place;

/**
 *
 * @author matmas
 */
public class RemoveTokenCommand implements Command {
	
	private Place place;
	private Marking marking;


	public RemoveTokenCommand(Place place, Marking marking) {
		this.place = place;
		this.marking = marking;
	}

	public void execute() {
		if (marking.getTokens(place) >= 1) {
			marking.setTokens(place, marking.getTokens(place) - 1);
		}
	}

	public void undo() {
		new AddTokenCommand(place, marking).execute();
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Remove token";
	}
		
}
