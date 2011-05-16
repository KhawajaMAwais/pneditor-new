package net.matmas.pneditor.functions.coverability;

import java.util.ArrayList;
import java.util.Stack;
import net.matmas.pnapi.PetriNet;
import net.matmas.pneditor.PNEditor;

public class Coverability {

	private static final int OMEGA = 1000000000;
	
	
	public static Graph findGraph(Net net) {
		int level = 0;
		Graph graph = new Graph();
		Node tempNode;
                String label = "";
                PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
                int order = 0;
                for (net.matmas.pnapi.Place places : petriNet.getPlaces()) {
                    order++;
                    if(places.getLabel().getText() == null)
                        label += "p" + order + ",";
                    else
                        label += places.getLabel().getText() + ",";
                }
                label = label.substring(0,label.length()-1);

                graph.setLabel(label);
                
		Stack<Node> unprocessed = new Stack<Node>();
		ArrayList<Transition> enabledTransitions;
		Marking initialMarking = new Marking(net);

		Node initialNode = new Node(initialMarking, level, null);
		graph.addNode(initialNode);

		unprocessed.push(initialNode);

		while (!unprocessed.empty()) {

			Node n = unprocessed.pop();

			enabledTransitions = getEnabledTransitions(n.getMarking(), net);

			if (enabledTransitions.isEmpty())
				graph.addDeadlock();

			for (Transition t : enabledTransitions) {
				boolean go = true;
				Node n3 = new Node(runTransition(t, n.getMarking(), net), n
						.getLevel() + 1, n);

				tempNode = new Node(n3);
				for (Node an : n3.getAncestors()) {
					if (n3.getMarking().isGreaterThan(an.getMarking())) {
						for (int i = 0; i < n3.getMarking().getMarking().size(); i++) {
							if (n3.getMarking().getMarking().get(i).getTokens() > an
									.getMarking().getMarking().get(i)
									.getTokens()) {
								tempNode.getMarking().getMarking().get(i)
										.setTokens(OMEGA);
								graph.setBoundness(false);
							}
						}
					}
				}
				n3.setAll(tempNode);

				for (Node n5 : graph.getNodes()) {
					if (n5.getMarking().equalsTo(n3.getMarking())) {
						n3.decrementCurrentId();
						graph.addEdge(n.getId(), n5.getId(), t.getLabel(), t);
						go = false;
						break;
					}
				}
				if (go) {
					graph.addNode(n3);
					graph.addEdge(n.getId(), n3.getId(), t.getLabel(), t);
					unprocessed.push(n3);

				}
				n3 = null;
			}

		}
		return graph;
	}

	public static Marking runTransition(Transition t, Marking m, Net n) {
		Marking current = new Marking(n);
		n.setMarking(m);
		if (t.isEnabled(n)) {
			t.run(n);
		}
		Marking newMarking = new Marking(n);
		n.setMarking(current);
		return newMarking;
	}

	public static ArrayList<Transition> getEnabledTransitions(Marking m, Net n) {
		ArrayList<Transition> enabled = new ArrayList<Transition>();
		Marking current = new Marking(n);
		n.setMarking(m);
		for (Transition t : n.getTransitions()) {
			if (t.isEnabled(n)) {
				enabled.add(t);
			}
		}
		n.setMarking(current);
		return enabled;
	}

	public static String findMarking(Net net, Graph graph, Marking marking) {
		Graph reversedGraph;
		boolean isReachable = false;
		boolean bounded = true;
		String result = "";

		if (!graph.isBounded()) {
			Marking oldMarking = new Marking(net);
			net.reverseNet();
			net.setMarking(marking);

			reversedGraph = findGraph(net);
			net.reverseNet();
			net.setMarking(oldMarking);
			if (!reversedGraph.isBounded())
				bounded = false;

			for (Node n : reversedGraph.getNodes()) {
				if (n.getMarking().equalsTo(new Marking(net))) {
					isReachable = true;
					result = marking.toString()
							+ " is reachable\n\nwith firing sequence:\n\n";
					net.setMarking(marking);
					int i = 0;
					for (Edge e : getRoute(reversedGraph, n.getMarking(), net)) {
						if (i++ == 0)
							result += e.getLabel();
						else
							result += " -> " + e.getLabel();
					}
				}
			}

		} else {
			for (Node n : graph.getNodes()) {
				if (n.getMarking().equalsTo(marking)) {
					isReachable = true;
					result = marking.toString()
							+ " is reachable\n\nwith firing sequence:\n\n";
					ArrayList<Edge> route = getRoute(graph, marking, net);
					for (int i = route.size() - 1; i >= 0; i--) {
						if (i == route.size() - 1)
							result += route.get(i).getLabel();
						else
							result += " -> " + route.get(i).getLabel();
					}
				}
			}
		}

		if (!isReachable) {
			if (!bounded) {
				result = "Can't decide if " + marking.toString()
						+ " is reachable";
			} else {
				result = marking.toString() + " is not reachable";
			}
		}

		return result;

	}

	public static ArrayList<Edge> getRoute(Graph graph, Marking marking, Net net) {
		ArrayList<Edge> route = new ArrayList<Edge>();
		Marking marking2 = new Marking(net);
		Node tempNode = new Node(marking2, 0, null);
		ArrayList<Node> passedNodes = new ArrayList<Node>();
		
		for (Node n : graph.getNodes()) {
			if (n.getMarking().equalsTo(marking))
				tempNode = n;
		}
		
		passedNodes.add(tempNode);

		while (!tempNode.getMarking().equalsTo(marking2)){
			for (Edge e : graph.getEdges()) {
				if (e.getDestinationId() == tempNode.getId()) {
					
					for (Node n1 : graph.getNodes()) {
						if (n1.getId() == e.getSourceId() && !passedNodes.contains(n1)){
							tempNode = n1;					
							passedNodes.add(n1);
							route.add(e);
						}
					}
				}
			}
		}
		
		return route;

	}

	public static boolean checkLiveness(Graph graph, Net net) {
		Graph tempGraph;
		boolean ok = false;
		ArrayList<Transition> l4live = new ArrayList<Transition>();
		Marking oldMarking = new Marking(net);

		for (Transition t : net.getTransitions()) { // prechod t
			for (Node n : graph.getNodes()) { // M dosiahnutelne z M0
				net.setMarking(n.getMarking());
				tempGraph = findGraph(net);
				ok = false;
				for (Node n2 : tempGraph.getNodes()) { // M' dosiahnutelne z M
					net.setMarking(n2.getMarking());
					if (t.isEnabled(net)) { // t je spustitelne v M'
						ok = true;
						break;
					}
				}
				if (ok == false) {
					net.setMarking(oldMarking);
					return false;
				}

			}
			if (!l4live.contains(t))
				l4live.add(t);

		}

		if (!net.transitionsEqualsTo(l4live)) {
			net.setMarking(oldMarking);
			return false;
		} else {
			net.setMarking(oldMarking);
			return true;
		}

	}

}
