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
package com.ericsson.oss.mediation.integration.supervision;

import java.util.concurrent.CountDownLatch;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.oss.itpf.sdk.eventbus.model.ModeledEvent;
import com.ericsson.oss.itpf.sdk.eventbus.model.annotation.Modeled;
import com.ericsson.oss.mediation.core.events.MediationTaskRequest;

/**
 * 
 * 
 * 
 */
@ApplicationScoped
public class ModelledBasicReceiver {

    public static CountDownLatch LATCH = new CountDownLatch(1);

    @Inject
    private Logger logger;

    private ModeledEvent<MediationTaskRequest> message;

    //TODO Uncomment and comment the above line, when team 2 has added the SupervisionTaskRequest to the same EAR that the MediationTaskRequest
    //void listen(@Observes @Modeled final ModeledEvent<SupervisionTaskRequest> obj) {
    void listen(@Observes @Modeled final ModeledEvent<MediationTaskRequest> obj) {
        this.message = obj;
        this.logger.debug("Received {}", obj);
        LATCH.countDown();
    }

    //TODO Uncomment for the same reason above
    //public ModeledEvent<MediationTaskRequest> getMessage() {
    public ModeledEvent<MediationTaskRequest> getMessage() {
        return this.message;
    }

    public void clear() {
        this.message = null;
        LATCH = new CountDownLatch(1);
    }

}
