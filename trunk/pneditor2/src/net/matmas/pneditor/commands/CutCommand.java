package net.matmas.pneditor.commands;

import java.util.Set;
import net.matmas.pnapi.Element;

/**
 *
 * @author matmas
 */
public class CutCommand implements Command {
	
	private Command deleteElements;

	public CutCommand(Set<Element> elementsToDelete) {
		deleteElements = new DeleteElementsCommand(elementsToDelete);
	}

	public void execute() {
		deleteElements.execute();
	}

	public void undo() {
		deleteElements.undo();
	}

	public void redo() {
		deleteElements.redo();
	}

	public String getName() {
		return "Cut";
	}
	
}
