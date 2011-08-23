package com.processpuzzle.party.domain;

import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.persistence.domain.GenericFactory;

public abstract class PartyFactory<P extends Party<?>> extends GenericFactory<P> {
   protected PartyTypeRepository partyTypeRepository;
   protected Class<P> partyClass;

   protected PartyFactory() {
      super();
      partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );
      partyClass = (Class<P>) entityClass;
   }

   protected PartyType findTypeFor( Class<? extends Party> partyClass ) {
      return partyTypeRepository.findByPartyClassName( partyClass.getName() );
   }
}
