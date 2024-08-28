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
package com.ericsson.oss.data.model.supervision.streaming;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit tests for Streaming Supervision Model.
 * 
 * 
 */
public class StreamingSupervisionObjectImplTest {

    @Test
    public void testCreateRootManagedObjectSuccess() {
        final String type = "StreamingSupervisionObject"; // NOPMD

        final Map<String, Object> initialAttributes = null; // NOPMD

        final StreamingSupervisionObject supervisionMO = new StreamingSupervisionObjectImpl(type, initialAttributes);
        Assert.assertNotNull("expected not null", supervisionMO.getId());
        Assert.assertNotNull("expected null", supervisionMO.getAttributes());
        Assert.assertEquals("expected empty", 0, supervisionMO.getAttributes().size());
        Assert.assertEquals("expected and actual type differ for supervisionMO ", type, supervisionMO.getType());
        Assert.assertEquals("expected false", new Boolean(false), supervisionMO.isActive());

    }
}
