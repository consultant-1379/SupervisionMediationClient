package com.ericsson.oss.mediation.integration.supervision;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.*;
import org.junit.runner.RunWith;

import com.ericsson.oss.data.model.supervision.SupervisionObjectImpl;
import com.ericsson.oss.itpf.sdk.eventbus.model.ModeledEvent;
import com.ericsson.oss.mediation.core.events.MediationTaskRequest;
import com.ericsson.oss.mediation.integration.CreateArtifact;
import com.ericsson.oss.mediation.integration.MockConfigurationProvider;
import com.ericsson.oss.mediation.supervision.SupervisionWorker;

@RunWith(Arquillian.class)
public class SupervisionWorkerEARTest {
    @Inject
    private SupervisionWorker serviceSupervisionWorker;

    @Inject
    private ModelledBasicReceiver receiver;

    @Deployment
    public static EnterpriseArchive createDeployment() {
        return CreateArtifact.createEAR(SupervisionWorkerEARTest.class, MockConfigurationProvider.class, ModelledBasicReceiver.class);
    }

    @Before
    public void setup() {
        this.receiver.clear();
    }

    @Test
    @InSequence(1)
    public void supervision_worker_not_null_test() {
        Assert.assertNotNull(this.serviceSupervisionWorker);
    }

    @Test
    @InSequence(2)
    public void supervision_worker_send_test() {

        final SupervisionObjectImpl supervisionObject = new SupervisionObjectImpl("Streaming", new HashMap<String, Object>());
        supervisionObject.setActive(true);
        this.serviceSupervisionWorker.initSupervision(supervisionObject);
    }

    @Test
    @InSequence(3)
    public void supervision_worker_receive_test() throws InterruptedException {
        Assert.assertTrue("expected message not received", ModelledBasicReceiver.LATCH.await(10, TimeUnit.SECONDS));
        //TODO Uncomment and comment the above line, when team 2 has added the SupervisionTaskRequest to the same EAR that the MediationTaskRequest
        //final ModeledEvent<SupervisionTaskRequest> message = this.receiver.getMessage();
        final ModeledEvent<MediationTaskRequest> message = this.receiver.getMessage();
        Assert.assertNotNull("message should not be null", message);

    }

}
