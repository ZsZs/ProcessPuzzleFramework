package com.processpuzzle.util.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
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
