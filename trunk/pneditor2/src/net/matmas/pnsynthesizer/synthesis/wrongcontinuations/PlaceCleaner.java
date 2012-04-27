package net.matmas.pnsynthesizer.synthesis.wrongcontinuations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.matmas.pnapi.FiringSequence;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Subnet;
import net.matmas.pneditor.commands.Command;
import net.matmas.pneditor.commands.DeletePlaceCommand;


/**
 *
 * @author matmas
 */
public class PlaceCleaner {

	private PetriNet petriNet;
	private Marking initialMarking;
	private List<FiringSequence> possibleWrongContinuations;

	public PlaceCleaner(PetriNet petriNet, List<FiringSequence> possibleWrongContinuations) {
		this.petriNet = petriNet;
		this.initialMarking = petriNet.getMarking();
		this.possibleWrongContinuations = possibleWrongContinuations;
	}
	
	public void cleanupUnnecessaryPlaces() {
		Set<FiringSequence> coveredWrongContinuations = getCoveredWrongContinuations();
//		for (Place place : petriNet.getPlaces()) {
//			Command deletePlaceCmd = new DeletePlaceCommand(place);
//			deletePlaceCmd.execute();
//			if (!areAllWrongContinuationCovered(coveredWrongContinuations)) {
//				deletePlaceCmd.undo();
//			}
//		}
                
                for(int i = 0;i<petriNet.getPlaces().size();i++){
                        Command deletePlaceCmd = new DeletePlaceCommand(petriNet.getPlaces().get(i));
			deletePlaceCmd.execute();
			if (!areAllWrongContinuationCovered(coveredWrongContinuations)) {
				deletePlaceCmd.undo();
			}
                }
                    
                
                
	}

	private boolean areAllWrongContinuationCovered(Set<FiringSequence> wrongContinuations) {
		for (FiringSequence wrongContinuation : wrongContinuations) {
			if (initialMarking.isCorrectContinuation(wrongContinuation)) {
				return false;
			}
		}
		return true;
	}

	private Set<FiringSequence> getCoveredWrongContinuations() {
		Set<FiringSequence> coveredWrongContinuations = new HashSet<FiringSequence>();
		for (FiringSequence wrongContinuation : possibleWrongContinuations) {
			if (initialMarking.isWrongContinuation(wrongContinuation)) {
				coveredWrongContinuations.add(wrongContinuation);
			}
		}
		return coveredWrongContinuations;
	}

}
