package net.matmas.pneditor.properties;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.BooleanProperty;
import net.matmas.pnapi.properties.Property;
import net.matmas.pnapi.properties.RealProperty;
import net.matmas.pnapi.properties.StringProperty;
import net.matmas.pnapi.properties.TimeProperty;
import net.matmas.pnapi.properties.TransitionProperty;
import net.matmas.pnapi.properties.WithProperties;

/**
 *
 * @author matmas
 */
public class PredefinedPropertySelector extends JComboBox {

	private WithProperties withProperties;
	private Map<String, Class<? extends Property>> propertyNames = new LinkedHashMap<String, Class<? extends Property>>();

	public PredefinedPropertySelector(WithProperties withProperties) {
		this.withProperties = withProperties;
		
		if (withProperties instanceof PetriNet) {
			propertyNames.put("name", StringProperty.class);
			propertyNames.put("author", StringProperty.class);
			propertyNames.put("description", StringProperty.class);
			propertyNames.put("soundness", BooleanProperty.class);
		}
		else if (withProperties instanceof Place) {
			propertyNames.put("static", BooleanProperty.class);
		}
		else if (withProperties instanceof Transition) {
			propertyNames.put("always fire", BooleanProperty.class);
			propertyNames.put("timer", TimeProperty.class);
			propertyNames.put("try to fire at", TimeProperty.class);
			propertyNames.put("trigger another transition", TransitionProperty.class);
			propertyNames.put("is workflow task", BooleanProperty.class);
			propertyNames.put("average task duration", TimeProperty.class);
			propertyNames.put("probability", RealProperty.class);
			propertyNames.put("open URL in browser", StringProperty.class);
			propertyNames.put("invoke URL in background", StringProperty.class);
		}
		else if (withProperties instanceof Arc) {
		}
		
		for (String propertyName : propertyNames.keySet()) {
			this.addItem(propertyName);
		}
	}

	public Property createSelectedPropertyInstance() {
		String propertyName = (String)this.getSelectedItem();
		Class<? extends Property> selectedClass = propertyNames.get(propertyName);
		Property property = null;
		try {
			property = selectedClass.getDeclaredConstructor(WithProperties.class).newInstance(withProperties);
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(PropertyTypeSelector.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(PropertyTypeSelector.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(PropertyTypeSelector.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(PropertyTypeSelector.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(PropertyTypeSelector.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(PropertyTypeSelector.class.getName()).log(Level.SEVERE, null, ex);
		}

		property.setId(propertyName);
		property.setIdEditable(false);
		return property;
	}

}
