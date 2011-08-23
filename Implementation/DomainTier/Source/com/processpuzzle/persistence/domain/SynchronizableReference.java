/*
 * Created on Dec 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.persistence.domain;

import com.processpuzzle.fundamental_types.domain.TimePoint;

/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @uml.annotations
 * derived_abstraction="pathmap://ObjectPuzzle/Design/Enterprise%20IT%20Design%20Model.emx#_14FaMGViEdqrUasQd_Buzg"
 */
public class SynchronizableReference {
    /**
     * Comment for <code>ObjectPuzzleOid</code>
     */
    private Integer ObjectPuzzleOid;
    /**
     * Comment for <code>RemoteSystemOid</code>
     */
    private Integer RemoteSystemOid;

    /**
     * @return Returns the ObjectPuzzleOid.
     */
    public Integer getObjectPuzzleOid() {
        return ObjectPuzzleOid;
    }

    /**
     * Comment for <code>ObjectPuzzleTimeStamp</code>
     */
    private TimePoint ObjectPuzzleTimeStamp;

    /**
     * Comment for <code>RemoteSystemTimeStamp</code>
     * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
     */
    private TimePoint RemoteSystemTimeStamp;

    /**
     * @param theObjectPuzzleOid The ObjectPuzzleOid to set.
     */
    public void setObjectPuzzleOid(Integer theObjectPuzzleOid) {
        ObjectPuzzleOid = theObjectPuzzleOid;
    }

    /**
     * @return Returns the RemoteSystemOid.
     */
    public Integer getRemoteSystemOid() {
        return RemoteSystemOid;
    }

    /**
     * @param theRemoteSystemOid The RemoteSystemOid to set.
     */
    public void setRemoteSystemOid(Integer theRemoteSystemOid) {
        RemoteSystemOid = theRemoteSystemOid;
    }

    /**
     * @return Returns the ObjectPuzzleTimeStamp.
     */
    public TimePoint getObjectPuzzleTimeStamp() {
        return ObjectPuzzleTimeStamp;
    }

    /**
     * @param theObjectPuzzleTimeStamp The ObjectPuzzleTimeStamp to set.
     */
    public void setObjectPuzzleTimeStamp(TimePoint theObjectPuzzleTimeStamp) {
        ObjectPuzzleTimeStamp = theObjectPuzzleTimeStamp;
    }

    /**
     * @return Returns the RemoteSystemTimeStamp.
     */
    public TimePoint getRemoteSystemTimeStamp() {
        return RemoteSystemTimeStamp;
    }

    /**
     * @param theRemoteSystemTimeStamp The RemoteSystemTimeStamp to set.
     */
    public void setRemoteSystemTimeStamp(TimePoint theRemoteSystemTimeStamp) {
        RemoteSystemTimeStamp = theRemoteSystemTimeStamp;
    }
}
