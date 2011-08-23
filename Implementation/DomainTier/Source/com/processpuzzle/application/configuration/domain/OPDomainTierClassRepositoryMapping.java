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