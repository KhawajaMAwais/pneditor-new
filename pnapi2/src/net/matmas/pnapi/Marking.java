package net.matmas.pnapi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.matmas.util.CollectionTools;

/**
 *
 * @author matmas
 */
public class Marking {
	
	protected Map<Place, Integer> map = new ConcurrentHashMap<Place, Integer>();
	private PetriNet petriNet;

	/**
	 * Copy constructor.
	 * @param marking the marking to be copied.
	 */
	public Marking(Marking marking) {
		this.map = new ConcurrentHashMap<Place, Integer>(marking.map);
		this.petriNet = marking.petriNet;
	}

	/**
	 * Creates EMPTY marking of the specified Petri net.
	 * @param petriNet Petri net to create marking from.
	 */
	public Marking(PetriNet petriNet) {
		this.petriNet = petriNet;
	}

	private List<Transition> getTransitions() {
		return petriNet.getTransitions();
	}

    public PetriNet getPetriNet() {
        return petriNet;
    }

	/**
	 * Returns the number of tokens based on the specified PlaceNode (Place or ReferencePlace).
	 * If specified PlaceNode is ReferencePlace, it will return number of tokens of its connected Place.
	 * If the specified ReferencePlace is not connected to any Place, it will return zero.
	 * If the resulting Place is static, number of tokens will be given from initial marking instead.
	 */
	public int getTokens(Place place) {
        if (this.map.get(place) == null) { // Place has zero tokens in the beginning. Not every place is in map. Only those previously edited.
			return 0;
		}

		return this.map.get(place);
	}

	/**
	 * Sets the number of tokens to the specified PlaceNode (Place or ReferencePlace).
	 * If specified PlaceNode is ReferencePlace, it will set number of tokens to its connected Place.
	 * If the specified ReferencePlace is not connected to any Place, it will throw RuntimeException.
	 * If the specified number of tokens is negative, it will throw RuntimeException.
	 * If the resulting Place is static, number of tokens will be set to initial marking instead.
	 */
	public void setTokens(Place place, int tokens) {
		if (tokens < 0) {
			throw new RuntimeException("Number of tokens must be non-negative");
		}
		this.map.put(place, tokens);
	}

	/**
	 * Determines if a transition is enabled in this marking
	 * @param transition - transition to be checked
	 * @return true if transition is enabled in the marking, otherwise false
	 */
	public boolean isEnabled(Transition transition) {
		boolean isEnabled = true;
		for (Arc arc : transition.getConnectedArcs()) {
			if (arc.isPlaceToTransition()) {
				if (getTokens(arc.getPlace()) < arc.getMultiplicity()) {
					isEnabled = false;
					break;
				}
			}
		}
		return isEnabled;
	}


	/**
	 * Fires a transition in this marking.
	 * Changes this marking.
	 * @param transition transition to be fired in the marking
	 * @return false if the specified transition was not enabled, otherwise true
	 */
	public boolean fire(Transition transition) {
		boolean success;
		if (isEnabled(transition)) {
			for (Arc arc : transition.getConnectedArcs()) {
				if (arc.isPlaceToTransition()) {
					int tokens = getTokens(arc.getPlace());
					setTokens(arc.getPlace(), tokens - arc.getMultiplicity());
				}
				else {
					int tokens = getTokens(arc.getPlace());
					setTokens(arc.getPlace(), tokens + arc.getMultiplicity());
				}
			}
			success = true;
		}
		else {
			success = false;
		}
		return success;
	}

	/**
	 * Returns a new marking after firing a transition.
	 * Original marking is not changed.
	 * @param transition transition to be fired
	 * @return new marking with fired transition
	 */
	public Marking getMarkingAfterFiring(Transition transition) {
		if ( !this.isEnabled(transition)) {
			return null;
		}
		Marking newMarking = new Marking(this);
		newMarking.fire(transition);
		return newMarking;
	}

	public Set<Transition> getEnabledTransitions(Set<Transition> transitions) {
		Set<Transition> enabledTransitions = new HashSet<Transition>();
		for (Transition transition : transitions) {
			if (isEnabled(transition)) {
				enabledTransitions.add(transition);
			}
		}
		return enabledTransitions;
	}

	/**
	 * Returns a set of all enabled transitions
	 * @return set of all enabled transitions
	 */
	public Set<Transition> getAllEnabledTransitions() {
		Set<Transition> enabledTransitions = new HashSet<Transition>();
		for (Transition transition : getTransitions()) {
			if (isEnabled(transition)) {
				enabledTransitions.add(transition);
			}
		}
		return enabledTransitions;
	}

	private List<Transition> getAllEnabledTransitionsByList() {
		List<Transition> fireableTransitions = new ArrayList<Transition>();
		for (Transition transition : getTransitions()) {
			if (isEnabled(transition)) {
				fireableTransitions.add(transition);
			}
		}
		return fireableTransitions;
	}


	/**
	 * Fires random chosen transition
	 * @throws RuntimeException if no transition is enabled.
	 * @return transition, which was fired
	 */
	public Transition fireRandomTransition() {
		List<Transition> fireableTransitions = getAllEnabledTransitionsByList();
		if (fireableTransitions.size() == 0) {
			throw new RuntimeException("fireRandomTransition() -> no transition is enabled");
		}
		Transition randomTransition = CollectionTools.getRandomElement(fireableTransitions);
		fire(randomTransition);
		return randomTransition;
	}


	/**
	 * Determines if this marking can be fired by any transition.
	 * @return true if there is a transition which can be fired in the marking.
	 */
	public boolean isEnabledByAnyTransition() {
		return !getAllEnabledTransitions().isEmpty();
	}

	/**
	 * Returns true if specified firingSequence leads to valid marking
	 * i.e. getMarkingAfterFiring(firingSequence) != null
	 */
	public boolean isCorrectContinuation(FiringSequence firingSequence) {
		return getMarkingAfterFiring(firingSequence) != null;
	}

	/**
	 * Returns true if specified firingSequence leads to invalid marking
	 * i.e. !isCorrectContinuation(firingSequence)
	 */
	public boolean isWrongContinuation(FiringSequence firingSequence) {
		return !isCorrectContinuation(firingSequence);
	}

	/**
	 * Returns a marking after firing a sequence of transitions.
	 * The original marking is not changed.
	 * @param firingSequence sequence of transitions to be fired one after the other
	 * @return a new marking after firing a sequence of transitions
	 */
	public Marking getMarkingAfterFiring(FiringSequence firingSequence) {
		Marking newMarking = new Marking(this);
		for (Transition transition : firingSequence) {
			if (!newMarking.isEnabled(transition)) {
				return null;
			}
			newMarking.fire(transition);
		}
		return newMarking;
	}

	/**
	 * Returns a set of all transition firing sequences, which can be fired in this marking.
	 * @throws PetriNetException if there the same marking is visited more than once.
	 */
	public Set<FiringSequence> getAllFiringSequencesRecursively() throws PetriNetException {
		Set<Marking> visitedMarkings = new HashSet<Marking>();
		visitedMarkings.add(this);
		return getAllFiringSequencesRecursively(this, visitedMarkings);
	}

	private Set<FiringSequence> getAllFiringSequencesRecursively(Marking marking, Set<Marking> visitedMarkings) throws PetriNetException {
		Set<FiringSequence> firingSequences = new HashSet<FiringSequence>();

		Set<Transition> enabledTransitions = marking.getAllEnabledTransitions();
		for (Transition transition : enabledTransitions) {
			Marking newMarking = marking.getMarkingAfterFiring(transition);

			if (visitedMarkings.contains(newMarking)) {
				throw new PetriNetException("There is a loop.");
			}
			visitedMarkings.add(newMarking);

			if (!newMarking.isEnabledByAnyTransition()) { // leaf marking
				FiringSequence firingSequence = new FiringSequence();
				firingSequence.add(transition);
				firingSequences.add(firingSequence);
			}

			for (FiringSequence nextFiringSequence : getAllFiringSequencesRecursively(newMarking, visitedMarkings)) {
				FiringSequence firingSequence = new FiringSequence();
				firingSequence.add(transition);
				firingSequence.addAll(nextFiringSequence);
				firingSequences.add(firingSequence);
			}

			visitedMarkings.remove(newMarking);
		}
		return firingSequences;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Fireable transitions: ");

		for (Transition transition : this.getAllEnabledTransitions()) {
			result.append(transition.getLabel() + " ");
		}
		if (this.getAllEnabledTransitions().isEmpty()) {
			result.append("-NONE-");
		}
		result.append("\nPlaces: ");
		for (Place place : petriNet.getPlaces()) {
			result.append(place.getLabel() + ":" + getTokens(place) + " ");
		}
		if (petriNet.getPlaces().isEmpty()) {
			result.append("-NONE-");
		}

		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Marking other = (Marking) obj;
		if (this.petriNet != other.petriNet && (this.petriNet == null || !this.petriNet.equals(other.petriNet))) {
			return false;
		}
		if (this.map == other.map) {
			return true;
		}
		Set<Place> places = new HashSet<Place>(); // because map is sparse
		places.addAll(this.map.keySet());
		places.addAll(other.map.keySet());
		for (Place place : places) {
			if (this.getTokens(place) != other.getTokens(place)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + (this.petriNet != null ? this.petriNet.hashCode() : 0);
		for (Place place : this.map.keySet()) { // because map is sparse
			hash = 73 * hash + this.getTokens(place);
		}
		return hash;
	}


}
