package net.matmas.pnapi.properties;

import java.awt.Component;
import javax.swing.JButton;

/**
 *
 * @author matmas
 */
public class ClassDefinitionProperty extends Property {

	public ClassDefinitionProperty() {
	}

	public ClassDefinitionProperty(WithProperties withProperties) {
		super(withProperties);
	}

	@Override
	public String getPropertyTypeName() {
		return "Class definition";
	}

	@Override
	public String toString() {
		if (getId() == null) {
			return "?";
		}
		return getId();

	}

	@Override
	public Component getEditor() {
		return new JButton("...");
	}

}
