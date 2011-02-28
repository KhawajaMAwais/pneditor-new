package net.matmas.pnapi.properties;

import java.awt.Component;
import javax.swing.JComboBox;
import net.matmas.pnapi.Transition;
import org.simpleframework.xml.Element;

/**
 *
 * @author matmas
 */
public class TransitionProperty extends Property {

	public TransitionProperty() {
	}

	public TransitionProperty(WithProperties withProperties) {
		super(withProperties);
	}

	@Override
	public String getPropertyTypeName() {
		return "Transition";
	}

	private JComboBox comboBox = new JComboBox();

	@Override
	public Component getEditor() {
		updateComboBox();
		return comboBox;
	}

	private void updateComboBox() {
		setTransition(transitionId);

		Transition selected = (Transition)comboBox.getSelectedItem();
		if (!getPetriNet().getTransitions().contains(selected)) {
			selected = null;
		}

		repopulateComboBox();
		comboBox.setSelectedItem(selected);
	}

	private void repopulateComboBox() {
		comboBox.removeAllItems();
		for (Transition transition : getPetriNet().getTransitions()) {
			comboBox.addItem(transition);
		}
	}

	@Element(required=false)
	public String getTransition() {
		updateComboBox();
		Transition selected = (Transition)comboBox.getSelectedItem();
		if (selected == null) {
			return null;
		}
		return selected.getId();
	}

	private String transitionId;

	@Element(required=false)
	public void setTransition(String transitionId) {
		if (getPetriNet() == null) {
			this.transitionId = transitionId;
		}
		else {
			if (transitionId != null) {
				repopulateComboBox();
				for (Transition transition : getPetriNet().getTransitions()) {
					if (transitionId.equals(transition.getId())) {
						comboBox.setSelectedItem(transition);
					}
				}
				this.transitionId = null;
			}
		}
	}

	
	
}
