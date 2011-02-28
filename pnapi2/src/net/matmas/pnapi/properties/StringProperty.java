package net.matmas.pnapi.properties;

import java.awt.Component;
import javax.swing.JTextField;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class StringProperty extends Property {

	public StringProperty() {
	}

	public StringProperty(WithProperties withProperties) {
		super(withProperties);
	}

	public String getPropertyTypeName() {
		return "String";
	}

	private JTextField textField = new JTextField(10);

	public Component getEditor() {
		return textField;
	}

	@Element(required=false)
	public String getText() {
		return textField.getText();
	}

	@Element(required=false)
	public void setText(String xml) {
		textField.setText(xml);
	}

}
