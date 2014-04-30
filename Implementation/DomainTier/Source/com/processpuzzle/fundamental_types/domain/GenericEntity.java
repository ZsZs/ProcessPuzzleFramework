/*
Name: 
    - GenericEntity

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

package com.processpuzzle.fundamental_types.domain;


import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

@XmlAccessorType( XmlAccessType.NONE )
public abstract class GenericEntity<E extends Entity> implements Entity {
   protected Integer id = null;
   protected int version = 0;
   protected DefaultIdentityExpression<E> defaultIdentity;
   protected Set<DefaultIdentityExpression<E>> identities = new HashSet<DefaultIdentityExpression<E>>();
   
   //Constructors
   protected GenericEntity() {
      //defineIdentityExpressions();
   }

   //Public accessors
   public abstract <I extends DefaultIdentityExpression<E>> I getDefaultIdentity();

   //Properties
   public Integer getId() { return id; }
   public void setId( Integer id ) { this.id = id; }
   
   public int getVersion() { return version; }
   public void setVersion( int version ) { this.version = version; }
   
   public Set<DefaultIdentityExpression<E>> getIdentities() { return identities; }
   
   //Protected private helper methods
   protected abstract void defineIdentityExpressions();
}
