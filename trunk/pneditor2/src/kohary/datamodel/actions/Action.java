/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.actions;

import javax.swing.AbstractAction;

/**
 *
 * @author Godric
 */
public abstract class Action extends AbstractAction{
    
        public boolean shouldBeEnabled() {
		return true;
	}

	public void refresh() {
		setEnabled(shouldBeEnabled());
	}
    
}
