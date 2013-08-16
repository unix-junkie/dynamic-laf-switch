/*
 * @(#)Random.java	1.28 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.backport.java.util;

/**
 * An instance of this class is used to generate a stream of
 * pseudorandom numbers. The class uses a 48-bit seed, which is
 * modified using a linear congruential formula. (See Donald Knuth,
 * <i>The Art of Computer Programming, Volume 2</i>, Section 3.2.1.)
 * <p>
 * If two instances of <code>Random</code> are created with the same
 * seed, and the same sequence of method calls is made for each, they
 * will generate and return identical sequences of numbers. In order to
 * guarantee this property, particular algorithms are specified for the
 * class <tt>Random</tt>. Java implementations must use all the algorithms
 * shown here for the class <tt>Random</tt>, for the sake of absolute
 * portability of Java code. However, subclasses of class <tt>Random</tt>
 * are permitted to use other algorithms, so long as they adhere to the
 * general contracts for all the methods.
 * <p>
 * The algorithms implemented by class <tt>Random</tt> use a
 * <tt>protected</tt> utility method that on each invocation can supply
 * up to 32 pseudorandomly generated bits.
 * <p>
 * Many applications will find the <code>random</code> method in
 * class <code>Math</code> simpler to use.
 *
 * @author  Frank Yellin
 * @version 1.28, 11/29/01
 * @see     java.lang.Math#random()
 * @since   JDK1.0
 */
public
class Random extends java.util.Random {
    /** use serialVersionUID from JDK 1.1 for interoperability */
    static final long serialVersionUID = 3905348978240129619L;

    /**
     * Creates a new random number generator. Its seed is initialized to
     * a value based on the current time:
     * <blockquote><pre>
     * public Random() { this(System.currentTimeMillis()); }</pre></blockquote>
     *
     * @see     java.lang.System#currentTimeMillis()
     */
    public Random() { super(System.currentTimeMillis()); }

    /**
     * Creates a new random number generator using a single
     * <code>long</code> seed:
     * <blockquote><pre>
     * public Random(long seed) { setSeed(seed); }</pre></blockquote>
     * Used by method <tt>next</tt> to hold
     * the state of the pseudorandom number generator.
     *
     * @param   seed   the initial seed.
     * @see     java.util.Random#setSeed(long)
     */
    public Random(final long seed) {
        super(seed);
    }

    /**
     * Returns a pseudorandom, uniformly distributed <tt>int</tt> value
     * between 0 (inclusive) and the specified value (exclusive), drawn from
     * this random number generator's sequence.  The general contract of
     * <tt>nextInt</tt> is that one <tt>int</tt> value in the specified range
     * is pseudorandomly generated and returned.  All <tt>n</tt> possible
     * <tt>int</tt> values are produced with (approximately) equal
     * probability.  The method <tt>nextInt(int n)</tt> is implemented by
     * class <tt>Random</tt> as follows:
     * <blockquote><pre>
     * public int nextInt(int n) {
     *     if (n<=0)
     *		throw new IllegalArgumentException("n must be positive");
     *
     *     if ((n & -n) == n)  // i.e., n is a power of 2
     *         return (int)((n * (long)next(31)) >> 31);
     *
     *     int bits, val;
     *     do {
     *         bits = next(31);
     *         val = bits % n;
     *     } while(bits - val + (n-1) < 0);
     *     return val;
     * }
     * </pre></blockquote>
     * <p>
     * The hedge "approximately" is used in the foregoing description only
     * because the next method is only approximately an unbiased source of
     * independently chosen bits.  If it were a perfect source of randomly
     * chosen bits, then the algorithm shown would choose <tt>int</tt>
     * values from the stated range with perfect uniformity.
     * <p>
     * The algorithm is slightly tricky.  It rejects values that would result
     * in an uneven distribution (due to the fact that 2^31 is not divisible
     * by n). The probability of a value being rejected depends on n.  The
     * worst case is n=2^30+1, for which the probability of a reject is 1/2,
     * and the expected number of iterations before the loop terminates is 2.
     * <p>
     * The algorithm treats the case where n is a power of two specially: it
     * returns the correct number of high-order bits from the underlying
     * pseudo-random number generator.  In the absence of special treatment,
     * the correct number of <i>low-order</i> bits would be returned.  Linear
     * congruential pseudo-random number generators such as the one
     * implemented by this class are known to have short periods in the
     * sequence of values of their low-order bits.  Thus, this special case
     * greatly increases the length of the sequence of values returned by
     * successive calls to this method if n is a small power of two.
     *
     * @parameter n the bound on the random number to be returned.  Must be
     *		  positive.
     * @return  a pseudorandom, uniformly distributed <tt>int</tt>
     *          value between 0 (inclusive) and n (exclusive).
     * @exception IllegalArgumentException n is not positive.
     * @since JDK1.2
     */

    public int nextInt(final int n) {
        if (n<=0) {
		throw new IllegalArgumentException("n must be positive");
	}

        if ((n & -n) == n) {
		return (int)(n * (long)this.next(31) >> 31);
	}

        int bits, val;
        do {
            bits = this.next(31);
            val = bits % n;
        } while(bits - val + n-1 < 0);
        return val;
    }

    /**
     * Returns the next pseudorandom, uniformly distributed
     * <code>boolean</code> value from this random number generator's
     * sequence. The general contract of <tt>nextBoolean</tt> is that one
     * <tt>boolean</tt> value is pseudorandomly generated and returned.  The
     * values <code>true</code> and <code>false</code> are produced with
     * (approximately) equal probability. The method <tt>nextBoolean</tt> is
     * implemented by class <tt>Random</tt> as follows:
     * <blockquote><pre>
     * public boolean nextBoolean() {return next(1) != 0;}
     *
     * @return  the next pseudorandom, uniformly distributed
     *          <code>boolean</code> value from this random number generator's
     *		sequence.
     * @since JDK1.2
     */
    public boolean nextBoolean() {return this.next(1) != 0;}
}
