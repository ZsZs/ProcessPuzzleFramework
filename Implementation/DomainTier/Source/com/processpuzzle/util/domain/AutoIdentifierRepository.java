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
