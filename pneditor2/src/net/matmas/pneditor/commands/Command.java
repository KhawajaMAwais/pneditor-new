package net.matmas.pneditor.commands;

/**
 *
 * @author matmas
 */
public interface Command {
	public void execute();
	public void undo();
	public void redo();
	public String getName();
}
