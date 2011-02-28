package net.matmas.pneditor.actions;

import javax.swing.AbstractAction;

/**
 *
 * @author matmas
 */
public abstract class Action extends AbstractAction {

	public boolean shouldBeEnabled() {
		return true;
	}

	public void refresh() {
		setEnabled(shouldBeEnabled());
	}
}
