package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import net.matmas.pnapi.Arc;
import net.matmas.pneditor.MainFrame;
import net.matmas.pneditor.PNEditor;
import net.matmas.pneditor.Selection;
import net.matmas.pneditor.commands.CompositeCommand;
import net.matmas.pneditor.commands.DeleteArcCommand;
import net.matmas.pneditor.commands.SetArcMultiplicityCommand;
import net.matmas.util.CollectionTools;
import net.matmas.pneditor.commands.Command;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class SetArcMultiplicityAction extends Action {
	
	public SetArcMultiplicityAction() {
		String name = "Set arc multiplicity";
		putValue(NAME, name);
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/multiplicity.gif"));
		putValue(SHORT_DESCRIPTION, name);
		putValue(MNEMONIC_KEY, KeyEvent.VK_M);
		setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		MainFrame mainFrame = PNEditor.getInstance().getMainFrame();
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		Set<Arc> selectedArcs = selection.getArcs();
		
		if (!selectedArcs.isEmpty()) {
			int multiplicity = 1;

			if (selectedArcs.size() == 1) {
				Arc arc = CollectionTools.getFirstElement(selectedArcs);
				multiplicity = arc.getMultiplicity();
			}

			String response = JOptionPane.showInputDialog(mainFrame, "Multiplicity:", multiplicity);
			if (response != null) {
				try {
					multiplicity = Integer.parseInt(response);
					setMultiplicities(multiplicity, selectedArcs);
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(mainFrame, ex.getMessage() + " is not a number");
				}
			}
		}
	}

	private void setMultiplicities(int multiplicity, Set<Arc> selectedArcs) {
		List<Command> commands = new ArrayList<Command>();
		if (multiplicity >= 1) {
			for (Arc arc : selectedArcs) {
				if (arc.getMultiplicity() != multiplicity) {
					commands.add(new SetArcMultiplicityCommand(arc, multiplicity));
				}
			}
		} else {
			for (Arc arc : selectedArcs) {
				commands.add(new DeleteArcCommand(arc));
			}
		}
		if (!commands.isEmpty()) {
			PNEditor.getInstance().getUndoManager().executeCommand(new CompositeCommand(commands));
		}
	}

	@Override
	public boolean shouldBeEnabled() {
		Selection selection = PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection();
		return !selection.getArcs().isEmpty();
	}
	
}

