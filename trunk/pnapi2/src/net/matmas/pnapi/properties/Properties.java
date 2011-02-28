package net.matmas.pnapi.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.matmas.util.CollectionTools;

/**
 *
 * @author matmas
 */
public class Properties implements Iterable<Property> {

	private List<Property> properties = new ArrayList<Property>();

	public void add(Property property) {
		properties.add(property);
	}

	public void remove(Property property) {
		properties.remove(property);
	}

	public Iterator<Property> iterator() {
		return properties.iterator();
	}

	public boolean contains(Property property) {
		return properties.contains(property);
	}

	public boolean containsPropertyWithId(String id) {
		for (Property property : properties) {
			if (id.equals(property.getId())) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings(value="unchecked")
	public <E> List<E> getFilteredByClass(Class<E> clazz) {
		List<E> result = new ArrayList<E>();
		for (Property property : properties) {
			if (CollectionTools.getClassAndItsSuperclasses(property.getClass()).contains(clazz)) {
				result.add((E)property);
			}
		}
		return result;
	}
	
}
