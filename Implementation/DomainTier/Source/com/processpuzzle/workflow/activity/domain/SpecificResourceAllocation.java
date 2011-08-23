/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 * @uml.annotations
 * derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_U_oDgD5fEdq3NJ6sg2oavA"
 */
public abstract class SpecificResourceAllocation extends ResourceAllocation {
	
	public SpecificResourceAllocation(ResourceType theResourceType,Quantity theQuantity) {
		super(theResourceType,theQuantity);
	}
	
	public SpecificResourceAllocation() {}
	
	
}
