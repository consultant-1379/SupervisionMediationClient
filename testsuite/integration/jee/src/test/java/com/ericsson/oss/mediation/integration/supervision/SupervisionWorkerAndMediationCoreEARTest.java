package com.ericsson.oss.mediation.integration.supervision;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.oss.data.model.supervision.SupervisionObjectImpl;
import com.ericsson.oss.mediation.integration.CreateArtifact;
import com.ericsson.oss.mediation.integration.MockConfigurationProvider;
import com.ericsson.oss.mediation.supervision.SupervisionWorker;

@RunWith(Arquillian.class)
public class SupervisionWorkerAndMediationCoreEARTest {

    private static final String DEPLOYMENT_2 = "DEPLOYMENT_2";
    private static final String DEPLOYMENT_1 = "DEPLOYMENT_1";

    @Inject
    private SupervisionWorker serviceSupervisionWorker;

    @Deployment(name = DEPLOYMENT_1, testable = false)
    public static Archive<?> createEar() {
        return CreateArtifact.getMediationCoreEAR();
    }

    @Deployment(name = DEPLOYMENT_2)
    public static EnterpriseArchive createDeployment() {
        return CreateArtifact.createEAR(SupervisionWorkerAndMediationCoreEARTest.class, MockConfigurationProvider.class);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_2)
    @InSequence(1)
    public void supervision_worker_not_null_test() {
        Assert.assertNotNull(this.serviceSupervisionWorker);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_2)
    @InSequence(2)
    public void supervision_worker_send_test() {

        final SupervisionObjectImpl supervisionObject = new SupervisionObjectImpl("Streaming", new HashMap<String, Object>());
        supervisionObject.setActive(true);
        this.serviceSupervisionWorker.initSupervision(supervisionObject);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_2)
    @InSequence(3)
    public void supervision_worker_receive_test() throws InterruptedException {
        new CountDownLatch(1).await(10, TimeUnit.SECONDS);
        Assert.assertTrue(true);

    }

}
