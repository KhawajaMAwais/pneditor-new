package net.matmas.pnapi.properties;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class IntegerProperty extends Property {

	public IntegerProperty() {
		initialize();
	}

	public IntegerProperty(WithProperties withProperties) {
		super(withProperties);
		initialize();
	}

	private JSpinner spinner = new JSpinner();

	private void initialize() {
		int prefferedHeight = spinner.getPreferredSize().height;
		spinner.setPreferredSize(new Dimension(140, prefferedHeight));
		((JSpinner.NumberEditor)spinner.getEditor()).getTextField().setHorizontalAlignment(JFormattedTextField.RIGHT);
	}

	@Override
	public String getPropertyTypeName() {
		return "Integer";
	}

	@Override
	public Component getEditor() {
		return spinner;
	}

	@Element
	public Integer getValue() {
		return (Integer)spinner.getValue();
	}

	@Element
	public void setValue(Integer value) {
		spinner.setValue(value);
	}



}
