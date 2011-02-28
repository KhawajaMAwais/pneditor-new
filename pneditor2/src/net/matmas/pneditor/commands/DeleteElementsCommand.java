package net.matmas.pneditor.commands;

import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.Element;
import net.matmas.util.CollectionTools;

/**
 * Delete clicked and selected elements
 * @author matmas
 */
public class DeleteElementsCommand implements Command {

	private Set<Command> deleteAllElements = new HashSet<Command>();
	
	public DeleteElementsCommand(Set<Element> elementsToDelete) {
		for (Element element : elementsToDelete) {
			deleteAllElements.add(new DeleteElementCommand(element));
		}
	}
	
	public void execute() {
		for (Command deleteElement : deleteAllElements) {
			deleteElement.execute();
		}
	}

	public void undo() {
		for (Command deleteElement : deleteAllElements) {
			deleteElement.undo();
		}
	}
	
	public void redo() {
		for (Command deleteElement : deleteAllElements) {
			deleteElement.redo();
		}
	}
	
	public String getName() {
		if (deleteAllElements.size() == 1) {
			return CollectionTools.getFirstElement(deleteAllElements).getName();
		}
		return "Delete elements";
	}
	
}
