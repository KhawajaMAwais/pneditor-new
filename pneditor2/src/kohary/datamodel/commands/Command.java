/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.commands;

/**
 *
 * @author Godric
 */
public interface Command {
    
    public void execute();
    public void undo();
    public void redo();
    public String actionName();
    
}
