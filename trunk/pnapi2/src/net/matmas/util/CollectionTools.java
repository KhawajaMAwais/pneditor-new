package net.matmas.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author matmas
 */
public class CollectionTools {
	public static boolean containsAtLeastOne(Collection c1, Collection c2) {
		for (Object c2Object : c2) {
			if (c1.contains(c2Object)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsOnlyInstancesOf(Collection collection, Class clazz) {
		for (Object object : collection) {
			if (object.getClass() != clazz) {
				return false;
			}
		}
		return true;
	}
	
	public static <E> E getFirstElement(Iterable<E> elements) {
		E firstElement = null;
		for (E element : elements) {
			firstElement = element;
			break;
		}
		return firstElement;
	}
	
	public static <E> E getFirstElementNotIn(Collection<E> elements, Collection<E> restricted) {
		E firstElement = null;
		for (E element : elements) {
			if (!restricted.contains(element)) {
				firstElement = element;
				break;
			}
		}
		return firstElement;
	}
	
	public static <E> Set<E> getElementsNotIn(Collection<E> elements, Collection<E> restricted) {
		Set<E> filtered = new HashSet<E>();
		for (E element : elements) {
			if (!restricted.contains(element)) {
				filtered.add(element);
			}
		}
		return filtered;
	}
	
	public static <E> E getLastElement(Collection<E> elements) {
		E lastElement = null;
		for (E element : elements) {
			lastElement = element;
		}
		return lastElement;
	}
	
	private final static Random random = new Random();
	public static <E> E getRandomElement(List<E> elements) {
		int randomIndex = random.nextInt(elements.size());
		return elements.get(randomIndex);
	}

	@SuppressWarnings(value="unchecked")
	public static <E> Set<E> getFilteredByClass(Collection<?> elements, Class<E> clazz) {
		Set<E> result = new HashSet<E>();
		for (Object element : elements) {
			if (getClassAndItsSuperclasses(element.getClass()).contains(clazz)) {
				result.add((E)element);
			}
		}
		return result;
	}

	public static List<Class> getClassAndItsSuperclasses(Class clazz) {
		List<Class> superclasses = new LinkedList<Class>();
		superclasses.add(clazz);
		while (clazz != Object.class) {
			clazz = clazz.getSuperclass();
			superclasses.add(clazz);
		}
		return superclasses;
	}
}
