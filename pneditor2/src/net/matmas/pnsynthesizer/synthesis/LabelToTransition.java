package net.matmas.pnsynthesizer.synthesis;

import net.matmas.pnapi.Transition;
import java.util.HashMap;
import java.util.Map;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author matmas
 */
public class LabelToTransition {
	
	private Map<String, Transition> map = new HashMap<String, Transition>();
	private PetriNet petriNet;

	public LabelToTransition(PetriNet petriNet) {
		this.petriNet = petriNet;
	}
	
	public Transition getTransition(String label) {
		if (label.equals(null)) {
			return null;
		}
		if (map.containsKey(label)) {
			return map.get(label);
		}
		Transition transition = new Transition();
                transition.getLabel().setText(label);
		petriNet.getIdGenerator().setUniqueId(transition);
		map.put(label, transition);
		return transition;
	}
}
