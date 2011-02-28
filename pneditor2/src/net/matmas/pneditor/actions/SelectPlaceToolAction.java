package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SelectPlaceToolAction extends Action {
	
	public SelectPlaceToolAction() {
		String name = "Place";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/place16.gif"));
		putValue(SHORT_DESCRIPTION, "Place");
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
	}

	public void actionPerformed(ActionEvent e) {
		PNEditor.getInstance().getToolSelector().selectTool_Place();
	}

}
	
