/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class MoveElementsCommand implements Command {

    private Set<Command> moveElements = new HashSet<Command>();

    public MoveElementsCommand(Set<Element> elements, Point deltaPosition) {
        for (Element element : elements) {
            moveElements.add(new MoveElementCommand(element, deltaPosition));
        }
    }

    public void execute() {
        for (Command moveElement : moveElements) {
            moveElement.execute();
        }
    }

    public void undo() {
        for (Command moveElement : moveElements) {
            moveElement.undo();
        }
    }

    public void redo() {
        for (Command moveElement : moveElements) {
            moveElement.redo();
        }
    }

    public String actionName() {
        if (moveElements.size() == 1) {
            for (Command moveElement : moveElements) {
                return moveElement.actionName();
            }
        }
        return "Move elements";
    }
}
