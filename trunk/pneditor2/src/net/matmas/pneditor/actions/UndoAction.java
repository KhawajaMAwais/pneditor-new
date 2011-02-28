package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.UndoManager;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class UndoAction extends Action {
	
	public UndoAction() {
		String name = "Undo";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Undo16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		PNEditor.getInstance().getUndoManager().undoCommand();
	}

	@Override
	public boolean shouldBeEnabled() {
		UndoManager undoManager = PNEditor.getInstance().getUndoManager();
		return undoManager.isUndoable();
	}

	@Override
	public void refresh() {
		super.refresh();
		UndoManager undoManager = PNEditor.getInstance().getUndoManager();
		if (undoManager.isUndoable()) {
			this.putValue(SHORT_DESCRIPTION, "Undo: " + undoManager.getUndoableCommand().getName());
		}
		else {
			this.putValue(SHORT_DESCRIPTION, "Undo");
		}
	}
}
