package net.matmas.pneditor.functions.coverability;

public class Place {
	
	private String id;
	private int x;
	private int y;
	private int tokens;
	private boolean isStatic;
	private String label;
	
	
	public Place(String id, int x, int y, int tokens, boolean isStatic,String label) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.tokens = tokens;
		this.isStatic = isStatic;
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


	public int getTokens() {
		return tokens;
	}


	public void setTokens(int tokens) {
		this.tokens = tokens;
	}


	public boolean isStatic() {
		return isStatic;
	}


	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}
	
	public void addTokens(int tokens){
		this.tokens += tokens;
	}
	
	public void substractTokens(int tokens){
		this.tokens -= tokens;
	}
	
	public String toString(){
		return "id:"+this.id+"\tx:"+this.x+"\ty:"+this.y+"\ttokens:"+this.tokens+"\tlabel:"+this.label+"\tisStatic:"+this.isStatic+"\n";
	}

}
