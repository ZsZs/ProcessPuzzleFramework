/*
Name: 
    - DefaultIdentityExpression

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

package com.processpuzzle.persistence.query.domain;

import hu.itkodex.commons.persistence.Entity;
import hu.itkodex.commons.persistence.query.IdentityExpression;

import java.lang.reflect.ParameterizedType;


public abstract class DefaultIdentityExpression<E extends Entity> extends DefaultQuery implements IdentityExpression<E> {

   @SuppressWarnings("unchecked")
   public DefaultIdentityExpression( DefaultQueryContext context ) {
      super();
      targetClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      if( context != null) this.context = context;
      buildQuery();
   }

   public DefaultIdentityExpression() {
      this( null );
   }
   
   
   @SuppressWarnings("unchecked")
   public DefaultIdentityExpression<E> clone() {
      DefaultIdentityExpression<E> clone = (DefaultIdentityExpression<E>) super.clone();
      return clone;
   }

   protected abstract void buildQuery();   
}
