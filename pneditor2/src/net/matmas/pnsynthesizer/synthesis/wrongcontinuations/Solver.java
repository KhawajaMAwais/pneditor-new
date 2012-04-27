package net.matmas.pnsynthesizer.synthesis.wrongcontinuations;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Element;
import net.matmas.pnapi.FiringSequence;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;

/**
 *
 * @author matmas
 */
public class Solver {

	private LpSolve problem;
	private boolean problemChangedSinceLastSolve = true;
	private List<Transition> transitions;
	private int numWrongContinuations = 0;

	public Solver(List<FiringSequence> correctContinuations, List<Transition> transitions) throws LpSolveException {
		this.transitions = transitions;
		problem = LpSolve.makeLp(0, 1 + transitions.size() * 2);
		problem.setVerbose(LpSolve.IMPORTANT);
		setIntegerMode();
//		setLabels();
//		problem.setAddRowmode(true);
		setObjectiveFunction();
		for (FiringSequence correctContinuation : correctContinuations) {
			addConstraintFromCorrectContinuation(correctContinuation);
		}
//		problem.setAddRowmode(false);
//		problem.printLp();
	}

	public int getNumWrongContinuations() {
		return numWrongContinuations;
	}
	
	public void printSolution(PrintStream out) throws LpSolveException {
		if (solutionExist()) {
			for (double var : getSolution()) {
				out.println(var);
			}
			out.println();
		}
		else {
			out.println("No solution exist.");
		}
	}

	private void solve() throws LpSolveException {
		problem.resetBasis();
		problem.solve();
		problemChangedSinceLastSolve = false;
	}

	public double[] getSolution() throws LpSolveException {
		if (solutionExist()) {
			return problem.getPtrVariables();
		}
		else {
			throw new RuntimeException("No solution exist.");
		}
	}

	public boolean solutionExist() throws LpSolveException {
		if (problemChangedSinceLastSolve) {
			solve();
		}
		if (problem.getSolutioncount() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void addWrongContinuation(FiringSequence wrongContinuation) throws LpSolveException {
		StringBuilder result = new StringBuilder();
		result.append("-1");
		for (Transition transition : transitions) {
			int outputMultiplicity = wrongContinuation.getNumOfTransition(transition);
			int inputMultiplicity;
			if (transition != wrongContinuation.getLastTransition()) {
				inputMultiplicity = outputMultiplicity;
			}
			else {
				inputMultiplicity = outputMultiplicity - 1;
			}
			result.append(" " + outputMultiplicity);
			result.append(" -" + inputMultiplicity);
		}
		problem.strAddConstraint(result.toString(), LpSolve.GE, 1);
//		int count = 1 + transitions.size() * 2;
//		double[] row = new double[count + 1];
//		int i = 1;
//		row[i++] = -1;
//		for (Transition transition : transitions) {
//			int outputMultiplicity = wrongContinuation.getNumOfTransition(transition);
//			int inputMultiplicity;
//			if (transition != wrongContinuation.getLastTransition()) {
//				inputMultiplicity = outputMultiplicity;
//			}
//			else {
//				inputMultiplicity = outputMultiplicity - 1;
//			}
//			row[i++] = outputMultiplicity;
//			row[i++] = -inputMultiplicity;
//		}
//		problem.addConstraint(row, LpSolve.GE, 1);
		problemChangedSinceLastSolve = true;
		numWrongContinuations++;
	}

	public void removeLastWrongContinuation() throws LpSolveException {
		if (numWrongContinuations > 0) {
			problem.delConstraint(problem.getNrows());
			problemChangedSinceLastSolve = true;
			numWrongContinuations--;
		}
		else {
			throw new RuntimeException();
		}
	}

	private void setIntegerMode() throws LpSolveException {
		for (int col = 1; col <= problem.getNcolumns(); col++) {
			problem.setInt(col, true);
		}
	}

	private void setLabels() throws LpSolveException {
		problem.setColName(1, "tokens");
		for (int t = 0; t < transitions.size(); t++) {
			Transition transition = transitions.get(t);
			int col1 = (t + 1) * 2;
			int col2 = (t + 1) * 2 + 1;
			problem.setColName(col1, transition.getLabel() + "");
			problem.setColName(col2, transition.getLabel() + "'");
		}
	}

	private void setObjectiveFunction() throws LpSolveException {
		StringBuilder result = new StringBuilder("10");
		for (Transition transition : transitions) {
			result.append(" 1 1");
		}
		problem.strSetObjFn(result.toString());
//		int count = 1 + transitions.size() * 2;
//		double[] row = new double[count + 1];
//		row[1] = 10;
//		for (int i = 2; i <= count; i++) {
//			row[i] = 1;
//		}
//		problem.setObjFn(row);
	}

	private void addConstraintFromCorrectContinuation(FiringSequence correctContinuation) throws LpSolveException {
		StringBuilder result = new StringBuilder();
		result.append("1");
		for (Transition transition : transitions) {
			int outputMultiplicity = correctContinuation.getNumOfTransition(transition);
			int inputMultiplicity;
			if (transition != correctContinuation.getLastTransition()) {
				inputMultiplicity = outputMultiplicity;
			}
			else {
				inputMultiplicity = outputMultiplicity - 1;
			}
			result.append(" -" + outputMultiplicity);
			result.append(" " + inputMultiplicity);
		}
		problem.strAddConstraint(result.toString(), LpSolve.GE, 0);
//		int count = 1 + transitions.size() * 2;
//		double[] row = new double[count + 1];
//		int i = 1;
//		row[i++] = 1;
//		for (Transition transition : transitions) {
//			int outputMultiplicity = correctContinuation.getNumOfTransition(transition);
//			int inputMultiplicity;
//			if (transition != correctContinuation.getLastTransition()) {
//				inputMultiplicity = outputMultiplicity;
//			}
//			else {
//				inputMultiplicity = outputMultiplicity - 1;
//			}
//			row[i++] = -outputMultiplicity;
//			row[i++] = inputMultiplicity;
//		}
//		problem.addConstraint(row, LpSolve.GE, 0);
	}

	public Set<Element> getPlaceAndArcsFromResult(List<Transition> transitions, PetriNet petriNet) throws LpSolveException {
		double[] result = getSolution();
		Set<Element> createdElements = new HashSet<Element>();
		Place place = new Place();
		petriNet.getIdGenerator().setUniqueId(place);
		petriNet.getMarking().setTokens(place, Math.round((float)result[0]));
		createdElements.add(place);

		for (int t = 0; t < transitions.size(); t++) {
			Transition transition = transitions.get(t);

			int placeToTransitionIndex = 1 + t * 2;
			int transitionToPlaceIndex = 1 + t * 2 + 1;
			int placeToTransitionMultiplicity = Math.round((float)result[placeToTransitionIndex]);
			int transitionToPlaceMultiplicity = Math.round((float)result[transitionToPlaceIndex]);
			Arc arc;
			if (placeToTransitionMultiplicity > 0) {
				arc = new Arc(place, transition, true);
				arc.setMultiplicity(placeToTransitionMultiplicity);
				createdElements.add(arc);
			}

			if (transitionToPlaceMultiplicity > 0) {
				arc = new Arc(place, transition, false);
				arc.setMultiplicity(transitionToPlaceMultiplicity);
				createdElements.add(arc);
			}
		}
		return createdElements;
	}
}
