/*
Name: 
    - PlanListRepository 

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

package com.processpuzzle.workflow.activity.artifact;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class PlanListRepository extends GenericRepository<PlanList> {

   public PlanListRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addPlanList( DefaultUnitOfWork work, PlanList planList ) {
      add( work, PlanList.class, planList );
   }

   public PlanList findPlanList() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PlanList planList = findPlanList( work );
      work.finish();
      return planList;
   }

   public PlanList findPlanList( DefaultUnitOfWork work ) {
      DefaultQuery q = new DefaultQuery( PlanList.class );
      TextAttributeCondition condition = new TextAttributeCondition( "name", PlanList.class.getSimpleName(), ComparisonOperators.EQUAL_TO );
      q.getQueryCondition().addAttributeCondition( condition );
      return (PlanList) findByQuery( work, q );
   }

}
