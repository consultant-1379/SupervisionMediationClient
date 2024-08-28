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
 * A Supervision Managed Object implementation.
 * A Supervision Managed Object will have Streaming , FM and PM child managed objects
 *   
 * @author ebujkri
 *
 */
public class SupervisionImpl extends ManagedObjectImpl implements Serializable {
	private static final long serialVersionUID = 7936535215098737528L;
	private final List<String> supportedChildren = Arrays.asList("StreamImpl","FMImpl","PMImpl");

	
	public SupervisionImpl(final String name, final String type,
			final ManagedObject parent,
			final Map<String, Object> initialAttributes)
			throws DataPersistenceException {

		super(name, type, parent, initialAttributes);
		if (parent != null) {
			throw new DataPersistenceException(new IllegalArgumentException(
					"The parent of a root node must be null"));
		}
	}

	@Override
	protected void validateChildSupportedByThisParent(final String child) {
		final String type = child.substring((child.lastIndexOf(",") + 1),
				child.lastIndexOf("="));

		if (!(supportedChildren.contains(type))) {
			throw new DataPersistenceException("Unsupported child type : "
					+ type);
		}
		boolean matchFound = false;
		for (final String supportedChild : supportedChildren) {
			matchFound = false;
			if (supportedChild.equalsIgnoreCase(type)) {
				matchFound = true;
				break;
			}
		}
		if (!matchFound) {
			throw new DataPersistenceException(new IllegalStateException(
					"Unsupported child type : " + type));
		}
	}

	@Override
	protected List<String> getSupportedChildren() {
		return supportedChildren;
	}

	@Override
	protected List<AttributeInfo> getExpectedAttributeInfo() {
		final List<AttributeInfo> attributeInfoList = new ArrayList<AttributeInfo>();
		/** TODO : Need to add attributes of Supervision Node **/
		return attributeInfoList;
	}
}
