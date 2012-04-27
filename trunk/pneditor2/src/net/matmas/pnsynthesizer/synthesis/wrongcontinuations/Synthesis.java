package net.matmas.pnsynthesizer.synthesis.wrongcontinuations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lpsolve.LpSolveException;
import net.matmas.pnapi.FiringSequence;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pnsynthesizer.synthesis.Language;
import net.matmas.pnsynthesizer.synthesis.Log;

/**
 *
 * @author matmas
 */
public class Synthesis {
	
	private PetriNet petriNet;
	private Log log;
	
	public Synthesis(File logFile, PetriNet petriNet) throws FileNotFoundException {
		this.petriNet = petriNet;
		this.log = new Log(logFile, petriNet);
	}
	
	public Set<FiringSequence> synthesize() throws LpSolveException {
//		Timer timer = new Timer("Loading log");
		
		Language language = new Language(log);
		List<FiringSequence> correctContinuations = new ArrayList<FiringSequence>(language.getCorrectContinuations());
		Collections.sort(correctContinuations);
		List<FiringSequence> wrongContinuations = new ArrayList<FiringSequence>(language.getWrongContinuations());
		Collections.sort(wrongContinuations);
		ArrayList<Transition> transitions = language.getTransitions();
		Collections.sort(transitions);
		for (Transition transition : transitions) {
			petriNet.addTransition(transition);
		}
//		timer.end();
//		timer = new Timer("prepare solver");
		Solver solver = new Solver(correctContinuations, transitions);
//		timer.end();
//		timer = new Timer("calculate places");
		for (FiringSequence wrongContinuation : wrongContinuations) {
			if (isOccurenceSequence(wrongContinuation, petriNet)) {
				solver.addWrongContinuation(wrongContinuation);
				if (solver.solutionExist()) {
					petriNet.addAll(solver.getPlaceAndArcsFromResult(transitions, petriNet));
				}
				solver.removeLastWrongContinuation();
			}
		}
//		timer.end();
//		timer = new Timer("cleanup unnecessary places");
		new PlaceCleaner(petriNet, wrongContinuations).cleanupUnnecessaryPlaces();
//		timer.end();
//		timer = new Timer("merge places");
		new PlaceMerger(petriNet).execute();
//		timer.end();
//		timer = new Timer("cleanup unnecessary places");
//		new PlaceCleaner(petriNet, wrongContinuations).cleanupUnnecessaryPlaces(); // maybe it will help, maybe not
//		timer.end();

		Set<FiringSequence> notCoveredWrongContinuations = new HashSet<FiringSequence>();
		notCoveredWrongContinuations.addAll(wrongContinuations);
		notCoveredWrongContinuations.removeAll(getCoveredWrongContinuations(petriNet.getMarking(), wrongContinuations));
		return notCoveredWrongContinuations;
	}
	
	private boolean isOccurenceSequence(FiringSequence firingSequence, PetriNet petriNet) {
		Marking marking = petriNet.getMarking();
		return marking.isCorrectContinuation(firingSequence);
	}

	public Set<FiringSequence> getCoveredWrongContinuations(Marking initialMarking, List<FiringSequence> possibleWrongContinuations) {
		Set<FiringSequence> coveredWrongContinuations = new HashSet<FiringSequence>();
		for (FiringSequence wrongContinuation : possibleWrongContinuations) {
			if (initialMarking.isWrongContinuation(wrongContinuation)) {
				coveredWrongContinuations.add(wrongContinuation);
			}
		}
		return coveredWrongContinuations;
	}
}
