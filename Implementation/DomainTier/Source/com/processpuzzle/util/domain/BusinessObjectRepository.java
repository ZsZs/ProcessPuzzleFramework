/*
Name: 
    - BusinessObjectRepository 

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

package com.processpuzzle.util.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.IntegerAttributeCondition;

public class BusinessObjectRepository extends GenericRepository <BusinessObject>{

   public BusinessObjectRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext) {
      super(strategy, applicationContext);
   }
   
   public RepositoryResultSet<BusinessObject> findBusinessObjectsByCommissionerCompanyId(DefaultUnitOfWork work,String id) {
      //return (List)find("from BusinessObject b where b.commissionerCompany.id = '"+id+"'");
	   DefaultQuery q=new DefaultQuery(BusinessObject.class);
	   q.getQueryCondition().addAttributeCondition(new IntegerAttributeCondition("commissionerCompany.id",new Integer(id),ComparisonOperators.EQUAL_TO));
	   return findByQuery(work, q);
   }
   
   public RepositoryResultSet<BusinessObject> findBusinessObjectsByCommissionerOrganizationUnitId(DefaultUnitOfWork work,String id) {
      //return (List)find("from BusinessObject b where b.commissionerOrganizationUnit.id = '"+id+"'");
	   DefaultQuery q=new DefaultQuery(BusinessObject.class);
	   q.getQueryCondition().addAttributeCondition(new IntegerAttributeCondition("commissionerOrganizationUnit.id",new Integer(id),ComparisonOperators.EQUAL_TO));
	   return findByQuery(work, q);
   }
   
   public void updateBusinessObject(DefaultUnitOfWork work,BusinessObject bo) {
      update(work, bo);
   }

   protected Object findByIdentityExpression(String identityExpression) {
      return null;
   }

}
