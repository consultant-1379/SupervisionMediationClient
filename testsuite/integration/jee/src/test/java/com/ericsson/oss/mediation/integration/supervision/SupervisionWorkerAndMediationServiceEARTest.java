package com.ericsson.oss.mediation.integration.supervision;

import java.io.IOException;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.*;
import org.junit.runner.RunWith;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.data.model.supervision.SupervisionObjectImpl;
import com.ericsson.oss.mediation.integration.CreateArtifact;
import com.ericsson.oss.mediation.integration.MockConfigurationProvider;
import com.ericsson.oss.mediation.supervision.SupervisionWorker;

@RunWith(Arquillian.class)
@Ignore
public class SupervisionWorkerAndMediationServiceEARTest {

    private static final String DEPLOYMENT_2 = "DEPLOYMENT_2";
    private static final String DEPLOYMENT_1 = "DEPLOYMENT_1";
    private static final String DEPLOYMENT_3 = "DEPLOYMENT_3";

    @Inject
    private SupervisionWorker serviceSupervisionWorker;

    @Deployment(name = DEPLOYMENT_1, testable = false)
    public static Archive<?> createMediationCoreEar() {
        return CreateArtifact.getMediationCoreEAR();
    }

    @Deployment(name = DEPLOYMENT_2, testable = false)
    public static Archive<?> createMediationServiceEar() {
        return CreateArtifact.getMediationServiceEAR();
    }

    @Deployment(name = DEPLOYMENT_3)
    public static EnterpriseArchive createDeployment() {
        return CreateArtifact.createEAR(SupervisionWorkerAndMediationServiceEARTest.class, MockConfigurationProvider.class);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_3)
    @InSequence(1)
    public void supervision_worker_not_null_test() {
        Assert.assertNotNull(this.serviceSupervisionWorker);
    }

    @Test
    @OperateOnDeployment(DEPLOYMENT_3)
    @InSequence(2)
    public void supervision_worker_send_test() throws Exception {

        final PersistentObject persistentObject = new SupervisionObjectImpl("FDN3", new HashMap<String, Object>());

        this.serviceSupervisionWorker.initSupervision(persistentObject);

        Thread.sleep(10000);

    }

    @Test
    @RunAsClient
    @InSequence(3)
    public void supervision_worker_check_port() throws Exception {
        SocketChannel socketChannel = null;
        try {
            final InetSocketAddress address = new InetSocketAddress(InetAddress.getByName("localhost"), 1234);
            try {
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.connect(address);
                while (socketChannel.isConnectionPending()) {
                    socketChannel.finishConnect();
                }

                Assert.assertTrue(socketChannel.isConnected());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                socketChannel.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
