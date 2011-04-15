package net.matmas.pneditor.functions.reachability;


public class Edge {
	
	private int sourceId;
	private int destinationId;
	private String label;
	private Transition transition;
	
	public Edge(int sourceId,int destinationId,String label,Transition transition){
		this.destinationId = destinationId;
		this.sourceId = sourceId;
		this.label = label;
		this.transition = transition;
	}
	
	public Edge(int sourceId,int destinationId){
		this.destinationId = destinationId;
		this.sourceId = sourceId;
		this.label = "";
	}

	public int getSourceId() {
		return sourceId;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public String getLabel() {
		return label;
	}
	
	public Transition getTransition() {
		return transition;
	}

	public String toString(){
		return "source ID: "+this.sourceId+"\tdestination ID: "+this.destinationId+"\n";
	}
	
	

}
