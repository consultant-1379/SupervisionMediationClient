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
package com.ericsson.oss.data.model;
import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

import com.ericsson.oss.data.api.DataPersistenceException;
import com.ericsson.oss.data.api.ManagedObject;

/**
 * Unit tests for Supervision Model.
 * @author ebujkri
 *
 */
public class SupervisionModelTest {
    
	@Test
    public void testCreateRootManagedObjectSuccess() {
    	// TODO
    }
    
	@Test
	public void testCreateChildrenUnderManagedObjectRoot() {
		// TODO
	}
    
	//@Test(expected = DataPersistenceException.class)
	public void testCreateDuplicateManagedObject() {
		// TODO
	}
	
	@Test
	public void testCreateManagedObjectWithInvalidName() {
		// TODO
	}
	
	//@Test(expected = DataPersistenceException.class)
	public void testCreateManagedObjectWithInvalidType() {
		// TODO
	}
	
	//@Test(expected = DataPersistenceException.class)
	public void testCreateManagedObjectWithUnknownType() {
		// TODO
	}

	//@Test(expected = DataPersistenceException.class)
	public void testCreateManagedObjectWithInvalidParentType() {
		// TODO
	}
	
	//@Test(expected = DataPersistenceException.class)
	public void testCreateManagedObjectWithInvalidAttributeType() {
		// TODO
	}
	
	//@Test(expected = DataPersistenceException.class)
	public void testCreateManagedObjectWithExtraAttributesProvided() {
		// TODO
	}
}
