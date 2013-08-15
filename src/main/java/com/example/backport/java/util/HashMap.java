/*
 * @(#)HashMap.java	1.30 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.backport.java.util;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Hash table based implementation of the <tt>Map</tt> interface.  This
 * implementation provides all of the optional map operations, and permits
 * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
 * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
 * unsynchronized and permits nulls.)  This class makes no guarantees as to
 * the order of the map; in particular, it does not guarantee that the order
 * will remain constant over time.<p>
 *
 * This implementation provides constant-time performance for the basic
 * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
 * disperses the elements properly among the buckets.  Iteration over
 * collection views requires time proportional to the "capacity" of the
 * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
 * of key-value mappings).  Thus, it's very important not to set the intial
 * capacity too high (or the load factor too low) if iteration performance is
 * important.<p>
 *
 * An instance of <tt>HashMap</tt> has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of buckets in the hash table, and the initial
 * capacity is simply the capacity at the time the hash table is created.  The
 * <i>load factor</i> is a measure of how full the hash table is allowed to
 * get before its capacity is automatically increased.  When the number of
 * entries in the hash table exceeds the product of the load factor and the
 * current capacity, the capacity is roughly doubled by calling the
 * <tt>rehash</tt> method.<p>
 *
 * As a general rule, te default load factor (.75) offers a good tradeoff
 * between time and space costs.  Higher values decrease the space overhead
 * but increase the lookup cost (reflected in most of the operations of the
 * <tt>HashMap</tt> class, including <tt>get</tt> and <tt>put</tt>).  The
 * expected number of entries in the map and its load factor should be taken
 * into account when setting its initial capacity, so as to minimize the
 * number of <tt>rehash</tt> operations.  If the initial capacity is greater
 * than the maximum number of entries divided by the load factor, no
 * <tt>rehash</tt> operations will ever occur.<p>
 *
 * If many mappings are to be stored in a <tt>HashMap</tt> instance, creating
 * it with a sufficiently large capacity will allow the mappings to be stored
 * more efficiently than letting it perform automatic rehashing as needed to
 * grow the table.<p>
 *
 * <b>Note that this implementation is not synchronized.</b> If multiple
 * threads access this map concurrently, and at least one of the threads
 * modifies the map structurally, it <i>must</i> be synchronized externally.
 * (A structural modification is any operation that adds or deletes one or
 * more mappings; merely changing the value associated with a key that an
 * instance already contains is not a structural modification.)  This is
 * typically accomplished by synchronizing on some object that naturally
 * encapsulates the map.  If no such object exists, the map should be
 * "wrapped" using the <tt>Collections.synchronizedMap</tt> method.  This is
 * best done at creation time, to prevent accidental unsynchronized access to
 * the map: <pre> Map m = Collections.synchronizedMap(new HashMap(...));
 * </pre><p>
 *
 * The iterators returned by all of this class's "collection view methods" are
 * <i>fail-fast</i>: if the map is structurally modified at any time after the
 * iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> or <tt>add</tt> methods, the iterator will throw a
 * <tt>ConcurrentModificationException</tt>.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * @author  Josh Bloch
 * @author  Arthur van Hoff
 * @version 1.30, 11/29/01
 * @see     Object#hashCode()
 * @see     Collection
 * @see	    Map
 * @see	    TreeMap
 * @see	    Hashtable
 * @since JDK1.2
 */

public class HashMap extends AbstractMap implements Cloneable,
					 Serializable {
    /**
     * The hash table data.
     */
    transient Entry table[];

    /**
     * The total number of mappings in the hash table.
     */
    transient int count;

    /**
     * The table is rehashed when its size exceeds this threshold.  (The
     * value of this field is (int)(capacity * loadFactor).)
     *
     * @serial
     */
    private int threshold;

    /**
     * The load factor for the hashtable.
     *
     * @serial
     */
    private final float loadFactor;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    transient int modCount = 0;

    /**
     * Constructs a new, empty map with the specified initial
     * capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the HashMap.
     * @param      loadFactor        the load factor of the HashMap
     * @throws     IllegalArgumentException  if the initial capacity is less
     *               than zero, or if the load factor is nonpositive.
     */
    public HashMap(int initialCapacity, final float loadFactor) {
	if (initialCapacity < 0) {
		throw new IllegalArgumentException("Illegal Initial Capacity: "+
		                                       initialCapacity);
	}
        if (loadFactor <= 0) {
		throw new IllegalArgumentException("Illegal Load factor: "+
		                                       loadFactor);
	}
        if (initialCapacity==0) {
		initialCapacity = 1;
	}
	this.loadFactor = loadFactor;
	this.table = new Entry[initialCapacity];
	this.threshold = (int)(initialCapacity * loadFactor);
    }

    /**
     * Constructs a new, empty map with the specified initial capacity
     * and default load factor, which is <tt>0.75</tt>.
     *
     * @param   initialCapacity   the initial capacity of the HashMap.
     * @throws    IllegalArgumentException if the initial capacity is less
     *              than zero.
     */
    public HashMap(final int initialCapacity) {
	this(initialCapacity, 0.75f);
    }

    /**
     * Constructs a new, empty map with a default capacity and load
     * factor, which is <tt>0.75</tt>.
     */
    public HashMap() {
	this(101, 0.75f);
    }

    /**
     * Constructs a new map with the same mappings as the given map.  The
     * map is created with a capacity of twice the number of mappings in
     * the given map or 11 (whichever is greater), and a default load factor,
     * which is <tt>0.75</tt>.
     */
    public HashMap(final Map t) {
	this(Math.max(2*t.size(), 11), 0.75f);
	this.putAll(t);
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {
	return this.count;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings.
     */
    public boolean isEmpty() {
	return this.count == 0;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.
     *
     * @param value value whose presence in this map is to be tested.
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value.
     */
    public boolean containsValue(final Object value) {
	final Entry tab[] = this.table;

	if (value==null) {
	    for (int i = tab.length ; i-- > 0 ;) {
		for (Entry e = tab[i] ; e != null ; e = e.next) {
			if (e.value==null) {
				return true;
			}
		}
	}
	} else {
	    for (int i = tab.length ; i-- > 0 ;) {
		for (Entry e = tab[i] ; e != null ; e = e.next) {
			if (value.equals(e.value)) {
				return true;
			}
		}
	}
	}

	return false;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.
     *
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key.
     * @param key key whose presence in this Map is to be tested.
     */
    public boolean containsKey(final Object key) {
	final Entry tab[] = this.table;
        if (key != null) {
            final int hash = key.hashCode();
            final int index = (hash & 0x7FFFFFFF) % tab.length;
            for (Entry e = tab[index]; e != null; e = e.next) {
		if (e.hash==hash && key.equals(e.key)) {
			return true;
		}
	}
        } else {
            for (Entry e = tab[0]; e != null; e = e.next) {
		if (e.key==null) {
			return true;
		}
	}
        }

	return false;
    }

    /**
     * Returns the value to which this map maps the specified key.  Returns
     * <tt>null</tt> if the map contains no mapping for this key.  A return
     * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
     * map contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
     * operation may be used to distinguish these two cases.
     *
     * @return the value to which this map maps the specified key.
     * @param key key whose associated value is to be returned.
     */
    public Object get(final Object key) {
	final Entry tab[] = this.table;

        if (key != null) {
            final int hash = key.hashCode();
            final int index = (hash & 0x7FFFFFFF) % tab.length;
            for (Entry e = tab[index]; e != null; e = e.next) {
		if (e.hash == hash && key.equals(e.key)) {
			return e.value;
		}
	}
	} else {
            for (Entry e = tab[0]; e != null; e = e.next) {
		if (e.key==null) {
			return e.value;
		}
	}
        }

	return null;
    }

    /**
     * Rehashes the contents of this map into a new <tt>HashMap</tt> instance
     * with a larger capacity. This method is called automatically when the
     * number of keys in this map exceeds its capacity and load factor.
     */
    private void rehash() {
	final int oldCapacity = this.table.length;
	final Entry oldMap[] = this.table;

	final int newCapacity = oldCapacity * 2 + 1;
	final Entry newMap[] = new Entry[newCapacity];

	this.modCount++;
	this.threshold = (int)(newCapacity * this.loadFactor);
	this.table = newMap;

	for (int i = oldCapacity ; i-- > 0 ;) {
	    for (Entry old = oldMap[i] ; old != null ; ) {
		final Entry e = old;
		old = old.next;

		final int index = (e.hash & 0x7FFFFFFF) % newCapacity;
		e.next = newMap[index];
		newMap[index] = e;
	    }
	}
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or <tt>null</tt>
     *	       if there was no mapping for key.  A <tt>null</tt> return can
     *	       also indicate that the HashMap previously associated
     *	       <tt>null</tt> with the specified key.
     */
    public Object put(final Object key, final Object value) {
	// Makes sure the key is not already in the HashMap.
	Entry tab[] = this.table;
        int hash = 0;
        int index = 0;

        if (key != null) {
            hash = key.hashCode();
            index = (hash & 0x7FFFFFFF) % tab.length;
            for (Entry e = tab[index] ; e != null ; e = e.next) {
                if (e.hash == hash && key.equals(e.key)) {
                    final Object old = e.value;
                    e.value = value;
                    return old;
                }
            }
        } else {
            for (Entry e = tab[0] ; e != null ; e = e.next) {
                if (e.key == null) {
                    final Object old = e.value;
                    e.value = value;
                    return old;
                }
            }
        }

	this.modCount++;
	if (this.count >= this.threshold) {
	    // Rehash the table if the threshold is exceeded
	    this.rehash();

            tab = this.table;
            index = (hash & 0x7FFFFFFF) % tab.length;
	}

	// Creates the new entry.
	final Entry e = new Entry(hash, key, value, tab[index]);
	tab[index] = e;
	this.count++;
	return null;
    }

    /**
     * Removes the mapping for this key from this map if present.
     *
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or <tt>null</tt>
     *	       if there was no mapping for key.  A <tt>null</tt> return can
     *	       also indicate that the map previously associated <tt>null</tt>
     *	       with the specified key.
     */
    public Object remove(final Object key) {
	final Entry tab[] = this.table;

        if (key != null) {
            final int hash = key.hashCode();
            final int index = (hash & 0x7FFFFFFF) % tab.length;

            for (Entry e = tab[index], prev = null; e != null;
                 prev = e, e = e.next) {
                if (e.hash == hash && key.equals(e.key)) {
                    this.modCount++;
                    if (prev != null) {
			prev.next = e.next;
		} else {
			tab[index] = e.next;
		}

                    this.count--;
                    final Object oldValue = e.value;
                    e.value = null;
                    return oldValue;
                }
            }
        } else {
            for (Entry e = tab[0], prev = null; e != null;
                 prev = e, e = e.next) {
                if (e.key == null) {
                    this.modCount++;
                    if (prev != null) {
			prev.next = e.next;
		} else {
			tab[0] = e.next;
		}

                    this.count--;
                    final Object oldValue = e.value;
                    e.value = null;
                    return oldValue;
                }
            }
        }

	return null;
    }

    /**
     * Copies all of the mappings from the specified map to this one.
     *
     * These mappings replace any mappings that this map had for any of the
     * keys currently in the specified Map.
     *
     * @param t Mappings to be stored in this map.
     */
    public void putAll(final Map t) {
	final Iterator i = t.entrySet().iterator();
	while (i.hasNext()) {
	    final Map.Entry e = (Map.Entry) i.next();
	    this.put(e.getKey(), e.getValue());
	}
    }

    /**
     * Removes all mappings from this map.
     */
    public void clear() {
	final Entry tab[] = this.table;
	this.modCount++;
	for (int index = tab.length; --index >= 0; ) {
		tab[index] = null;
	}
	this.count = 0;
    }

    /**
     * Returns a shallow copy of this <tt>HashMap</tt> instance: the keys and
     * values themselves are not cloned.
     *
     * @return a shallow copy of this map.
     */
    public Object clone() {
	try {
	    final HashMap t = (HashMap)super.clone();
	    t.table = new Entry[this.table.length];
	    for (int i = this.table.length ; i-- > 0 ; ) {
		t.table[i] = this.table[i] != null
		    ? (Entry)this.table[i].clone() : null;
	    }
	    t.keySet = null;
	    t.entrySet = null;
            t.values = null;
	    t.modCount = 0;
	    return t;
	} catch (final CloneNotSupportedException e) {
	    // this shouldn't happen, since we are Cloneable
	    throw new InternalError();
	}
    }

    // Views

    private transient Set keySet = null;
    private transient Set entrySet = null;
    private transient Collection values = null;

    /**
     * Returns a set view of the keys contained in this map.  The set is
     * backed by the map, so changes to the map are reflected in the set, and
     * vice-versa.  The set supports element removal, which removes the
     * corresponding mapping from this map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt>, and
     * <tt>clear</tt> operations.  It does not support the <tt>add</tt> or
     * <tt>addAll</tt> operations.
     *
     * @return a set view of the keys contained in this map.
     */
    public Set keySet() {
	if (this.keySet == null) {
	    this.keySet = new AbstractSet() {
		public Iterator iterator() {
		    return new HashIterator(KEYS);
		}
		public int size() {
		    return HashMap.this.count;
		}
                public boolean contains(final Object o) {
                    return HashMap.this.containsKey(o);
                }
		public boolean remove(final Object o) {
		    return HashMap.this.remove(o) != null;
		}
		public void clear() {
		    HashMap.this.clear();
		}
	    };
	}
	return this.keySet;
    }

    /**
     * Returns a collection view of the values contained in this map.  The
     * collection is backed by the map, so changes to the map are reflected in
     * the collection, and vice-versa.  The collection supports element
     * removal, which removes the corresponding mapping from this map, via the
     * <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt> operations.
     * It does not support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map.
     */
    public Collection values() {
	if (this.values==null) {
	    this.values = new AbstractCollection() {
                public Iterator iterator() {
                    return new HashIterator(VALUES);
                }
                public int size() {
                    return HashMap.this.count;
                }
                public boolean contains(final Object o) {
                    return HashMap.this.containsValue(o);
                }
                public void clear() {
                    HashMap.this.clear();
                }
            };
        }
	return this.values;
    }

    /**
     * Returns a collection view of the mappings contained in this map.  Each
     * element in the returned collection is a <tt>Map.Entry</tt>.  The
     * collection is backed by the map, so changes to the map are reflected in
     * the collection, and vice-versa.  The collection supports element
     * removal, which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt> operations.
     * It does not support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the mappings contained in this map.
     * @see Map.Entry
     */
    public Set entrySet() {
	if (this.entrySet==null) {
	    this.entrySet = new AbstractSet() {
                public Iterator iterator() {
                    return new HashIterator(ENTRIES);
                }

                public boolean contains(final Object o) {
                    if (!(o instanceof Map.Entry)) {
			return false;
		}
                    final Map.Entry entry = (Map.Entry)o;
                    final Object key = entry.getKey();
                    final Entry tab[] = HashMap.this.table;
                    final int hash = key==null ? 0 : key.hashCode();
                    final int index = (hash & 0x7FFFFFFF) % tab.length;

                    for (Entry e = tab[index]; e != null; e = e.next) {
			if (e.hash==hash && e.equals(entry)) {
				return true;
			}
		}
                    return false;
                }

		public boolean remove(final Object o) {
                    if (!(o instanceof Map.Entry)) {
			return false;
		}
                    final Map.Entry entry = (Map.Entry)o;
                    final Object key = entry.getKey();
                    final Entry tab[] = HashMap.this.table;
                    final int hash = key==null ? 0 : key.hashCode();
                    final int index = (hash & 0x7FFFFFFF) % tab.length;

                    for (Entry e = tab[index], prev = null; e != null;
                         prev = e, e = e.next) {
                        if (e.hash==hash && e.equals(entry)) {
                            HashMap.this.modCount++;
                            if (prev != null) {
				prev.next = e.next;
			} else {
				tab[index] = e.next;
			}

                            HashMap.this.count--;
                            e.value = null;
                            return true;
                        }
                    }
                    return false;
                }

                public int size() {
                    return HashMap.this.count;
                }

                public void clear() {
                    HashMap.this.clear();
                }
            };
        }

	return this.entrySet;
    }

    /**
     * HashMap collision list entry.
     */
    private static class Entry implements Map.Entry {
	int hash;
	Object key;
	Object value;
	Entry next;

	Entry(final int hash, final Object key, final Object value, final Entry next) {
	    this.hash = hash;
	    this.key = key;
	    this.value = value;
	    this.next = next;
	}

	protected Object clone() {
	    return new Entry(this.hash, this.key, this.value,
			     this.next==null ? null : (Entry)this.next.clone());
	}

	// Map.Entry Ops

	public Object getKey() {
	    return this.key;
	}

	public Object getValue() {
	    return this.value;
	}

	public Object setValue(final Object value) {
	    final Object oldValue = this.value;
	    this.value = value;
	    return oldValue;
	}

	public boolean equals(final Object o) {
	    if (!(o instanceof Map.Entry)) {
		return false;
	}
	    final Map.Entry e = (Map.Entry)o;

	    return (this.key==null ? e.getKey()==null : this.key.equals(e.getKey())) &&
	       (this.value==null ? e.getValue()==null : this.value.equals(e.getValue()));
	}

	public int hashCode() {
	    return this.hash ^ (this.value==null ? 0 : this.value.hashCode());
	}

	public String toString() {
	    return this.key+"="+this.value;
	}
    }

    // Types of Iterators
    private static final int KEYS = 0;
    private static final int VALUES = 1;
    private static final int ENTRIES = 2;

    private class HashIterator implements Iterator {
	Entry[] table = HashMap.this.table;
	int index = this.table.length;
	Entry entry = null;
	Entry lastReturned = null;
	int type;

	/**
	 * The modCount value that the iterator believes that the backing
	 * List should have.  If this expectation is violated, the iterator
	 * has detected concurrent modification.
	 */
	private int expectedModCount = HashMap.this.modCount;

	HashIterator(final int type) {
	    this.type = type;
	}

	public boolean hasNext() {
	    while (this.entry==null && this.index>0) {
		this.entry = this.table[--this.index];
	}

	    return this.entry != null;
	}

	public Object next() {
	    if (HashMap.this.modCount != this.expectedModCount) {
		throw new ConcurrentModificationException();
	}

	    while (this.entry==null && this.index>0) {
		this.entry = this.table[--this.index];
	}

	    if (this.entry != null) {
		final Entry e = this.lastReturned = this.entry;
		this.entry = e.next;
		return this.type == KEYS ? e.key : this.type == VALUES ? e.value : e;
	    }
	    throw new NoSuchElementException();
	}

	public void remove() {
	    if (this.lastReturned == null) {
		throw new IllegalStateException();
	}
	    if (HashMap.this.modCount != this.expectedModCount) {
		throw new ConcurrentModificationException();
	}

	    final Entry[] tab = HashMap.this.table;
	    final int index = (this.lastReturned.hash & 0x7FFFFFFF) % tab.length;

	    for (Entry e = tab[index], prev = null; e != null;
		 prev = e, e = e.next) {
		if (e == this.lastReturned) {
		    HashMap.this.modCount++;
		    this.expectedModCount++;
		    if (prev == null) {
			tab[index] = e.next;
		} else {
			prev.next = e.next;
		}
		    HashMap.this.count--;
		    this.lastReturned = null;
		    return;
		}
	    }
	    throw new ConcurrentModificationException();
	}
    }

    /**
     * Save the state of the <tt>HashMap</tt> instance to a stream (i.e.,
     * serialize it).
     *
     * @serialData The <i>capacity</i> of the HashMap (the length of the
     *		   bucket array) is emitted (int), followed  by the
     *		   <i>size</i> of the HashMap (the number of key-value
     *		   mappings), followed by the key (Object) and value (Object)
     *		   for each key-value mapping represented by the HashMap
     * The key-value mappings are emitted in no particular order.
     */
    private void writeObject(final java.io.ObjectOutputStream s)
        throws IOException
    {
	// Write out the threshold, loadfactor, and any hidden stuff
	s.defaultWriteObject();

	// Write out number of buckets
	s.writeInt(this.table.length);

	// Write out size (number of Mappings)
	s.writeInt(this.count);

        // Write out keys and values (alternating)
	for (int index = this.table.length-1; index >= 0; index--) {
	    Entry entry = this.table[index];

	    while (entry != null) {
		s.writeObject(entry.key);
		s.writeObject(entry.value);
		entry = entry.next;
	    }
	}
    }

    private static final long serialVersionUID = 362498820763181265L;

    /**
     * Reconstitute the <tt>HashMap</tt> instance from a stream (i.e.,
     * deserialize it).
     */
    private void readObject(final java.io.ObjectInputStream s)
         throws IOException, ClassNotFoundException
    {
	// Read in the threshold, loadfactor, and any hidden stuff
	s.defaultReadObject();

	// Read in number of buckets and allocate the bucket array;
	final int numBuckets = s.readInt();
	this.table = new Entry[numBuckets];

	// Read in size (number of Mappings)
	final int size = s.readInt();

	// Read the keys and values, and put the mappings in the HashMap
	for (int i=0; i<size; i++) {
	    final Object key = s.readObject();
	    final Object value = s.readObject();
	    this.put(key, value);
	}
    }

    int capacity() {
        return this.table.length;
    }

    float loadFactor() {
        return this.loadFactor;
    }
}
