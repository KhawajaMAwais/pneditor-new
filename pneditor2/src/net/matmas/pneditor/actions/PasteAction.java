package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.KeyStroke;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.LocalClipboard;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pneditor.commands.PasteCommand;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class PasteAction extends Action {

	public PasteAction() {
		String name = "Paste";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Paste16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();

		Set<Element> pastedElements = PNEditor.getInstance().getClipboard().getContents(petriNet);
		PNEditor.getInstance().getUndoManager().executeCommand(new PasteCommand(pastedElements, petriNet));
		selection.replaceWith(pastedElements);
		PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().repaint();
	}

	@Override
	public boolean shouldBeEnabled() {
		LocalClipboard clipboard = PNEditor.getInstance().getClipboard();
		return !clipboard.isEmpty();
	}
}
