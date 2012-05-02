/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kohary.datamodel.dapi.Element;

/**
 *
 * @author Godric
 */
public class Elements implements Iterable<Element>{

    private Set<Element> elements = new HashSet<Element>();

    public Set<Element> getElements() {
        return elements;
    }
    
    
    
    public Iterator<Element> iterator() {
       return elements.iterator();
    }
    
    public void replaceWith(Element element) {
		this.clear();
		this.add(element);
	}

	public void replaceWith(Set<Element> elements) {
		this.clear();
		this.addAll(elements);
	}

	public void clear() {
		elements.clear();
	}

	public void add(Element element) {
		this.elements.add(element);
	}

	public void addAll(Set<? extends Element> elements) {
		this.elements.addAll(elements);
	}

	public boolean contains(Element element) {
		return elements.contains(element);
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public int size() {
		return elements.size();
	}
        public Element get(int index){
            int i=0;
            for(Element element:elements){
                if(i==index){
                    return element;
                }
            }
            return null;
        }
       
    
}
