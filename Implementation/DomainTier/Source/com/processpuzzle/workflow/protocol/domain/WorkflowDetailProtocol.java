/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.protocol.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 * @uml.annotations
 * derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_ULCk4T5fEdq3NJ6sg2oavA"
 */
public class WorkflowDetailProtocol extends CompositeProtocol {
	
    public WorkflowDetailProtocol(String name) {
        super(name);
    }
    
    public WorkflowDetailProtocol() {}
    
    public void addparentLifecyclePhase(LifecyclePhaseProtocol lcpp) {
    	addCompositeProtocol(lcpp);
    }
    
    public void addActivity(ActivityProtocol activity) {
    	addStep(activity);
    }

	public Set<ActivityProtocol> getActivities() {
		Set<ActivityProtocol> activities = new HashSet<ActivityProtocol>();
		for (Iterator<?> iter = getProtocols().iterator(); iter.hasNext();) {
			Protocol pr = (Protocol) iter.next();
			if (pr instanceof ActivityProtocol) {
				ActivityProtocol activity = (ActivityProtocol) pr;
				activities.add(activity);
			}
		}
		return activities;
	}
    
    public void setActivities(Set<ActivityProtocol> activities) {
    	for (ActivityProtocol protocol : activities) {
			addStep(protocol);
		}
    }

}
