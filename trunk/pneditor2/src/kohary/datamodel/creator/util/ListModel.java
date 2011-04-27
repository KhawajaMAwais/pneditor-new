package kohary.datamodel.creator.util;

import kohary.datamodel.creator.home.ListEditorModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author matmas
 */
public abstract class ListModel<E> extends AbstractListModel implements Iterable<E> {

	protected List<E> elements = new ArrayList<E>();
	
	abstract public void addNew(ListEditorModel list);
	abstract public void addNew();
        
	public int getSize() {
		return elements.size();
	}
	
	public E getElementAt(int i) {
		return elements.get(i);
	}
	
	public void delete(int[] selectedIndices) {
		List<E> elementsToDelete = new LinkedList<E>();
		for (int i : selectedIndices) {
			elementsToDelete.add(elements.get(i));
		}
		for (E e : elementsToDelete) {
			int i = elements.indexOf(e);
			elements.remove(i);
			fireIntervalRemoved(this, i, i);
		}
	}
	
	public Iterator<E> iterator() {
		return elements.iterator();
	}
	
	public void clear() {
		int lastIndex = elements.size() - 1;
		elements.clear();
		if (lastIndex >= 0) {
			fireIntervalRemoved(this, 0, lastIndex);
		}
	}
        public void deleteComponent(E element) {
           
                    elements.remove(element);
            
        }
	
	public void add(E element) {
		elements.add(element);
	}

    public List<E> getElements() {
        return elements;
    }
        
}
