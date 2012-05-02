/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.managers;

import java.util.ArrayList;
import java.util.List;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.commands.Command;

/**
 *
 * @author Godric
 */
public class UndoManager {

  
    

    private List<Command> executedCommands = new ArrayList<Command>();
    int currentIndex = -1;

    public void executeCommand(Command command) {
        executedCommands.add(command);
        command.execute();
        currentIndex = executedCommands.size() - 1;
        refresh();
        setUndoRedoButtonState();
    }

    public void undoCommand() {
        if (isUndoable()) {
            Command command = executedCommands.get(currentIndex);
            command.undo();
            currentIndex--;
            refresh();
            setUndoRedoButtonState();
        }


    }

    public void redoCommand() {
        if (isRedoable()) {
            Command command = executedCommands.get(currentIndex + 1);
            command.redo();
            currentIndex++;
            refresh();
            setUndoRedoButtonState();
        }
    }

    private void setUndoRedoButtonState() {
        if (!isUndoable()) {
            DatamodelCreator.getInstance().getMainFrame().undoAction.setEnabled(false);
        } else {
            DatamodelCreator.getInstance().getMainFrame().undoAction.setEnabled(true);
        }
        if (isRedoable()) {
            DatamodelCreator.getInstance().getMainFrame().redoAction.setEnabled(true);
        } else {
            DatamodelCreator.getInstance().getMainFrame().redoAction.setEnabled(false);
        }
        refresh();
    }

    public boolean isUndoable() {
        if (currentIndex != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRedoable() {
        if (currentIndex < executedCommands.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    private void refresh() {
        DatamodelCreator.getInstance().getMainFrame().getModellingBoard().getCanvas().repaint();
    }
}
