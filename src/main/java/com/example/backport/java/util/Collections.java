/*
 * @(#)Collections.java	1.35 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.backport.java.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.example.backport.java.lang.Comparable;
import com.example.backport.java.lang.UnsupportedOperationException;

/**
 * This class consists exclusively of static methods that operate on or return
 * collections.  It contains polymorphic algorithms that operate on
 * collections, "wrappers", which return a new collection backed by a
 * specified collection, and a few other odds and ends.<p>
 *
 * The documentation for the polymorphic algorithms contained in this class
 * generally includes a brief description of the <i>implementation</i>.  Such
 * descriptions should be regarded as <i>implementation notes</i>, rather than
 * parts of the <i>specification</i>.  Implementors should feel free to
 * substitute other algorithms, so long as the specification itself is adhered
 * to.  (For example, the algorithm used by <tt>sort</tt> does not have to be
 * a mergesort, but it does have to be <i>stable</i>.)
 *
 * @author  Josh Bloch
 * @version 1.35 11/29/01
 * @see	    Collection
 * @see	    Set
 * @see	    List
 * @see	    Map
 * @since JDK1.2
 */

public class Collections {
    // Suppresses default constructor, ensuring non-instantiability.
    private Collections() {
    }

    // Algorithms

    /**
     * Sorts the specified list into ascending order, according to the
     * <i>natural ordering</i> of its elements.  All elements in the list must
     * implement the <tt>Comparable</tt> interface.  Furthermore, all elements
     * in the list must be <i>mutually comparable</i> (that is,
     * <tt>e1.compareTo(e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the list).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The specified list must be modifiable, but need not be resizable.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n log(n) performance, and can approach linear performance on nearly
     * sorted lists.<p>
     *
     * This implementation dumps the specified list into an array, sorts
     * the array, and iterates over the list resetting each element
     * from the corresponding position in the array.  This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.
     *
     * @param  list the list to be sorted.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> (for example, strings and integers).
     * @throws UnsupportedOperationException if the specified list's
     *	       list-iterator does not support the <tt>set</tt> operation.
     * @see Comparable
     */
    public static void sort(final List list) {
	final Object a[] = list.toArray();
	Arrays.sort(a);
	final ListIterator i = list.listIterator();
	for (int j=0; j<a.length; j++) {
	    i.next();
	    i.set(a[j]);
	}
    }

    /**
     * Sorts the specified list according to the order induced by the
     * specified comparator.  All elements in the list must be <i>mutually
     * comparable</i> using the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the list).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n log(n) performance, and can approach linear performance on nearly
     * sorted lists.<p>
     *
     * The specified list must be modifiable, but need not be resizable.
     * This implementation dumps the specified list into an array, sorts
     * the array, and iterates over the list resetting each element
     * from the corresponding position in the array.  This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.
     *
     * @param  list the list to be sorted.
     * @param  c the comparator to determine the order of the array.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator.
     * @throws UnsupportedOperationException if the specified list's
     *	       list-iterator does not support the <tt>set</tt> operation.
     * @see Comparator
     */
    public static void sort(final List list, final Comparator c) {
	final Object a[] = list.toArray();
	Arrays.sort(a, c);
	final ListIterator i = list.listIterator();
	for (int j=0; j<a.length; j++) {
	    i.next();
	    i.set(a[j]);
	}
    }


    /**
     * Searches the specified list for the specified object using the binary
     * search algorithm.  The list must be sorted into ascending order
     * according to the <i>natural ordering</i> of its elements (as by the
     * <tt>sort(List)</tt> method, above) prior to making this call.  If it is
     * not sorted, the results are undefined.  If the list contains multiple
     * elements equal to the specified object, there is no guarantee which one
     * will be found.<p>
     *
     * This method runs in log(n) time for a "random access" list (which
     * provides near-constant-time positional access).  It may
     * run in n log(n) time if it is called on a "sequential access" list
     * (which provides linear-time positional access).</p>
     *
     * If the specified list implements the <tt>AbstracSequentialList</tt>
     * interface, this method will do a sequential search instead of a binary
     * search; this offers linear performance instead of n log(n) performance
     * if this method is called on a <tt>LinkedList</tt> object.
     *
     * @param  list the list to be searched.
     * @param  key the key to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> (for example, strings and
     *	       integers), or the search key in not mutually comparable
     *	       with the elements of the list.
     * @see    Comparable
     * @see #sort(List)
     */
    public static int binarySearch(final List list, final Object key) {
	// Do a sequential search if appropriate
	if (list instanceof AbstractSequentialList) {
	    final ListIterator i = list.listIterator();
	    while (i.hasNext()) {
		final int cmp = ((Comparable)i.next()).compareTo(key);
		if (cmp == 0) {
			return i.previousIndex();
		} else if (cmp > 0)
		 {
			return -i.nextIndex();  // key not found.
		}
	    }
	    return -i.nextIndex()-1;  // key not found, list exhausted
	}

	// Otherwise, do a binary search
	int low = 0;
	int high = list.size()-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final Object midVal = list.get(mid);
	    final int cmp = ((Comparable)midVal).compareTo(key);

	    if (cmp < 0) {
		low = mid + 1;
	} else if (cmp > 0) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found
    }

    /**
     * Searches the specified list for the specified object using the binary
     * search algorithm.  The list must be sorted into ascending order
     * according to the specified comparator (as by the <tt>Sort(List,
     * Comparator)</tt> method, above), prior to making this call.  If it is
     * not sorted, the results are undefined.  If the list contains multiple
     * elements equal to the specified object, there is no guarantee which one
     * will be found.<p>
     *
     * This method runs in log(n) time for a "random access" list (which
     * provides near-constant-time positional access).  It may
     * run in n log(n) time if it is called on a "sequential access" list
     * (which provides linear-time positional access).</p>
     *
     * If the specified list implements the <tt>AbstracSequentialList</tt>
     * interface, this method will do a sequential search instead of a binary
     * search; this offers linear performance instead of n log(n) performance
     * if this method is called on a <tt>LinkedList</tt> object.
     *
     * @param  list the list to be searched.
     * @param  key the key to be searched for.
     * @param  c the comparator by which the list is ordered.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the list contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator,
     *	       or the search key in not mutually comparable with the
     *	       elements of the list using this comparator.
     * @see    Comparable
     * @see #sort(List, Comparator)
     */
    public static int binarySearch(final List list, final Object key, final Comparator c) {
	// Do a sequential search if appropriate
	if (list instanceof AbstractSequentialList) {
	    final ListIterator i = list.listIterator();
	    while (i.hasNext()) {
		final int cmp = c.compare(i.next(), key);
		if (cmp == 0) {
			return i.previousIndex();
		} else if (cmp > 0)
		 {
			return -i.nextIndex();  // key not found.
		}
	    }
	    return -i.nextIndex()-1;  // key not found, list exhausted
	}

	// Otherwise, do a binary search
	int low = 0;
	int high = list.size()-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final Object midVal = list.get(mid);
	    final int cmp = c.compare(midVal, key);

	    if (cmp < 0) {
		low = mid + 1;
	} else if (cmp > 0) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found
    }

    /**
     * Reverses the order of the elements in the specified list.<p>
     *
     * This method runs in linear time.
     *
     * @param  list the list whose elements are to be reversed.
     * @throws UnsupportedOperationException if the specified list's
     *	       list-iterator does not support the <tt>set</tt> operation.
     */
    public static void reverse(final List l) {
        final ListIterator fwd = l.listIterator(), rev = l.listIterator(l.size());
        for (int i=0, n=l.size()/2; i<n; i++) {
            final Object tmp = fwd.next();
            fwd.set(rev.previous());
            rev.set(tmp);
        }
    }

    /**
     * Randomly permutes the specified list using a default source of
     * randomness.  All permutations occur with approximately equal
     * likelihood.<p>
     *
     * The hedge "approximately" is used in the foregoing description because
     * default source of randomenss is only approximately an unbiased source
     * of independently chosen bits. If it were a perfect source of randomly
     * chosen bits, then the algorithm would choose permutations with perfect
     * uniformity.<p>
     *
     * This implementation traverses the list backwards, from the last element
     * up to the second, repeatedly swapping a randomly selected element into
     * the "current position".  Elements are randomly selected from the
     * portion of the list that runs from the first element to the current
     * position, inclusive.<p>
     *
     * This method runs in linear time for a "random access" list (which
     * provides near-constant-time positional access).  It may require
     * quadratic time for a "sequential access" list.
     *
     * @param  list the list to be shuffled.
     * @throws UnsupportedOperationException if the specified list's
     *         list-iterator does not support the <tt>set</tt> operation.
     */
    public static void shuffle(final List list) {
        shuffle(list, r);
    }
    private static Random r = new Random();

    /**
     * Randomly permute the specified list using the specified source of
     * randomness.  All permutations occur with equal likelihood
     * assuming that the source of randomness is fair.<p>
     *
     * This implementation traverses the list backwards, from the last element
     * up to the second, repeatedly swapping a randomly selected element into
     * the "current position".  Elements are randomly selected from the
     * portion of the list that runs from the first element to the current
     * position, inclusive.<p>
     *
     * This method runs in linear time for a "random access" list (which
     * provides near-constant-time positional access).  It may require
     * quadratic time for a "sequential access" list.
     *
     * @param  list the list to be shuffled.
     * @param  r the source of randomness to use to shuffle the list.
     * @throws UnsupportedOperationException if the specified list's
     *         list-iterator does not support the <tt>set</tt> operation.
     */
    public static void shuffle(final List list, final Random rnd) {
        for (int i=list.size(); i>1; i--) {
		swap(list, i-1, rnd.nextInt(i));
	}
    }

    /**
     * Swaps the two specified elements in the specified list.
     */
    private static void swap(final List a, final int i, final int j) {
        final Object tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }

    /**
     * Replaces all of the elements of the specified list with the specified
     * element. <p>
     *
     * This method runs in linear time.
     *
     * @param  list the list to be filled with the specified element.
     * @param  o The element with which to fill the specified list.
     * @throws UnsupportedOperationException if the specified list's
     *	       list-iterator does not support the <tt>set</tt> operation.
     */
    public static void fill(final List list, final Object o) {
        for (final ListIterator i = list.listIterator(); i.hasNext(); ) {
            i.next();
            i.set(o);
        }
    }

    /**
     * Copies all of the elements from one list into another.  After the
     * operation, the index of each copied element in the destination list
     * will be identical to its index in the source list.  The destination
     * list must be at least as long as the source list.  If it is longer, the
     * remaining elements in the destination list are unaffected. <p>
     *
     * This method runs in linear time.
     *
     * @param  dest The destination list.
     * @param  src The source list.
     * @throws IndexOutOfBoundsException if the destination list is too small
     *         to contain the entire source List.
     * @throws UnsupportedOperationException if the destination list's
     *         list-iterator does not support the <tt>set</tt> operation.
     */
    public static void copy (final List dest, final List src) {
        try {
	    for (ListIterator di=dest.listIterator(), si=src.listIterator();
		 si.hasNext(); ) {
                di.next();
                di.set(si.next());
            }
	} catch(final NoSuchElementException e) {
           throw new IndexOutOfBoundsException("Source does not fit in dest.");
        }
    }

    /**
     * Returns the minimum element of the given collection, according to the
     * <i>natural ordering</i> of its elements.  All elements in the
     * collection must implement the <tt>Comparable</tt> interface.
     * Furthermore, all elements in the collection must be <i>mutually
     * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose minimum element is to be determined.
     * @return the minimum element of the given collection, according
     *         to the <i>natural ordering</i> of its elements.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> (for example, strings and
     *	       integers).
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object min(final Collection coll) {
	final Iterator i = coll.iterator();
	Comparable candidate = (Comparable)i.next();
	while (i.hasNext()) {
	    final Comparable next = (Comparable)i.next();
	    if (next.compareTo(candidate) < 0) {
		candidate = next;
	}
	}
	return candidate;
    }

    /**
     * Returns the minimum element of the given collection, according to the
     * order induced by the specified comparator.  All elements in the
     * collection must be <i>mutually comparable</i> by the specified
     * comparator (that is, <tt>comp.compare(e1, e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose minimum element is to be determined.
     * @return the minimum element of the given collection, according
     *         to the specified comparator.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> using the specified comparator.
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object min(final Collection coll, final Comparator comp) {
	final Iterator i = coll.iterator();
	Object candidate = i.next();
	while (i.hasNext()) {
	    final Object next = i.next();
	    if (comp.compare(next, candidate) < 0) {
		candidate = next;
	}
	}
	return candidate;
    }

    /**
     * Returns the maximum element of the given collection, according to the
     * <i>natural ordering</i> of its elements.  All elements in the
     * collection must implement the <tt>Comparable</tt> interface.
     * Furthermore, all elements in the collection must be <i>mutually
     * comparable</i> (that is, <tt>e1.compareTo(e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose maximum element is to be determined.
     * @return the maximum element of the given collection, according
     *         to the <i>natural ordering</i> of its elements.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> (for example, strings and
     *         integers).
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object max(final Collection coll) {
	final Iterator i = coll.iterator();
	Comparable candidate = (Comparable)i.next();
	while (i.hasNext()) {
	    final Comparable next = (Comparable)i.next();
	    if (next.compareTo(candidate) > 0) {
		candidate = next;
	}
	}
	return candidate;
    }

    /**
     * Returns the maximum element of the given collection, according to the
     * order induced by the specified comparator.  All elements in the
     * collection must be <i>mutually comparable</i> by the specified
     * comparator (that is, <tt>comp.compare(e1, e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the collection).<p>
     *
     * This method iterates over the entire collection, hence it requires
     * time proportional to the size of the collection.
     *
     * @param  coll the collection whose maximum element is to be determined.
     * @return the maximum element of the given collection, according
     *         to the specified comparator.
     * @throws ClassCastException if the collection contains elements that are
     *	       not <i>mutually comparable</i> using the specified comparator.
     * @throws NoSuchElementException if the collection is empty.
     * @see Comparable
     */
    public static Object max(final Collection coll, final Comparator comp) {
	final Iterator i = coll.iterator();
	Object candidate = i.next();
	while (i.hasNext()) {
	    final Object next = i.next();
	    if (comp.compare(next, candidate) > 0) {
		candidate = next;
	}
	}
	return candidate;
    }


    // Unmodifiable Wrappers

    /**
     * Returns an unmodifiable view of the specified collection.  This method
     * allows modules to provide users with "read-only" access to internal
     * collections.  Query operations on the returned collection "read through"
     * to the specified collection, and attempts to modify the returned
     * collection, whether direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned collection does <i>not</i> pass the hashCode and equals
     * operations through to the backing collection, but relies on
     * <tt>Object</tt>'s <tt>equals</tt> and <tt>hashCode</tt> methods.  This
     * is necessary to preserve the contracts of these operations in the case
     * that the backing collection is a set or a list.<p>
     *
     * The returned collection will be serializable if the specified collection
     * is serializable.
     *
     * @param  c the collection for which an unmodifiable view is to be
     *	       returned.
     * @return an unmodifiable view of the specified collection.
     */
    public static Collection unmodifiableCollection(final Collection c) {
	return new UnmodifiableCollection(c);
    }

    static class UnmodifiableCollection implements Collection, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3667270770690864937L;
	Collection c;

	UnmodifiableCollection(final Collection c) {this.c = c;}

	public int size() 		    {return this.c.size();}
	public boolean isEmpty() 	    {return this.c.isEmpty();}
	public boolean contains(final Object o)   {return this.c.contains(o);}
	public Object[] toArray() 	    {return this.c.toArray();}
	public Object[] toArray(final Object[] a) {return this.c.toArray(a);}

	public Iterator iterator() {
	    return new Iterator() {
		Iterator i = UnmodifiableCollection.this.c.iterator();

		public boolean hasNext() {return this.i.hasNext();}
		public Object next() 	 {return this.i.next();}
		public void remove() {
		    throw new UnsupportedOperationException();
                }
	    };
        }

	public boolean add(final Object o){
	    throw new UnsupportedOperationException();
        }
	public boolean remove(final Object o) {
	    throw new UnsupportedOperationException();
        }

	public boolean containsAll(final Collection coll) {
	    return this.c.containsAll(coll);
        }
	public boolean addAll(final Collection coll) {
	    throw new UnsupportedOperationException();
        }
	public boolean removeAll(final Collection coll) {
	    throw new UnsupportedOperationException();
        }
	public boolean retainAll(final Collection coll) {
	    throw new UnsupportedOperationException();
        }
	public void clear() {
	    throw new UnsupportedOperationException();
        }
    }

    /**
     * Returns an unmodifiable view of the specified set.  This method allows
     * modules to provide users with "read-only" access to internal sets.
     * Query operations on the returned set "read through" to the specified
     * set, and attempts to modify the returned set, whether direct or via its
     * iterator, result in an <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned set will be serializable if the specified set
     * is serializable.
     *
     * @param  s the set for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified set.
     */

    public static Set unmodifiableSet(final Set s) {
	return new UnmodifiableSet(s);
    }

    static class UnmodifiableSet extends UnmodifiableCollection
    				 implements Set {
	private static final long serialVersionUID = -8030089987476292319L;

	UnmodifiableSet(final Set s) 		{super(s);}

	public boolean equals(final Object o) {return this.c.equals(o);}
	public int hashCode() 		{return this.c.hashCode();}
    }

    /**
     * Returns an unmodifiable view of the specified sorted set.  This method
     * allows modules to provide users with "read-only" access to internal
     * sorted sets.  Query operations on the returned sorted set "read
     * through" to the specified sorted set.  Attempts to modify the returned
     * sorted set, whether direct, via its iterator, or via its
     * <tt>subSet</tt>, <tt>headSet</tt>, or <tt>tailSet</tt> views, result in
     * an <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned sorted set will be serializable if the specified sorted set
     * is serializable.
     *
     * @param s the sorted set for which an unmodifiable view is to be
     *        returned.
     * @return an unmodifiable view of the specified sorted set.
     */
    public static SortedSet unmodifiableSortedSet(final SortedSet s) {
	return new UnmodifiableSortedSet(s);
    }

    static class UnmodifiableSortedSet extends UnmodifiableSet
    				 implements SortedSet {
	private static final long serialVersionUID = -5274635770611632962L;

	private final SortedSet ss;

	UnmodifiableSortedSet(final SortedSet s) {super(s); this.ss = s;}

        public Comparator comparator()     {return this.ss.comparator();}

        public SortedSet subSet(final Object fromElement, final Object toElement) {
            return new UnmodifiableSortedSet(this.ss.subSet(fromElement,toElement));
        }
        public SortedSet headSet(final Object toElement) {
            return new UnmodifiableSortedSet(this.ss.headSet(toElement));
        }
        public SortedSet tailSet(final Object fromElement) {
            return new UnmodifiableSortedSet(this.ss.tailSet(fromElement));
        }

        public Object first() 	           {return this.ss.first();}
        public Object last()  	           {return this.ss.last();}
    }

    /**
     * Returns an unmodifiable view of the specified list.  This method allows
     * modules to provide users with "read-only" access to internal
     * lists.  Query operations on the returned list "read through" to the
     * specified list, and attempts to modify the returned list, whether
     * direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned list will be serializable if the specified list
     * is serializable.
     *
     * @param  list the list for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified list.
     */
    public static List unmodifiableList(final List list) {
	return new UnmodifiableList(list);
    }

    static class UnmodifiableList extends UnmodifiableCollection
    				  implements List {
	private static final long serialVersionUID = -2561208740524521422L;

	final List list;

	UnmodifiableList(final List list) {
	    super(list);
	    this.list = list;
	}

	public boolean equals(final Object o) {return this.list.equals(o);}
	public int hashCode() 		{return this.list.hashCode();}

	public Object get(final int index) {return this.list.get(index);}
	public Object set(final int index, final Object element) {
	    throw new UnsupportedOperationException();
        }
	public void add(final int index, final Object element) {
	    throw new UnsupportedOperationException();
        }
	public Object remove(final int index) {
	    throw new UnsupportedOperationException();
        }
	public int indexOf(final Object o)            {return this.list.indexOf(o);}
	public int lastIndexOf(final Object o)        {return this.list.lastIndexOf(o);}
	public boolean addAll(final int index, final Collection c) {
	    throw new UnsupportedOperationException();
        }
	public ListIterator listIterator() 	{return this.listIterator(0);}

	public ListIterator listIterator(final int index) {
	    return new ListIterator() {
		ListIterator i = UnmodifiableList.this.list.listIterator(index);

		public boolean hasNext()     {return this.i.hasNext();}
		public Object next()         {return this.i.next();}
		public boolean hasPrevious() {return this.i.hasPrevious();}
		public Object previous()     {return this.i.previous();}
		public int nextIndex()       {return this.i.nextIndex();}
		public int previousIndex()   {return this.i.previousIndex();}

		public void remove() {
		    throw new UnsupportedOperationException();
                }
		public void set(final Object o) {
		    throw new UnsupportedOperationException();
                }
		public void add(final Object o) {
		    throw new UnsupportedOperationException();
                }
	    };
	}

	public List subList(final int fromIndex, final int toIndex) {
            return new UnmodifiableList(this.list.subList(fromIndex, toIndex));
        }
    }

    /**
     * Returns an unmodifiable view of the specified map.  This method
     * allows modules to provide users with "read-only" access to internal
     * maps.  Query operations on the returned map "read through"
     * to the specified map, and attempts to modify the returned
     * map, whether direct or via its collection views, result in an
     * <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned map will be serializable if the specified map
     * is serializable.
     *
     * @param  m the map for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified map.
     */
    public static Map unmodifiableMap(final Map m) {
	return new UnmodifiableMap(m);
    }

    private static class UnmodifiableMap implements Map, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 2288897531685569830L;
	private final Map m;

	UnmodifiableMap(final Map m) 		         {this.m = m;}

	public int size() 		         {return this.m.size();}
	public boolean isEmpty() 	         {return this.m.isEmpty();}
	public boolean containsKey(final Object key)   {return this.m.containsKey(key);}
	public boolean containsValue(final Object val) {return this.m.containsValue(val);}
	public Object get(final Object key) 	         {return this.m.get(key);}

	public Object put(final Object key, final Object value) {
	    throw new UnsupportedOperationException();
        }
	public Object remove(final Object key) {
	    throw new UnsupportedOperationException();
        }
	public void putAll(final Map t) {
	    throw new UnsupportedOperationException();
        }
	public void clear() {
	    throw new UnsupportedOperationException();
        }

	private transient Set keySet = null;
	private transient Set entrySet = null;
	private transient Collection values = null;

	public Set keySet() {
	    if (this.keySet==null) {
		this.keySet = unmodifiableSet(this.m.keySet());
	}
	    return this.keySet;
	}

	public Set entrySet() {
	    if (this.entrySet==null) {
		this.entrySet = new UnmodifiableEntrySet(this.m.entrySet());
	}
	    return this.entrySet;
	}

	public Collection values() {
	    if (this.values==null) {
		this.values = unmodifiableCollection(this.m.values());
	}
	    return this.values;
	}

	public boolean equals(final Object o) {return this.m.equals(o);}
	public int hashCode()           {return this.m.hashCode();}


        /**
         * We need this class in addition to UnmodifiableSet as
         * Map.Entries themselves permit modification of the backing Map
         * via their setValue operation.  This class is subtle: there are
         * many possible attacks that must be thwarted.
         */
        static class UnmodifiableEntrySet extends UnmodifiableSet {
            /**
		 *
		 */
		private static final long serialVersionUID = -4867454309214303478L;

	UnmodifiableEntrySet(final Set s) {
                super(s);
            }

            public Iterator iterator() {
                return new Iterator() {
                    Iterator i = UnmodifiableEntrySet.this.c.iterator();

                    public boolean hasNext() {
                        return this.i.hasNext();
                    }
                    public Object next() 	 {
                        return new UnmodifiableEntry((Map.Entry)this.i.next());
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            public Object[] toArray() {
                final Object[] a = this.c.toArray();
                for (int i=0; i<a.length; i++) {
			a[i] = new UnmodifiableEntry((Map.Entry)a[i]);
		}
                return a;
            }

            public Object[] toArray(final Object a[]) {
                // We don't pass a to c.toArray, to avoid window of
                // vulnerability wherein an unscrupulous multithreaded client
                // could get his hands on raw (unwrapped) Entries from c.
                final Object[] arr = this.c.toArray(a.length==0 ? a :
                            (Object[])java.lang.reflect.Array.newInstance(
                                          a.getClass().getComponentType(), 0));
                for (int i=0; i<arr.length; i++) {
			arr[i] = new UnmodifiableEntry((Map.Entry)arr[i]);
		}

                if (arr.length > a.length) {
			return arr;
		}

                System.arraycopy(arr, 0, a, 0, arr.length);
                if (a.length > arr.length) {
			a[arr.length] = null;
		}
                return a;
            }

            /**
             * This method is overridden to protect the backing set against
             * an object with a nefarious equals function that senses
             * that the equality-candidate is Map.Entry and calls its
             * setValue method.
             */
            public boolean contains(final Object o) {
                if (!(o instanceof Map.Entry)) {
			return false;
		}
                return this.c.contains(new UnmodifiableEntry((Map.Entry)o));
            }

            /**
             * The next two methods are overridden to protect against
             * an unscrupulous List whose contains(Object o) method senses
             * when o is a Map.Entry, and calls o.setValue.
             */
            public boolean containsAll(final Collection coll) {
                final Iterator e = coll.iterator();
                while (e.hasNext()) {
			if(!this.contains(e.next())) {
				return false;
			}
		}
                return true;
            }
            public boolean equals(final Object o) {
                if (o == this) {
			return true;
		}

                if (!(o instanceof Set)) {
			return false;
		}
                final Set s = (Set) o;
                if (s.size() != this.c.size()) {
			return false;
		}
                return this.containsAll(s); // Invokes safe containsAll() above
            }

            /**
             * This "wrapper class" serves two purposes: it prevents
             * the client from modifying the backing Map, by short-circuiting
             * the setValue method, and it protects the backing Map against
             * an ill-behaved Map.Entry that attempts to modify another
             * Map Entry when asked to perform an equality check.
             */
            private static class UnmodifiableEntry implements Map.Entry {
                private final Map.Entry e;

                UnmodifiableEntry(final Map.Entry e) {this.e = e;}

                public Object getKey()	  {return this.e.getKey();}
                public Object getValue()  {return this.e.getValue();}
                public Object setValue(final Object value) {
                    throw new UnsupportedOperationException();
                }
                public int hashCode()	  {return this.e.hashCode();}
                public boolean equals(final Object o) {
                    if (!(o instanceof Map.Entry)) {
			return false;
		}
                    final Map.Entry t = (Map.Entry)o;
                    return eq(this.e.getKey(),   t.getKey()) &&
                           eq(this.e.getValue(), t.getValue());
                }
                public String toString()  {return this.e.toString();}
            }
        }
    }

    /**
     * Returns an unmodifiable view of the specified sorted map.  This method
     * allows modules to provide users with "read-only" access to internal
     * sorted maps.  Query operations on the returned sorted map "read through"
     * to the specified sorted map.  Attempts to modify the returned
     * sorted map, whether direct, via its collection views, or via its
     * <tt>subMap</tt>, <tt>headMap</tt>, or <tt>tailMap</tt> views, result in
     * an <tt>UnsupportedOperationException</tt>.<p>
     *
     * The returned sorted map will be serializable if the specified sorted map
     * is serializable.
     *
     * @param m the sorted map for which an unmodifiable view is to be
     *        returned.
     * @return an unmodifiable view of the specified sorted map.
     */
    public static SortedMap unmodifiableSortedMap(final SortedMap m) {
	return new UnmodifiableSortedMap(m);
    }

    static class UnmodifiableSortedMap extends UnmodifiableMap
    				 implements SortedMap {
	private static final long serialVersionUID = -8700365885619028569L;

	private final SortedMap sm;

	UnmodifiableSortedMap(final SortedMap m) {super(m); this.sm = m;}

        public Comparator comparator()     {return this.sm.comparator();}

        public SortedMap subMap(final Object fromKey, final Object toKey) {
            return new UnmodifiableSortedMap(this.sm.subMap(fromKey, toKey));
        }
        public SortedMap headMap(final Object toKey) {
            return new UnmodifiableSortedMap(this.sm.headMap(toKey));
        }
        public SortedMap tailMap(final Object fromKey) {
            return new UnmodifiableSortedMap(this.sm.tailMap(fromKey));
        }

        public Object firstKey()           {return this.sm.firstKey();}
        public Object lastKey()            {return this.sm.lastKey();}
    }


    // Synch Wrappers

    /**
     * Returns a synchronized (thread-safe) collection backed by the specified
     * collection.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing collection is accomplished
     * through the returned collection.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * collection when iterating over it:
     * <pre>
     *  Collection c = Collections.synchronizedCollection(myCollection);
     *     ...
     *  synchronized(c) {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned collection does <i>not</i> pass the <tt>hashCode</tt>
     * and <tt>equals</tt> operations through to the backing collection, but
     * relies on <tt>Object</tt>'s equals and hashCode methods.  This is
     * necessary to preserve the contracts of these operations in the case
     * that the backing collection is a set or a list.<p>
     *
     * The returned collection will be serializable if the specified collection
     * is serializable.
     *
     * @param  c the collection to be "wrapped" in a synchronized collection.
     * @return a synchronized view of the specified collection.
     */
    public static Collection synchronizedCollection(final Collection c) {
	return new SynchronizedCollection(c);
    }

    static Collection synchronizedCollection(final Collection c, final Object mutex) {
	return new SynchronizedCollection(c, mutex);
    }

    static class SynchronizedCollection implements Collection, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 5247158193211725141L;
	Collection c;	   // Backing Collection
	Object	   mutex;  // Object on which to synchronize

	SynchronizedCollection(final Collection c) {
	    this.c = c; this.mutex = this;
        }
	SynchronizedCollection(final Collection c, final Object mutex) {
	    this.c = c; this.mutex = mutex;
        }

	public int size() {
	    synchronized(this.mutex) {return this.c.size();}
        }
	public boolean isEmpty() {
	    synchronized(this.mutex) {return this.c.isEmpty();}
        }
	public boolean contains(final Object o) {
	    synchronized(this.mutex) {return this.c.contains(o);}
        }
	public Object[] toArray() {
	    synchronized(this.mutex) {return this.c.toArray();}
        }
	public Object[] toArray(final Object[] a) {
	    synchronized(this.mutex) {return this.c.toArray(a);}
        }

	public Iterator iterator() {
            return this.c.iterator(); // Must be manually synched by user!
        }

	public boolean add(final Object o) {
	    synchronized(this.mutex) {return this.c.add(o);}
        }
	public boolean remove(final Object o) {
	    synchronized(this.mutex) {return this.c.remove(o);}
        }

	public boolean containsAll(final Collection coll) {
	    synchronized(this.mutex) {return this.c.containsAll(coll);}
        }
	public boolean addAll(final Collection coll) {
	    synchronized(this.mutex) {return this.c.addAll(coll);}
        }
	public boolean removeAll(final Collection coll) {
	    synchronized(this.mutex) {return this.c.removeAll(coll);}
        }
	public boolean retainAll(final Collection coll) {
	    synchronized(this.mutex) {return this.c.retainAll(coll);}
        }
	public void clear() {
	    synchronized(this.mutex) {this.c.clear();}
        }
    }

    /**
     * Returns a synchronized (thread-safe) set backed by the specified
     * set.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing set is accomplished
     * through the returned set.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * set when iterating over it:
     * <pre>
     *  Set s = Collections.synchronizedSet(new HashSet());
     *      ...
     *  synchronized(s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned set will be serializable if the specified set is
     * serializable.
     *
     * @param  s the set to be "wrapped" in a synchronized set.
     * @return a synchronized view of the specified set.
     */
    public static Set synchronizedSet(final Set s) {
	return new SynchronizedSet(s);
    }

    static Set synchronizedSet(final Set s, final Object mutex) {
	return new SynchronizedSet(s, mutex);
    }

    static class SynchronizedSet extends SynchronizedCollection
			         implements Set {
	/**
	 *
	 */
	private static final long serialVersionUID = 150482378911212577L;
	SynchronizedSet(final Set s) {
            super(s);
        }
	SynchronizedSet(final Set s, final Object mutex) {
            super(s, mutex);
        }

	public boolean equals(final Object o) {
	    synchronized(this.mutex) {return this.c.equals(o);}
        }
	public int hashCode() {
	    synchronized(this.mutex) {return this.c.hashCode();}
        }
    }

    /**
     * Returns a synchronized (thread-safe) sorted set backed by the specified
     * sorted set.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing sorted set is accomplished
     * through the returned sorted set (or its views).<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * sorted set when iterating over it or any of its <tt>subSet</tt>,
     * <tt>headSet</tt>, or <tt>tailSet</tt> views.
     * <pre>
     *  SortedSet s = Collections.synchronizedSortedSet(new HashSortedSet());
     *      ...
     *  synchronized(s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * or:
     * <pre>
     *  SortedSet s = Collections.synchronizedSortedSet(new HashSortedSet());
     *  SortedSet s2 = s.headSet(foo);
     *      ...
     *  synchronized(s) {  // Note: s, not s2!!!
     *      Iterator i = s2.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned sorted set will be serializable if the specified
     * sorted set is serializable.
     *
     * @param  s the sorted set to be "wrapped" in a synchronized sorted set.
     * @return a synchronized view of the specified sorted set.
     */
    public static SortedSet synchronizedSortedSet(final SortedSet s) {
	return new SynchronizedSortedSet(s);
    }

    static class SynchronizedSortedSet extends SynchronizedSet
			         implements SortedSet
    {
        /**
	 *
	 */
	private static final long serialVersionUID = 8245191640861687527L;
	private final SortedSet ss;

	SynchronizedSortedSet(final SortedSet s) {
            super(s);
            this.ss = s;
        }
	SynchronizedSortedSet(final SortedSet s, final Object mutex) {
            super(s, mutex);
            this.ss = s;
        }

	public Comparator comparator() {
	    synchronized(this.mutex) {return this.ss.comparator();}
        }

        public SortedSet subSet(final Object fromElement, final Object toElement) {
	    synchronized(this.mutex) {
                return new SynchronizedSortedSet(
                    this.ss.subSet(fromElement, toElement), this.mutex);
            }
        }
        public SortedSet headSet(final Object toElement) {
	    synchronized(this.mutex) {
                return new SynchronizedSortedSet(this.ss.headSet(toElement), this.mutex);
            }
        }
        public SortedSet tailSet(final Object fromElement) {
	    synchronized(this.mutex) {
               return new SynchronizedSortedSet(this.ss.tailSet(fromElement),this.mutex);
            }
        }

        public Object first() {
	    synchronized(this.mutex) {return this.ss.first();}
        }
        public Object last() {
	    synchronized(this.mutex) {return this.ss.last();}
        }
    }

    /**
     * Returns a synchronized (thread-safe) list backed by the specified
     * list.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing list is accomplished
     * through the returned list.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * list when iterating over it:
     * <pre>
     *  List list = Collections.synchronizedList(new ArrayList());
     *      ...
     *  synchronized(list) {
     *      Iterator i = list.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned list will be serializable if the specified list is
     * serializable.
     *
     * @param  list the list to be "wrapped" in a synchronized list.
     * @return a synchronized view of the specified list.
     */
    public static List synchronizedList(final List list) {
	return new SynchronizedList(list);
    }

    static List synchronizedList(final List list, final Object mutex) {
	return new SynchronizedList(list, mutex);
    }

    static class SynchronizedList extends SynchronizedCollection
    			          implements List {
	/**
	 *
	 */
	private static final long serialVersionUID = 7512331162356771531L;
	private final List list;

	SynchronizedList(final List list) {
	    super(list);
	    this.list = list;
	}
	SynchronizedList(final List list, final Object mutex) {
            super(list, mutex);
	    this.list = list;
        }

	public boolean equals(final Object o) {
	    synchronized(this.mutex) {return this.list.equals(o);}
        }
	public int hashCode() {
	    synchronized(this.mutex) {return this.list.hashCode();}
        }

	public Object get(final int index) {
	    synchronized(this.mutex) {return this.list.get(index);}
        }
	public Object set(final int index, final Object element) {
	    synchronized(this.mutex) {return this.list.set(index, element);}
        }
	public void add(final int index, final Object element) {
	    synchronized(this.mutex) {this.list.add(index, element);}
        }
	public Object remove(final int index) {
	    synchronized(this.mutex) {return this.list.remove(index);}
        }

	public int indexOf(final Object o) {
	    synchronized(this.mutex) {return this.list.indexOf(o);}
        }
	public int lastIndexOf(final Object o) {
	    synchronized(this.mutex) {return this.list.lastIndexOf(o);}
        }

	public boolean addAll(final int index, final Collection c) {
	    synchronized(this.mutex) {return this.list.addAll(index, c);}
        }

	public ListIterator listIterator() {
	    return this.list.listIterator(); // Must be manually synched by user
        }

	public ListIterator listIterator(final int index) {
	    return this.list.listIterator(index); // Must be manually synched by usr
        }

	public List subList(final int fromIndex, final int toIndex) {
	    synchronized(this.mutex) {
                return new SynchronizedList(this.list.subList(fromIndex, toIndex),
                                            this.mutex);
            }
        }
    }

    /**
     * Returns a synchronized (thread-safe) map backed by the specified
     * map.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing map is accomplished
     * through the returned map.<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * map when iterating over any of its collection views:
     * <pre>
     *  Map m = Collections.synchronizedMap(new HashMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned map will be serializable if the specified map is
     * serializable.
     *
     * @param  m the map to be "wrapped" in a synchronized map.
     * @return a synchronized view of the specified map.
     */
    public static Map synchronizedMap(final Map m) {
	return new SynchronizedMap(m);
    }

    private static class SynchronizedMap implements Map, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6038956312966651938L;
	private final Map m;	        // Backing Map
        Object      mutex;	// Object on which to synchronize

	SynchronizedMap(final Map m) {
            this.m = m;	 this.mutex = this;
        }

	SynchronizedMap(final Map m, final Object mutex) {
            this.m = m;	 this.mutex = mutex;
        }

	public int size() {
	    synchronized(this.mutex) {return this.m.size();}
        }
	public boolean isEmpty(){
	    synchronized(this.mutex) {return this.m.isEmpty();}
        }
	public boolean containsKey(final Object key) {
	    synchronized(this.mutex) {return this.m.containsKey(key);}
        }
	public boolean containsValue(final Object value){
	    synchronized(this.mutex) {return this.m.containsValue(value);}
        }
	public Object get(final Object key) {
	    synchronized(this.mutex) {return this.m.get(key);}
        }

	public Object put(final Object key, final Object value) {
	    synchronized(this.mutex) {return this.m.put(key, value);}
        }
	public Object remove(final Object key) {
	    synchronized(this.mutex) {return this.m.remove(key);}
        }
	public void putAll(final Map map) {
	    synchronized(this.mutex) {this.m.putAll(map);}
        }
	public void clear() {
	    synchronized(this.mutex) {this.m.clear();}
        }

	private transient Set keySet = null;
	private transient Set entrySet = null;
	private transient Collection values = null;

	public Set keySet() {
            synchronized(this.mutex) {
                if (this.keySet==null) {
			this.keySet = new SynchronizedSet(this.m.keySet(), this);
		}
                return this.keySet;
            }
	}

	public Set entrySet() {
            synchronized(this.mutex) {
                if (this.entrySet==null) {
			this.entrySet = new SynchronizedSet(this.m.entrySet(), this);
		}
                return this.entrySet;
            }
	}

	public Collection values() {
            synchronized(this.mutex) {
                if (this.values==null) {
			this.values = new SynchronizedCollection(this.m.values(), this);
		}
                return this.values;
            }
        }

	public boolean equals(final Object o) {
            synchronized(this.mutex) {return this.m.equals(o);}
        }
	public int hashCode() {
            synchronized(this.mutex) {return this.m.hashCode();}
        }
    }

    /**
     * Returns a synchronized (thread-safe) sorted map backed by the specified
     * sorted map.  In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing sorted map is accomplished
     * through the returned sorted map (or its views).<p>
     *
     * It is imperative that the user manually synchronize on the returned
     * sorted map when iterating over any of its collection views, or the
     * collections views of any of its <tt>subMap</tt>, <tt>headMap</tt> or
     * <tt>tailMap</tt> views.
     * <pre>
     *  SortedMap m = Collections.synchronizedSortedMap(new HashSortedMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * or:
     * <pre>
     *  SortedMap m = Collections.synchronizedSortedMap(new HashSortedMap());
     *  SortedMap m2 = m.subMap(foo, bar);
     *      ...
     *  Set s2 = m2.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not m2 or s2!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     *
     * <p>The returned sorted map will be serializable if the specified
     * sorted map is serializable.
     *
     * @param  m the sorted map to be "wrapped" in a synchronized sorted map.
     * @return a synchronized view of the specified sorted map.
     */
    public static SortedMap synchronizedSortedMap(final SortedMap m) {
	return new SynchronizedSortedMap(m);
    }


    static class SynchronizedSortedMap extends SynchronizedMap
			         implements SortedMap
    {
        /**
	 *
	 */
	private static final long serialVersionUID = -5693395712145026239L;
	private final SortedMap sm;

	SynchronizedSortedMap(final SortedMap m) {
            super(m);
            this.sm = m;
        }
	SynchronizedSortedMap(final SortedMap m, final Object mutex) {
            super(m, mutex);
            this.sm = m;
        }

	public Comparator comparator() {
	    synchronized(this.mutex) {return this.sm.comparator();}
        }

        public SortedMap subMap(final Object fromKey, final Object toKey) {
	    synchronized(this.mutex) {
                return new SynchronizedSortedMap(
                    this.sm.subMap(fromKey, toKey), this.mutex);
            }
        }
        public SortedMap headMap(final Object toKey) {
	    synchronized(this.mutex) {
                return new SynchronizedSortedMap(this.sm.headMap(toKey), this.mutex);
            }
        }
        public SortedMap tailMap(final Object fromKey) {
	    synchronized(this.mutex) {
               return new SynchronizedSortedMap(this.sm.tailMap(fromKey),this.mutex);
            }
        }

        public Object firstKey() {
	    synchronized(this.mutex) {return this.sm.firstKey();}
        }
        public Object lastKey() {
	    synchronized(this.mutex) {return this.sm.lastKey();}
        }
    }


    // Miscellaneous

    /**
     * The empty set (immutable).  This set is serializable.
     */
    public static final Set EMPTY_SET = new EmptySet();

    private static class EmptySet extends AbstractSet implements Serializable {
	private static final long serialVersionUID = 6156926943123123201L;

	EmptySet() {
		// empty
	}

	public Iterator iterator() {
            return new Iterator() {
                public boolean hasNext() {
                    return false;
                }
                public Object next() {
                    throw new NoSuchElementException();
                }
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        public int size() {return 0;}

        public boolean contains(final Object obj) {return false;}
    }

    /**
     * The empty list (immutable).  This list is serializable.
     */
    public static final List EMPTY_LIST = new EmptyList();

    private static class EmptyList extends AbstractList
                                   implements Serializable {
	private static final long serialVersionUID = -941676809523447274L;

	EmptyList() {
		// empty
	}

	public int size() {return 0;}

        public boolean contains(final Object obj) {return false;}

        public Object get(final int index) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }

    /**
     * Returns an immutable set containing only the specified object.
     * The returned set is serializable.
     *
     * @return an immutable set containing only the specified object.
     */
    public static Set singleton(final Object o) {
	return new SingletonSet(o);
    }

    private static class SingletonSet extends AbstractSet
                                      implements Serializable
    {
	private static final long serialVersionUID = 3543566247715090095L;

	final Object element;

        SingletonSet(final Object o) {this.element = o;}

        public Iterator iterator() {
            return new Iterator() {
                private boolean hasNext = true;
                public boolean hasNext() {
                    return this.hasNext;
                }
                public Object next() {
                    if (this.hasNext) {
                        this.hasNext = false;
                        return SingletonSet.this.element;
                    }
                    throw new NoSuchElementException();
                }
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        public int size() {return 1;}

        public boolean contains(final Object o) {return eq(o, this.element);}
    }

    /**
     * Returns an immutable list consisting of <tt>n</tt> copies of the
     * specified object.  The newly allocated data object is tiny (it contains
     * a single reference to the data object).  This method is useful in
     * combination with the <tt>List.addAll</tt> method to grow lists.
     * The returned list is serializable.
     *
     * @param  n the number of elements in the returned list.
     * @param  o the element to appear repeatedly in the returned list.
     * @return an immutable list consisting of <tt>n</tt> copies of the
     * 	       specified object.
     * @throws IllegalArgumentException if n &lt; 0.
     * @see    List#addAll(Collection)
     * @see    List#addAll(int, Collection)
     */
    public static List nCopies(final int n, final Object o) {
        return new CopiesList(n, o);
    }

    private static class CopiesList extends AbstractList
                                    implements Serializable
    {
        /**
	 *
	 */
	private static final long serialVersionUID = -8511394365323709595L;
	int n;
        Object element;

        CopiesList(final int n, final Object o) {
            if (n < 0) {
		throw new IllegalArgumentException("List length = " + n);
	}
            this.n = n;
            this.element = o;
        }

        public int size() {
            return this.n;
        }

        public boolean contains(final Object obj) {
            return this.n != 0 && eq(obj, this.element);
        }

        public Object get(final int index) {
            if (index<0 || index>=this.n) {
		throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+this.n);
	}
            return this.element;
        }
    }

    /**
     * Returns a comparator that imposes the reverse of the <i>natural
     * ordering</i> on a collection of objects that implement the
     * <tt>Comparable</tt> interface.  (The natural ordering is the ordering
     * imposed by the objects' own <tt>compareTo</tt> method.)  This enables a
     * simple idiom for sorting (or maintaining) collections (or arrays) of
     * objects that implement the <tt>Comparable</tt> interface in
     * reverse-natural-order.  For example, suppose a is an array of
     * strings. Then: <pre>
     * 		Arrays.sort(a, Collections.reverseOrder());
     * </pre> sorts the array in reverse-lexicographic (alphabetical) order.<p>
     *
     * The returned comparator is serializable.
     *
     * @return a comparator that imposes the reverse of the <i>natural
     * 	       ordering</i> on a collection of objects that implement
     *	       the <tt>Comparable</tt> interface.
     * @see Comparable
     */
    public static Comparator reverseOrder() {
        return REVERSE_ORDER;
    }

    private static final Comparator REVERSE_ORDER = new ReverseComparator();

    private static class ReverseComparator implements Comparator,Serializable {
	private static final long serialVersionUID = 8956579072019723321L;

	ReverseComparator() {
		// empty
	}

	public int compare(final Object o1, final Object o2) {
            final Comparable c1 = (Comparable)o1;
            final Comparable c2 = (Comparable)o2;
            return -c1.compareTo(c2);
        }
    }

    /**
     * Returns an enumeration over the specified collection.  This provides
     * interoperatbility with legacy APIs that require an enumeration
     * as input.
     *
     * @param c the collection for which an enumeration is to be returned.
     * @return an enumeration over the specified collection.
     */
    public static Enumeration enumeration(final Collection c) {
	return new Enumeration() {
	    Iterator i = c.iterator();

	    public boolean hasMoreElements() {
		return this.i.hasNext();
	    }

	    public Object nextElement() {
		return this.i.next();
	    }
        };
    }

    /**
     * Returns an array list containing the elements returned by the
     * specified enumeration in the order they are returned by the
     * enumeration.  This method provides interoperatbility between
     * legacy APIs that return enumerations and new APIs that require
     * collections.
     *
     * @param e enumeration providing elements for the returned
     *          array list
     * @return an array list containing the elements returned
     *         by the specified enumeration.
     * @since 1.4
     * @see Enumeration
     * @see ArrayList
     */
    public static ArrayList list(final Enumeration e) {
        final ArrayList l = new ArrayList();
        while (e.hasMoreElements()) {
		l.add(e.nextElement());
	}
        return l;
    }

    /**
     * Returns true if the specified arguments are equal, or both null.
     */
    static boolean eq(final Object o1, final Object o2) {
        return o1==null ? o2==null : o1.equals(o2);
    }
}
