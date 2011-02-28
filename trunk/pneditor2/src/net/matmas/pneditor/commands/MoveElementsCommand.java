package net.matmas.pneditor.commands;

import java.util.HashSet;
import java.util.Set;
import net.matmas.pnapi.Element;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class MoveElementsCommand implements Command {

	private Set<Command> moveElements = new HashSet<Command>();
	
	public MoveElementsCommand(Set<Element> elements, Point deltaPosition) {
		for (Element element : elements) {
			moveElements.add(new MoveElementCommand(element, deltaPosition));
		}
	}
	
	public void execute() {
		for (Command moveElement : moveElements) {
			moveElement.execute();
		}
	}

	public void undo() {
		for (Command moveElement : moveElements) {
			moveElement.undo();
		}
	}

	public void redo() {
		for (Command moveElement : moveElements) {
			moveElement.redo();
		}
	}

	public String getName() {
		if (moveElements.size() == 1) {
			for (Command moveElement : moveElements) {
				return moveElement.getName();
			}
		}
		return "Move elements";
	}
	
}
