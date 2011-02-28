package net.matmas.pnapi.properties;

import java.awt.Component;
import javax.swing.JComboBox;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class RoleProperty extends Property {

	public RoleProperty() {
	}

	public RoleProperty(WithProperties withProperties) {
		super(withProperties);
	}

	@Override
	public String getPropertyTypeName() {
		return "Role";
	}

	@Override
	public boolean isIdEditable() {
		return false;
	}
	
	private JComboBox comboBox = new JComboBox();

	@Override
	public Component getEditor() {
		updateComboBox();
		return comboBox;
	}

	private void updateComboBox() {
		setRoleId(roleId);

		RoleDefinitionProperty selected = (RoleDefinitionProperty)comboBox.getSelectedItem();
		if (!getPetriNet().getProperties().contains(selected)) {
			selected = null;
		}

		repopulateComboBox();
		comboBox.setSelectedItem(selected);
	}

	private void repopulateComboBox() {
		comboBox.removeAllItems();
		for (RoleDefinitionProperty roleDefinitionProperty : getPetriNet().getProperties().getFilteredByClass(RoleDefinitionProperty.class)) {
			comboBox.addItem(roleDefinitionProperty);
		}
	}

	@Element(required=false)
	public String getRoleId() {
		updateComboBox();
		RoleDefinitionProperty selected = (RoleDefinitionProperty)comboBox.getSelectedItem();
		if (selected == null) {
			return null;
		}
		return selected.getId();
	}

	private String roleId;

	@Element(required=false)
	public void setRoleId(String roleId) {
		if (getPetriNet() == null) {
			this.roleId = roleId;
		}
		else {
			if (roleId != null) {
				repopulateComboBox();
				for (RoleDefinitionProperty roleDefinitionProperty : getPetriNet().getProperties().getFilteredByClass(RoleDefinitionProperty.class)) {
					if (roleDefinitionProperty.getId() != null && roleDefinitionProperty.getId().equals(roleId)) {
						comboBox.setSelectedItem(roleDefinitionProperty);
					}
				}
				this.roleId = null;
			}
		}
	}

}
