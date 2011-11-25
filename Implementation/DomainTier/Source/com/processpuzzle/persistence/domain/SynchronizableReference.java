/*
Name: 
    - SynchronizableReference 

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
