package com.ericsson.nms.mediation.core.events;

import com.ericsson.oss.itpf.sdk.modeling.eventbus.annotation.ModeledEventDefinition;
import com.ericsson.oss.mediation.core.events.MediationTaskRequest;

@ModeledEventDefinition(defaultChannelId = "EventBasedMediationClient", version = "1.0.0", description = "MediationTaskRequest modeled event")
public class SupervisionTaskRequest extends MediationTaskRequest {
    public SupervisionTaskRequest() {
        super();
    }

    public SupervisionTaskRequest(String address) {
        this();
        this.setNodeAddress(address);

    }
}
