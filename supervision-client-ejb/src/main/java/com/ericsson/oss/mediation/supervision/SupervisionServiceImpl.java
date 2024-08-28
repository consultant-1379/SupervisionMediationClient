package com.ericsson.oss.mediation.supervision;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.data.model.supervision.SupervisionObject;
import com.ericsson.oss.mediation.supervision.integration.DPSFacade;

@Stateless
public class SupervisionServiceImpl implements SupervisionService {
    @Inject
    private SupervisionWorker workerSupervision;

    @Inject
    private DPSFacade serviceDpsFacade;

    @Inject
    private Logger logger;

    @Override
    public void initAllSupervisions() {
        final PersistentObject[] supervisions = this.serviceDpsFacade.findByType(SupervisionObject.class);

        for (final PersistentObject persistenceObject : supervisions) {

            final SupervisionObject supervisionObject = (SupervisionObject) persistenceObject;

            if (supervisionObject.isActive()) {
                this.workerSupervision.initSupervision(supervisionObject);
                logger.debug("Init supervision for SupervisionObject {}", supervisionObject.getType());
            }
        }

    }
}