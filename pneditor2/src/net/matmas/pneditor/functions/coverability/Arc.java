package net.matmas.pneditor.functions.coverability;

public class Arc {

	private String sourceId;
	private String destinationId;
	private int multiplicity;
	
	public Arc(String sourceId, String destinationId, int multiplicity) {
		super();
		this.sourceId = sourceId;
		this.destinationId = destinationId;
		this.multiplicity = multiplicity;
	}
	
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}
	public int getMultiplicity() {
		return multiplicity;
	}
	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}
	
	public String toString(){
		return "source ID:"+this.sourceId+"\tdestination ID:"+this.destinationId+"\tmultiplicity:"+this.multiplicity+"\n";
	}
	
	
}
