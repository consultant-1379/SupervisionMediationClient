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
package com.ericsson.oss.data.model.supervision.streaming;

import java.util.Map;

import com.ericsson.oss.data.api.DataPersistenceException;
import com.ericsson.oss.data.model.supervision.SupervisionObjectImpl;

/**
 * Implementation for the StreamingSupervisionObject interface
 * 
 * @author eraumun
 */
public class StreamingSupervisionObjectImpl extends SupervisionObjectImpl implements StreamingSupervisionObject {

    private static final long serialVersionUID = 5413051393887910243L;

    /**
     * @param type
     * @param initialAttributes
     * @throws DataPersistenceException
     */
    public StreamingSupervisionObjectImpl(final String type, final Map<String, Object> initialAttributes) throws DataPersistenceException {
        super(type, initialAttributes);
    }

}
