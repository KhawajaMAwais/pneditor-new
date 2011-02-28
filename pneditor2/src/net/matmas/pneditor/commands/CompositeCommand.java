package net.matmas.pneditor.commands;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author matmas
 */
public class CompositeCommand implements Command {

	private List<Command> commands;

	public CompositeCommand(List<Command> commands) {
		this.commands = commands;
	}
	
	public void execute() {
		for (Command command : commands) {
			command.execute();
		}
	}

	public void undo() {
		Collections.reverse(commands);
		for (Command command : commands) {
			command.undo();
		}
		Collections.reverse(commands);
	}

	public void redo() {
		for (Command command : commands) {
			command.execute();
		}
	}

	public String getName() {
		return commands.get(0).getName() + ", ...";
	}

}
