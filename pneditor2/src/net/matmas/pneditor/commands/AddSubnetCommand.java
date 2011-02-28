package net.matmas.pneditor.commands;

import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Subnet;

/**
 *
 * @author matmas
 */
public class AddSubnetCommand implements Command {

	private PetriNet petriNet;
	private Subnet subnet;

	public AddSubnetCommand(Subnet subnet, PetriNet petriNet) {
		this.petriNet = petriNet;
		this.subnet = subnet.getClone();
	}
	
	public void execute() {
		petriNet.add(subnet);
	}

	public void undo() {
		petriNet.remove(subnet);
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Add subnet";
	}

}
