package net.matmas.pneditor.commands;

import net.matmas.pnapi.Element;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class MoveElementCommand implements Command {

	private Element element;
	private Point deltaPosition;

	public MoveElementCommand(Element element, Point deltaPosition) {
		this.element = element;
		this.deltaPosition = deltaPosition;
	}
	
	public void execute() {
		element.moveBy(deltaPosition.getX(), deltaPosition.getY());
	}

	public void undo() {
		element.moveBy(-deltaPosition.getX(), -deltaPosition.getY());
	}

	public void redo() {
		execute();
	}
	
	public String getName() {
		return "Move element";
	}
	
}
