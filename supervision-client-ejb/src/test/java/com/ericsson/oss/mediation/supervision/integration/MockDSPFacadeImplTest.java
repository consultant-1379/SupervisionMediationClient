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
package com.ericsson.oss.mediation.supervision.integration;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObject;
import com.ericsson.oss.data.model.supervision.streaming.StreamingSupervisionObjectImpl;

@RunWith(MockitoJUnitRunner.class)
public class MockDSPFacadeImplTest {

    private transient final MockDSPFacadeImpl dpsImpl = new MockDSPFacadeImpl();

    @Mock
    private Logger logger; // NOPMD

    @Before
    public void setUp() throws IllegalAccessException {
        final String[] supervisionPOs = new String[] { "streaming" };
        final String[] supervisionPOsactives = new String[] { "true" };
        ReflectionTestUtils.setPrimitiveField(MockDSPFacadeImpl.class, String.class, "supervisionPOs", dpsImpl, supervisionPOs);
        ReflectionTestUtils.setPrimitiveField(MockDSPFacadeImpl.class, String.class, "supervisionPOsactives", dpsImpl, supervisionPOsactives);
        ReflectionTestUtils.setNonPrimitiveField(MockDSPFacadeImpl.class, Logger.class, dpsImpl, logger);
    }

    /**
     * Test method for {@link com.ericsson.oss.mediation.supervision.integration.MockDSPFacadeImpl#findByType(java.lang.Class)} .
     */
    @Test
    public void testFindByTypeReturnsStreamingPO() throws IllegalAccessException {
        final PersistentObject expectedStreamPO = createDefaultStreamImpl();
        final PersistentObject[] StreamingPOs = dpsImpl.findByType(StreamingSupervisionObject.class);
        final PersistentObject actualStreamingPO = StreamingPOs[0];
        assertEquals("expected and actual Values do not matech ", expectedStreamPO.getType(), actualStreamingPO.getType());
    }

    @Test
    public void testStreamingPOHasAValidPort() {
        final PersistentObject expectedStreamPO = createDefaultStreamImpl();
        final PersistentObject[] StreamingPOs = dpsImpl.findByType(StreamingSupervisionObject.class);
        final PersistentObject actualStreamingPO = StreamingPOs[0];
        Assert.assertNotNull("expected not null", actualStreamingPO);
    }

    @Test(expected = DPSException.class)
    public void testInvalidSupervisionPOs() throws IllegalAccessException {
        String[] invalidPOs = null; // NOPMD
        ReflectionTestUtils.setPrimitiveField(MockDSPFacadeImpl.class, String.class, "supervisionPOs", dpsImpl, invalidPOs);
        dpsImpl.findByType(StreamingSupervisionObject.class);
    }

    private PersistentObject createDefaultStreamImpl() {
        final String name = StreamingSupervisionObjectImpl.class.toString();
        final String type = "StreamingSupervisionObject"; // NOPMD        
        final Map<String, Object> initialAttributes = new HashMap<String, Object>();
        int port = 8080; // NOPMD 
        initialAttributes.put("port", port);
        final PersistentObject streamingMO = new StreamingSupervisionObjectImpl(type, initialAttributes);
        return streamingMO;
    }

}
