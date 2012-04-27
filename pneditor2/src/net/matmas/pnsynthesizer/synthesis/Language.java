package net.matmas.pnsynthesizer.synthesis;

import java.util.ArrayList;
import java.util.List;
import net.matmas.pnapi.FiringSequence;
import java.util.Set;
import net.matmas.pnapi.Marking;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.PetriNetException;
import net.matmas.pnapi.Transition;

/**
 *
 * @author matmas
 */
public class Language {
	
	private LanguageNode rootNode = new LanguageNode(null);
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	
	public Language(Log log) {
		for (FiringSequence firingSequence : log) {
			insertCorrectContinuation(firingSequence);
		}
		this.transitions = log.getTransitions();
		setupDisallowedTransitionsRecursively(rootNode, transitions);
	}
	
	public Language(PetriNet petriNet) throws PetriNetException {
		Marking initialMarking = petriNet.getMarking();
		for (FiringSequence firingSequence : initialMarking.getAllFiringSequencesRecursively()) {
			insertCorrectContinuation(firingSequence);
		}
	//	this.transitions =  (ArrayList<Transition>) petriNet.getTransitions();
                for(Transition tran : petriNet.getTransitions()){
                    this.transitions.add(tran);
                }
		setupDisallowedTransitionsRecursively(rootNode, transitions);
	}
	
	private void insertCorrectContinuation(FiringSequence correctContinuation) {
		LanguageNode currentNode = rootNode;
		for (Transition transition : correctContinuation) {
			LanguageNode nextNode = currentNode.getNextNode(transition);
			if (nextNode == null) {
				nextNode = currentNode.addNextNode(transition);
			}
			currentNode = nextNode;
		}
	}

	private void setupDisallowedTransitionsRecursively(LanguageNode node, List<Transition> allTransitions) {
		node.getDisallowedNextTransitions().addAll(allTransitions);
		node.getDisallowedNextTransitions().removeAll(node.getNextTransitions());
		for (LanguageNode nextNode : node.getNextNodes()) {
			setupDisallowedTransitionsRecursively(nextNode, allTransitions);
		}
	}
	
	public LanguageNode getRootNode() {
		return rootNode;
	}
	
	public Set<LanguageNode> getFirstNodes() {
		return rootNode.getNextNodes();
	}
	
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	
	public Set<FiringSequence> getCorrectContinuations() {
		return rootNode.getCorrectContinuationsRecursively();
	}
	
	public Set<FiringSequence> getWrongContinuations() {
		return rootNode.getWrongContinuationsRecursively();
	}
}
