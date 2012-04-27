package net.matmas.pnsynthesizer.synthesis.wrongcontinuations;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lpsolve.LpSolveException;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.FiringSequence;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.PetriNetException;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.commands.Command;
import net.matmas.pnsynthesizer.synthesis.Language;
import net.matmas.pneditor.commands.DeletePlaceCommand;

/**
 *
 * @author matmas
 */
public class PlaceMerger {

	private PetriNet petriNet;
	private Marking initialMarking;
	private List<FiringSequence> correctContinuations;
	private List<FiringSequence> wrongContinuations;
	private ArrayList<Transition> transitions= new ArrayList<Transition>();
	private Solver solver;

	public PlaceMerger(PetriNet petriNet) throws LpSolveException {
		this.petriNet = petriNet;
		initialMarking = petriNet.getMarking();
		Language language;
		try {
			language = new Language(petriNet);
		} catch (PetriNetException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		correctContinuations = new ArrayList<FiringSequence>(language.getCorrectContinuations());
		Collections.sort(correctContinuations);
		wrongContinuations = new ArrayList<FiringSequence>(language.getWrongContinuations());
		Collections.sort(wrongContinuations);
		transitions = language.getTransitions();
		Collections.sort(transitions);
	}

	public void execute() throws LpSolveException {
		int oldNumPlaces = petriNet.getPlaces().size();
		while (true) {
		solver = new Solver(correctContinuations, transitions);
			tryToMergePlaces();
			int newNumPlaces = petriNet.getPlaces().size();
			if (newNumPlaces < oldNumPlaces) {
				oldNumPlaces = newNumPlaces;
				continue;
			}
			else {
				break;
			}
		}
	}

	private void tryToMergePlaces() throws LpSolveException {
                
            Set<Place> places = new HashSet<Place>();
	//	Set<Place> places =  (Set<Place>) petriNet.getPlaces();
                for(Place place : petriNet.getPlaces()){
                    places.add(place);
                }
		Map<Place, Set<FiringSequence>> wrongContinuationMap = getWrongContinuationMap(places);
		for (Place place : places) {
			for (Place anotherPlace : getNearestPlaces(place)) {
				int oldScore = getScoreFromExistingPlace(place) + getScoreFromExistingPlace(anotherPlace);

				Set<FiringSequence> coveredWrongContinuations = wrongContinuationMap.get(place);
				Set<FiringSequence> anotherCoveredWrongContinuations = wrongContinuationMap.get(anotherPlace);

				Set<FiringSequence> combinedCoveredWrongContinuations = new HashSet<FiringSequence>();
				combinedCoveredWrongContinuations.addAll(coveredWrongContinuations);
				combinedCoveredWrongContinuations.addAll(anotherCoveredWrongContinuations);

				for (FiringSequence coveredWrongContinuation : combinedCoveredWrongContinuations) {
					solver.addWrongContinuation(coveredWrongContinuation);
				}
				if (solver.solutionExist()) {
					Set<Element> elements = solver.getPlaceAndArcsFromResult(transitions, petriNet);
					int newScore = getScoreFromElements(elements);
					if (newScore < oldScore) {
//						System.out.println("newscore = " + newScore);
						new DeletePlaceCommand(place).execute();
						new DeletePlaceCommand(anotherPlace).execute();
						if (elements.size() > 1) { // if there is one disconnected place, it is useless
//							System.out.println("ignoring disconnected place");
							petriNet.addAll(elements);
						}
						return;
					}
				}
				for (FiringSequence coveredWrongContinuation : combinedCoveredWrongContinuations) {
					solver.removeLastWrongContinuation();
				}
			}
		}
	}

	private int getScoreFromExistingPlace(Place place) {
		int score = 0;
		score += initialMarking.getTokens(place);
		for (Arc arc : place.getConnectedArcs()) {
			score += arc.getMultiplicity();
		}
		return score;
	}

	private int getScoreFromElements(Set<Element> elements) {
		int score = 0;
		for (Element element : elements) {
			if (element instanceof Place) {
				Place place = (Place)element;
				score += initialMarking.getTokens(place);
			}
			if (element instanceof Arc) {
				Arc arc = (Arc)element;
				score += arc.getMultiplicity();
			}
		}
		return score;
	}

	private Set<Place> getNearestPlaces(Place place) {
		Set<Place> nearestPlaces = new HashSet<Place>();
		for (Transition connectedTransitionNode : place.getConnectedTransitionNodes()) {
			for (Place connectedPlaceNode : connectedTransitionNode.getConnectedPlaceNodes()) {
				Place nearestPlace = connectedPlaceNode;
				if (place != nearestPlace) {
					nearestPlaces.add(nearestPlace);
				}
			}
		}
		return nearestPlaces;
	}

	private Map<Place, Set<FiringSequence>> getWrongContinuationMap(Set<Place> places) {
		Map<Place, Set<FiringSequence>> wrongContinuationMap = new HashMap<Place, Set<FiringSequence>>();
		
                for (Place place : places) {
			Command deletePlace = new DeletePlaceCommand(place);
			deletePlace.execute();
			Set<FiringSequence> coveredWrongContinuations = new HashSet<FiringSequence>();
			for (FiringSequence wrongContinuation : wrongContinuations) {
				if (initialMarking.isCorrectContinuation(wrongContinuation)) {
					coveredWrongContinuations.add(wrongContinuation);
				}
			}
			deletePlace.undo();
			wrongContinuationMap.put(place, coveredWrongContinuations);
		}
                
                
//                for(int i = 0;i<places.size();i++){
//                        Command deletePlace = new DeletePlaceCommand(places.get(i));
//			deletePlace.execute();
//                        Set<FiringSequence> coveredWrongContinuations = new HashSet<FiringSequence>();
//                        for (FiringSequence wrongContinuation : wrongContinuations) {
//			if (initialMarking.isCorrectContinuation(wrongContinuation)) {
//                            coveredWrongContinuations.add(wrongContinuation);
//				}
//			}
//			deletePlace.undo();
//			wrongContinuationMap.put(places.get(i), coveredWrongContinuations);
//                    }
//                
                
		return wrongContinuationMap;
	}
}
