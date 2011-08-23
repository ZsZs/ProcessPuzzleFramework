package com.processpuzzle.party.domain;

import com.processpuzzle.persistence.domain.GenericFactory;

public class PartySummaryFactory extends GenericFactory<PartySummary> {

   public PartySummary createPartySummary( Party<?> party ) {
      return new PartySummary( party );
   }

}
