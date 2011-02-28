package net.matmas.pneditor.commands;

import net.matmas.pnapi.Subnet;

/**
 *
 * @author matmas
 */
public class DeleteSubnetCommand implements Command {

	private Subnet subnet;

	public DeleteSubnetCommand(Subnet subnet) {
		this.subnet = subnet;
	}

	public void execute() {
		subnet.getPetriNet().remove(subnet);
	}

	public void undo() {
		subnet.getPetriNet().add(subnet);
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Delete subnet";
	}

}
