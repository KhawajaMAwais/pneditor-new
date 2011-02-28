package net.matmas.pnapi.properties;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class RealProperty extends Property {

	public RealProperty() {
		initialize();
	}

	public RealProperty(WithProperties withProperties) {
		super(withProperties);
		initialize();
	}

	private JSpinner spinner = new JSpinner(new SpinnerNumberModel(0.0, null, null, 0.1));

	private void initialize() {
		int prefferedHeight = spinner.getPreferredSize().height;
		spinner.setPreferredSize(new Dimension(140, prefferedHeight));
		((JSpinner.NumberEditor)spinner.getEditor()).getTextField().setHorizontalAlignment(JFormattedTextField.RIGHT);
	}

	@Override
	public String getPropertyTypeName() {
		return "Real";
	}

	@Override
	public Component getEditor() {
		return spinner;
	}

	@Element
	public Double getValue() {
		return (Double)spinner.getValue();
	}

	@Element
	public void setValue(Double value) {
		spinner.setValue(value);
	}



}
