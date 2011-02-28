package net.matmas.pnapi.properties;

import java.awt.Component;
import javax.swing.JCheckBox;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author matmas
 */
@Root(name="boolean")
public class BooleanProperty extends Property {

	public BooleanProperty() {
	}

	public BooleanProperty(WithProperties withProperties) {
		super(withProperties);
	}

	public String getPropertyTypeName() {
		return "Boolean";
	}

	private JCheckBox checkBox = new JCheckBox();

	public Component getEditor() {
		return checkBox;
	}

	@Element
	public boolean isSelected() {
		return checkBox.isSelected();
	}

	@Element
	public void setSelected(boolean selected) {
		checkBox.setSelected(selected);
	}
	
}