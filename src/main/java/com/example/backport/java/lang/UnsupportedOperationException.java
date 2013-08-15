/*
 * @(#)UnsupportedOperationException.java	1.8 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.backport.java.lang;

/**
 * Thrown to indicate that the requested operation is not supported.
 *
 * @author  Josh Bloch
 * @version 1.8 11/29/01
 * @since   JDK1.2
 */
public class UnsupportedOperationException extends RuntimeException {
	private static final long serialVersionUID = -1540314224475368138L;

    /**
     * Constructs an UnsupportedOperationException with no detail message.
     */
    public UnsupportedOperationException() {
    }

    /**
     * Constructs an UnsupportedOperationException with the specified
     * detail message.
     */
    public UnsupportedOperationException(final String message) {
	super(message);
    }
}
