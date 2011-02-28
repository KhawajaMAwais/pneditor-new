package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;import net.matmas.pnapi.Element;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;

/**
 *
 * @author matmas
 */
public class SelectAllAction extends Action {

	public SelectAllAction() {
		String name = "Select All";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
		for (Element element : petriNet) {
			selection.add(element);
		}
	}

	@Override
	public boolean shouldBeEnabled() {
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
		return !petriNet.isEmpty();
	}

}
