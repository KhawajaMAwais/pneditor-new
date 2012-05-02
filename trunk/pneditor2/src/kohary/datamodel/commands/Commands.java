/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Godric
 */
public class Commands implements Command{
    
    private List<Command> commands;

    public Commands(List<Command> commands) {
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

    public String actionName() {
        return "...";
    }
    
}
