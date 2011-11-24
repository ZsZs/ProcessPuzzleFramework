/*
Name: 
    - OPDomainTierClassRepositoryMapping 

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

package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.application.security.domain.DefaultAccessRight;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.fundamental_types.textformat.domain.FormatSpecifier;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public class OPDomainTierClassRepositoryMapping extends RepositoryMappings {

//   private Class[] domainClasses = new Class[]{ 
//            ProcessPuzzleLocaleRepository.class, ProcessPuzzleLocale.class,
//            UserRepository.class, User.class,
//            null, Query.class,
//            AccessRightRepository.class, AccessRight.class,
//            null, DefaultAccessRight.class,
//            UnitRepository.class, null,
//            null,FormatSpecifier.class 
//   };

   public OPDomainTierClassRepositoryMapping() {
//	   this.appendMappings(domainClasses);
   }

   @Override
   protected void setUpEntityAndRepositoryMappings() {
      appendEntityRepositoryMapping(ProcessPuzzleLocale.class, InternalizationContext.class);
      appendEntityRepositoryMapping(User.class, UserRepository.class);
      appendEntityRepositoryMapping(DefaultQuery.class, DefaultQuery.class);
      appendEntityRepositoryMapping(DefaultAccessRight.class, DefaultAccessRight.class);
      appendEntityRepositoryMapping(null, MeasurementContext.class);
      appendEntityRepositoryMapping(FormatSpecifier.class, null);
   }

   @Override
   protected void setUpRespositoryAndStrategyMappings() {
      // TODO Auto-generated method stub
      
   }   
}