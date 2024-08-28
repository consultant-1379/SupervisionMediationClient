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
package com.ericsson.oss.mediation.integration.supervision.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.data.model.supervision.SupervisionObject;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObject;
import com.ericsson.oss.mediation.integration.CreateArtifact;
import com.ericsson.oss.mediation.integration.MockConfigurationProvider;
import com.ericsson.oss.mediation.supervision.integration.DPSException;
import com.ericsson.oss.mediation.supervision.integration.DPSFacade;

@RunWith(Arquillian.class)
public class DPSFacadeEARTest {

    @Inject
    private DPSFacade serviceDps;
    /**
     * Logger for this test
     */
    private static final Logger logger = LoggerFactory.getLogger(DPSFacadeEARTest.class);

    @Deployment
    public static EnterpriseArchive createDeployment() {

        return CreateArtifact.createEAR(DPSFacadeEARTest.class, MockConfigurationProvider.class);
    }

    /**
     * Test method for {@link com.ericsson.oss.mediation.supervision.integration.MockDSPFacadeImpl#findByType(java.lang.Class)} .
     * 
     * @throws DPSException
     */
    @Test
    public void test_dps_facade_not_null() {

        assertNotNull(this.serviceDps);
    }

    @Test
    public void testFindByType_classExists_findsRootInstances() throws DPSException {
        PersistentObject[] returnedPO = this.serviceDps.findByType(SupervisionObject.class);

        assertNotNull(returnedPO);
    }

    @Test
    public void testFindByType_Supervision_findsOnlySupervisions() throws DPSException {
        PersistentObject[] returnedPOs = this.serviceDps.findByType(SupervisionObject.class);
        logger.debug("returnedPOs: {} ", returnedPOs);
        assertNotNull(returnedPOs);

        String[] expectedTypes = { StreamingSupervisionObject.class.getSimpleName() };

        boolean allPOsAreSupervisions = true;
        for (PersistentObject po : returnedPOs) {
            logger.debug("po is of type {}", po.getType());
            if (!Arrays.asList(expectedTypes).contains(po.getType())) {

                allPOsAreSupervisions = false;
            }
        }
        assertTrue("Not all POs returned were supervisions! ", allPOsAreSupervisions);
    }

}
