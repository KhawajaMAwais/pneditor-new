package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SelectTokenToolAction extends Action {
	
	public SelectTokenToolAction() {
		String name = "Edit tokens";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/tokens.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
		PNEditor.getInstance().getToolSelector().selectTool_Token();
	}

}
