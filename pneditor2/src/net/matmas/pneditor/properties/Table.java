package net.matmas.pneditor.properties;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import net.matmas.pnapi.properties.Property;
import net.matmas.pnapi.properties.WithProperties;

/**
 *
 * @author matmas
 */
public class Table extends JPanel {

	private WithProperties withProperties;
	private ArrayList<Row> rows = new ArrayList<Row>();

	public Table(WithProperties withProperties) {
		this.withProperties = withProperties;

		JLabel typeLabel = new JLabel("Type");
		JLabel nameLabel = new JLabel("Name");
		JLabel valueLabel = new JLabel("Value");
		JLabel deleteLabel = new JLabel("Delete");

		Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
		typeLabel.setFont(boldFont);
		nameLabel.setFont(boldFont);
		valueLabel.setFont(boldFont);
		deleteLabel.setFont(boldFont);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.gridy = 0;
		c.gridx = 0;
		add(typeLabel, c);
		c.gridx = 1;
		add(nameLabel, c);
		c.gridx = 2;
		add(valueLabel, c);
		c.gridx = 3;
		add(deleteLabel, c);

		for (Property property : withProperties.getProperties()) {
			addRow(property);
		}
		
		c.gridy = 100;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = Integer.MAX_VALUE;
		add(new JLabel(), c);
	}

	private int rowNumber = 1;

	private void addRow(Property property) {
		Row row = new Row(property, this);
		rows.add(row);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = rowNumber++;
		c.gridx = 0;
		this.add(row.getPropertyTypeLabel(), c);
		c.gridx = 1;
		this.add(row.getPropertyIdField(), c);
		c.gridx = 2;
		this.add(row.getProperty().getEditor(), c);
		c.gridx = 3;
		this.add(row.getPropertyDeleteButton(), c);
	}

	// -------------------------------------------------------------------------

	public void addRowAndProperty(Property property) {
		addRow(property);
		withProperties.getProperties().add(property);
		this.getParent().validate();
		Container parentParentContainer = this.getParent().getParent();
		if (parentParentContainer instanceof JScrollPane) {
			JScrollPane scrollPane = (JScrollPane)parentParentContainer;
			JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
			verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		}
	}

	public void removeRowAndProperty(Row row) {
		this.remove(row.getPropertyTypeLabel());
		this.remove(row.getPropertyIdField());
		this.remove(row.getProperty().getEditor());
		this.remove(row.getPropertyDeleteButton());
		this.withProperties.getProperties().remove(row.getProperty());
		this.getParent().validate();
		this.getParent().repaint();
	}

	// -------------------------------------------------------------------------

	public ArrayList<Row> getRows() {
		return rows;
	}

}
