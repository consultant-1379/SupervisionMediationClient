package com.ericsson.oss.mediation.supervision.integration;

import javax.ejb.Local;

import com.ericsson.oss.data.api.PersistentObject;

@Local
public interface DPSFacade {
    /***
     * Finds all POs that are of the specified type (and its sub-types)
     * 
     * @param poType
     *            accepted classes are PersistnantObject or any of its children
     * @return a list of PersistantObjects that are in the DPS of the type specified
     * @throws DPSException
     *             if any issue is encountered while accessing the DPS
     */
    PersistentObject[] findByType(Class<? extends PersistentObject> poType);
}
