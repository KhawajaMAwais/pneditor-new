package net.matmas.pneditor.commands;

import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Place;

/**
 * Set tokens to clicked place node
 * @author matmas
 */
public class SetTokensCommand implements Command {

	private Place place;
	private int newValue;
	private Marking marking;
	
	public SetTokensCommand(Place place, int tokens, Marking marking) {
		this.place = place;
		this.newValue = tokens;
		this.marking = marking;
	}
	
	private int oldValue;
	
	public void execute() {
		oldValue = marking.getTokens(place);
		marking.setTokens(place, newValue);
	}

	public void undo() {
		marking.setTokens(place, oldValue);
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Set tokens";
	}
}
