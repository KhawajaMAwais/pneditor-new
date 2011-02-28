package net.matmas.pneditor.commands;

import java.util.LinkedList;
import java.util.List;
import net.matmas.pnapi.Arc;
import net.matmas.util.Point;

/**
 *
 * @author matmas
 */
public class SetArcBreakPointCommand implements Command {

	private Arc arc;
	private Point startingLocation;
	private Point targetLocation;
	private List<Point> oldBreakPoints;
	
	public SetArcBreakPointCommand(Arc arc, Point startingLocation, Point targetLocation) {
		this.arc = arc;
		this.startingLocation = startingLocation;
		this.targetLocation = targetLocation;
	}
	
	public void execute() {
		redo();
	}

	public void undo() {
		arc.setBreakPoints(oldBreakPoints);
	}

	public void redo() {
		oldBreakPoints = new LinkedList<Point>(arc.getBreakPoints());
		arc.setBreakPoint(arc.addOrGetBreakPoint(startingLocation), targetLocation);
		arc.cleanupUnecessaryBreakPoints();
	}

	public String getName() {
		return "Set arc break point";
	}
	
}
