package net.matmas.pneditor.properties;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import net.matmas.util.GraphicsTools;

/**
 *
 * @author matmas
 */
public class PropertyDeleteAction extends AbstractAction {

	private Table table;
	private Row row;

	public PropertyDeleteAction(Table table, Row row) {
		this.table = table;
		this.row = row;
		String name = "Delete";
		putValue(NAME, "");
		putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Delete16.gif"));
		putValue(SHORT_DESCRIPTION, name);
	}

	public void actionPerformed(ActionEvent e) {
		table.removeRowAndProperty(row);
		row.highlightDuplicateIds();
	}
}

