package net.matmas.pnsynthesizer.synthesis;

import net.matmas.pnapi.Transition;
import net.matmas.pnapi.FiringSequence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author matmas
 */
public class Log extends LinkedList<FiringSequence> {
	
	private ArrayList<Transition> transitions;
	
	public Log() {
	}
	
	public Log(File file, PetriNet petriNet) throws FileNotFoundException {
		transitions = readFromFile(file, petriNet);
	}
	
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	
	public void writeToFile(File file) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
		
		for (int i = 0; i < this.size(); i++) {
			FiringSequence firingSequence = this.get(i);
			for (Transition transition : firingSequence) {
				out.write(i + " " + transition.getLabel().getText().toString() + '\n');
			}
		}
		out.close();
	}
	
	private ArrayList<Transition> readFromFile(File file, PetriNet petriNet) throws FileNotFoundException {
		ArrayList<Transition> transitionSet = new ArrayList<Transition>();
		LabelToTransition labelToTransition = new LabelToTransition(petriNet);
		int previousCaseId = -1;
		FiringSequence firingSequence = null;
		Scanner fileScanner = new Scanner(file);
		while (fileScanner.hasNextLine()) {
			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			lineScanner.useDelimiter(" ");
			if (lineScanner.hasNext()) {
				int caseId = lineScanner.nextInt();
				String transitionFullName = "";
				while (lineScanner.hasNext()) {
					transitionFullName = transitionFullName + lineScanner.next() + " ";
				}
				transitionFullName = transitionFullName.trim();
				Transition transition = labelToTransition.getTransition(transitionFullName);
				transitionSet.add(transition);
				if (caseId != previousCaseId || firingSequence == null) {
					if (firingSequence != null) {
						this.add(firingSequence);
					}
					firingSequence = new FiringSequence();
					previousCaseId = caseId;
				}
				firingSequence.add(transition);
			}
			lineScanner.close();
		}
		this.add(firingSequence);
		fileScanner.close();
		return transitionSet;
	}
}
