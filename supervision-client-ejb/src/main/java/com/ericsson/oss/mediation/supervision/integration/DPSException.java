/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.supervision.integration;

public class DPSException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2867981325729352148L;

    public DPSException() {
        super();
    }

    /**
     * Constructor with String argument describing exception
     * 
     * @param msg
     *            String cause/description of exception
     */
    public DPSException(final String msg) {
        super(msg);
    }

    /**
     * Constructor with Throwable argument
     * 
     * @param thr
     *            Throwable cause
     * @param msg
     *            String cause/description of exception
     */
    public DPSException(final Throwable thr, final String msg) {
        super(msg, thr);
    }

}
