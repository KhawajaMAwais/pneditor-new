package net.matmas.pnapi.properties;

import java.awt.Component;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class TimeProperty extends Property {

	public TimeProperty() {
		initialize();
	}

	private JFormattedTextField formattedTextField;

	public TimeProperty(WithProperties withProperties) {
		super(withProperties);
		initialize();
	}

	private void initialize() {
		try {
			MaskFormatter maskFormatter = new MaskFormatter("####-##-## ##:##:##");
			maskFormatter.setPlaceholderCharacter('_');
			formattedTextField = new JFormattedTextField(maskFormatter);
			formattedTextField.setText("0000-00-00 00:00:00");
		} catch (ParseException ex) {
			Logger.getLogger(TimeProperty.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public String getPropertyTypeName() {
		return "Time";
	}

	@Override
	public Component getEditor() {
		return formattedTextField;
	}

	@Element
	public String getTime() {
		return formattedTextField.getText();
	}

	@Element
	public void setTime(String time) {
		formattedTextField.setText(time);
	}
	
}
