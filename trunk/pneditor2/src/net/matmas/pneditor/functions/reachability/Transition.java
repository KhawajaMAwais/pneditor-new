package net.matmas.pneditor.functions.reachability;

public class Transition {
	
	private String id;
	private int x;
	private int y;
	private String label;
	
	public Transition(String id, int x, int y, String label) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String toString(){
		return "id:"+this.id+"\tx:"+this.x+"\ty:"+this.y+"\tlabel:"+this.label+"\n";
	}
	
	public boolean isEnabled(Net net){		
		for(Arc a : net.getArcs()){
			if(a.getDestinationId().equals(this.id)){
				for(Place p : net.getPlaces()){
					if(p.getId().equals(a.getSourceId())){
						if(p.getTokens() < a.getMultiplicity())
							return false;
					}
				}
			}
		}
		return true;		
	}
	
	public void run(Net net){
		for(Arc a : net.getArcs()){
			if(a.getSourceId().equals(this.id)){
				for(Place p : net.getPlaces()){
					if(p.getId().equals(a.getDestinationId())){
						p.addTokens(a.getMultiplicity());
					}
				}
			}
		}
		for(Arc a : net.getArcs()){
			if(a.getDestinationId().equals(this.id)){
				for(Place p : net.getPlaces()){
					if(p.getId().equals(a.getSourceId())){
						p.substractTokens(a.getMultiplicity());
					}
				}
			}
		}
	}

}
