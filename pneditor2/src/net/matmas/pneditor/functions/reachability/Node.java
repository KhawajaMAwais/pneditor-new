package net.matmas.pneditor.functions.reachability;

import java.util.ArrayList;

public class Node {
	private static int currentId = 0;
	private int id;
	private Marking marking;
	private int level;
	private int x;
	private int y;
	private ArrayList<Node> ancestors;
	
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

	public Node(Marking marking, int level, Node ancestor) {
		this.id = currentId++;
		this.marking = marking;
		this.level = level;
		this.ancestors = new ArrayList<Node>();
		if(ancestor != null){
			for(Node n : ancestor.getAncestors()){
				if(!this.ancestors.contains(n)){
						this.ancestors.add(n);
				}
			}
			this.ancestors.add(ancestor);
		}
		
	}
	
	public Node(Node node){
		this.id = node.getId();
		this.marking = node.getMarking();
		this.level = node.getLevel();
		if(node.getAncestors() != null)
			this.ancestors = node.getAncestors();
		else
			this.ancestors = new ArrayList<Node>();
	}
	
	public void setAll(Node node){
		this.id = node.getId();
		this.marking = node.getMarking();
		this.level = node.getLevel();
		this.ancestors = node.getAncestors();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void decrementCurrentId(){
		currentId--;
	}

	public Marking getMarking() {
		return marking;
	}
	
	public void setMarking(Marking marking){
		this.marking = marking;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public ArrayList<Node> getAncestors(){
		return this.ancestors;
	}
	
	public boolean hasOmega(){
		for(Mark m :this.marking.getMarking()){
			if(m.getTokens()>=1000000000)
				return true;
		}
		return false;
	}
	
	public boolean equalsTo(Node node){
		if(node.getId() == this.id)
			return true;
		else return false;
	}
	
	public String toString(){
		return "ID: "+this.id+"\tMarking: "+this.marking.toString()+"\tlevel: "+this.level+"\n";
	}
	
	public static void resetCurrentId(){
		currentId = 0;
	}

}
