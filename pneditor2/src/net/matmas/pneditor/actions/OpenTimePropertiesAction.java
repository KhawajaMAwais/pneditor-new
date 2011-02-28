package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.util.Set;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.MainFrame;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pnapi.properties.WithProperties;
import net.matmas.pnapi.Node;
import net.matmas.pneditor.properties.TimePropertiesDialog;
import net.matmas.util.CollectionTools;

/**
 *
 * @author matmas
 */
public class OpenTimePropertiesAction extends Action {

	public OpenTimePropertiesAction() {
		String name = "Set time properties";
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
		MainFrame mainFrame = PNEditor.getInstance().getMainFrame();
		Selection selection = mainFrame.getDrawingBoard().getCanvas().getSelection();
		PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
                Node node = CollectionTools.getFirstElement(selection.getNodes());;
                Set<Arc> selectedArcs = selection.getArcs();
		Arc arc = CollectionTools.getFirstElement(selectedArcs);

                WithProperties withProperties = null;
		if (selection.isEmpty()) {
			withProperties = petriNet;
		}
		if (selection.size() == 1) {
			withProperties = CollectionTools.getFirstElement(selection);
		}
		new TimePropertiesDialog(mainFrame, withProperties, node, arc);
	}

	@Override
	public boolean shouldBeEnabled() {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		return selection.isEmpty() || selection.size() == 1;
	}
	
}
