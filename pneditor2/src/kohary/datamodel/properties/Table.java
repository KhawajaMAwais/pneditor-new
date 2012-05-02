package kohary.datamodel.properties;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import kohary.datamodel.dapi.ComboBoxItem;
import kohary.datamodel.dapi.DComboBox;


/**
 *
 * @author matmas
 */
public class Table extends JPanel {

	
	private ArrayList<Row> rows = new ArrayList<Row>();
        DComboBox combobox;

	public Table( DComboBox combobox) {
                this.combobox=combobox;    

		JLabel typeLabel = new JLabel("Item");
		JLabel valueLabel = new JLabel("Value");
		JLabel deleteLabel = new JLabel("Delete");

		Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		typeLabel.setFont(boldFont);		
		valueLabel.setFont(boldFont);
		deleteLabel.setFont(boldFont);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridy = 0;
		c.gridx = 0;
		add(typeLabel, c);		
		c.gridx = 2;
		add(valueLabel, c);
		c.gridx = 3;
		add(deleteLabel, c);

		for (ComboBoxItem item : combobox.getItems()) {
			addRow(item);
		}
		
		c.gridy = 100;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = Integer.MAX_VALUE;
		add(new JLabel(), c);
	}

	private int rowNumber = 1;

	private void addRow(ComboBoxItem item) {
		Row row = new Row(item, this);
		rows.add(row);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = rowNumber++;
		c.gridx = 0;
		this.add(row.getComboboxItem(), c);
		c.gridx = 1;
		this.add(row.getComboboxValue(), c);
		c.gridx = 2;	
		this.add(row.getItemDelete(), c);
	}

	// -------------------------------------------------------------------------

	public void addRowAndProperty(ComboBoxItem item) {
		addRow(item);
		combobox.getItems().add(item);
		this.getParent().validate();
		Container parentParentContainer = this.getParent().getParent();
		if (parentParentContainer instanceof JScrollPane) {
			JScrollPane scrollPane = (JScrollPane)parentParentContainer;
			JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		}
	}

	public void removeRowAndProperty(Row row) {
		this.remove(row.getComboboxItem());
		this.remove(row.getComboboxValue());
		this.remove(row.getItemDelete());
		
		this.combobox.getItems().remove(row.getItem());
		this.getParent().validate();
		this.getParent().repaint();
	}

	// -------------------------------------------------------------------------

	public ArrayList<Row> getRows() {
		return rows;
	}

}
