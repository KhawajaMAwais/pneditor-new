package net.matmas.pneditor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import net.matmas.pneditor.actions.Action;
import net.matmas.pneditor.commands.Command;

/**
 * UndoManager provides the basic undo-redo capability.
 * @author matmas
 */
public class UndoManager {
	
	private List<Command> executedCommands = new ArrayList<Command>();
	private int currentCommandIndex = -1;

	/**
	 * Execute command in UndoManager.
	 * @param command command to be executed
	 */
	public void executeCommand(Command command) {
		List<Command> nonRedoedCommands = new ArrayList<Command>(executedCommands.subList(currentCommandIndex + 1, executedCommands.size()));
		executedCommands.removeAll(nonRedoedCommands);
		executedCommands.add(command);
		currentCommandIndex = executedCommands.size() - 1;
		command.execute();
		refreshButtonLabels();
		documentStateChanged();
	}
	
	/**
	 * Performs the undo action.
	 */
	public void undoCommand() {
		if (isUndoable()) {
			Command command = executedCommands.get(currentCommandIndex);
			command.undo();
			currentCommandIndex--;
			refreshButtonLabels();
			documentStateChanged();
		}
	}
	
	/**
	 * Performs the redo action.
	 */
	public void redoNextCommand() {
		if (isRedoable()) {
			Command command = executedCommands.get(currentCommandIndex + 1);
			command.redo();
			currentCommandIndex++;
			refreshButtonLabels();
			documentStateChanged();
		}
	}
	
	/**
	 * Determines if undo is possible.
	 * @return true if undo action is possible otherwise false
	 */
	public boolean isUndoable() {
		return currentCommandIndex != -1;
	}
	
	/**
	 * Determines if redo is possible.
	 * @return true if redo action is possible otherwise false
	 */
	public boolean isRedoable() {
		return currentCommandIndex < executedCommands.size() - 1;
	}
	
	/**
	 * Erases all commands from the undo manager.
	 */
	public void eraseAll() {
		executedCommands = new ArrayList<Command>();
		currentCommandIndex = -1;
		refreshButtonLabels();
	}

	public Command getUndoableCommand() {
		return executedCommands.get(currentCommandIndex);
	}

	public Command getRedoableCommand() {
		return executedCommands.get(currentCommandIndex + 1);
	}

	// -------------------------------------------------------------------------

	private boolean documentModified = false;

	public boolean isDocumentModified() {
		return documentModified;
	}

	public void setDocumentModified(boolean documentModified) {
		this.documentModified = documentModified;
		PNEditor.getInstance().getMainFrame().setTitle(PNEditor.getInstance().getNewWindowTitle());
		PNEditor.getInstance().getMainFrame().repaint();
	}

	private void documentStateChanged() {
		setDocumentModified(true);
	}

	private void refreshButtonLabels() {
		PNEditor.getInstance().getMainFrame().undoAction.refresh();
		PNEditor.getInstance().getMainFrame().redoAction.refresh();
	}

}
