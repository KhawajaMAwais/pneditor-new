/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

import java.awt.Point;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class MoveElementCommand implements  Command{

	private Element element;
	private Point deltaPosition;

	public MoveElementCommand(Element element, Point deltaPosition) {
		this.element = element;
		this.deltaPosition = deltaPosition;
	}
	
	public void execute() {
		element.moveBy((int)deltaPosition.getX(), (int)deltaPosition.getY());
	}

	public void undo() {
		element.moveBy((int)-deltaPosition.getX(),(int) -deltaPosition.getY());
	}

	public void redo() {
		execute();
	}
	
	

    public String actionName() {
        return "Move attribute";
    }
    
}
