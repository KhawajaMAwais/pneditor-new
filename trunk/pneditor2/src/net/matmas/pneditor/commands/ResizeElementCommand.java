package net.matmas.pneditor.commands;

import net.matmas.pnapi.editor.Resizable;
import net.matmas.pnapi.editor.Resize;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class ResizeElementCommand implements Command {

	private Resizable resizable;
	private Resize resize;
	private Point newPoint;
	private Point oldPoint;

	public ResizeElementCommand(Resizable resizable, Resize resize, Point point) {
		this.resizable = resizable;
		this.resize = resize;
		this.newPoint = point;
	}

	public void execute() {
		oldPoint = resizable.getResizePoint(resize);
		resizable.setResizePoint(resize, newPoint);
	}

	public void undo() {
		resizable.setResizePoint(resize, oldPoint);
	}

	public void redo() {
		resizable.setResizePoint(resize, newPoint);
	}

	public String getName() {
		return "Resize";
	}

}
