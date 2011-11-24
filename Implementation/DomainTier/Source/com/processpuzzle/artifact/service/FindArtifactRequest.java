/*
Name: 
    - FindArtifactRequest

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

package com.processpuzzle.artifact.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.fundamental_types.domain.ServiceMessage;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name="FindByIdRequest", namespace="http://www.processpuzzle.com" )
public class FindArtifactRequest extends ServiceMessage{
   private @XmlElement( name="id", required = true, namespace="http://www.processpuzzle.com" ) String id;
   private @XmlElement( name="requestedView" ) String requestedViewName;

   protected FindArtifactRequest() {}
   
   public FindArtifactRequest( String artifactId ) {
      this( artifactId, null );
   }
   
   public FindArtifactRequest( String artifactId, String requestedViewName ) {
      this.id = artifactId;
      this.requestedViewName = requestedViewName;
   }
   
   public Integer getId() { return new Integer( id ); }
   public String getRequestedViewName() { return requestedViewName; }
   void setRequestedViewName( String requestedViewName ) { this.requestedViewName = requestedViewName; }
}
