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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

/**
 * Test the SupervisionWorkerImpl class
 * 
 * @author eraumun
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class SupervisionStartupSingletonTest {
    @Mock
    private SupervisionService mockSupervisionService;
    @Mock
    private Logger mockLogger;
    @InjectMocks
    private SupervisionStartupSingleton supervisionStartupSingleton = new SupervisionStartupSingleton();

    @Test
    public void testInitSupervision() {

        //expect
        doNothing().when(mockSupervisionService).initAllSupervisions();

        //business logic
        supervisionStartupSingleton.initSupervision();

        //verify
        verify(mockSupervisionService).initAllSupervisions();

        Assert.assertNotNull(supervisionStartupSingleton);

    }
}