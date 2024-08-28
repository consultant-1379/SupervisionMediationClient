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
package com.ericsson.oss.data.model.mo;

import java.io.Serializable;
import java.util.*;

import com.ericsson.oss.data.api.DataPersistenceException;
import com.ericsson.oss.data.api.ManagedObject;
import com.ericsson.oss.data.impl.AttributeInfo;
import com.ericsson.oss.data.impl.ManagedObjectImpl;

/**
 * Streaming Managed Object having a port attribute.
 * 
 * @author ebujkri
 */
public class StreamImpl extends ManagedObjectImpl implements Serializable {
	private static final long serialVersionUID = -6481567892144257882L;
	
	/* Streaming Managed Object has an attribute called port */
	private static final String STREAMING_PORT = "port";

	public StreamImpl(final String name, final String type,
			final ManagedObject parent,
			final Map<String, Object> initialAttributes)
			throws DataPersistenceException {

		super(name, type, parent, initialAttributes);
		if (parent == null) {
			throw new DataPersistenceException(
					new IllegalArgumentException(
							"The parent of a streaming node must have a non-null parent"));
		} else {
			final ManagedObjectImpl parentImpl = (ManagedObjectImpl) parent;
			parentImpl.addChild(this.getFDN());
		}
	}

	@Override
	public Collection<ManagedObject> getChildren() {
		return Collections.emptyList();
	}

	@Override
	public Collection<ManagedObject> getChildren(final String... types)
			throws DataPersistenceException {
		return Collections.emptyList();
	}

	@Override
	protected void validateChildSupportedByThisParent(final String child)
			throws DataPersistenceException {

		throw new DataPersistenceException(new AssertionError(
				"Should not be trying to add a child to a leaf node"));
	}

	@Override
	protected List<String> getSupportedChildren() {
		throw new DataPersistenceException(
				new AssertionError(
						"A leaf node should override getChildren so should never get here"));
	}

	@Override
	protected List<AttributeInfo> getExpectedAttributeInfo() {
		final List<AttributeInfo> attributeInfoList = new ArrayList<AttributeInfo>();

		attributeInfoList.add(new AttributeInfo(STREAMING_PORT, Integer.class));
		return attributeInfoList;
	}
}
