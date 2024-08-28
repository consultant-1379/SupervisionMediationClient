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
package com.ericsson.oss.data.model.po;

import java.io.Serializable;
import java.util.Map;

import com.ericsson.oss.data.api.DataPersistenceException;
import com.ericsson.oss.data.impl.PersistentObjectImpl;

/**
 * A Concrete implementation of Supervision Persistent Object.
 * @author ebujkri
 *
 */
public class SupervisionPersistentObjectImpl extends PersistentObjectImpl implements
        Serializable {
	private static final long serialVersionUID = -9011959273728101380L;

	public SupervisionPersistentObjectImpl(final String type,
            final Map<String, Object> initialAttributes)
            throws DataPersistenceException {

        super(type, initialAttributes);

    }

}
