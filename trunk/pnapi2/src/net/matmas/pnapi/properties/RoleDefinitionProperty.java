package net.matmas.pnapi.properties;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class RoleDefinitionProperty extends Property {

	public RoleDefinitionProperty() {
		initialize();
	}

	public RoleDefinitionProperty(WithProperties withProperties) {
		super(withProperties);
		initialize();
	}

	private JPanel panel = new JPanel();
	private JCheckBox createCaseCheckBox = new JCheckBox();
	private JCheckBox deleteCaseCheckBox = new JCheckBox();

	private void initialize() {
		panel.add(new JLabel("Create"));
		panel.add(createCaseCheckBox);
		createCaseCheckBox.setToolTipText("Permission to create cases");
		panel.add(new JLabel("Delete"));
		panel.add(deleteCaseCheckBox);
		deleteCaseCheckBox.setToolTipText("Permission to delete cases");
	}

	@Override
	public String getPropertyTypeName() {
		return "Role definition";
	}

	@Override
	public Component getEditor() {
		return panel;
	}

	@Override
	public String toString() {
		if (getId() == null) {
			return "?";
		}
		return getId();

	}

	@Element
	public boolean isCreateCase() {
		return createCaseCheckBox.isSelected();
	}

	@Element
	public void setCreateCase(boolean createCase) {
		createCaseCheckBox.setSelected(createCase);
	}

	@Element
	public boolean isDeleteCase() {
		return deleteCaseCheckBox.isSelected();
	}

	@Element
	public void setDeleteCase(boolean deleteCase) {
		deleteCaseCheckBox.setSelected(deleteCase);
	}

	
}
