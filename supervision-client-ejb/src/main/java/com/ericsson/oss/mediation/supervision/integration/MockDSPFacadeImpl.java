package com.ericsson.oss.mediation.supervision.integration;

import java.util.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObject;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObjectImpl;
import com.ericsson.oss.itpf.sdk.config.annotation.Configured;

@Stateless
public class MockDSPFacadeImpl implements DPSFacade {

    @Inject
    @NotNull
    @Configured(propertyName = "MediationClient.supervisionPOs")
    private String[] supervisionPOs;

    @Inject
    @NotNull
    @Configured(propertyName = "MediationClient.actives")
    private String[] supervisionPOSactives;

    @Inject
    private Logger logger;

    @Override
    public PersistentObject[] findByType(Class<? extends PersistentObject> poType) {
        this.logger.debug("finding all POs of type {}", poType);

        if (this.supervisionPOs == null) {
            throw new DPSException("Configuration for Supervisions not found!");
        }
        this.logger.debug("Supervisions found: [{}]", this.supervisionPOs);
        this.logger.debug("{} Supervisions total in mDPS", this.supervisionPOs.length);

        final List<PersistentObject> result = new ArrayList<PersistentObject>(this.supervisionPOs.length);
        final Map<String, Object> attributes = new HashMap<String, Object>();

        for (int i = 0; i < this.supervisionPOs.length; i++) {
            try {
                this.logger.debug("processing [{}]: {}", i, this.supervisionPOs[i]);

                if (this.supervisionPOs[i].equalsIgnoreCase("streaming")) {
                    final StreamingSupervisionObjectImpl streamingSupervisionObjectImpl = new StreamingSupervisionObjectImpl(StreamingSupervisionObject.class.getSimpleName(), attributes);
                    streamingSupervisionObjectImpl.setActive(Boolean.valueOf(this.supervisionPOSactives[i]));
                    result.add(streamingSupervisionObjectImpl);
                    this.logger.debug("new PO loaded into DPS: {}", result.get(result.size() - 1));
                }

            } catch (IllegalArgumentException | SecurityException e) {
                throw new DPSException(e, "An internal exceptuion occured when trying to to find all POs of selected type");
            }
        }

        return result.toArray(new PersistentObject[0]);
    }

}
