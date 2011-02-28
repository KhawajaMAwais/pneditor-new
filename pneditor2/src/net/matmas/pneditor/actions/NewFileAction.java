package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Document;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class NewFileAction extends Action {
	
	public NewFileAction() {
		String name = "New";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/New16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
	}

	public void actionPerformed(ActionEvent e) {
		if (!PNEditor.getInstance().getUndoManager().isDocumentModified() || JOptionPane.showOptionDialog(
				PNEditor.getInstance().getMainFrame(),
				"Any unsaved changes will be lost. Continue?",
				"New file",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				new String[] {"New file", "Cancel"},
				"Cancel") == JOptionPane.YES_OPTION
		) {
			PNEditor.getInstance().setDocument(new Document());
			PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection().clear();
			PNEditor.getInstance().getUndoManager().eraseAll();;
			PNEditor.getInstance().getUndoManager().setDocumentModified(false);
			PNEditor.getInstance().setCurrentFile(null);
		}
	}

}