package net.matmas.pneditor.functions.reachability;

import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
	private boolean isBounded;
	private int deadlocks;
	
	public Graph(){
		this.edges = new ArrayList<Edge>();
		this.nodes = new ArrayList<Node>();
		this.isBounded = true;
		this.deadlocks = 0;
	}

	public void addEdge(int sourceId, int destinationId){
		this.edges.add(new Edge(sourceId, destinationId));
	}
	
	public void addEdge(int sourceId, int destinationId, String label, Transition transition){
		this.edges.add(new Edge(sourceId, destinationId, label, transition));
	}
	
	public void addEdge(Edge edge){
		this.edges.add(edge);
	}
	
	public Edge getEdge(int index){
		return this.edges.get(index);
	}

	public ArrayList<Edge> getEdges() {
		return this.edges;
	}

	public ArrayList<Node> getNodes() {
		return this.nodes;
	}
	
	public void addNode(Marking marking,int level){
		this.nodes.add(new Node(marking,level,null));
	}
	
	public void addNode(Node node){
		this.nodes.add(node);
	}
	
	public Node getNode(int index){
		return this.nodes.get(index);
	}
	
	public void removeNode(Node node){
		for(int i=0;i<this.nodes.size();i++){
			if(this.nodes.get(i).getId() == node.getId()){
				this.nodes.remove(i);
			}
		}
	}
	
	public void removeNode(int nodeId){
		this.nodes.remove(nodeId);
	}
	
	public void removeEdge(Edge edge){
		for(int i=0;i<this.edges.size();i++){
			if(this.edges.get(i).getSourceId() == edge.getSourceId() && this.edges.get(i).getDestinationId() == edge.getDestinationId()){
				this.edges.remove(i);
			}
		}
	}
	
	public boolean hasEdge(Edge edge){
		for(Edge e : this.edges){
			if(e.getSourceId() == edge.getSourceId() && e.getDestinationId() == edge.getDestinationId())
				return true;
		}
		return false;
	}
	
	public String toString(){
		String result="Nodes:\n\n";
		for(Node n :this.nodes){
			result += n.toString();
		}
		result += "\n\nEdges:\n\n";
		for(Edge e : this.edges){
			result += e.toString();
		}
		return result;
	}
	
	public void setBoundness(boolean boundness){
		this.isBounded = boundness;
	}
	
	public boolean isBounded(){
		if(this.isBounded)
			return true;
		else
			return false;
	}
	
	public void addDeadlock(){
		this.deadlocks++;
	}
	
	public int getDeadlocks(){
		return this.deadlocks;
	}
	
	public boolean nodesEqualsTo(ArrayList<Node> nodes){
		if(this.nodes.size() != nodes.size())
			return false;
		for(Node n : nodes){
			if(!this.nodes.contains(n))
				return false;
		}
		return true;
		
	}

}
