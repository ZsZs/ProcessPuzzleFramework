/*
Name: 
    - BusinessImplementation

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

package com.processpuzzle.business.definition.domain;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.artifact.domain.Artifact;

@XmlAccessorType( XmlAccessType.FIELD ) 
@XmlType( name = "", propOrder = { "" })
@XmlRootElement( name = "businessImplementation" )
public class BusinessImplementation {
   public @XmlTransient Set<Artifact<?>> artifacts = new HashSet<Artifact<?>>(); //@XmlElementWrapper(name="artifacts") @XmlElement 
   private static Logger log = LoggerFactory.getLogger( BusinessImplementation.class );
   
   public BusinessImplementation() {
      log.debug( "BusinessImplementation was instantiated." );
   }
}
