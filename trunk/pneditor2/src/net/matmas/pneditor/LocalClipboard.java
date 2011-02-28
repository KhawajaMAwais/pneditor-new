package net.matmas.pneditor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.Node;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.util.CollectionTools;

/**
 *
 * @author matmas
 */
public class LocalClipboard {

	private PetriNet clipboardNet = new PetriNet();

	public void setContents(Set<Element> elements, PetriNet petriNet) {
		clipboardNet.clear();
		Map<Element, Element> originalToCloneMap = makeOriginalToCloneMap(elements);
		copyMarking(originalToCloneMap, petriNet.getMarking(), clipboardNet.getMarking());
		clipboardNet.addAll(new HashSet<Element>(originalToCloneMap.values()));
	}

	public Set<Element> getContents(PetriNet petriNet) {
		Map<Element, Element> originalToCloneMap = makeOriginalToCloneMap(clipboardNet.getElements());
		copyMarking(originalToCloneMap, clipboardNet.getMarking(), petriNet.getMarking());
		return new HashSet<Element>(originalToCloneMap.values());
	}

	public boolean isEmpty() {
		return clipboardNet.isEmpty();
	}

	// -------------------------------------------------------------------------

	private void copyMarking(Map<Element, Element> originalToCloneMap, Marking sourceMarking, Marking destinationMarking) {
		for (Place place : CollectionTools.getFilteredByClass(originalToCloneMap.keySet(), Place.class)) {
			int tokens = sourceMarking.getTokens(place);
			destinationMarking.setTokens((Place)originalToCloneMap.get(place), tokens);
		}
	}

	private static Map<Element, Element> makeOriginalToCloneMap(Collection<Element> elements) {
		Map<Element, Element> clones = new Hashtable<Element, Element>();
		Set<Arc> connectedArcs = new HashSet<Arc>();

		for (Node node : CollectionTools.getFilteredByClass(elements, Node.class)) {
			clones.put(node, node.getClone());
			connectedArcs.addAll(node.getConnectedArcs());
		}

		for (Arc arc : connectedArcs) {
			if (elements.contains(arc.getPlace()) || elements.contains(arc.getTransition())) {
				Arc clonedArc = arc.getClone();

				if (elements.contains(arc.getPlace())) {
					clonedArc.setPlace((Place)clones.get(arc.getPlace()));
				}
				if (elements.contains(arc.getTransition())) {
					clonedArc.setTransition((Transition)clones.get(arc.getTransition()));
				}
				clones.put(arc, clonedArc);
			}
		}
		return clones;
	}

	// -------------------------------------------------------------------------
}
