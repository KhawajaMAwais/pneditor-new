package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pneditor.commands.CutCommand;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class CutAction extends Action {

	public CutAction() {
		String name = "Cut";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Cut16.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl X"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		PNEditor.getInstance().getClipboard().setContents(selection.getElements(), petriNet);
		PNEditor.getInstance().getUndoManager().executeCommand(new CutCommand(selection.getElements()));
		selection.clear();
	}

	@Override
	public boolean shouldBeEnabled() {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		return !selection.isEmpty();
	}
}
