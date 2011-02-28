package net.matmas.pneditor.properties;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.matmas.pnapi.properties.Property;

/**
 *
 * @author matmas
 */
public class Row {

	private Property property;
	private JTextField propertyIdField;
	private JLabel propertyTypeLabel;
	private JButton propertyDeleteButton;
	private Table table;

	public Row(Property property, Table table) {
		this.table = table;
		this.property = property;
		this.propertyIdField = new JTextField(property.getId(), 16);

		if (!property.isIdEditable()) {
			this.propertyIdField.setEnabled(false);
		}

		this.propertyTypeLabel = new JLabel(property.getPropertyTypeName());
		this.propertyDeleteButton = new JButton(new PropertyDeleteAction(table, this));

		propertyIdField.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { propertyIdChanged(); }
			public void removeUpdate(DocumentEvent e) { propertyIdChanged(); }
			public void changedUpdate(DocumentEvent e) { propertyIdChanged(); }
		});
	}

	public JTextField getPropertyIdField() {
		return propertyIdField;
	}

	public JLabel getPropertyTypeLabel() {
		return propertyTypeLabel;
	}

	public Property getProperty() {
		return property;
	}

	public JButton getPropertyDeleteButton() {
		return propertyDeleteButton;
	}
	
	private void propertyIdChanged() {
		property.setId(propertyIdField.getText());
		highlightDuplicateIds();
	}

	public void highlightDuplicateIds() {
		for (Property property1 : this.property.getWithProperties().getProperties()) {
			Row row = getPropertyRowOfThisTable(property1);
			row.propertyIdField.setForeground(Color.black);
		}
		for (Property property1 : this.property.getWithProperties().getProperties()) {
			Row row1 = getPropertyRowOfThisTable(property1);
			for (Property property2 : this.property.getWithProperties().getProperties()) {
				Row row2 = getPropertyRowOfThisTable(property1);
				if (property1 != property2) {
					if (property1.getId() != null &&
						!property1.getId().equals("") &&
						property1.getId().equals(property2.getId())
					) {
						row1.propertyIdField.setForeground(Color.red);
						row2.propertyIdField.setForeground(Color.red);
					}
				}
			}
		}
	}

	private Row getPropertyRowOfThisTable(Property property) {
		for (Row row : table.getRows()) {
			if (row.property == property) {
				return row;
			}
		}
		return null;
	}
}
