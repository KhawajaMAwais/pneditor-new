package net.matmas.pneditor.functions.reachability;

import java.util.ArrayList;
public class Marking {
	
	private ArrayList<Mark> marks;

	public Marking(Net net) {
		marks = new ArrayList<Mark>();
		for(Place p : net.getPlaces()){
			this.marks.add(new Mark(p.getId(), p.getTokens()));
		}
	}
	
	public Marking(int[] marks,Net net){
		this.marks = new ArrayList<Mark>();
		for(int i=0;i<net.getPlaces().size();i++){
			this.marks.add(new Mark(net.getPlaces().get(i).getId(), marks[i]));
		}
	}
	
	public Marking() {
		marks = new ArrayList<Mark>();
	}
	
	public void add(Mark mark){
		this.marks.add(mark);
	}
	
	public Mark get(int index){
		return this.marks.get(index);
	}
	
	public boolean equalsTo(Marking marking){		
		for(int i=0;i<marking.getMarking().size();i++){
			//if(marking.get(i).getPlaceId() == this.marks.get(i).getPlaceId()){
				if(marking.get(i).getTokens() != this.marks.get(i).getTokens()){
					if(!marking.get(i).isOmega() || !this.marks.get(i).isOmega()){
					return false;
					}
				}
			//}
		}
		return true;		
	}
	
	public ArrayList<Mark> getMarking(){
		return this.marks;
	}
	
	public String toString(){
		String result = "";
		for(Mark m : this.marks){
			result += " "+((m.getTokens()>=900000000)?"w":m.getTokens());
		}
		return result;
	}
	
	public boolean isGreaterThan(Marking marking){
		int greater = 0;
		int equal = 0;
		for(Mark m : this.marks){
			for(Mark m2 : marking.getMarking()){
				if(m.getPlaceId().equals(m2.getPlaceId())){
					if(m.getTokens() > m2.getTokens()){
						greater++;
					}
					if(m.getTokens() == m2.getTokens()){
						equal++;
					}
				}
			}
		}
		if((greater+equal) == this.marks.size() && greater != 0){
			return true;
		}
		else
			return false;
	}

}
