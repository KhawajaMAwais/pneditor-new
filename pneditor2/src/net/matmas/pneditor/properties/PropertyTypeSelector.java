package net.matmas.pneditor.properties;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.BooleanProperty;
import net.matmas.pnapi.properties.ClassDefinitionProperty;
import net.matmas.pnapi.properties.IntegerProperty;
import net.matmas.pnapi.properties.Property;
import net.matmas.pnapi.properties.RealProperty;
import net.matmas.pnapi.properties.RoleDefinitionProperty;
import net.matmas.pnapi.properties.RoleProperty;
import net.matmas.pnapi.properties.StringProperty;
import net.matmas.pnapi.properties.TimeProperty;
import net.matmas.pnapi.properties.TransitionProperty;
import net.matmas.pnapi.properties.WithProperties;

/**
 *
 * @author matmas
 */
public class PropertyTypeSelector extends JComboBox {

	private WithProperties withProperties;
	private Map<String, Class<? extends Property>> propertyNames = new LinkedHashMap<String, Class<? extends Property>>();

	public PropertyTypeSelector(WithProperties withProperties) {
		this.withProperties = withProperties;
		
		propertyNames.put(new StringProperty(withProperties).getPropertyTypeName(), StringProperty.class);
		propertyNames.put(new IntegerProperty(withProperties).getPropertyTypeName(), IntegerProperty.class);
		propertyNames.put(new RealProperty(withProperties).getPropertyTypeName(), RealProperty.class);
		propertyNames.put(new BooleanProperty(withProperties).getPropertyTypeName(), BooleanProperty.class);
		propertyNames.put(new TimeProperty(withProperties).getPropertyTypeName(), TimeProperty.class);
		propertyNames.put(new TransitionProperty(withProperties).getPropertyTypeName(), TransitionProperty.class);
		if (withProperties instanceof PetriNet) {
			propertyNames.put(new RoleDefinitionProperty(withProperties).getPropertyTypeName(), RoleDefinitionProperty.class);
			propertyNames.put(new ClassDefinitionProperty(withProperties).getPropertyTypeName(), ClassDefinitionProperty.class);
		}
		if (withProperties instanceof Transition) {
			propertyNames.put(new RoleProperty(withProperties).getPropertyTypeName(), RoleProperty.class);
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
		return property;
	}

}
