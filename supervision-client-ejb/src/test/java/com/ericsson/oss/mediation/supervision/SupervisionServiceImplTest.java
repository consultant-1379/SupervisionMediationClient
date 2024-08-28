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
import com.ericsson.oss.data.model.supervision.SupervisionObject;
import com.ericsson.oss.data.model.supervision.SupervisionObjectImpl;
import com.ericsson.oss.mediation.supervision.integration.DPSFacade;

/**
 * Test the SupervisionServiceImplTest
 * 
 * @author eraumun
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SupervisionServiceImplTest {

    @Mock
    private SupervisionWorker mockSupervisionWorker;
    @Mock
    private Logger mockLogger;
    @Mock
    private DPSFacade mockDPSFacade;
    @InjectMocks
    private SupervisionServiceImpl supervisionServiceImpl = new SupervisionServiceImpl();

    @Test
    public void testInitAllSupervisions() {
        final SupervisionObjectImpl supervisionObject = new SupervisionObjectImpl(null, null);
        supervisionObject.setActive(true);

        final PersistentObject[] supervisions = new SupervisionObjectImpl[] { supervisionObject };

        //expect
        when(mockDPSFacade.findByType(SupervisionObject.class)).thenReturn(supervisions);
        doNothing().when(mockSupervisionWorker).initSupervision(supervisions[0]);

        //business logic
        supervisionServiceImpl.initAllSupervisions();

        //verify
        verify(this.mockDPSFacade).findByType(SupervisionObject.class);
        verify(this.mockSupervisionWorker).initSupervision(supervisions[0]);

        Assert.assertNotNull(supervisionServiceImpl);

    }

    @Test
    public void testInitAllSupervisionsTwo() {
        final SupervisionObjectImpl supervisionObject = new SupervisionObjectImpl(null, null);
        supervisionObject.setActive(true);
        final PersistentObject[] supervisions = new SupervisionObjectImpl[] { supervisionObject, supervisionObject };
        //expect        
        when(mockDPSFacade.findByType(SupervisionObject.class)).thenReturn(supervisions);
        doNothing().when(mockSupervisionWorker).initSupervision(any(PersistentObject.class));
        //business logic
        supervisionServiceImpl.initAllSupervisions();

        //verify
        verify(this.mockDPSFacade).findByType(SupervisionObject.class);
        verify(this.mockSupervisionWorker, times(2)).initSupervision(any(PersistentObject.class));

        Assert.assertNotNull(supervisionServiceImpl);

    }

}