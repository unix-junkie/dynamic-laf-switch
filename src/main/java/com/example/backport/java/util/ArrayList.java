/*
 * @(#)ArrayList.java	1.20 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.backport.java.util;

import java.io.Serializable;
import java.util.Vector;

/**
 * Resizable-array implementation of the <tt>List</tt> interface.  Implements
 * all optional list operations, and permits all elements, including
 * <tt>null</tt>.  In addition to implementing the <tt>List</tt> interface,
 * this class provides methods to manipulate the size of the array that is
 * used internally to store the list.  (This class is roughly equivalent to
 * <tt>Vector</tt>, except that it is unsynchronized.)<p>
 *
 * The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>,
 * <tt>iterator</tt>, and <tt>listIterator</tt> operations run in constant
 * time.  The <tt>add</tt> operation runs in <i>amortized constant time</i>,
 * that is, adding n elements requires O(n) time.  All of the other operations
 * run in linear time (roughly speaking).  The constant factor is low compared
 * to that for the <tt>LinkedList</tt> implementation.<p>
 *
 * Each <tt>ArrayList</tt> instance has a <i>capacity</i>.  The capacity is
 * the size of the array used to store the elements in the list.  It is always
 * at least as large as the list size.  As elements are added an ArrayList,
 * its capacity grows automatically.  The details of the growth policy are not
 * specified beyond the fact that adding an element has constant amortized
 * time cost.<p>
 *
 * An application can increase the capacity of an <tt>ArrayList</tt> instance
 * before adding a large number of elements using the <tt>ensureCapacity</tt>
 * operation.  This may reduce the amount of incremental reallocation.<p>
 *
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an <tt>ArrayList</tt> instance concurrently, and at
 * least one of the threads modifies the list structurally, it <i>must</i> be
 * synchronized externally.  (A structural modification is any operation that
 * adds or deletes one or more elements, or explicitly resizes the backing
 * array; merely setting the value of an element is not a structural
 * modification.)  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the list.  If no such object exists, the
 * list should be "wrapped" using the <tt>Collections.synchronizedList</tt>
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the list:
 * <pre>
 *	List list = Collections.synchronizedList(new ArrayList(...));
 * </pre><p>
 *
 * The iterators returned by this class's <tt>iterator</tt> and
 * <tt>listIterator</tt> methods are <i>fail-fast</i>: if list is structurally
 * modified at any time after the iterator is created, in any way except
 * through the iterator's own remove or add methods, the iterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * @author  Josh Bloch
 * @version 1.20 11/29/01
 * @see	    Collection
 * @see	    List
 * @see	    LinkedList
 * @see	    Vector
 * @see	    Collections#synchronizedList(List)
 * @since JDK1.2
 */

public class ArrayList extends AbstractList implements Cloneable,
					            Serializable {
	private static final long serialVersionUID = 7873701401556464682L;

   /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    private transient Object elementData[];

    /**
     * The size of the ArrayList (the number of elements it contains).
     *
     * @serial
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param   initialCapacity   the initial capacity of the list.
     */
    public ArrayList(final int initialCapacity) {
	super();
	this.elementData = new Object[initialCapacity];
    }

    /**
     * Constructs an empty list.
     */
    public ArrayList() {
	this(10);
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.  The <tt>ArrayList</tt> instance has an initial capacity of
     * 110% the size of the specified collection.
     */
    public ArrayList(final Collection c) {
        this.size = c.size();
	this.elementData = new Object[this.size*110/100]; // Allow 10% room for growth
        c.toArray(this.elementData);
    }

    /**
     * Trims the capacity of this <tt>ArrayList</tt> instance to be the
     * list's current size.  An application can use this operation to minimize
     * the storage of an <tt>ArrayList</tt> instance.
     */
    public void trimToSize() {
	this.modCount++;
	final int oldCapacity = this.elementData.length;
	if (this.size < oldCapacity) {
	    final Object oldData[] = this.elementData;
	    this.elementData = new Object[this.size];
	    System.arraycopy(oldData, 0, this.elementData, 0, this.size);
	}
    }

    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure  that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity.
     */
    public void ensureCapacity(final int minCapacity) {
	this.modCount++;
	final int oldCapacity = this.elementData.length;
	if (minCapacity > oldCapacity) {
	    final Object oldData[] = this.elementData;
	    int newCapacity = oldCapacity * 3/2 + 1;
    	    if (newCapacity < minCapacity) {
		newCapacity = minCapacity;
	}
	    this.elementData = new Object[newCapacity];
	    System.arraycopy(oldData, 0, this.elementData, 0, this.size);
	}
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return  the number of elements in this list.
     */
    public int size() {
	return this.size;
    }

    /**
     * Tests if this list has no elements.
     *
     * @return  <tt>true</tt> if this list has no elements;
     *          <tt>false</tt> otherwise.
     */
    public boolean isEmpty() {
	return this.size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o element whose presence in this List is to be tested.
     */
    public boolean contains(final Object elem) {
	return this.indexOf(elem) >= 0;
    }

    /**
     * Searches for the first occurence of the given argument, testing
     * for equality using the <tt>equals</tt> method.
     *
     * @param   elem   an object.
     * @return  the index of the first occurrence of the argument in this
     *          list; returns <tt>-1</tt> if the object is not found.
     * @see     Object#equals(Object)
     */
    public int indexOf(final Object elem) {
	if (elem == null) {
	    for (int i = 0; i < this.size; i++) {
		if (this.elementData[i]==null) {
			return i;
		}
	}
	} else {
	    for (int i = 0; i < this.size; i++) {
		if (elem.equals(this.elementData[i])) {
			return i;
		}
	}
	}
	return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified object in
     * this list.
     *
     * @param   elem   the desired element.
     * @return  the index of the last occurrence of the specified object in
     *          this list; returns -1 if the object is not found.
     */
    public int lastIndexOf(final Object elem) {
	if (elem == null) {
	    for (int i = this.size-1; i >= 0; i--) {
		if (this.elementData[i]==null) {
			return i;
		}
	}
	} else {
	    for (int i = this.size-1; i >= 0; i--) {
		if (elem.equals(this.elementData[i])) {
			return i;
		}
	}
	}
	return -1;
    }

    /**
     * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
     * elements themselves are not copied.)
     *
     * @return  a clone of this <tt>ArrayList</tt> instance.
     */
    public Object clone() {
	try {
	    final ArrayList v = (ArrayList)super.clone();
	    v.elementData = new Object[this.size];
	    System.arraycopy(this.elementData, 0, v.elementData, 0, this.size);
	    v.modCount = 0;
	    return v;
	} catch (final CloneNotSupportedException e) {
	    // this shouldn't happen, since we are Cloneable
	    throw new InternalError();
	}
    }

    /**
     * Returns an array containing all of the elements in this list
     * in the correct order.
     *
     * @return an array containing all of the elements in this list
     * 	       in the correct order.
     */
    public Object[] toArray() {
	final Object[] result = new Object[this.size];
	System.arraycopy(this.elementData, 0, result, 0, this.size);
	return result;
    }

    /**
     * Returns an array containing all of the elements in this list in the
     * correct order.  The runtime type of the returned array is that of the
     * specified array.  If the list fits in the specified array, it is
     * returned therein.  Otherwise, a new array is allocated with the runtime
     * type of the specified array and the size of this list.<p>
     *
     * If the list fits in the specified array with room to spare (i.e., the
     * array has more elements than the list), the element in the array
     * immediately following the end of the collection is set to
     * <tt>null</tt>.  This is useful in determining the length of the list
     * <i>only</i> if the caller knows that the list does not contain any
     * <tt>null</tt> elements.
     *
     * @param a the array into which the elements of the list are to
     *		be stored, if it is big enough; otherwise, a new array of the
     * 		same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list.
     * @throws ArrayStoreException if the runtime type of a is not a supertype
     *         of the runtime type of every element in this list.
     */
    public Object[] toArray(Object a[]) {
        if (a.length < this.size) {
		a = (Object[])java.lang.reflect.Array.newInstance(
		                        a.getClass().getComponentType(), this.size);
	}

	System.arraycopy(this.elementData, 0, a, 0, this.size);

        if (a.length > this.size) {
		a[this.size] = null;
	}

        return a;
    }

    // Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of element to return.
     * @return the element at the specified position in this list.
     * @throws    IndexOutOfBoundsException if index is out of range <tt>(index
     * 		  &lt; 0 || index &gt;= size())</tt>.
     */
    public Object get(final int index) {
	this.RangeCheck(index);

	return this.elementData[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws    IndexOutOfBoundsException if index out of range
     *		  <tt>(index &lt; 0 || index &gt;= size())</tt>.
     */
    public Object set(final int index, final Object element) {
	this.RangeCheck(index);

	final Object oldValue = this.elementData[index];
	this.elementData[index] = element;
	return oldValue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param o element to be appended to this list.
     * @return <tt>true</tt> (as per the general contract of Collection.add).
     */
    public boolean add(final Object o) {
	this.ensureCapacity(this.size + 1);  // Increments modCount!!
	this.elementData[this.size++] = o;
	return true;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws    IndexOutOfBoundsException if index is out of range
     *		  <tt>(index &lt; 0 || index &gt; size())</tt>.
     */
    public void add(final int index, final Object element) {
	if (index > this.size || index < 0) {
		throw new IndexOutOfBoundsException(
			"Index: "+index+", Size: "+this.size);
	}

	this.ensureCapacity(this.size+1);  // Increments modCount!!
	System.arraycopy(this.elementData, index, this.elementData, index + 1,
			 this.size - index);
	this.elementData[index] = element;
	this.size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to removed.
     * @return the element that was removed from the list.
     * @throws    IndexOutOfBoundsException if index out of range <tt>(index
     * 		  &lt; 0 || index &gt;= size())</tt>.
     */
    public Object remove(final int index) {
	this.RangeCheck(index);

	this.modCount++;
	final Object oldValue = this.elementData[index];

	final int numMoved = this.size - index - 1;
	if (numMoved > 0) {
		System.arraycopy(this.elementData, index+1, this.elementData, index,
				     numMoved);
	}
	this.elementData[--this.size] = null; // Let gc do its work

	return oldValue;
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear() {
	this.modCount++;

	// Let gc do its work
	for (int i = 0; i < this.size; i++) {
		this.elementData[i] = null;
	}

	this.size = 0;
    }

    /**
     * Appends all of the elements in the specified Collection to the end of
     * this list, in the order that they are returned by the
     * specified Collection's Iterator.  The behavior of this operation is
     * undefined if the specified Collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the specified Collection is this list, and this
     * list is nonempty.)
     *
     * @param index index at which to insert first element
     *			  from the specified collection.
     * @param c elements to be inserted into this list.
     * @throws    IndexOutOfBoundsException if index out of range <tt>(index
     *		  &lt; 0 || index &gt; size())</tt>.
     */
    public boolean addAll(final Collection c) {
	this.modCount++;
	final int numNew = c.size();
	this.ensureCapacity(this.size + numNew);

	final Iterator e = c.iterator();
	for (int i=0; i<numNew; i++) {
		this.elementData[this.size++] = e.next();
	}

	return numNew != 0;
    }

    /**
     * Inserts all of the elements in the specified Collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified Collection's iterator.
     *
     * @param index index at which to insert first element
     *		    from the specified collection.
     * @param c elements to be inserted into this list.
     * @throws    IndexOutOfBoundsException if index out of range <tt>(index
     *		  &lt; 0 || index &gt; size())</tt>.
     */
    public boolean addAll(int index, final Collection c) {
	if (index > this.size || index < 0) {
		throw new IndexOutOfBoundsException(
			"Index: "+index+", Size: "+this.size);
	}

	final int numNew = c.size();
	this.ensureCapacity(this.size + numNew);  // Increments modCount!!

	final int numMoved = this.size - index;
	if (numMoved > 0) {
		System.arraycopy(this.elementData, index, this.elementData, index + numNew,
				     numMoved);
	}

	final Iterator e = c.iterator();
	for (int i=0; i<numNew; i++) {
		this.elementData[index++] = e.next();
	}

	this.size += numNew;
	return numNew != 0;
    }

    /**
     * Removes from this List all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
     * elements to the left (reduces their index).
     * This call shortens the list by <tt>(toIndex - fromIndex)</tt> elements.
     * (If <tt>toIndex==fromIndex</tt>, this operation has no effect.)
     *
     * @param fromIndex index of first element to be removed.
     * @param fromIndex index after last element to be removed.
     */
    protected void removeRange(final int fromIndex, final int toIndex) {
	this.modCount++;
	final int numMoved = this.size - toIndex;
        System.arraycopy(this.elementData, toIndex, this.elementData, fromIndex,
                         numMoved);

	// Let gc do its work
	final int newSize = this.size - (toIndex-fromIndex);
	while (this.size != newSize) {
		this.elementData[--this.size] = null;
	}
    }

    /**
     * Check if the given index is in range.  If not, throw an appropriate
     * runtime exception.
     */
    private void RangeCheck(final int index) {
	if (index >= this.size || index < 0) {
		throw new IndexOutOfBoundsException(
			"Index: "+index+", Size: "+this.size);
	}
    }

    /**
     * Save the state of the <tt>ArrayList</tt> instance to a stream (that
     * is, serialize it).
     *
     * @serialData The length of the array backing the <tt>ArrayList</tt>
     *             instance is emitted (int), followed by all of its elements
     *             (each an <tt>Object</tt>) in the proper order.
     */
    private synchronized void writeObject(final java.io.ObjectOutputStream s)
        throws java.io.IOException{
	// Write out element count, and any hidden stuff
	s.defaultWriteObject();

        // Write out array length
        s.writeInt(this.elementData.length);

	// Write out all elements in the proper order.
	for (int i=0; i<this.size; i++) {
		s.writeObject(this.elementData[i]);
	}
    }

    /**
     * Reconstitute the <tt>ArrayList</tt> instance from a stream (that is,
     * deserialize it).
     */
    private synchronized void readObject(final java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {
	// Read in size, and any hidden stuff
	s.defaultReadObject();

        // Read in array length and allocate array
        final int arrayLength = s.readInt();
        this.elementData = new Object[arrayLength];

	// Read in all elements in the proper order.
	for (int i=0; i<this.size; i++) {
		this.elementData[i] = s.readObject();
	}
    }
}
