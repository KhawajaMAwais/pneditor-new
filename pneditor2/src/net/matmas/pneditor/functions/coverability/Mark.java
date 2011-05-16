package net.matmas.pneditor.functions.coverability;

public class Mark {
	
	private String placeId;
	private int tokens;
	
	public Mark(String placeId, int tokens) {
		super();
		this.placeId = placeId;
		this.tokens = tokens;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
	public boolean equalsTo(Mark mark){
		if(mark.getTokens() == this.tokens)
			return true;
		else
			return false;
	}
	
	public boolean isOmega(){
		if(this.tokens > 900000000)
			return true;
		else
			return false;
	}

}
