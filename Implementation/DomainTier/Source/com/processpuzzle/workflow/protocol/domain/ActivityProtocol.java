/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.protocol.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;

import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;

public class ActivityProtocol extends Protocol {
	@XmlIDREF @XmlAttribute(name="performerRole") private PartyRoleType performerRole;
    
    public ActivityProtocol(String theName, PartyRoleType performer) {
        super(theName);
        performerRole = performer;
    }
    
    public ActivityProtocol(String theName) {
    	this(theName, null);
    }
    
    public ActivityProtocol() {
    	this(null, null);
    }
    
    public void addParentLifecyclePhaseProtocol(LifecyclePhaseProtocol lcpp) {
    	addCompositeProtocol(lcpp);
    }
    
    public void addParentWorkFlowDetailPrrotocol(WorkflowDetailProtocol wdp) {
    	addCompositeProtocol(wdp);
    }
    
    public void setPerformerRole(PartyRoleType performerRole) {
		this.performerRole = performerRole;
	}

    public PartyRoleType getPerformerRole() {
        return performerRole;
    }
}