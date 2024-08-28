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

package com.ericsson.oss.mediation.supervision;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import com.ericsson.oss.data.api.PersistentObject;
import com.ericsson.oss.itpf.sdk.eventbus.model.EventSender;
import com.ericsson.oss.mediation.core.events.MediationTaskRequest;

/**
 * Test the SupervisionWorkerImpl class
 * 
 * @author eraumun
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SupervisionWorkerImplTest {

    @Mock
    private EventSender<MediationTaskRequest> mockSender;
    @Mock
    private Logger mockLogger;
    @Mock
    private PersistentObject mockPersistentObject;
    @InjectMocks
    private SupervisionWorkerImpl supervisionWorkerImpl = new SupervisionWorkerImpl();

    @Test
    public void testInitSupervision() {
        //expect
        when(mockPersistentObject.getType()).thenReturn("id");
        doNothing().when(mockSender).send(any(MediationTaskRequest.class));

        //business logic
        supervisionWorkerImpl.initSupervision(mockPersistentObject);

        //verify
        verify(this.mockSender).send(any(MediationTaskRequest.class));
        verify(mockPersistentObject).getType();

        Assert.assertNotNull(supervisionWorkerImpl);

    }

}