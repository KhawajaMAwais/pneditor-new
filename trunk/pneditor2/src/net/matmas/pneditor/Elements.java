package net.matmas.pneditor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Node;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.util.CollectionTools;

/**
 *
 * @author matmas
 */
public class Elements implements Iterable<Element>{

	private Set<Element> elements = new HashSet<Element>();

	public Set<Element> getElements() {
		return Collections.unmodifiableSet(elements);
	}

	// -------------------------------------------------------------------------

	public Set<Place> getPlaces() {
		return CollectionTools.getFilteredByClass(elements, Place.class);
	}

	public Set<Transition> getTransitions() {
		return CollectionTools.getFilteredByClass(elements, Transition.class);
	}

	public Set<Arc> getArcs() {
		return CollectionTools.getFilteredByClass(elements, Arc.class);
	}

	public Set<Node> getNodes() {
		return CollectionTools.getFilteredByClass(elements, Node.class);
	}

	// -------------------------------------------------------------------------

	public Iterator<Element> iterator() {
		return elements.iterator();
	}

	public void replaceWith(Element element) {
		this.clear();
		this.add(element);
	}

	public void replaceWith(Set<Element> elements) {
		this.clear();
		this.addAll(elements);
	}

	public void clear() {
		elements.clear();
	}

	public void add(Element element) {
		this.elements.add(element);
	}

	public void addAll(Set<? extends Element> elements) {
		this.elements.addAll(elements);
	}

	public boolean contains(Element element) {
		return elements.contains(element);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public int size() {
		return elements.size();
	}
}
