package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Set;
import javax.swing.KeyStroke;
import net.matmas.pnapi.Element;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pneditor.commands.DeleteElementsCommand;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class DeleteAction extends Action {
		
	public DeleteAction() {
		String name = "Delete";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Delete16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) { //TODO: use getSelectedElementsWithClickedElement()
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();

		Set<Element> elementsToDelete = selection.getElements();
		
		if ( !elementsToDelete.isEmpty()) {
			PNEditor.getInstance().getUndoManager().executeCommand(new DeleteElementsCommand(elementsToDelete));

			selection.clear();
		}
	}

	@Override
	public boolean shouldBeEnabled() {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		return !selection.isEmpty();
	}
}
