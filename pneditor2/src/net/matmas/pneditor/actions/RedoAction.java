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
public class RedoAction extends Action {
	
	public RedoAction() {
		String name = "Redo";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Redo16.gif"));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		PNEditor.getInstance().getUndoManager().redoNextCommand();
	}

	@Override
	public boolean shouldBeEnabled() {
		UndoManager undoManager = PNEditor.getInstance().getUndoManager();
		return undoManager.isRedoable();
	}

	@Override
	public void refresh() {
		super.refresh();
		UndoManager undoManager = PNEditor.getInstance().getUndoManager();
		if (undoManager.isRedoable()) {
			this.putValue(SHORT_DESCRIPTION, "Redo: " + undoManager.getRedoableCommand().getName());
		}
		else {
			this.putValue(SHORT_DESCRIPTION, "Redo");
		}
	}
}
