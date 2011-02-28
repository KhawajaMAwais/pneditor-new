package net.matmas.pneditor.commands;

import net.matmas.pnapi.Arc;

/**
 * Set multiplicity to clicked arc
 * @author matmas
 */
public class SetArcMultiplicityCommand implements Command {
	
	private Arc arc;
	private int newMultiplicity;
	private int oldMultiplicity;
	
	public SetArcMultiplicityCommand(Arc arc, int newMultiplicity) {
		this.arc = arc;
		this.newMultiplicity = newMultiplicity;
	}
	
	public void execute() {
		oldMultiplicity = arc.getMultiplicity();
		arc.setMultiplicity(newMultiplicity);
	}

	public void undo() {
		arc.setMultiplicity(oldMultiplicity);
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Set arc multiplicity";
	}
	
}
