/*
Name: 
    - AutoIdentifierRepository 

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
 * Created on Jul 7, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.util.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class AutoIdentifierRepository extends GenericRepository<AutoIdentifier> {

   public AutoIdentifierRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext) {
      super(strategy, applicationContext);
   }

   public AutoIdentifier getLastAutoIdentifierByIdType(DefaultUnitOfWork work, String idType) {
      // return (AutoIdentifier) get("from AutoIdentifier a where a.idType = '"
      // + idType + "'");
      DefaultQuery query = new DefaultQuery(AutoIdentifier.class);
      query.getQueryCondition().addAttributeCondition(new TextAttributeCondition("idType", idType, ComparisonOperators.EQUAL_TO));
      return (AutoIdentifier) findByQuery(work, query);
   }

   public void addDefaultAutoIdentifier(DefaultUnitOfWork work, AutoIdentifier number) {
      add(work, number);
   }

   public void updateAutoIdentifier(DefaultUnitOfWork work, AutoIdentifier number) {
      update(work, number);
   }

   // @Override
   protected Object findByIdentityExpression(String identityExpression) {
      // TODO Auto-generated method stub
      return null;
   }
}
