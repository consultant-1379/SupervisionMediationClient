package com.ericsson.oss.mediation.supervision;

import java.util.UUID;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.itpf.sdk.eventbus.model.EventSender;
import com.ericsson.oss.itpf.sdk.eventbus.model.annotation.Modeled;
import com.ericsson.oss.mediation.core.events.MediationTaskRequest;

@Stateless
public class SupervisionWorkerImpl implements SupervisionWorker {

    @Inject
    private Logger logger;

    @Inject
    @Modeled
    private EventSender<MediationTaskRequest> sender;

    // TODO Until ModelService is ready modeled events used by services MUST be packaged inside service's EAR files. 
    // Any event used by service (and all modeled subclasses of that event) must be packaged inside service EAR. 
    // So the team 2 has to add our SupervisionTaskRequest to the same EAR that the MediationTaskRequest    
    //private EventSender<SupervisionTaskRequest> sender;

    @Override
    @Asynchronous
    public void initSupervision(final PersistentObject persistentObject) {

        //final MediationTaskRequest supervisionTaskRequest = new MediationTaskRequest(persistentObject.getId(), "jobId");        
        final MediationTaskRequest supervisionTaskRequest = new MediationTaskRequest(persistentObject.getType(), UUID.randomUUID().toString());
        logger.debug("Streaming FDN {}", supervisionTaskRequest.getNodeAddress());
        // TODO Uncomment and comment the above line, when team 2 has added the SupervisionTaskRequest to the same EAR that the MediationTaskRequest
        //SupervisionTaskRequest supervisionTaskRequest = new SupervisionTaskRequest(persistentObject.getId());

        this.sender.send(supervisionTaskRequest);
    }

}
