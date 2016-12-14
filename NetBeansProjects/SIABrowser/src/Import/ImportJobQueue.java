package Import;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ImportJobQueue extends ArrayList<ImportRecord> implements Queue<ImportRecord> {

	/**
		Retrieves, but does not remove, the head of this queue.
		This method differs from peek only in that it throws an exception if this queue is empty.
		
		Returns:the head of this queue
		
		Throws:NoSuchElementException - if this queue is empty
	*/
	@Override
	public ImportRecord element() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
		Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
		When using a capacity-restricted queue, this method is generally preferable to add(E), which can fail to insert an element
		only by throwing an exception.
		Parameters:e - the element to add
	
		Returns:true if the element was added to this queue, else false
	
		Throws:ClassCastException - if the class of the specified element prevents it from being added to this queue
		NullPointerException - if the specified element is null and this queue does not permit null elements
		IllegalArgumentException - if some property of this element prevents it from being added to this queue
	*/
	@Override
	public boolean offer(ImportRecord arg0) {
		if (arg0 == null) throw new NullPointerException();
		if ((arg0 instanceof ImportRecord) == false) throw new ClassCastException();
		
		int i = this.size();
		add(i, arg0);
		return true;
	}

	/**
		Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
		
		Returns:the head of this queue, or null if this queue is empty
	*/
	@Override
	public ImportRecord peek() {
		if (this.isEmpty()) return null;
		return get(0);
	}

	/**
	 * Retrieves and removes the head of this queue, or returns null if this queue is empty.
	 * 
	 * Returns:the head of this queue, or null if this queue is empty
	 */
	@Override
	public ImportRecord poll() {
		if (this.isEmpty()) return null;
		return get(0);
	}

	/**
		Retrieves and removes the head of this queue.
		This method differs from poll only in that it throws an exception if this queue is empty.
		
		Returns:the head of this queue
		
		Throws:NoSuchElementException - if this queue is empty
	*/
	@Override
	public ImportRecord remove() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return remove(0);
	}
	
	public ImportRecord get(int id) {
		return null;
	}
	
	public String getStatus(int id) {
		return null;
	}
}
