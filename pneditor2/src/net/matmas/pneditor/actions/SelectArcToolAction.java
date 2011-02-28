package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SelectArcToolAction extends Action {
	
	public SelectArcToolAction() {
		String name = "Arc";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/arc.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
	}

	public void actionPerformed(ActionEvent e) {
		PNEditor.getInstance().getToolSelector().selectTool_Arc();
	}

}
	