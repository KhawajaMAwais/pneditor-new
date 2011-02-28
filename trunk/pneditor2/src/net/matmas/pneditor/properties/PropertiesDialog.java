package net.matmas.pneditor.properties;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.Property;
import net.matmas.pnapi.properties.WithProperties;

/**
 *
 * @author matmas
 */
public class PropertiesDialog extends JDialog implements ActionListener {

	private Table table;
	private PropertyTypeSelector propertyTypeSelector;
	private JButton addPredefinedPropertyButton = new JButton("Add predefined property");
	private PredefinedPropertySelector predefinedPropertySelector;
	private JButton addCustomPropertyButton = new JButton("Add custom property");


	public PropertiesDialog(JFrame parentFrame, WithProperties withProperties) {
		super(parentFrame);
		String title = "Properties";
		if (withProperties instanceof PetriNet) {
			title += " of Petri net";
		}
		else if (withProperties instanceof Place) {
			title += " of place";
		}
		else if (withProperties instanceof Transition) {
			title += " of transition";
		}
		else if (withProperties instanceof Arc) {
			title += " of arc";
		}
		this.setTitle(title);

		this.table = new Table(withProperties);
		this.predefinedPropertySelector = new PredefinedPropertySelector(withProperties);
		this.propertyTypeSelector = new PropertyTypeSelector(withProperties);

		this.setSize(600, 500);
		this.setLocationRelativeTo(getParent());
		this.getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.add(new JScrollPane(table), c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(predefinedPropertySelector, c);
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(addPredefinedPropertyButton, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(propertyTypeSelector, c);
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		this.add(addCustomPropertyButton, c);

		this.setVisible(true);
		addPredefinedPropertyButton.addActionListener(this);
		addCustomPropertyButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.addCustomPropertyButton) {
			Property property = propertyTypeSelector.createSelectedPropertyInstance();
			table.addRowAndProperty(property);
		}
		else if (e.getSource() == addPredefinedPropertyButton) {
			Property property = predefinedPropertySelector.createSelectedPropertyInstance();
			table.addRowAndProperty(property);
		}
	}
	
}
