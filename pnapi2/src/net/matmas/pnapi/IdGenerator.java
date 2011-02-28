package net.matmas.pnapi;

/**
 *
 * @author matmas
 */
public class IdGenerator {
	private int nextUniquePlaceNumber = 1;
	private int nextUniqueTransitionNumber = 1;

	public void setUniqueId(Node node) {
		String id;
		if (node instanceof Place) {
			id = "p" + Integer.toString(nextUniquePlaceNumber++);
		}
		else if (node instanceof Transition) {
			id = "t" + Integer.toString(nextUniqueTransitionNumber++);
		}
		else {
			throw new RuntimeException();
		}
		node.setId(id);
	}

	public void fixFutureUniqueIds(PetriNet petriNet) {
		int maxPlaceNumber = 0;
		int maxTransitionNumber = 0;
		
		for (Place place : petriNet.getPlaces()) {
			String id = place.getId();
			if (id != null) {
				if (id.startsWith("p")) {
					try {
						int placeNumber = Integer.parseInt(id.substring(1));
						if (placeNumber > maxPlaceNumber) {
							maxPlaceNumber = placeNumber;
						}
					}
					catch (NumberFormatException ex) {
						//do nothing
					}
				}
			}
		}
		for (Transition transition : petriNet.getTransitions()) {
			String id = transition.getId();
			if (id != null) {
				if (id.startsWith("t")) {
					try {
						int transitionNumber = Integer.parseInt(id.substring(1));
						if (transitionNumber > maxTransitionNumber) {
							maxTransitionNumber = transitionNumber;
						}
					}
					catch (NumberFormatException ex) {
						//do nothing
					}
				}
			}
		}
		nextUniquePlaceNumber = maxPlaceNumber + 1;
		nextUniqueTransitionNumber = maxTransitionNumber + 1;
	}

}
