/*
 * @(#)Arrays.java	1.30 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.backport.java.util;

import com.example.backport.java.lang.Comparable;

/**
 * This class contains various methods for manipulating arrays (such as
 * sorting and searching).  It also contains a static factory that allows
 * arrays to be viewed as lists.<p>
 *
 * The documentation for the sorting and searching methods contained in this
 * class includes briefs description of the <i>implementations</i>.  Such
 * descriptions should be regarded as <i>implementation notes</i>, rather than
 * parts of the <i>specification</i>.  Implementors should feel free to
 * substitute other algorithms, so long as the specification itself is adhered
 * to.  (For example, the algorithm used by <tt>sort(Object[])</tt> does not
 * have to be a mergesort, but it does have to be <i>stable</i>.)
 *
 * @author  Josh Bloch
 * @version 1.30 11/29/01
 * @see Comparable
 * @see Comparator
 * @since JDK1.2
 */

public class Arrays {
    // Suppresses default constructor, ensuring non-instantiability.
    private Arrays() {
    }

    // Sorting

    /**
     * Sorts the specified array of longs into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final long[] a) {
	sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of longs into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final long[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort1(a, fromIndex, toIndex-fromIndex);
    }

    /**
     * Sorts the specified array of ints into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final int[] a) {
	sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of ints into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final int[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort1(a, fromIndex, toIndex-fromIndex);
    }

    /**
     * Sorts the specified array of shorts into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final short[] a) {
	sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of shorts into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final short[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort1(a, fromIndex, toIndex-fromIndex);
    }

    /**
     * Sorts the specified array of chars into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final char[] a) {
	sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of chars into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final char[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort1(a, fromIndex, toIndex-fromIndex);
    }

    /**
     * Sorts the specified array of bytes into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final byte[] a) {
	sort1(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of bytes into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final byte[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort1(a, fromIndex, toIndex-fromIndex);
    }

    /**
     * Sorts the specified array of doubles into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final double[] a) {
	sort2(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of doubles into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final double[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort2(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array of floats into ascending numerical order.
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     *
     * @param a the array to be sorted.
     */
    public static void sort(final float[] a) {
	sort2(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of floats into
     * ascending numerical order.  The sorting algorithm is a tuned quicksort,
     * adapted from Jon L. Bentley and M. Douglas McIlroy's "Engineering a
     * Sort Function", Software-Practice and Experience, Vol. 23(11)
     * P. 1249-1265 (November 1993).  This algorithm offers n*log(n)
     * performance on many data sets that cause other quicksorts to degrade to
     * quadratic performance.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void sort(final float[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
	sort2(a, fromIndex, toIndex);
    }

    private static void sort2(final double a[], final int fromIndex, final int toIndex) {
        final long NEG_ZERO_BITS = Double.doubleToLongBits(-0.0d);
        /*
         * The sort is done in three phases to avoid the expense of using
         * NaN and -0.0 aware comparisons during the main sort.
         */

        /*
         * Preprocessing phase:  Move any NaN's to end of array, count the
         * number of -0.0's, and turn them into 0.0's.
         */
        int numNegZeros = 0;
        int i = fromIndex, n = toIndex;
        while(i < n) {
            if (a[i] != a[i]) {
                a[i] = a[--n];
                a[n] = Double.NaN;
            } else {
                if (a[i]==0 && Double.doubleToLongBits(a[i])==NEG_ZERO_BITS) {
                    a[i] = 0.0d;
                    numNegZeros++;
                }
                i++;
            }
        }

        // Main sort phase: quicksort everything but the NaN's
	sort1(a, fromIndex, n-fromIndex);

        // Postprocessing phase: change 0.0's to -0.0's as required
        if (numNegZeros != 0) {
            int j = binarySearch(a, 0.0d, fromIndex, n-1); // posn of ANY zero
            do {
                j--;
            } while (j>=0 && a[j]==0.0d);

            // j is now one less than the index of the FIRST zero
            for (int k=0; k<numNegZeros; k++) {
		a[++j] = -0.0d;
	}
        }
    }


    private static void sort2(final float a[], final int fromIndex, final int toIndex) {
        final int NEG_ZERO_BITS = Float.floatToIntBits(-0.0f);
        /*
         * The sort is done in three phases to avoid the expense of using
         * NaN and -0.0 aware comparisons during the main sort.
         */

        /*
         * Preprocessing phase:  Move any NaN's to end of array, count the
         * number of -0.0's, and turn them into 0.0's.
         */
        int numNegZeros = 0;
        int i = fromIndex, n = toIndex;
        while(i < n) {
            if (a[i] != a[i]) {
                a[i] = a[--n];
                a[n] = Float.NaN;
            } else {
                if (a[i]==0 && Float.floatToIntBits(a[i])==NEG_ZERO_BITS) {
                    a[i] = 0.0f;
                    numNegZeros++;
                }
                i++;
            }
        }

        // Main sort phase: quicksort everything but the NaN's
	sort1(a, fromIndex, n-fromIndex);

        // Postprocessing phase: change 0.0's to -0.0's as required
        if (numNegZeros != 0) {
            int j = binarySearch(a, 0.0f, fromIndex, n-1); // posn of ANY zero
            do {
                j--;
            } while (j>=0 && a[j]==0.0f);

            // j is now one less than the index of the FIRST zero
            for (int k=0; k<numNegZeros; k++) {
		a[++j] = -0.0f;
	}
        }
    }


    /*
     * The code for each of the seven primitive types is largely identical.
     * C'est la vie.
     */

    /**
     * Sorts the specified sub-array of longs into ascending order.
     */
    private static void sort1(final long x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final long v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final long x[], final int a, final int b) {
	final long t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final long x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed longs.
     */
    private static int med3(final long x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }

    /**
     * Sorts the specified sub-array of integers into ascending order.
     */
    private static void sort1(final int x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final int v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final int x[], final int a, final int b) {
	final int t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final int x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed integers.
     */
    private static int med3(final int x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }

    /**
     * Sorts the specified sub-array of shorts into ascending order.
     */
    private static void sort1(final short x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final short v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final short x[], final int a, final int b) {
	final short t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final short x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed shorts.
     */
    private static int med3(final short x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }


    /**
     * Sorts the specified sub-array of chars into ascending order.
     */
    private static void sort1(final char x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final char v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final char x[], final int a, final int b) {
	final char t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final char x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed chars.
     */
    private static int med3(final char x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }


    /**
     * Sorts the specified sub-array of bytes into ascending order.
     */
    private static void sort1(final byte x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final byte v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final byte x[], final int a, final int b) {
	final byte t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final byte x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed bytes.
     */
    private static int med3(final byte x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }


    /**
     * Sorts the specified sub-array of doubles into ascending order.
     */
    private static void sort1(final double x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final double v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final double x[], final int a, final int b) {
	final double t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final double x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed doubles.
     */
    private static int med3(final double x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }


    /**
     * Sorts the specified sub-array of floats into ascending order.
     */
    private static void sort1(final float x[], final int off, final int len) {
	// Insertion sort on smallest arrays
	if (len < 7) {
	    for (int i=off; i<len+off; i++) {
		for (int j=i; j>off && x[j-1]>x[j]; j--) {
			swap(x, j, j-1);
		}
	}
	    return;
	}

	// Choose a partition element, v
	int m = off + len/2;       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		final int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	final float v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v) {
			swap(x, a++, b);
		}
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v) {
			swap(x, c, d--);
		}
		c--;
	    }
	    if (b > c) {
		break;
	}
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s;
	final int n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1) {
		sort1(x, off, s);
	}
	if ((s = d-c) > 1) {
		sort1(x, n-s, s);
	}
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final float x[], final int a, final int b) {
	final float t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(final float x[], int a, int b, final int n) {
	for (int i=0; i<n; i++, a++, b++) {
		swap(x, a, b);
	}
    }

    /**
     * Returns the index of the median of the three indexed floats.
     */
    private static int med3(final float x[], final int a, final int b, final int c) {
	return x[a] < x[b] ?
		x[b] < x[c] ? b : x[a] < x[c] ? c : a :
		x[b] > x[c] ? b : x[a] > x[c] ? c : a;
    }


    /**
     * Sorts the specified array of objects into ascending order, according to
     * the <i>natural ordering</i> of its elements.  All elements in the array
     * must implement the <tt>Comparable</tt> interface.  Furthermore, all
     * elements in the array must be <i>mutually comparable</i> (that is,
     * <tt>e1.compareTo(e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance, and can approach linear performance on nearly
     * sorted lists.
     *
     * @param a the array to be sorted.
     * @throws  ClassCastException if the array contains elements that are not
     *		<i>mutually comparable</i> (for example, strings and integers).
     * @see Comparable
     */
    public static void sort(final Object[] a) {
        final Object aux[] = (Object[])a.clone();
        mergeSort(aux, a, 0, a.length);
    }

    /**
     * Sorts the specified range of the specified array of objects into
     * ascending order, according to the <i>natural ordering</i> of its
     * elements.  All elements in this range must implement the
     * <tt>Comparable</tt> interface.  Furthermore, all elements in this range
     * must be <i>mutually comparable</i> (that is, <tt>e1.compareTo(e2)</tt>
     * must not throw a <tt>ClassCastException</tt> for any elements
     * <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance, and can approach linear performance on nearly
     * sorted lists.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     * @throws    ClassCastException if the array contains elements that are
     *		  not <i>mutually comparable</i> (for example, strings and
     *		  integers).
     * @see Comparable
     */
    public static void sort(final Object[] a, final int fromIndex, final int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        final Object aux[] = (Object[])a.clone();  // Optimization opportunity
        mergeSort(aux, a, fromIndex, toIndex);
    }

    private static void mergeSort(final Object src[], final Object dest[],
                                  final int low, final int high) {
	final int length = high - low;

	// Insertion sort on smallest arrays
	if (length < 7) {
	    for (int i=low; i<high; i++) {
		for (int j=i; j>low &&
                 ((Comparable)dest[j-1]).compareTo(dest[j])>0; j--) {
			swap(dest, j, j-1);
		}
	}
	    return;
	}

        // Recursively sort halves of dest into src
        final int mid = (low + high)/2;
        mergeSort(dest, src, low, mid);
        mergeSort(dest, src, mid, high);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
           System.arraycopy(src, low, dest, low, length);
           return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = low, p = low, q = mid; i < high; i++) {
            if (q>=high || p<mid && ((Comparable)src[p]).compareTo(src[q])<=0) {
		dest[i] = src[p++];
	} else {
		dest[i] = src[q++];
	}
        }
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(final Object x[], final int a, final int b) {
	final Object t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Sorts the specified array of objects according to the order induced by
     * the specified comparator.  All elements in the array must be
     * <i>mutually comparable</i> by the specified comparator (that is,
     * <tt>c.compare(e1, e2)</tt> must not throw a <tt>ClassCastException</tt>
     * for any elements <tt>e1</tt> and <tt>e2</tt> in the array).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance, and can approach linear performance on nearly
     * sorted lists.
     *
     * @param a the array to be sorted.
     * @param c the comparator to determine the order of the array.
     * @throws  ClassCastException if the array contains elements that are
     *		not <i>mutually comparable</i> using the specified comparator.
     * @see Comparator
     */
    public static void sort(final Object[] a, final Comparator c) {
        final Object aux[] = (Object[])a.clone();
        mergeSort(aux, a, 0, a.length, c);
    }

    /**
     * Sorts the specified range of the specified array of objects according
     * to the order induced by the specified comparator.  All elements in the
     * range must be <i>mutually comparable</i> by the specified comparator
     * (that is, <tt>c.compare(e1, e2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>e1</tt> and
     * <tt>e2</tt> in the range).<p>
     *
     * This sort is guaranteed to be <i>stable</i>:  equal elements will
     * not be reordered as a result of the sort.<p>
     *
     * The sorting algorithm is a modified mergesort (in which the merge is
     * omitted if the highest element in the low sublist is less than the
     * lowest element in the high sublist).  This algorithm offers guaranteed
     * n*log(n) performance, and can approach linear performance on nearly
     * sorted lists.
     *
     * @param a the array to be sorted.
     * @param fromIndex the index of the first element (inclusive) to be
     *        sorted.
     * @param toIndex the index of the last element (exclusive) to be sorted.
     * @param c the comparator to determine the order of the array.
     * @throws ClassCastException if the array contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     * @see Comparator
     */
    public static void sort(final Object[] a, final int fromIndex, final int toIndex,
                            final Comparator c) {
        rangeCheck(a.length, fromIndex, toIndex);
        final Object aux[] = (Object[])a.clone();
        mergeSort(aux, a, fromIndex, toIndex, c);
    }

    private static void mergeSort(final Object src[], final Object dest[],
                                  final int low, final int high, final Comparator c) {
	final int length = high - low;

	// Insertion sort on smallest arrays
	if (length < 7) {
	    for (int i=low; i<high; i++) {
		for (int j=i; j>low && c.compare(dest[j-1], dest[j])>0; j--) {
			swap(dest, j, j-1);
		}
	}
	    return;
	}

        // Recursively sort halves of dest into src
        final int mid = (low + high)/2;
        mergeSort(dest, src, low, mid, c);
        mergeSort(dest, src, mid, high, c);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (c.compare(src[mid-1], src[mid]) <= 0) {
           System.arraycopy(src, low, dest, low, length);
           return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = low, p = low, q = mid; i < high; i++) {
            if (q>=high || p<mid && c.compare(src[p], src[q]) <= 0) {
		dest[i] = src[p++];
	} else {
		dest[i] = src[q++];
	}
        }
    }

    /**
     * Check that fromIndex and toIndex are in range, and throw an
     * appropriate exception if they aren't.
     */
    private static void rangeCheck(final int arrayLen, final int fromIndex, final int toIndex) {
        if (fromIndex > toIndex) {
		throw new IllegalArgumentException("fromIndex(" + fromIndex +
		               ") > toIndex(" + toIndex+")");
	}
        if (fromIndex < 0) {
		throw new ArrayIndexOutOfBoundsException(fromIndex);
	}
        if (toIndex > arrayLen) {
		throw new ArrayIndexOutOfBoundsException(toIndex);
	}
    }

    // Searching

    /**
     * Searches the specified array of longs for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(long[])
     */
    public static int binarySearch(final long[] a, final long key) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final long midVal = a[mid];

	    if (midVal < key) {
		low = mid + 1;
	} else if (midVal > key) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }


    /**
     * Searches the specified array of ints for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(int[])
     */
    public static int binarySearch(final int[] a, final int key) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final int midVal = a[mid];

	    if (midVal < key) {
		low = mid + 1;
	} else if (midVal > key) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of shorts for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(short[])
     */
    public static int binarySearch(final short[] a, final short key) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final short midVal = a[mid];

	    if (midVal < key) {
		low = mid + 1;
	} else if (midVal > key) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of chars for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(char[])
     */
    public static int binarySearch(final char[] a, final char key) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final char midVal = a[mid];

	    if (midVal < key) {
		low = mid + 1;
	} else if (midVal > key) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of bytes for the specified value using the
     * binary search algorithm.  The array <strong>must</strong> be sorted (as
     * by the <tt>sort</tt> method, above) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(byte[])
     */
    public static int binarySearch(final byte[] a, final byte key) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final byte midVal = a[mid];

	    if (midVal < key) {
		low = mid + 1;
	} else if (midVal > key) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of doubles for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(double[])
     */
    public static int binarySearch(final double[] a, final double key) {
        return binarySearch(a, key, 0, a.length-1);
    }

    private static int binarySearch(final double[] a, final double key, int low,int high) {
	while (low <= high) {
	    final int mid =(low + high)/2;
	    final double midVal = a[mid];

            int cmp;
            if (midVal < key) {
                cmp = -1;   // Neither val is NaN, thisVal is smaller
            } else if (midVal > key) {
                cmp = 1;    // Neither val is NaN, thisVal is larger
            } else {
                final long midBits = Double.doubleToLongBits(midVal);
                final long keyBits = Double.doubleToLongBits(key);
                cmp = midBits == keyBits ?  0 : // Values are equal
                       midBits < keyBits ? -1 : // (-0.0, 0.0) or (!NaN, NaN)
                        1;                     // (0.0, -0.0) or (NaN, !NaN)
            }

	    if (cmp < 0) {
		low = mid + 1;
	} else if (cmp > 0) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of floats for the specified value using
     * the binary search algorithm.  The array <strong>must</strong> be sorted
     * (as by the <tt>sort</tt> method, above) prior to making this call.  If
     * it is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @see #sort(float[])
     */
    public static int binarySearch(final float[] a, final float key) {
        return binarySearch(a, key, 0, a.length-1);
    }

    private static int binarySearch(final float[] a, final float key, int low,int high) {
	while (low <= high) {
	    final int mid =(low + high)/2;
	    final float midVal = a[mid];

            int cmp;
            if (midVal < key) {
                cmp = -1;   // Neither val is NaN, thisVal is smaller
            } else if (midVal > key) {
                cmp = 1;    // Neither val is NaN, thisVal is larger
            } else {
                final int midBits = Float.floatToIntBits(midVal);
                final int keyBits = Float.floatToIntBits(key);
                cmp = midBits == keyBits ?  0 : // Values are equal
                       midBits < keyBits ? -1 : // (-0.0, 0.0) or (!NaN, NaN)
                        1;                     // (0.0, -0.0) or (NaN, !NaN)
            }

	    if (cmp < 0) {
		low = mid + 1;
	} else if (cmp > 0) {
		high = mid - 1;
	}
	else {
		return mid; // key found
	}
	}
	return -(low + 1);  // key not found.
    }


    /**
     * Searches the specified array for the specified object using the binary
     * search algorithm.  The array must be sorted into ascending order
     * according to the <i>natural ordering</i> of its elements (as by
     * <tt>Sort(Object[]</tt>), above) prior to making this call.  If it is
     * not sorted, the results are undefined.  If the array contains multiple
     * elements equal to the specified object, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the array contains elements that are not
     *	       <i>mutually comparable</i> (for example, strings and integers),
     *         or the search key in not mutually comparable with the elements
     *         of the array.
     * @see Comparable
     * @see #sort(Object[])
     */
    public static int binarySearch(final Object[] a, final Object key) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final Object midVal = a[mid];
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
	return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array for the specified object using the binary
     * search algorithm.  The array must be sorted into ascending order
     * according to the specified comparator (as by the <tt>Sort(Object[],
     * Comparator)</tt> method, above), prior to making this call.  If it is
     * not sorted, the results are undefined.  If the array contains multiple
     * elements equal to the specified object, there is no guarantee which one
     * will be found.
     *
     * @param a the array to be searched.
     * @param key the value to be searched for.
     * @param c the comparator by which the array is ordered.
     * @return index of the search key, if it is contained in the list;
     *	       otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.  The
     *	       <i>insertion point</i> is defined as the point at which the
     *	       key would be inserted into the list: the index of the first
     *	       element greater than the key, or <tt>list.size()</tt>, if all
     *	       elements in the list are less than the specified key.  Note
     *	       that this guarantees that the return value will be &gt;= 0 if
     *	       and only if the key is found.
     * @throws ClassCastException if the array contains elements that are not
     *	       <i>mutually comparable</i> using the specified comparator,
     *	       or the search key in not mutually comparable with the
     *	       elements of the array using this comparator.
     * @see Comparable
     * @see #sort(Object[], Comparator)
     */
    public static int binarySearch(final Object[] a, final Object key, final Comparator c) {
	int low = 0;
	int high = a.length-1;

	while (low <= high) {
	    final int mid =(low + high)/2;
	    final Object midVal = a[mid];
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
	return -(low + 1);  // key not found.
    }


    // Equality Testing

    /**
     * Returns <tt>true</tt> if the two specified arrays of longs are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final long[] a, final long[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (a[i] != a2[i]) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of ints are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final int[] a, final int[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (a[i] != a2[i]) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of shorts are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final short[] a, final short a2[]) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (a[i] != a2[i]) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of chars are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final char[] a, final char[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (a[i] != a2[i]) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of bytes are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final byte[] a, final byte[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (a[i] != a2[i]) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of equals are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final boolean[] a, final boolean[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (a[i] != a2[i]) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of doubles are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * Two doubles <tt>d1</tt> and <tt>d2</tt> are considered equal if:
     * <pre>    <tt>new Double(d1).equals(new Double(d2))</tt></pre>
     * (Unlike the <tt>==</tt> operator, this method considers
     * <tt>NaN</tt> equals to itself, and 0.0d unequal to -0.0d.)
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     * @see Double#equals(Double)
     */
    public static boolean equals(final double[] a, final double[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (Double.doubleToLongBits(a[i])!=Double.doubleToLongBits(a2[i])) {
			return false;
		}
	}

        return true;
    }

    /**
     * Returns <tt>true</tt> if the two specified arrays of floats are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are <tt>null</tt>.<p>
     *
     * Two doubles <tt>d1</tt> and <tt>d2</tt> are considered equal if:
     * <pre>    <tt>new Double(d1).equals(new Double(d2))</tt></pre>
     * (Unlike the <tt>==</tt> operator, this method considers
     * <tt>NaN</tt> equals to itself, and 0.0d unequal to -0.0d.)
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     * @see Double#equals(Double)
     */
    public static boolean equals(final float[] a, final float[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
		if (Float.floatToIntBits(a[i])!=Float.floatToIntBits(a2[i])) {
			return false;
		}
	}

        return true;
    }


    /**
     * Returns <tt>true</tt> if the two specified arrays of Objects are
     * <i>equal</i> to one another.  The two arrays are considered equal if
     * both arrays contain the same number of elements, and all corresponding
     * pairs of elements in the two arrays are equal.  Two objects <tt>e1</tt>
     * and <tt>e2</tt> are considered <i>equal</i> if <tt>(e1==null ? e2==null
     * : e1.equals(e2))</tt>.  In other words, the two arrays are equal if
     * they contain the same elements in the same order.  Also, two array
     * references are considered equal if both are <tt>null</tt>.<p>
     *
     * @param a one array to be tested for equality.
     * @param a2 the other array to be tested for equality.
     * @return <tt>true</tt> if the two arrays are equal.
     */
    public static boolean equals(final Object[] a, final Object[] a2) {
        if (a==a2) {
		return true;
	}
        if (a==null || a2==null) {
		return false;
	}

        final int length = a.length;
        if (a2.length != length) {
		return false;
	}

        for (int i=0; i<length; i++) {
            final Object o1 = a[i];
            final Object o2 = a2[i];
            if (!(o1==null ? o2==null : o1.equals(o2))) {
		return false;
	}
        }

        return true;
    }


    // Filling

    /**
     * Assigns the specified long value to each element of the specified array
     * of longs.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final long[] a, final long val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified long value to each element of the specified
     * range of the specified array of longs.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final long[] a, final int fromIndex, final int toIndex, final long val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified int value to each element of the specified array
     * of ints.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final int[] a, final int val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified int value to each element of the specified
     * range of the specified array of ints.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final int[] a, final int fromIndex, final int toIndex, final int val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified short value to each element of the specified array
     * of shorts.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final short[] a, final short val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified short value to each element of the specified
     * range of the specified array of shorts.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final short[] a, final int fromIndex, final int toIndex, final short val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified char value to each element of the specified array
     * of chars.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final char[] a, final char val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified char value to each element of the specified
     * range of the specified array of chars.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final char[] a, final int fromIndex, final int toIndex, final char val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified byte value to each element of the specified array
     * of bytes.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final byte[] a, final byte val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified byte value to each element of the specified
     * range of the specified array of bytes.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final byte[] a, final int fromIndex, final int toIndex, final byte val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified boolean value to each element of the specified
     * array of booleans.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final boolean[] a, final boolean val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified boolean value to each element of the specified
     * range of the specified array of booleans.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final boolean[] a, final int fromIndex, final int toIndex,
                            final boolean val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified double value to each element of the specified
     * array of doubles.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final double[] a, final double val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified double value to each element of the specified
     * range of the specified array of doubles.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final double[] a, final int fromIndex, final int toIndex,final double val){
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified float value to each element of the specified array
     * of floats.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final float[] a, final float val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified float value to each element of the specified
     * range of the specified array of floats.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final float[] a, final int fromIndex, final int toIndex, final float val) {
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }

    /**
     * Assigns the specified Object reference to each element of the specified
     * array of Objects.
     *
     * @param a the array to be filled.
     * @param val the value to be stored in all elements of the array.
     */
    public static void fill(final Object[] a, final Object val) {
        fill(a, 0, a.length, val);
    }

    /**
     * Assigns the specified Object reference to each element of the specified
     * range of the specified array of Objects.
     *
     * @param a the array to be filled.
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value.
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value.
     * @param val the value to be stored in all elements of the array.
     * @throws IllegalArgumentException if <tt>fromIndex &gt; toIndex</tt>
     * @throws ArrayIndexOutOfBoundsException if <tt>fromIndex &lt; 0</tt> or
     *	       <tt>toIndex &gt; a.length</tt>
     */
    public static void fill(final Object[] a, final int fromIndex, final int toIndex,final Object val){
        rangeCheck(a.length, fromIndex, toIndex);
        for (int i=fromIndex; i<toIndex; i++) {
		a[i] = val;
	}
    }


    // Misc

    /**
     * Returns a fixed-size list backed by the specified array.  (Changes to
     * the returned list "write through" to the array.)  This method acts
     * as bridge between array-based and collection-based APIs, in
     * combination with <tt>Collection.toArray</tt>.  The returned list is
     * serializable.
     *
     * @param a the array by which the list will be backed.
     * @return a list view of the specified array.
     * @see Collection#toArray()
     */
    public static List asList(final Object[] a) {
	return new ArrayList(a);
    }

    private static class ArrayList extends AbstractList
    				   implements java.io.Serializable
    {
        private static final long serialVersionUID = -2764017481108945198L;
	private final Object[] a;

	ArrayList(final Object[] array) {
	    this.a = array;
	}

	public int size() {
	    return this.a.length;
	}

	public Object[] toArray() {
	    return (Object[]) this.a.clone();
	}

	public Object get(final int index) {
	    return this.a[index];
	}

	public Object set(final int index, final Object element) {
	    final Object oldValue = this.a[index];
	    this.a[index] = element;
	    return oldValue;
	}

        public int indexOf(final Object o) {
            if (o==null) {
                for (int i=0; i<this.a.length; i++) {
			if (this.a[i]==null) {
				return i;
			}
		}
            } else {
                for (int i=0; i<this.a.length; i++) {
			if (o.equals(this.a[i])) {
				return i;
			}
		}
            }
            return -1;
        }

        public boolean contains(final Object o) {
            return this.indexOf(o) != -1;
        }
    }
}
