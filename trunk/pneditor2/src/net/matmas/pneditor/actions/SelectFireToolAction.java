package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SelectFireToolAction extends Action {

	public SelectFireToolAction() {
		String name = "Enter simulation mode";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/fire.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
		PNEditor.getInstance().getToolSelector().selectTool_Fire();
	}

}
