package net.matmas.pneditor.commands;

import net.matmas.pnapi.Node;

/**
 * Set label to clicked element
 * @author matmas
 */
public class SetLabelCommand implements Command {

	private Node node;
	private String newLabel;
	private String oldLabel;
	
	public SetLabelCommand(Node node, String newLabel) {
		this.node = node;
		this.newLabel = newLabel;
	}

	public void execute() {
		this.oldLabel = node.getLabel().getText();
		node.getLabel().setText(newLabel);
	}

	public void undo() {
		node.getLabel().setText(oldLabel);
	}

	public void redo() {
		execute();
	}

	public String getName() {
		return "Set label to " + newLabel;
	}
	
}
