package com.ericsson.oss.mediation.integration.supervision;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.oss.mediation.integration.CreateArtifact;
import com.ericsson.oss.mediation.integration.MockConfigurationProvider;
import com.ericsson.oss.mediation.supervision.SupervisionService;

@RunWith(Arquillian.class)
public class SimpleEARTest {
    @Inject
    private SupervisionService serviceSupervision;

    @Deployment
    public static EnterpriseArchive createDeployment() {
        return CreateArtifact.createEAR(SimpleEARTest.class, MockConfigurationProvider.class);
    }

    @Test
    public void supervision_service_not_null_test() {
        Assert.assertNotNull(this.serviceSupervision);
    }
}
